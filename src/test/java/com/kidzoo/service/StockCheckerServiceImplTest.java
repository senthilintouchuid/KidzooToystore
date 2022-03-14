package com.kidzoo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kidzoo.bean.Toy;
import com.kidzoo.constant.InventoryStatus;
import com.kidzoo.service.bean.ToyStatusDetail;
import com.kidzoo.service.bean.ToyStatusDetailsList;
import com.kidzoo.service.impl.StockCheckerServiceImpl;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class StockCheckerServiceImplTest {

	@InjectMocks
	private StockCheckerServiceImpl stockCheckerService;
	
	@Mock
	private RestTemplate restTemplate;
	
	private List<com.kidzoo.entity.Toy> toyListRepo;
	
	private List<ToyStatusDetail> avalabilityStatusList;
	private ToyStatusDetailsList toyStatusDetailList;
	
	Map<Object, InventoryStatus> avalabilityStatus = new HashMap<>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		toyListRepo = new ArrayList<>();
		toyListRepo.add(new com.kidzoo.entity.Toy(1L, "Toy-1", BigDecimal.valueOf(500.06), 5, "https://kidzoo.com/toy-1.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(2L, "Toy-2", BigDecimal.valueOf(700.99), 5, "https://kidzoo.com/toy-2.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(3L, "Toy-3", BigDecimal.valueOf(1500.48), 5, "https://kidzoo.com/toy-3.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(4L, "Toy-3", BigDecimal.valueOf(1550.48), 5, "https://kidzoo.com/toy-4png"));	
		avalabilityStatus.put(1L, InventoryStatus.available);
		avalabilityStatus.put(2L, InventoryStatus.backorder);
		avalabilityStatus.put(3L, InventoryStatus.available);
	}
	
	@Test
	public void filterAvailableToysTest() {
		avalabilityStatusList = new ArrayList<ToyStatusDetail>();
		avalabilityStatusList.add(new ToyStatusDetail(1L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(3L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(2L, InventoryStatus.available));
		List<Toy> filteredAvailableToys = stockCheckerService.filterAvailableToys(toyListRepo, avalabilityStatusList);
		Assert.assertEquals(avalabilityStatusList.size(), filteredAvailableToys.size());
	}
	
	@Test
	public void getToysAvailabilityStatusTest() {
		avalabilityStatusList = new ArrayList<ToyStatusDetail>();
		toyStatusDetailList = new ToyStatusDetailsList();
		avalabilityStatusList.add(new ToyStatusDetail(1L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(2L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(3L, InventoryStatus.backorder));
		toyStatusDetailList.setToyList(avalabilityStatusList);
		Mockito.when((restTemplate.getForObject(Mockito.anyString(), Mockito.any()))).thenReturn(toyStatusDetailList);
		List<ToyStatusDetail> availableToyStatus = stockCheckerService.getToysAvailabilityStatus();
		Assert.assertEquals(avalabilityStatus.size(), availableToyStatus.size());
	}
	
	@org.junit.Test
	public void getToysAvailabilityStatusNullTest() {
		Mockito.when((restTemplate.getForObject(Mockito.anyString(), Mockito.any()))).thenReturn(null);
		List<ToyStatusDetail> availableToyStatus = stockCheckerService.getToysAvailabilityStatus();
		Assert.assertEquals(0, availableToyStatus.size());
	}
	
	@Test
	public void getToysAvailabilityStatusExceptionTest() {
		Mockito.when((restTemplate.getForObject(Mockito.anyString(), Mockito.any())))
		.thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		 boolean flag = false;
		try {
			stockCheckerService.getToysAvailabilityStatus();
		} catch(Exception exp) {
			flag = true;
		}
		Assert.assertTrue(flag);
	}
}
