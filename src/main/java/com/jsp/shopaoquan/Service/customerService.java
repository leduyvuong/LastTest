package com.jsp.shopaoquan.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.shopaoquan.DAO.customerDAO;
import com.jsp.shopaoquan.Entity.customer;
import com.jsp.shopaoquan.Entity.order_detail;

@Service(value = "customerService")
@Transactional
public class customerService {
	@Autowired
	private customerDAO customerDAO;
	public List<customer> findAll(){
		return customerDAO.findAll();
	}
	public customer findByID(String id) {
		return customerDAO.findbyID(id);
	}
	public void save(customer cus) {
		customerDAO.save(cus);
	}
	public void update(customer cus) {
		customerDAO.update(cus);
	}
	public void delete(customer cus) {
		customerDAO.delete(cus);
	}
	public String getMD5(String passs) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] mess = md.digest(passs.getBytes());
			StringBuffer sb = new StringBuffer();
			for ( int i = 0; i < mess.length; i++) {
				sb.append(Integer.toString((mess[i] & 0xff)+ 0x100,16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public String hideMail(String mail) {
		String[] hide = mail.split("@");
		String result = "";
		for (int i = 0; i < hide[0].length(); i++) {
			result+="*";
		}
		result += "@"+hide[1];
		return result;
	}
	public int getCode() {
		return ThreadLocalRandom.current().nextInt(100000, 999999);
	}
}
