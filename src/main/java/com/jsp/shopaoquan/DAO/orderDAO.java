package com.jsp.shopaoquan.DAO;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.shopaoquan.Entity.orderr;



@Repository(value = "orderDAO")
@Component
@Transactional(rollbackFor = Exception.class)
public class orderDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public void save(final orderr order) {
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
	}
	public orderr findbyID(final int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(orderr.class, id);
	}
	
	public void update(final orderr ord) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(ord);
	}
	public void delete(final orderr ord) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(ord);
	}
	public List<orderr> findM(int month){
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("From orderr o where month(o.date_of_sale)=:month order   by date(o.date_of_sale) asc"); 
		query.setParameter("month", month);
		return query.getResultList();
	}
	public LocalDate getDay() {
		LocalDate day = LocalDate.now();
		return day;
	}
	public List<orderr> findAll(){
		Session session = sessionFactory.getCurrentSession();		
		return  session.createQuery("from orderr",orderr.class).getResultList();
	}
	public HashMap<Integer, Float> salesY(){
		HashMap<Integer, Float> result = new HashMap<Integer, Float>();
		for (int i = 1; i <= 12; i ++) {
			List<orderr> list = findM(i);
			float sub = 0;
			for (orderr or : list) {
				sub += or.getTotal();
			}
			result.put(i, sub);
		}
		
		return result;
	}
	public String formatVND(float price) {
		DecimalFormat formatPrice = new DecimalFormat("###,###,###");
		return formatPrice.format(price) +" đ";
	}
}
