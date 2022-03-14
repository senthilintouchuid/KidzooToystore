package com.kidzoo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kidzoo.bean.Toy;
import com.kidzoo.constant.ErrorCode;
import com.kidzoo.customexception.KidZooException;
import com.kidzoo.service.StockCheckerService;
import com.kidzoo.service.bean.ToyStatusDetail;
import com.kidzoo.service.bean.ToyStatusDetailsList;

@Service("stockCheckerService")
public class StockCheckerServiceImpl implements StockCheckerService {

	@Autowired
	@Qualifier("toyRestTemplate")
	private RestTemplate restTemplate;

	private static final String URL = "https://inventory.kidzoo.com/v2/inventory/findByStatus?status=available,backorder";

	private static final Logger LOGGER = LoggerFactory.getLogger(StockCheckerServiceImpl.class);

	/**
	 * Method to filter the toys in store based on the stock  
	 * 
	 * @param toyList
	 * @param avalabilityStatus
	 * @return
	 */
	@Override
	public List<Toy> filterAvailableToys(List<com.kidzoo.entity.Toy> toyList,
			List<ToyStatusDetail> avalabilityStatus) {
		List<Toy> availableToysList = new ArrayList<>();
		toyList.forEach(toyLst -> {
			if (avalabilityStatus.stream().anyMatch(stock -> (stock.getToyId().equals(toyLst.getToyId())))) {
				Toy toy = new Toy();
				BeanUtils.copyProperties(toyLst, toy);
				availableToysList.add(toy);
			}
		});
		return availableToysList;
	}

	/**
	 * Method to fetch the in stock toys from the inventory api
	 * 
	 * @return
	 * @throws KidZooException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ToyStatusDetail> getToysAvailabilityStatus() throws KidZooException {
		List<ToyStatusDetail> avalabilityStatus = new ArrayList<>();
		try {
			ToyStatusDetailsList response= restTemplate.getForObject(URL, ToyStatusDetailsList.class);
			
			if(response != null) {
				avalabilityStatus = response.getToyList();
			};
		} catch (Exception exception) {
			LOGGER.error("Exception occured at the time of making a call for checking stock {} ", exception.getMessage());
			throw new KidZooException(ErrorCode.REST_CALL_EXCEPTION.getErrorCode(), exception.getMessage(), 
					"Exception raised while making call to inventory service", exception.getCause());
		}
		return avalabilityStatus;
	}
}
