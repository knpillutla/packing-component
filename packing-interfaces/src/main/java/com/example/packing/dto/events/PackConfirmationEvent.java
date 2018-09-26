package com.example.packing.dto.events;

import java.util.Map;

import com.example.packing.dto.responses.PackDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
public class PackConfirmationEvent extends BaseEvent{
	private static String EVENT_NAME = "PackConfirmationEvent";
	PackDTO packDTO;
	public PackConfirmationEvent(PackDTO dto) {
		this(dto, null);
	}
	public PackConfirmationEvent(PackDTO packDTO, Map headerMap) {
		super(EVENT_NAME);
		this.packDTO = packDTO;
		if(headerMap != null)
			this.setHeaderMap(headerMap);
		this.addHeader("eventName", getEventName());
		this.addHeader("busName", packDTO.getBusName());
		this.addHeader("locnNbr", packDTO.getLocnNbr());
		this.addHeader("OrderNbr", packDTO.getOrderNbr());
		this.addHeader("company", packDTO.getCompany());
		this.addHeader("division", packDTO.getDivision());
		this.addHeader("busUnit", packDTO.getBusUnit());
		this.addHeader("batchNbr", packDTO.getBatchNbr());
	}
}
