package com.jsp.shopaoquan.Controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jsp.shopaoquan.Entity.MyFile;
import com.jsp.shopaoquan.Entity.customer;
import com.jsp.shopaoquan.Entity.images;
import com.jsp.shopaoquan.Entity.orderr;
import com.jsp.shopaoquan.Entity.product;
import com.jsp.shopaoquan.Entity.type;
import com.jsp.shopaoquan.Service.TypeService;
import com.jsp.shopaoquan.Service.imgService;
import com.jsp.shopaoquan.Service.orderService;
import com.jsp.shopaoquan.Service.productService;

@Controller
public class adminController {
	@Autowired
	private productService productService;
	@Autowired
	private TypeService TypeService;
	@Autowired
	private orderService orderService;
	@Autowired
	private imgService imgService;
	@RequestMapping("/admin")
	public String admin(HttpSession session, HttpServletRequest request) {
		customer cus = (customer)session.getAttribute("session");
		String file = request.getServletContext().getRealPath("/resources/img");
	
		if ( cus != null && cus.getName_customer().equals("admin")) {
			return "admin/admin";
		} else {
			return "redirect:/";
		}
	}
	@RequestMapping("/typeList")
	public String typeList(Model model) {
		List<type> type = TypeService.findAll();
		model.addAttribute("type", type);
		return "admin/typeList";
	}
	@RequestMapping("/productList")
	public String productList(Model model) {
		model.addAttribute("product", new product());
		List<product> list = productService.findAll();
		model.addAttribute("list", list);
		List<type> type = TypeService.findAll();
		model.addAttribute("type", type);
		return "admin/productList";
	}
	@RequestMapping("/productAdd")
	public String producAdd(Model model,HttpSession session ) {
		model.addAttribute("myFile", new MyFile());
		model.addAttribute("product", new product());
		List<type> type = TypeService.findAll();
		HashMap<Integer , String> listType = new HashMap<Integer , String>();
		for (type type2 : type) {
			listType.put(type2.getId_prd_type(), type2.getName_prd_type());
		}
		model.addAttribute("listType", listType);
		return "admin/productAdd";
	}
	@RequestMapping("/edit")
	public String editPrd(Model model, @RequestParam("id")String name) {
		
		product prd = productService.findByID(name);
		model.addAttribute("prd", prd);
		MyFile myFile = new MyFile();		
		model.addAttribute("myFile", myFile);
		return "admin/editPrd";
	}
	@RequestMapping("/add")
	public String add(@ModelAttribute("product")product prd, Model model, @RequestParam("descrip") String decrip,
			@RequestParam("name")String name, HttpServletRequest request,
						@RequestParam("price")float price,@RequestParam("orgPrice")float orgPrice,MyFile myFile) {
		String fileName ="";
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			fileName = multipartFile.getOriginalFilename();
			String path = request.getServletContext().getRealPath("/resources/img/products");
			File file = new File(request.getServletContext().getRealPath("/resources/img/products"),fileName);
			
			multipartFile.transferTo(file);
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		model.addAttribute("image",fileName);
		product newPrd = new product(name,price,fileName,orgPrice,prd.getId_type());
		productService.save(newPrd);
		return "redirect:/productList";
	}
	@RequestMapping("/update")
	public String update(@ModelAttribute("prd")product prd, @ModelAttribute("myFile")MyFile myFile, HttpServletRequest request,
						@RequestParam("img")String img) {
		String fileName ="";
		
		
		try {
			MultipartFile multipartFile = myFile.getMultipartFile();
			fileName = multipartFile.getOriginalFilename();
			
			String path = request.getServletContext().getRealPath("/resources/img/products");
			File file = new File(request.getServletContext().getRealPath("/resources/img/products"),fileName);
			
			multipartFile.transferTo(file);
			prd.setImg_Prd(fileName);
			
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (prd.getImg_Prd() == null)
		{
			prd.setImg_Prd(img);
		}

		productService.update(prd);
		return "redirect:/productList";
	}
	@RequestMapping(value="/productDelete" , method = RequestMethod.GET)
	public String deleteAdd(Model model , @RequestParam("id")String product) {
		product  prd = productService.findByID(product);
		productService.delete(prd); 
		System.out.println(product);
		return "redirect:/productList";
		
	}
	@RequestMapping("/sales")
	public String sales(Model model) {
		return "admin/sales";
	}
	@RequestMapping("/saleM")
	public String saleM(Model model,@RequestParam("month")int month) {
		List<orderr> list = orderService.findM(month);
		float sub = 0;
		for (orderr or : list) {
			sub += or.getTotal();
		}
		
		model.addAttribute("sub",sub);
		model.addAttribute("list",list);
		return "admin/sales";
	}
	@RequestMapping("/saleY")
	public String saleQ(Model model) {
		HashMap<Integer, Float> result = new HashMap<Integer, Float>();
		for (int i = 1; i <= 12; i ++) {
			List<orderr> list = orderService.findM(i);
			float sub = 0;
			for (orderr or : list) {
				sub += or.getTotal();
			}
			result.put(i, sub);
		}
		orderr or = new orderr();
		model.addAttribute("ord", or);
		model.addAttribute("result", result);
		return "admin/sales";
	}
	@RequestMapping("/aa")
	public String aa(Model model/*,@RequestParam("file")CommonsMultipartFile[] fileUpload*/) {
//		if (fileUpload == null) {
//			if (fileUpload != null && fileUpload.length > 0) {
//				for ( CommonsMultipartFile aFile : fileUpload) {
//					images img = new images(aFile.getOriginalFilename(), aFile.getBytes());
//					System.out.println(img.getImg());
//					imgService.save(img);
//				}
//			}
//		}
		List<images> list = imgService.findAll();
		
		
		model.addAttribute("list", list);
		
		return "guest/img";
	}
	@RequestMapping("/saveIMG")
	public String saveIMG(Model model,@RequestParam("file")CommonsMultipartFile[] fileUpload) {
		if (fileUpload != null) {
			if (fileUpload != null && fileUpload.length > 0) {
				for ( CommonsMultipartFile aFile : fileUpload) {
					images img = new images(aFile.getOriginalFilename(), aFile.getBytes());
					System.out.println(img.getImg());
					imgService.save(img);
				}
			}
		}else {
			String err = "ERRORRR";
			model.addAttribute("err",err);
		}
		return "redirect:/aa";
	}
	@RequestMapping("/up")
	public String up() {
		return "guest/up";
	}
	@RequestMapping(value = "/Photo/{id}")
	public void getStudentPhoto(HttpServletResponse response,@PathVariable("id") String id) throws Exception {
		
		response.setContentType("image/jpeg");
		images img = imgService.find(id);
		System.out.println(img.getImg());
		
		
		
		InputStream inputStream = new ByteArrayInputStream(img.getImg());
		IOUtils.copy(inputStream, response.getOutputStream());
	}
}
