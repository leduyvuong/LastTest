package com.jsp.shopaoquan.Entity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class images {
	@Id
	@Column(name = "imgname")
	private String imgname;
	
	@Column(name = "img")
	@Lob
	private byte[] img;
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public byte[] getImg() {
		return img;
	}
	public images() {
		// TODO Auto-generated constructor stub
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	public images(String imgname, byte[] img) {
		super();
		this.imgname = imgname;
		this.img = img;
	}
	public FileOutputStream convertBtoF() throws IOException {
		try {
			
			FileOutputStream fos = new FileOutputStream("amm.jpg");
			fos.write(img);
			System.out.println("avc");
			return fos;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("aaaaaaaa");
			return null;
		}
		
	}
}
