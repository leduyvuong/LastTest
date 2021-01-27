package com.jsp.shopaoquan.DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.shopaoquan.Entity.order_detail;
import com.jsp.shopaoquan.Service.productService;




@Repository(value = "orderDetailDAO")
@Transactional(rollbackFor = Exception.class)
@Component
public class orderDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private productService productService;
	public void save(final order_detail ord) {		
		Session session = sessionFactory.getCurrentSession();
		session.save(ord);
	}
	public order_detail findbyID(final int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(order_detail.class, id);
	}
	
	public void update(final order_detail detail) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(detail);
	}
	public void delete(final order_detail detail) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(detail);
	}
	public List<order_detail> findAll(){
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From order_detail", order_detail.class).getResultList();
	}
	public void clearTable(final String name_customer) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from order_detail where name_customer = :name");
		query.setParameter("name", name_customer);
		query.executeUpdate();
		
		
	}
	public float getSubTotal(List<order_detail> list) {
		float subTotal = 0;
		for(int i = 0;i < list.size(); i++) {
			subTotal += list.get(i).getTotal();
		}
		return subTotal;
	}
	public float getORSubTotal(List<order_detail> list) {
		float subTotal = 0;
		for(int i = 0;i < list.size(); i++) {
			subTotal += productService.findByID(list.get(i).getName_prd()).getOriginal_price();
		}
		return subTotal;
	}
}
