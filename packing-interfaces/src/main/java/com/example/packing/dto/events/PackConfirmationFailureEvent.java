package com.example.packing.dto.events;

import java.util.Map;

import com.example.packing.dto.requests.PackConfirmRequestDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
public class PackConfirmationFailureEvent extends ExceptionEvent{
	private static String EVENT_NAME = "PackConfirmationFailureEvent";
	String orderNbr;
	Long orderId;
	Long pickListId;
	PackConfirmRequestDTO packConfirmRequestDTO;
	public PackConfirmationFailureEvent(PackConfirmRequestDTO req, String errorMsg, Exception exceptionObj) {
		this(req, null, errorMsg, exceptionObj);
	}
	public PackConfirmationFailureEvent(PackConfirmRequestDTO req, Map headerMap, String errorMsg, Exception exceptionObj) {
		super(EVENT_NAME,errorMsg, exceptionObj);
		this.packConfirmRequestDTO = req;
		if(headerMap != null)
			this.setHeaderMap(headerMap);
		this.addHeader("eventName", getEventName());
		this.addHeader("busName", req.getBusName());
		this.addHeader("locnNbr", req.getLocnNbr());
		this.addHeader("OrderNbr", req.getOrderNbr());
		this.addHeader("company", req.getCompany());
		this.addHeader("division", req.getDivision());
		this.addHeader("busUnit", req.getBusUnit());
		this.addHeader("batchNbr", req.getBatchNbr());
		//this.addHeader("orderId", pickConfirmRequestDTO.getOrderId());
	}
}
