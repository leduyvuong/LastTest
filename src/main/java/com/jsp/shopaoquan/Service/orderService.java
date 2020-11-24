package com.jsp.shopaoquan.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.shopaoquan.DAO.orderDAO;
import com.jsp.shopaoquan.Entity.order_detail;
import com.jsp.shopaoquan.Entity.orderr;

@Service(value = "orderService")
@Transactional
public class orderService {
	@Autowired
	private orderDAO orderDAO;
	public List<orderr> findAll(){
		return orderDAO.findAll();
	}
	public orderr findByID(int id) {
		return orderDAO.findbyID(id);
	}
	public void save(orderr ord) {
		orderDAO.save(ord);;
	}
	public void update(orderr ord) {
		orderDAO.update(ord);
	}
	public void delete(orderr ord) {
		orderDAO.delete(ord);
	}
	public int getRandomID() {
		Random rd = new Random();
		int j = rd.nextInt(10000);
		while (findByID(j)!= null) {
			j = rd.nextInt(10000);
		}
		return j;
	}
	public LocalDate getDay() {
		return orderDAO.getDay();
	}
	public String getDecrip(List<order_detail> ord) {
		String descrip = "";
		for (order_detail order_detail : ord) {
			descrip += "Tên :" +order_detail.getName_prd()+ ", Số lương : "+order_detail.getQuantity()+"\n";
			
		}
		return descrip;
	}
	public List<orderr> findM(int month){
		return orderDAO.findM(month);
	}
	public HashMap<Integer, Float> salesY(){
		return orderDAO.salesY();
	}
	public String formatVND(float price) {
		return orderDAO.formatVND(price);
	}
}
