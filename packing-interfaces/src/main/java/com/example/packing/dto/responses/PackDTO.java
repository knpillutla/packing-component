package com.example.packing.dto.responses;

import com.example.packing.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PackDTO  extends BaseDTO{
	Long id;
	String batchNbr;
	String busName;
	Integer locnNbr;
	String busUnit;
	String company;
	String division;
	String itemBrcd;
	Integer qty;
	Integer packedQty;
	String fromContainer;
	String toContainer;
	Integer statCode;
	Integer orderId;
	String orderNbr;
	String packageNbr;
	Integer orderLineNbr;
	String transName;
	String source;
	String hostName;
	String userId;
}
