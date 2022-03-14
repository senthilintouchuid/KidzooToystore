package com.kidzoo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kidzoo.bean.Toy;
import com.kidzoo.constant.InventoryStatus;
import com.kidzoo.customexception.KidZooException;
import com.kidzoo.service.bean.ToyStatusDetail;
import com.kidzoo.service.bean.ToyStatusDetailsList;
import com.kidzoo.service.impl.StockCheckerServiceImpl;

@SpringBootTest
@AutoConfigureTestDatabase
public class StockCheckerServiceImplTest {

	@Autowired
	private StockCheckerServiceImpl stockCheckerService;
	
	@MockBean
	private RestTemplate restTemplate;
	
	private List<ToyStatusDetail> avalabilityStatusList;
	private ToyStatusDetailsList toyStatusDetailList;
	
	@Test
	public void filterAvailableToysTest() {
		List<com.kidzoo.entity.Toy> toyListRepo = new ArrayList<>();
		toyListRepo.add(new com.kidzoo.entity.Toy(1L, "Toy-1", BigDecimal.valueOf(500.06), 5, "https://kidzoo.com/toy-1.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(2L, "Toy-2", BigDecimal.valueOf(700.99), 6, "https://kidzoo.com/toy-2.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(3L, "Toy-3", BigDecimal.valueOf(1500.48), 7, "https://kidzoo.com/toy-3.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(4L, "Toy-3", BigDecimal.valueOf(1550.48), 8, "https://kidzoo.com/toy-4png"));
		
		avalabilityStatusList = new ArrayList<ToyStatusDetail>();
		avalabilityStatusList.add(new ToyStatusDetail(1L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(3L, InventoryStatus.available));
		avalabilityStatusList.add(new ToyStatusDetail(2L, InventoryStatus.available));
		
		List<Toy> filteredAvailableToys = stockCheckerService.filterAvailableToys(toyListRepo, avalabilityStatusList);
		Assertions.assertEquals(3, filteredAvailableToys.size());
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
		
		Assertions.assertEquals(3, availableToyStatus.size());
	}
	
	@Test
	public void getToysAvailabilityStatusNullTest() {
		Mockito.when((restTemplate.getForObject(Mockito.anyString(), Mockito.any()))).thenReturn(null);
		List<ToyStatusDetail> availableToyStatus = stockCheckerService.getToysAvailabilityStatus();
		
		Assertions.assertEquals(0, availableToyStatus.size());
	}
	
	@Test
	public void getToysAvailabilityStatusExceptionTest() {
		Mockito.when((restTemplate.getForObject(Mockito.anyString(), Mockito.any())))
		.thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		
		Assertions.assertThrows(KidZooException.class, ()-> stockCheckerService.getToysAvailabilityStatus());
		
	}
}