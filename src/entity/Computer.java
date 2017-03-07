/*
 * Computer.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package entity;

/**
 * 购物的电脑信息
 * @author LT
 * @version 1.0, 2015年9月26日
 */
public class Computer {
	private long id;
	private String model;
	private String pic;
	private String prodDesc;
	private double price;
	
	public Computer(){
		
	}
	/**
	 * 构造函数，给所有参数赋值
	 * @param model
	 * @param pic
	 * @param prodDesc
	 * @param price
	 */
	public Computer(String model, String pic, String prodDesc, double price) {
		this.model = model;
		this.pic = pic;
		this.prodDesc = prodDesc;
		this.price = price;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
