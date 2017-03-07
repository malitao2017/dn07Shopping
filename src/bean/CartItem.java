/*
 * CartItem.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package bean;

import entity.Computer;

/**
 * 商品条目
 * @author LT
 * @version 1.0, 2015年9月26日
 */
public class CartItem {
	private Computer c;
	private int qty;

	public Computer getC() {
		return c;
	}
	public void setC(Computer c) {
		this.c = c;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
