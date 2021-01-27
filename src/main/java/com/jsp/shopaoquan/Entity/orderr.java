package com.jsp.shopaoquan.Entity;



import java.text.DecimalFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "orderr")
public class orderr {
	@Id
	@Column(name = "id_order")
	private int id_order;
	@Column(name = "total")
	private float total;
	@Column(name = "original_total")
	private float original_total;
	@Column(name = "date_of_sale")
	private LocalDate date_of_sale;
	@Column(name = "name_customer")
	private String name_customer;
	@Column(name = "description")
	private String description;
	public int getId_order() {
		return id_order;
	}
	
	public float getOriginal_total() {
		return original_total;
	}

	public void setOriginal_total(float original_total) {
		this.original_total = original_total;
	}

	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public LocalDate getDate_of_sale() {
		return date_of_sale;
	}
	public void setDate_of_sale(LocalDate date_of_sale) {
		this.date_of_sale = date_of_sale;
	}
	public String getName_customer() {
		return name_customer;
	}
	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
	
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String formatVND(float price) {
		DecimalFormat formatPrice = new DecimalFormat("###,###,###");
		return formatPrice.format(price) +" đ";
	}
	
	public orderr(int id_order, float total, float original_total, LocalDate date_of_sale, String name_customer,
			String description) {
		super();
		this.id_order = id_order;
		this.total = total;
		this.original_total = original_total;
		this.date_of_sale = date_of_sale;
		this.name_customer = name_customer;
		this.description = description;
	}

	public orderr() {
		
	}
}
	
