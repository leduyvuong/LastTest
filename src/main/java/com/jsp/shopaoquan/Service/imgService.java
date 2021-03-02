package com.jsp.shopaoquan.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.shopaoquan.DAO.imagesDAO;
import com.jsp.shopaoquan.Entity.images;


@Service(value = "imgService")
@Transactional
public class imgService {
	@Autowired
	private imagesDAO imagesDAO;
	public void save(images img) {
		imagesDAO.save(img);
	}
	public List<images> findAll(){
		return imagesDAO.findAll();
	}
	public byte[] convertFtoB(File file) {
		try {
			System.out.println(file.toPath());
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			return null;
		}
	}
	public Blob findById(String id) throws SerialException, SQLException {
		
		return imagesDAO.findById(id);
	}
	public images find (String id) {
		
		return imagesDAO.find(id);
		
	}
}
