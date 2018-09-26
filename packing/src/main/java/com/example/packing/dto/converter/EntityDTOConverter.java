package com.example.packing.dto.converter;

import com.example.packing.db.Pack;
import com.example.packing.dto.requests.PackCreationRequestDTO;
import com.example.packing.dto.responses.PackDTO;

public class EntityDTOConverter {

	public static PackDTO getPackDTO(Pack packEntity) {
		PackDTO packDTO = new PackDTO(packEntity.getId(), packEntity.getBatchNbr(), packEntity.getBusName(),
				packEntity.getLocnNbr(), packEntity.getBusUnit(), packEntity.getCompany(), packEntity.getDivision(),
				packEntity.getItemBrcd(), packEntity.getQty(), packEntity.getPackedQty(), packEntity.getFromContainer(),
				packEntity.getToContainer(), packEntity.getStatCode(), packEntity.getOrderId(),
				packEntity.getOrderNbr(), packEntity.getPackageNbr(), packEntity.getOrderLineNbr(),
				packEntity.getTransName(), packEntity.getSource(), packEntity.getHostName(), packEntity.getUserId());
		return packDTO;
	}

	public static Pack getPackEntity(PackCreationRequestDTO packCreationReq) {
		Pack packEntity = new Pack();
		packEntity.setBatchNbr(packCreationReq.getBatchNbr());
		packEntity.setBusName(packCreationReq.getBusName());
		packEntity.setBusUnit(packCreationReq.getBusUnit());
		packEntity.setCompany(packCreationReq.getCompany());
		packEntity.setDivision(packCreationReq.getDivision());
		packEntity.setFromContainer(packCreationReq.getFromContainer());
		packEntity.setToContainer(packCreationReq.getToContainer());
		packEntity.setHostName("");
		packEntity.setUserId(packCreationReq.getUserId());
		packEntity.setItemBrcd(packCreationReq.getItemBrcd());
		packEntity.setQty(packCreationReq.getQty());
		return packEntity;
	}

}
