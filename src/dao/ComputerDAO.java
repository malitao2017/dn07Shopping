/*
 * ComputerDAO.java
 * Copyright: TsingSoft (c) 2015
 * Company: 北京清软创新科技有限公司
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import entity.Computer;

/**
 * 数据库访问操作类
 * @author LT
 * @version 1.0, 2015年9月26日
 */
public class ComputerDAO {
	public List<Computer> findAll() {
		Connection con = DBUtil.getConnect();
		List<Computer> list = new ArrayList<Computer>();
		try{
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery("select * from t_computer");
			while(rs.next()){
				Computer entity = new Computer();
				entity.setId(rs.getLong("id"));
				entity.setModel(rs.getString("model"));
				entity.setPic(rs.getString("pic"));
				entity.setProdDesc(rs.getString("proddesc"));
				entity.setPrice(rs.getDouble("price"));
				list.add(entity);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(con);
		}
		return list;
	}
	
	public Computer findById(long id) {
		Connection con = DBUtil.getConnect();
		Computer entity = null; 
		try{
			PreparedStatement sta = con.prepareStatement("select * from t_computer where id=?");
			sta.setLong(1, id);
			ResultSet rs = sta.executeQuery();
			while(rs.next()){
				entity = new Computer();
				entity.setId(id);
				entity.setModel(rs.getString("model"));
				entity.setPic(rs.getString("pic"));
				entity.setProdDesc(rs.getString("proddesc"));
				entity.setPrice(rs.getDouble("price"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(con);
		}
		return entity;
	}
}
