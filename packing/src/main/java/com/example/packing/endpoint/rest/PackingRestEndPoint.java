package com.example.packing.endpoint.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.packing.dto.requests.PackConfirmRequestDTO;
import com.example.packing.service.PackingService;

import io.swagger.annotations.Api;
@Controller
@RequestMapping("/packing/v1")
@Api(value="Pack Service", description="Operations pertaining to packing")
@RefreshScope
public class PackingRestEndPoint {

    @Value("${message: Packing Service - Config Server is not working..pelase check}")
    private String msg;
    
    @Autowired
	PackingService packingService;
	Logger logger = LoggerFactory.getLogger(PackingRestEndPoint.class);
	
	@GetMapping("/")
	public ResponseEntity hello() throws Exception {
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/{busName}/{locnNbr}/packs/{id}")
	public ResponseEntity getByPackId(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr, @PathVariable("id") Long packId) throws IOException {
		try {
			return ResponseEntity.ok(packingService.findByPackId(busName, locnNbr, packId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error occured while getting next pick task"));
		}
	}

	@GetMapping("/{busName}/{locnNbr}/packs/order/{id}")
	public ResponseEntity getPacksByOrderId(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr, @PathVariable("id") Long orderId) throws IOException {
		try {
			return ResponseEntity.ok(packingService.findByOrderId(busName, locnNbr, orderId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error occured while getting next pick task"));
		}
	}

	@GetMapping("/{busName}/{locnNbr}/packs/container/{containerNbr}")
	public ResponseEntity getPacksByContainerNbr(@PathVariable("busName") String busName, @PathVariable("locnNbr") Integer locnNbr, @PathVariable("containerNbr") String containerNbr) throws IOException {
		try {
			return ResponseEntity.ok(packingService.findByContainerNbr(busName, locnNbr, containerNbr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error occured while getting next pick task"));
		}
	}

	@PostMapping("/{busName}/{locnNbr}/packs/{id}")
	public ResponseEntity confirmPack(@PathVariable("busName") String busName,@PathVariable("locnNbr") Integer locnNbr, @PathVariable("id") Long id, @RequestBody PackConfirmRequestDTO pickReq) throws IOException {
		try {
			return ResponseEntity.ok(packingService.confirmPack(pickReq));
		} catch (Exception e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ErrorRestResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Occured for GET request busName:" + busName + ", id:" + id + " : " + e.getMessage()));
		}
	}	
}
