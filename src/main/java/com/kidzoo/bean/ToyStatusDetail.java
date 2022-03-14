package com.kidzoo.bean;

import com.kidzoo.constant.InventoryStatus;

public class ToyStatusDetail {

	private Long id;
	private InventoryStatus status;

	public ToyStatusDetail() {
	}

	public ToyStatusDetail(Long toyId, InventoryStatus status) {
		this.id = toyId;
		this.status = status;
	}

	public Long getToyId() {
		return id;
	}

	public void setToyId(Long toyId) {
		this.id = toyId;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

}
