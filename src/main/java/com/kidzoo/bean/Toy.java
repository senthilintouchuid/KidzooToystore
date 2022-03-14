package com.kidzoo.bean;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Toy {
	
	private Long toyId;
	private BigDecimal price;
	private int age;
	private String toyName;
	private String imageUrl;
	
	public Toy() {}
	
	public Toy(Long toyId, String toyName, BigDecimal price, int age, String imageUrl) {
		this.toyId = toyId;
		this.price = price;
		this.age = age;
		this.toyName = toyName;
		this.imageUrl = imageUrl;
	}

	public Long getId() {
		return toyId;
	}

	public void setId(Long toyId) {
		this.toyId = toyId;
	}

	public String getName() {
		return toyName;
	}

	public void setName(String toyName) {
		this.toyName = toyName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int toyAge) {
		this.age = toyAge;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String toyImageUrl) {
		this.imageUrl = toyImageUrl;
	}
	
}
