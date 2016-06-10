package com.the.harbor.commons.components.globalconfig;

public class MemeberPrice {

	/**
	 * 几个月
	 */
	private int months;

	/**
	 * 总价，以分为单位
	 */
	private int prices;
	
	private String priceYuan;

	/**
	 * 折扣描述
	 */
	private String discountDesc;

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getPrices() {
		return prices;
	}

	public void setPrices(int prices) {
		this.prices = prices;
	}

	public String getDiscountDesc() {
		return discountDesc;
	}

	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}

	public String getPriceYuan() {
		return priceYuan;
	}

	public void setPriceYuan(String priceYuan) {
		this.priceYuan = priceYuan;
	}
	
	
	
	

}
