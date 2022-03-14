package com.kidzoo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kidzoo.bean.Toy;
import com.kidzoo.repository.ToyRespository;
import com.kidzoo.service.StockCheckerService;
import com.kidzoo.service.ToyService;

@Service("toyService")
public class ToyServiceImpl implements ToyService {

	@Autowired
	private ToyRespository toyRepository;

	@Autowired
	private StockCheckerService stockCheckerService;

	/**
	 * Method to get all available toys from store 
	 * 
	 * @return
	 */
	@Override
	public List<Toy> getAllAvailableToys() {
		List<com.kidzoo.entity.Toy> toyList = toyRepository.findAll();
		return getToys(toyList); 
	}
	

	/**
	 * Method to get all available toys from given price range in store
	 * 
	 * @param fromPrice
	 * @return
	 */
	@Override
	public List<Toy> getToysBasedOnPriceRangeFrom(BigDecimal fromPrice) {
		List<com.kidzoo.entity.Toy> toyList = toyRepository.findByPriceGreaterThanEqual(fromPrice);
		return getToys(toyList); 
	}

	/**
	 * Method to get all available toys from store within the given range
	 * 
	 * @param fromPrice
	 * @param toPrice
	 * @return
	 */
	@Override
	public List<Toy> getToysBasedOnPriceRange(BigDecimal fromPrice, BigDecimal toPrice) {
		List<com.kidzoo.entity.Toy> toyList = toyRepository.findByPriceBetween(fromPrice, toPrice);
		return getToys(toyList); 
	}


	/**
	 * Method to return toys based on the status 
	 * 
	 * @param toyList
	 * @return
	 */
	private List<Toy> getToys(List<com.kidzoo.entity.Toy> toyList) {
		List<Toy> toys = new ArrayList<>();
		if(toyList!= null && !toyList.isEmpty()) 
			return stockCheckerService.filterAvailableToys(toyList, stockCheckerService.getToysAvailabilityStatus());
		else 
			return toys;
	}

}
