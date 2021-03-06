package com.jsp.shopaoquan.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.shopaoquan.Entity.product;
import com.jsp.shopaoquan.Entity.type;
import com.jsp.shopaoquan.Service.productService;
import com.jsp.shopaoquan.Service.TypeService;

@Controller
public class showProductsController {
	@Autowired
	private productService productService;
	@Autowired
	private TypeService TypeService;
	@RequestMapping("/products")
	public String product(Model model,@RequestParam("id") String id,@RequestParam("page")String start) {
		List<product> list;		
		int max;
		if (id.equals("all")) {			
			List<product> listAll = productService.findAll();
			max = listAll.size();
			System.out.println(max);
			model.addAttribute("id", id);
			list = productService.pagination(Integer.parseInt(start));
		} else {			
			model.addAttribute("id", id);
			list = productService.productType(Integer.parseInt(id),Integer.parseInt(start));
			max = list.size();
		}
		System.out.println(max/8);
		if (max/8 == 0) {
			model.addAttribute("max", max/8);
		}else {
			model.addAttribute("max", max/8+1);
		}
		model.addAttribute("list", list);		
		return "/guest/products";
	}
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest rq) {
		HttpSession session = rq.getSession();
		List<product> listFP = productService.findFP();
		List<product> listLP = productService.findLP();
		List<type> listType = TypeService.findAll();
		model.addAttribute("listFP", listFP);
		model.addAttribute("listLP", listLP);
		session.setAttribute("listType", listType);
		return "/guest/index";
	}
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String doGet(Model model,@RequestParam("id") String id) {
		product prd = productService.findByID(id);
		List<type> listType = TypeService.findAll();
		model.addAttribute("listType", listType);
		model.addAttribute("prd", prd);
		return "/guest/product_detail";
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchProduct(Model model, @RequestParam("word") String word) {
		List<product> listSearch = productService.searchProduct(word);
		model.addAttribute("list", listSearch);
		if (listSearch != null) {
			model.addAttribute("list", listSearch);
			return "guest/products";
		}else {
			model.addAttribute("errS","Do not find your products!");
			return "guest/products";
		}
	}
	@RequestMapping("/about")
	public String about() {
		return "/guest/about";
	}
	
}
