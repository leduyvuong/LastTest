package com.jsp.shopaoquan.Controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.shopaoquan.Entity.customer;
import com.jsp.shopaoquan.Entity.order_detail;
import com.jsp.shopaoquan.Entity.orderr;
import com.jsp.shopaoquan.Entity.product;

import com.jsp.shopaoquan.Service.orderDetailService;
import com.jsp.shopaoquan.Service.orderService;
import com.jsp.shopaoquan.Service.productService;
import com.jsp.shopaoquan.Service.SendMail;

import com.jsp.shopaoquan.Service.customerService;

@Controller
public class cartController {
	
	@Autowired
	private customerService customerService;
	@Autowired
	private orderDetailService orderDetailService;
	@Autowired
	private productService productService;
	@Autowired
	private orderService orderService;
	@Autowired
	private SendMail sendMail;
	@RequestMapping("/cart")
	public String cart(Model model) {
		List<order_detail> listOR = orderDetailService.findAll();
		
		float subTotal = orderDetailService.getSubTotal(listOR);
		
		
		model.addAttribute("listOr",listOR);
		model.addAttribute("subTotal",subTotal);
		return "/guest/cart";
	}
	
	@RequestMapping(path =  "/cart/add", method = RequestMethod.POST)
	public String doGet(Model model, @RequestParam("id") String id_prd,@RequestParam("quanlity") int quanlity,
			HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		System.out.println(id_prd);
		customer cus= (customer)request.getSession().getAttribute("session");
		if (cus == null) {
			session.setAttribute("err", "You must be logged in to order!");
			return "redirect:/login";
		}else {
			product prd = productService.findByID(id_prd);
			float total = prd.getPrice_Prd()*quanlity;
			order_detail detail = new order_detail(orderDetailService.getRandomID(),quanlity,total,prd.getName_Prd(),prd.getImg_Prd(),cus.getName_customer());
			orderDetailService.save(detail);
			return "redirect:/";
		}
	}
	@RequestMapping(value = "/cart/delete", method = RequestMethod.GET)
	public String add(Model model, @RequestParam("id") int id) {
		order_detail ord = orderDetailService.findByID(id);
		orderDetailService.delete(ord);
		return "redirect:/cart";
	}
	@RequestMapping("/sendmail")
	public String sendMail(Model model, @RequestParam("email")String email,HttpSession session)  {
		customer cus = (customer)session.getAttribute("session");
		try {
			sendMail.sendText(email,cus.getName_customer());
		} catch (AddressException e) {
			
			
			e.printStackTrace();
			return "redirect:/";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}
	    return "guest/test";
	}
	@RequestMapping("/infor")
	public String infor(Model model,HttpSession session) {
		
		return "guest/report";
	}
	@RequestMapping(value = "/payment")
	public String payment(Model model,HttpServletRequest request, @RequestParam("id")String name) {
		HttpSession session = request.getSession();
		customer cusS = customerService.findByID(name);
		session.setAttribute("session", cusS);
		List<order_detail> ord = orderDetailService.findAll();
		String descrip = orderService.getDecrip(ord);
		float subTotal=orderDetailService.getSubTotal(ord);	
		float subOR = orderDetailService.getORSubTotal(ord);
		System.out.println(descrip);
		customer cus= (customer)session.getAttribute("session");
		orderDetailService.clearTable(cus.getName_customer());
		orderr report = new orderr(orderService.getRandomID(), subTotal, subOR, orderService.getDay(), cus.getName_customer(),descrip);
		orderService.save(report);
		model.addAttribute("ord", ord);
		return "redirect:/";
	}
}
