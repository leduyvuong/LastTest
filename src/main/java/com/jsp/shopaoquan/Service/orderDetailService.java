package com.jsp.shopaoquan.Service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.shopaoquan.DAO.orderDetailDAO;
import com.jsp.shopaoquan.Entity.order_detail;


@Service(value = "orderDetailService")
@Transactional
public class orderDetailService {
	@Autowired
	private orderDetailDAO orderDetailDAO;
	public List<order_detail> findAll(){
		return orderDetailDAO.findAll();
	}
	public order_detail findByID(int id) {
		return orderDetailDAO.findbyID(id);
	}
	public void save(order_detail ord) {
		orderDetailDAO.save(ord);
	}
	public void update(order_detail detail) {
		orderDetailDAO.update(detail);
	}
	public void delete(order_detail detail) {
		orderDetailDAO.delete(detail);
	}
	public int getRandomID() {
		Random rd = new Random();
		int j = rd.nextInt(10000);
		while (findByID(j)!= null) {
			j = rd.nextInt(10000);
		}
		return j;
	}
	public void clearTable(String name_customer) {
		orderDetailDAO.clearTable(name_customer);
	}
	public float getSubTotal(List<order_detail> list) {
		return orderDetailDAO.getSubTotal(list);
	}
	public float getORSubTotal(List<order_detail> list) {
		return orderDetailDAO.getORSubTotal(list);
	}
}
