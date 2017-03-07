/*
 * Cart.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package bean;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import util.CookieUtil;
import dao.ComputerDAO;

/**
 * 购物车
 * 实现商品的列表、添加、删除、修改、清空、总价
 * @author LT
 * @version 1.0, 2015年9月26日
 */
public class Cart {
	private List<CartItem> list = new ArrayList<CartItem>();
	public List<CartItem> list(){
		return list;
	}
	/**
	 * 增加一种商品
	 * @param item
	 * @return
	 */
	public boolean add(CartItem item){
		boolean flag = false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getC().getId() ==item.getC().getId()){
				return true;
			}
		}
		list.add(item);
		return flag;
	}
	/**
	 * 删除此单品
	 * @param id
	 */
	public void delete(long id){
		for(int i=0;i<list.size();i++){
			CartItem item = list.get(i);
			if(item.getC().getId()==id){
				list.remove(item);
				break;
			}
		}
	}
	/**
	 * 更改单品的数量
	 * @param id
	 * @param qty
	 */
	public void modify(long id,int qty){
		for(int i=0;i<list.size();i++){
			CartItem item = list.get(i);
			if(item.getC().getId()==id){
				item.setQty(qty);
				break;
			}
		}
	}
	/**
	 * 清空所有的商品
	 */
	public void clear(){
		list.clear();
	}
	/**
	 * 所有商品的价值总额
	 * @return
	 */
	public double total(){
		double total = 0;
		for(int i=0;i<list.size();i++){
			total = total + list.get(i).getQty()*list.get(i).getC().getPrice();
		}
		return total;
	}
	
	//使用第二种购物车方式：cookie机制
	/**
	 * 得到商品项目的关键节点信息
	 */
	public String getInfo(){
		if(!CookieUtil.SECOND) return null;
		StringBuffer info = null;
		if(null != list){
			info = new StringBuffer();
			for(CartItem item:list){
				info.append(item.getC().getId()).append(",").append(item.getQty()).append(";");
			}
			if(info.length()>1){
				info.deleteCharAt(info.length()-1);//此处必须减一
			}
		}
		return info.toString();
	}
	/**
	 * 还原对应的商品信息
	 */
	public void load(String info){
		if(!CookieUtil.SECOND) return ;
		if(StringUtils.isEmpty(info)) return;
		String[] items = info.split(";");
		ComputerDAO dao = new ComputerDAO();
		for(String item:items){
			String[] obj = item.split(",");
			CartItem ci = new CartItem();
			ci.setC(dao.findById(Long.parseLong(obj[0])));
			ci.setQty(Integer.parseInt(obj[1]));
			list.add(ci);
		}
	}
	
	
}
