package com.kidzoo.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kidzoo.entity.Toy;

public interface ToyRespository extends JpaRepository<Toy, Long> {
	
	/**
	 * Method to fetch the toy in between range
	 * 
	 * @param startPrice
	 * @param endPrice
	 * @return
	 */
	List<Toy> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

	/**
	 * Method to fetch the toys from the price range given
	 * 
	 * @param startPrice
	 * @return
	 */
	List<Toy> findByPriceGreaterThanEqual(BigDecimal startPrice);
}
