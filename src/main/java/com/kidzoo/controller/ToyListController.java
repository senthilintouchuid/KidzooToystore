package com.kidzoo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kidzoo.bean.KidZooRestResponse;
import com.kidzoo.bean.Toy;
import com.kidzoo.service.ToyService;
import com.kidzoo.util.KidZooRestUtil;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "kidzoo/v1/api")
public class ToyListController {

	@Autowired
	private ToyService toyService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ToyListController.class);
	
	// API:  http://localhost:8080/kidzoo/v1/api/getAvailableToys
	// API:  http://localhost:8080/kidzoo/v1/api/getAvailableToys?fromPrice=500
	// API:  http://localhost:8080/kidzoo/v1/api/getAvailableToys?toPrice=500
	// API:  http://localhost:8080/kidzoo/v1/api/getAvailableToys?fromPrice=500&toPrice=1000

	/**
	 * Controller to get the available toys for sale. Price range can be optional parameters.
	 * 
	 * @param fromPrice
	 * @param toPrice
	 * @return
	 */
	@GetMapping(value = "/toys", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Returns the toys which are available for sale based on input price range")
	public ResponseEntity<KidZooRestResponse> getAllAvailableToys(
			@RequestParam(required = false, name = "fromPrice") Optional<BigDecimal> fromPrice,
			@RequestParam(required = false, name = "toPrice") Optional<BigDecimal> toPrice) {

		LOGGER.info("URL: /toys - Processing the Request");
		
		List<Toy> allAvailableToys = null;

		if (fromPrice.isPresent() && toPrice.isPresent()) {
			allAvailableToys = toyService.getToysBasedOnPriceRange(fromPrice.get(), toPrice.get());
		} else if (fromPrice.isPresent()) {
			allAvailableToys = toyService.getToysBasedOnPriceRangeFrom(fromPrice.get());
		} else if (toPrice.isPresent()) {
			allAvailableToys = toyService.getToysBasedOnPriceRange(BigDecimal.ZERO, toPrice.get());
		} else {
			allAvailableToys = toyService.getAllAvailableToys();
		}
		return new ResponseEntity<>(KidZooRestUtil.createResponse(allAvailableToys), HttpStatus.OK);
	}
}
