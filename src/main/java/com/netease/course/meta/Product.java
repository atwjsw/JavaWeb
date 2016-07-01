package com.netease.course.meta;

/**
 * @author: atwjsw
 * @Description: Data container for content product
 * @Date: Apr 20, 2016 3:23:35 PM
 */
public class Product {
	
	private int id;
	private String title;
	private String image;
	private Double price;
	private Double buyPrice;
	private String summary;
	private String detail;
	private boolean isBuy;
	private boolean isSell;
	private long buyTime;

	public long getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public boolean getIsSell() {
		return isSell;
	}

	public void setIsSell(boolean isSell) {
		this.isSell = isSell;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}
}
