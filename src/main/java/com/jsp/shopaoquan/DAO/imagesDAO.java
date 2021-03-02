package com.jsp.shopaoquan.DAO;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.jsp.shopaoquan.Entity.images;

@Repository(value = "imagesDAO")
@Transactional
@Component
public class imagesDAO {
	@Autowired
	private SessionFactory sessionFactory;
	public void save(final images img) {
		Session session = sessionFactory.getCurrentSession();
		session.save(img);
	}
	public List<images> findAll(){
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From images", images.class).getResultList();
	}
	public Blob findById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Blob query = (Blob) session.createQuery("select i.img from images i where imgname=:name",images.class).setParameter("name", id).uniqueResult();
		System.out.println(session.createQuery("select i.img from images i where imgname=:name",images.class).setParameter("name", id).uniqueResult());
		
		return query;
		
	}
	public images find(String id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(images.class, id);
	}
}
