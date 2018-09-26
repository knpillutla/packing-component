package com.example.packing.dto.events;

import java.util.Map;

import com.example.packing.dto.requests.PackCreationRequestDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
public class PackCreationFailureEvent extends ExceptionEvent{
	private static String EVENT_NAME = "PackCreationFailureEvent";
	PackCreationRequestDTO packCreationReq;
	public PackCreationFailureEvent(PackCreationRequestDTO pickCreationReq, String errorMsg, Exception exceptionObj) {
		this(pickCreationReq, null, errorMsg, exceptionObj);
	}
	public PackCreationFailureEvent(PackCreationRequestDTO req, Map headerMap, String errorMsg, Exception exceptionObj) {
		super(EVENT_NAME, errorMsg, exceptionObj);
		this.packCreationReq = req;
		if(headerMap != null)
			this.setHeaderMap(headerMap);
		this.addHeader("eventName", getEventName());
		this.addHeader("busName", this.packCreationReq.getBusName());
		this.addHeader("locnNbr", this.packCreationReq.getLocnNbr());
		this.addHeader("OrderNbr", this.packCreationReq.getOrderNbr());
		this.addHeader("company", this.packCreationReq.getCompany());
		this.addHeader("division", this.packCreationReq.getDivision());
		this.addHeader("busUnit", this.packCreationReq.getBusUnit());
	}
	
}
