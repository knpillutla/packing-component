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
public class PackCreatedEvent extends BaseEvent{
	private static String EVENT_NAME = "PackCreatedEvent";
	PackDTO packDTO;
	public PackCreatedEvent(PackDTO packDTO) {
		this(packDTO, null);
	}
	public PackCreatedEvent(PackDTO packDTO, Map headerMap) {
		super(EVENT_NAME);
		this.packDTO = packDTO;
		if(headerMap != null)
			this.setHeaderMap(headerMap);
		this.addHeader("eventName", getEventName());
		this.addHeader("busName", this.packDTO.getBusName());
		this.addHeader("locnNbr", this.packDTO.getLocnNbr());
		this.addHeader("OrderNbr", this.packDTO.getOrderNbr());
		this.addHeader("company", this.packDTO.getCompany());
		this.addHeader("division", this.packDTO.getDivision());
		this.addHeader("busUnit", this.packDTO.getBusUnit());
	}
	
}
