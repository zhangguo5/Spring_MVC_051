package com.zhangguo.springmvc51.entities;

import java.io.Serializable;

import javax.servlet.annotation.HandlesTypes;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

/**
 * 产品
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	/*
	 * 编号
	 */
	private int id;
	/*
	 * 名称
	 */
	@Size(min=1,max=50,message="名称长度必须介于{2}-{1}之间")
	@Pattern(regexp="^[\\w\\u4e00-\\u9fa5]{0,10}$",message="格式错误，必须是字母数字与中文")
	private String name;
	/*
	 * 价格
	 */
	@Range(min=0,max=1000000,message="价格只允许在{2}-{1}之间")
	private double price;
	/*
	 * 产品类型
	 */
	private ProductType productType;

	public Product() {
		productType=new ProductType();
	}

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Product(int id, String name, double price, ProductType type) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.productType = type;
	}

	@Override
	public String toString() {
		return "编号(id)：" + this.getId() + "，名称(name)：" + this.getName() + "，价格(price)：" + this.getPrice()
				+ "，类型(productType.Name)：" + this.getProductType().getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
}
