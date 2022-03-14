package com.kidzoo.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOY")
public class Toy {

	@Id
	@Column(name = "ID")
	private Long toyId;

	@Column(name = "NAME")
	private String toyName;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "AGE")
	private int age;

	@Column(name = "IMAGEURL")
	private String imageUrl;

	public Toy() {
	}

	public Toy(Long toyId, String toyName, BigDecimal price, int age, String imageUrl) {
		this.toyId = toyId;
		this.toyName = toyName ;
		this.price = price;
		this.age = age;
		this.imageUrl = imageUrl;
	}

	public Long getToyId() {
		return toyId;
	}

	public void setToyId(Long toyId) {
		this.toyId = toyId;
	}

	public String getToyName() {
		return toyName ;
	}

	public void setToyName(String toyName) {
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

	public void setAge(int age) {
		this.age = age;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
