package com.jsp.shopaoquan.Controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.jsp.shopaoquan.Entity.GooglePojo;
import com.jsp.shopaoquan.Entity.customer;

import com.jsp.shopaoquan.Service.customerService;
import com.jsp.shopaoquan.Service.orderDetailService;
import com.jsp.shopaoquan.Service.restFB;
import com.restfb.types.User;
import com.jsp.shopaoquan.Service.GoogleUtils;
import com.jsp.shopaoquan.Service.SendMail;


@Controller
public class loginController {
	@Autowired
	private customerService customerService;
	
	@Autowired
	private orderDetailService orderDetailService;
	@Autowired
	private restFB restFB;
	@Autowired
	private GoogleUtils googleUtils;
	@Autowired
	private SendMail sendMail;
	@RequestMapping("/login")
	public String login(Model model) {
		
		return "/guest/login";
	}
	//Log in
	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public String loginCheck(Model model, @RequestParam("user") String user, @RequestParam("pass") String pass, HttpServletRequest request,HttpServletResponse response) {
		customer cus = customerService.findByID(user);
		String url = "redirect:/login";
		HttpSession session = request.getSession();
		if (customerService.findByID(user) != null ) {
			
			if (user.equals("admin") && cus.getPass().equals(pass)) {
				
				session.setAttribute("session", customerService.findByID(user));
				url = "redirect:/admin";
			} 
			else if(customerService.getMD5(pass).equals(cus.getPass())) {
				session.setAttribute("session", customerService.findByID(user));
				url ="redirect:/";
			} else {
				session.setAttribute("err", "Wrong password!");
				return url;
			}
		}else {
			session.setAttribute("err", "Username not found!");
			return url;
		}
		return url;
	}
	//End log in
	// Register
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Register(Model model, HttpServletRequest request, @RequestParam("userReg") String user, @RequestParam("pass") String pass, @RequestParam("full_name") String full_name,
						@RequestParam("email") String email, @RequestParam("address") String address, @RequestParam("phone") int phone) {
		pass = customerService.getMD5(pass);
		if (customerService.findByID(user)==null) {
			customer cus = new customer(user, address, email, phone, pass, full_name);
			customerService.save(cus);
			HttpSession session = request.getSession();
			session.setAttribute("session", cus);
			return "redirect:/";
		} else {
			model.addAttribute("errR","Username used!");
			return "/guest/login";
		}
		
	}
	//End Register
	//Log out
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		
		customer cus = (customer)session.getAttribute("session");
		System.out.println(cus.getName_customer());
		orderDetailService.clearTable(cus.getName_customer());
		session.invalidate();		
		return "redirect:/";
	}
	//End Logout
	//Login with FB
	@RequestMapping("/login-facebook")
	public String loginFa(HttpSession session, Model model,HttpServletRequest rq) {
		String code = rq.getParameter("code");
		System.out.println(code);
		String access = "";
		try {
			access = restFB.getToken(code);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		User user = restFB.getUserInfo(access);
		customer userDetails = restFB.buildUser(user);
		if (customerService.findByID(userDetails.getName_customer()) == null) {
		customerService.save(userDetails);
		}
		
		session.setAttribute("session", userDetails);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null
		        );
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(rq));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    return "redirect:/";
	}
	//End login with FB
	//Login with GG
	@RequestMapping("/login-google")
	public String logingg(HttpSession session, Model model,HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
	    
	    if (code == null || code.isEmpty()) {
	      return "redirect:/login?message=google_error";
	    }
	    String accessToken = googleUtils.getToken(code);
	    
	    GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
	    customer userDetail = googleUtils.buildUser(googlePojo);
	    if (customerService.findByID(userDetail.getName_customer()) == null) {
			customerService.save(userDetail);
			}
	    session.setAttribute("session", userDetail);
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null
	        );
	    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    return "redirect:/";
	}
	//End login with GG
	//Forget password
	@RequestMapping("/forgetP")
	public String forgetP(Model model) {
		int i = 0;
		model.addAttribute("i",i);
		return "/guest/forgetP";
	}
	@RequestMapping("/getName")
	public String getName(Model model, @RequestParam("userName")String name, HttpSession session) throws AddressException, MessagingException {
		customer C = customerService.findByID(name);
		
		String mail;
		int i = 1;
		if (C != null) {
			session.setAttribute("nameC", C.getName_customer());
			mail = customerService.hideMail(C.getEmail());
			model.addAttribute("mail",mail);
			int code = customerService.getCode();
			session.setAttribute("code", code);			
			sendMail.sendCode(C.getEmail(), code);
			model.addAttribute("i",i);
			return "/guest/forgetP";
		}else {
			mail = "Not found your account";
			model.addAttribute(mail);
			i = 2;
			model.addAttribute("i",i);
			return "/guest/forgetP";
		}
		
	}
	@RequestMapping("/cofirm")
	public String cofirm(Model model, HttpSession session,@RequestParam("code")int code) {
		int scode = (Integer) session.getAttribute("code");
		
		if (code == scode) {
			int i = 2;
			model.addAttribute("i",i);
			session.removeAttribute("code");
			return "/guest/forgetP";
		} else {
			int i = 1;
			model.addAttribute("i",i);
			model.addAttribute("err", "Incorrect code");
			return "/guest/forgetP";
		}
	}
	@RequestMapping("/changeP")
	public String changeP(Model model, HttpSession session, @RequestParam("newPass")String pass) {
		customer c = customerService.findByID((String)session.getAttribute("nameC"));
		
		pass = customerService.getMD5(pass);
		c.setPass(pass);
		customerService.update(c);
		session.removeAttribute("nameC");
		session.setAttribute("session", c);
		return "redirect:/";
	}
}
