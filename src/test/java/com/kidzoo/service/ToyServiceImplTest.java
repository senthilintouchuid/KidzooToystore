package com.kidzoo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.kidzoo.bean.Toy;
import com.kidzoo.constant.InventoryStatus;
import com.kidzoo.repository.ToyRespository;
import com.kidzoo.service.bean.ToyStatusDetail;
import com.kidzoo.service.impl.ToyServiceImpl;

@SpringBootTest
public class ToyServiceImplTest {

	@Autowired
	private ToyServiceImpl toyService;

	@MockBean
	private ToyRespository toyRepository;

	@MockBean
	private StockCheckerService stockCheckerService;

	private List<com.kidzoo.entity.Toy> toyListRepo;
	List<ToyStatusDetail> avalabilityStatus;
	private List<Toy> toyListBean;

	@BeforeEach
	public void init() {
		toyListRepo = new ArrayList<>();
		toyListRepo.add(new com.kidzoo.entity.Toy(1L, "Toy-1", BigDecimal.valueOf(500.06), 5, "https://kidzoo.com/toy-1.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(2L, "Toy-2", BigDecimal.valueOf(700.99), 6, "https://kidzoo.com/toy-2.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(3L, "Toy-3", BigDecimal.valueOf(1500.48), 7, "https://kidzoo.com/toy-3.png"));
		toyListRepo.add(new com.kidzoo.entity.Toy(4L, "Toy-3", BigDecimal.valueOf(1550.48), 8, "https://kidzoo.com/toy-4png"));

		avalabilityStatus = new ArrayList<>();
		avalabilityStatus.add(new ToyStatusDetail(1L, InventoryStatus.backorder));
		avalabilityStatus.add(new ToyStatusDetail(2L, InventoryStatus.available));
		avalabilityStatus.add(new ToyStatusDetail(3L, InventoryStatus.backorder));
		
		toyListBean = new ArrayList<>();
		toyListBean.add(new Toy(1L, "Toy-1", BigDecimal.valueOf(500.06), 5, "https://kidzoo.com/toy-1.png"));
		toyListBean.add(new Toy(2L, "Toy-2", BigDecimal.valueOf(700.99), 6, "https://kidzoo.com/toy-2.png"));
		toyListBean.add(new Toy(3L, "Toy-3", BigDecimal.valueOf(1500.48), 7, "https://kidzoo.com/toy-3.png"));
		
	}

	@Test
	public void getAllAvailableToysTest() {
		Mockito.when(toyRepository.findAll()).thenReturn(toyListRepo);
		Mockito.when(stockCheckerService.getToysAvailabilityStatus()).thenReturn(avalabilityStatus);
		Mockito.when(stockCheckerService.filterAvailableToys(Mockito.anyList(), Mockito.anyList())).thenReturn(toyListBean);

		List<Toy> toyList = toyService.getAllAvailableToys();
		Assertions.assertEquals(3, toyList.size());
	}

	@Test
	public void getToysBasedOnPriceRangeFromTest() {
		Mockito.when(toyRepository.findAll()).thenReturn(toyListRepo);
		Mockito.when(stockCheckerService.getToysAvailabilityStatus()).thenReturn(avalabilityStatus);
		Mockito.when(stockCheckerService.filterAvailableToys(Mockito.anyList(), Mockito.anyList())).thenReturn(toyListBean);

		List<Toy> toyList = toyService.getToysBasedOnPriceRangeFrom(BigDecimal.valueOf(500.00));
		Assertions.assertEquals(3, toyList.size());
	}

	@Test
	public void getToysBasedOnPriceRange() {
		Mockito.when(toyRepository.findAll()).thenReturn(toyListRepo);
		Mockito.when(stockCheckerService.getToysAvailabilityStatus()).thenReturn(avalabilityStatus);
		Mockito.when(stockCheckerService.filterAvailableToys(Mockito.anyList(), Mockito.anyList())).thenReturn(toyListBean);

		List<Toy> toyList = toyService.getToysBasedOnPriceRange(BigDecimal.valueOf(500.00),BigDecimal.valueOf(1600.00));
		Assertions.assertEquals(3, toyList.size());
	}

}