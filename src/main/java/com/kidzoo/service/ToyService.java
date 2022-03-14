package com.kidzoo.service;

import java.math.BigDecimal;
import java.util.List;

import com.kidzoo.bean.Toy;

public interface ToyService {

	/**
	 * Method to get all available toys from store 
	 * 
	 * @return
	 */
	List<Toy> getAllAvailableToys();

	/**
	 * Method to get all available toys from given price range in store
	 * 
	 * @param fromPrice
	 * @return
	 */
	List<Toy> getToysBasedOnPriceRangeFrom(BigDecimal fromPrice);

	/**
	 * Method to get all available toys from store within the given range
	 * 
	 * @param fromPrice
	 * @param toPrice
	 * @return
	 */
	List<Toy> getToysBasedOnPriceRange(BigDecimal fromPrice, BigDecimal toPrice);

}