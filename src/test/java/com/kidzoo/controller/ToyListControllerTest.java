package com.kidzoo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kidzoo.bean.KidZooRestResponse;
import com.kidzoo.bean.Toy;
import com.kidzoo.service.impl.ToyServiceImpl;

import junit.framework.Assert;



@RunWith(MockitoJUnitRunner.class)
public class ToyListControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private ToyListController toyListController;

	@Mock
	private ToyServiceImpl toyService;

	private List<Toy> toyList;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(toyListController).build();
		toyList = new ArrayList<>();
		toyList.add(new Toy(1L, "Toy-1", BigDecimal.valueOf(500.06), 5, "https://kidzoo.com/toy-1.png"));
		toyList.add(new Toy(2L, "Toy-2", BigDecimal.valueOf(700.99), 5, "https://kidzoo.com/toy-1.png"));
		toyList.add(new Toy(3L, "Toy-3", BigDecimal.valueOf(1500.48), 5, "https://kidzoo.com/toy-1.png"));
	}

	@Test
	public void getAllAvailableToysTest() throws Exception {
		Mockito.when(toyService.getToysBasedOnPriceRange(Mockito.any(), Mockito.any())).thenReturn(toyList);
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/kidzoo/v1/api/toys?fromPrice=500&toPrice=1600"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

		Mockito.verify(toyService).getToysBasedOnPriceRange(Mockito.any(),Mockito.any());
	}

	@Test
	public void getAllAvailableToysWithoutFilterTest() throws Exception {
		Mockito.when(toyService.getAllAvailableToys()).thenReturn(toyList);
		ResponseEntity<KidZooRestResponse> availableToys = toyListController.getAllAvailableToys(Optional.empty(),
				Optional.empty());

		Assert.assertEquals(HttpStatus.OK, availableToys.getStatusCode());
		Assert.assertEquals(3, availableToys.getBody().getData().size());
	}

	@Test
	public void getAllAvailableToysWithFilterFromTest() throws Exception {
		Mockito.when(toyService.getToysBasedOnPriceRangeFrom(Mockito.any(BigDecimal.class))).thenReturn(toyList);
		ResponseEntity<KidZooRestResponse> availableToys = toyListController
				.getAllAvailableToys(Optional.ofNullable(BigDecimal.valueOf(500)), Optional.empty());

		Assert.assertEquals(HttpStatus.OK, availableToys.getStatusCode());
		Assert.assertEquals(3, availableToys.getBody().getData().size());
	}
	
	@Test
	public void getAllAvailableToysWithFilterToTest() throws Exception {
		Mockito.when(toyService.getToysBasedOnPriceRange(Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class))).thenReturn(toyList);
		ResponseEntity<KidZooRestResponse> availableToys = toyListController
				.getAllAvailableToys(Optional.empty(), Optional.ofNullable(BigDecimal.valueOf(1600.00)));

		Assert.assertEquals(HttpStatus.OK, availableToys.getStatusCode());
		Assert.assertEquals(3, availableToys.getBody().getData().size());
	}

	@Test
	public void getAllAvailableToysWithFilterFromAndToTest() throws Exception {
		Mockito.when(toyService.getToysBasedOnPriceRange(Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class))).thenReturn(toyList);
		ResponseEntity<KidZooRestResponse> availableToys = toyListController
				.getAllAvailableToys(Optional.ofNullable(BigDecimal.valueOf(500.00)), Optional.ofNullable(BigDecimal.valueOf(1600.00)));

		Assert.assertEquals(HttpStatus.OK, availableToys.getStatusCode());
		Assert.assertEquals(3, availableToys.getBody().getData().size());
	}
}
