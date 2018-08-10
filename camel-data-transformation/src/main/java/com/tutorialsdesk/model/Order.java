package com.tutorialsdesk.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@CsvRecord(separator = ",")
public class Order implements Serializable{

	@DataField(pos = 1)
	private String orderName;
	
	@DataField(pos = 2)
	private String customerName;
	
	@DataField(pos = 3, precision = 2)
	private BigDecimal price;
	
	@DataField(pos = 4)
	private Integer count;
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Order [orderName=" + orderName + ", customerName=" + customerName + ", price=" + price + ", count="
				+ count + "]";
	}
	
	
}
