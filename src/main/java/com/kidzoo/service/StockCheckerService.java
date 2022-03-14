package com.kidzoo.service;

import java.util.List;

import com.kidzoo.bean.Toy;
import com.kidzoo.customexception.KidZooException;
import com.kidzoo.service.bean.ToyStatusDetail;

public interface StockCheckerService {

	/**
	 * Method to filter the toys in store based on the stock  
	 * 
	 * @param toyList
	 * @param avalabilityStatus
	 * @return
	 */
	List<Toy> filterAvailableToys(List<com.kidzoo.entity.Toy> toyList, List<ToyStatusDetail> avalabilityStatus);

	/**
	 * Method to fetch the in stock toys from the inventory api
	 * 
	 * @return
	 * @throws KidZooException
	 */
	List<ToyStatusDetail> getToysAvailabilityStatus() throws KidZooException;

}