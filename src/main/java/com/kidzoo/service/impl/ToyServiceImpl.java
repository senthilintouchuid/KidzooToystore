package com.kidzoo.service.impl;

import java.math.BigDecimal;
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
		return stockCheckerService.filterAvailableToys(toyList, stockCheckerService.getToysAvailabilityStatus());
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
		return stockCheckerService.filterAvailableToys(toyList, stockCheckerService.getToysAvailabilityStatus());
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
		return stockCheckerService.filterAvailableToys(toyList, stockCheckerService.getToysAvailabilityStatus());
	}

}
