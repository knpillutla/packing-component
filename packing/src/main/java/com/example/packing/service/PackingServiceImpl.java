package com.example.packing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.packing.db.Pack;
import com.example.packing.db.PackingRepository;
import com.example.packing.dto.converter.EntityDTOConverter;
import com.example.packing.dto.events.PackConfirmationEvent;
import com.example.packing.dto.events.PackCreatedEvent;
import com.example.packing.dto.requests.PackConfirmRequestDTO;
import com.example.packing.dto.requests.PackCreationRequestDTO;
import com.example.packing.dto.responses.PackDTO;

@Service
public class PackingServiceImpl implements PackingService {
	private static final Logger logger = LoggerFactory.getLogger(PackingServiceImpl.class);
	
	@Autowired
	PackingRepository pickDAO;
	
	@Autowired
	EventPublisher eventPublisher;
	
	public enum PackStatus {
		CREATED(100), RELEASED(110), PACKED(120), SHORTED(140), CANCELLED(199);
		PackStatus(Integer statCode) {
			this.statCode = statCode;
		}

		private Integer statCode;

		public Integer getStatCode() {
			return statCode;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.example.demo.PickingService#confirmPick(com.example.AvroPickTask)
	 */
	@Override
	@Transactional
	public PackDTO confirmPack(PackConfirmRequestDTO pickConfirmRequest) throws Exception{
		logger.info("confirmPick Start, :" + pickConfirmRequest);
		PackDTO packDTO = null;
		Optional<Pack> pickDtl = pickDAO.findById(pickConfirmRequest.getId());
		if(pickDtl.isPresent()) {
			Pack packEntity = pickDtl.get();
			packEntity.setPackedQty(packEntity.getPackedQty() + pickConfirmRequest.getQtyPacked());
			packEntity.setUserId(pickConfirmRequest.getUserId());
			packEntity.setStatCode(PackStatus.PACKED.getStatCode());
			Pack updatedPickObj = pickDAO.save(packEntity);
			packDTO = EntityDTOConverter.getPackDTO(updatedPickObj);
			PackConfirmationEvent pickConfirmEvent = new PackConfirmationEvent(packDTO);
			logger.info("confirmPick End, updated pick obj:" + packDTO);
		}
		return packDTO;
	}

	/* (non-Javadoc)
	 * @see com.example.demo.PickingService#createNew(com.example.AvroPickTask)
	 */
	@Override
	@Transactional
	public PackDTO createPack(PackCreationRequestDTO pickCreationReq) throws Exception {
		Pack newPickEntity = EntityDTOConverter.getPackEntity(pickCreationReq);
		newPickEntity.setStatCode(PackStatus.RELEASED.getStatCode());
		PackDTO packDTO = EntityDTOConverter.getPackDTO(pickDAO.save(newPickEntity));
		PackCreatedEvent pickCreatedEvent = new PackCreatedEvent(packDTO);
		eventPublisher.publish(pickCreatedEvent);
		logger.info("createPick End, created new pick:" + packDTO);
		return packDTO;
	}

	@Override
	public List<PackDTO> findByOrderId(String busName, Integer locnNbr, Long orderId) throws Exception {
		List<Pack> pickEntityList = pickDAO.findByBusNameAndLocnNbrAndOrderId(busName, locnNbr, orderId);
		List<PackDTO> pickDTOList = new ArrayList();
		if(pickEntityList!=null) {
			for(Pack packEntity : pickEntityList) {
				pickDTOList.add(EntityDTOConverter.getPackDTO(packEntity));
			}
		}
		return pickDTOList;
	}

	@Override
	public PackDTO findByPackId(String busName, Integer locnNbr, Long pickId) throws Exception {
		Pack packEntity = pickDAO.findByPickId(busName, locnNbr, pickId);
		return EntityDTOConverter.getPackDTO(packEntity);
	}

	@Override
	public List<PackDTO> findByOrderNbr(String busName, Integer locnNbr, String orderNbr) throws Exception {
		List<Pack> pickEntityList = pickDAO.findByBusNameAndLocnNbrAndOrderNbr(busName, locnNbr, orderNbr);
		List<PackDTO> pickDTOList = new ArrayList();
		if(pickEntityList!=null) {
			for(Pack packEntity : pickEntityList) {
				pickDTOList.add(EntityDTOConverter.getPackDTO(packEntity));
			}
		}
		return pickDTOList;
	}

	@Override
	public List<PackDTO> findByBatchNbr(String busName, Integer locnNbr, String batchNbr) throws Exception {
		List<Pack> pickEntityList = pickDAO.findByBusNameAndLocnNbrAndBatchNbr(busName, locnNbr, batchNbr);
		List<PackDTO> pickDTOList = new ArrayList();
		if(pickEntityList!=null) {
			for(Pack packEntity : pickEntityList) {
				pickDTOList.add(EntityDTOConverter.getPackDTO(packEntity));
			}
		}
		return pickDTOList;
	}

	@Override
	public List<PackDTO> findByContainerNbr(String busName, Integer locnNbr, String containerNbr) throws Exception {
		List<Pack> pickEntityList = pickDAO.findByBusNameAndLocnNbrAndContainerNbr(busName, locnNbr, containerNbr);
		List<PackDTO> pickDTOList = new ArrayList();
		if(pickEntityList!=null) {
			for(Pack packEntity : pickEntityList) {
				pickDTOList.add(EntityDTOConverter.getPackDTO(packEntity));
			}
		}
		return pickDTOList;
	}
}
