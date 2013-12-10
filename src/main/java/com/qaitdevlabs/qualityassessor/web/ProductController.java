package com.qaitdevlabs.qualityassessor.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaitdevlabs.qualityassessor.dto.AjaxResponse;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.product.service.ProductService;
import com.qaitdevlabs.qualityassessor.service.UserService;

@Controller
public class ProductController {
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	@RequestMapping(value = "/myProducts", method = RequestMethod.GET)
	public String getListOfProductsByUser(ModelMap map, HttpServletRequest request) {
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);
		List<Product> listOfProducts = productService.getListOfProductsByUser(user);
		map.addAttribute("productList", listOfProducts);
		return "myProductsView";
	}
	
	@RequestMapping(value = "/saveOrUpdateProduct", method = RequestMethod.POST)
	public @ResponseBody
	String saveOrUpdateProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String id, @RequestParam String productName,
			@RequestParam String productDescription) {
		System.out.println("contre");
		String result = "Updation UnSuccessfull";
		Long productId = null;
		Product product = null;
		Long userId = (Long) request.getSession().getAttribute(
				"USER_ID");
		User user = userService.getUser(userId);
		
		if (!id.equals("")) {
			productId = Long.valueOf(id);
			System.out.println("productId " + productId);
			product = productService.getProductById(productId);
			if( product == null){
				response.setStatus(500);
				return "Deletion Unsuccessfull ,Product doesn't exist in system";
			}
			if( !isProductBeUpdateable(product, user)){
				response.setStatus(500);
				return "You have no permission to Update this product";
			}
		}
		else{
			product = new Product();
			product.setUser(user);
		}
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		productService.saveOrUpdateProduct(product);
		result = product.getProductId().toString(); 
		return result;
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST)
	public @ResponseBody
	String deleteProduct(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String id){
		String result = "Deletion Unsuccessfull";
		Long productId = null;
		Product product = null;
		if(id.equals("")){
			response.setStatus(500);
		}
		if (!id.equals("")) {
			productId = Long.valueOf(id);
			System.out.println("productId " + productId);
			product = productService.getProductById(productId);
			if (product != null) {
				Long userId = (Long) request.getSession().getAttribute(
						"USER_ID");
				User user = userService.getUser(userId);
				if (isProductBeDeletable(product, user)) {
					productService.deleteProduct(product);
					result = "Deletion Successfull";
				}
				else{
					response.setStatus(500);
					result = "You have no permission to Delete this product";
				}
			}
			else{
				response.setStatus(500);
				result = "Deletion Unsuccessfull ,Product doesn't exist in system";
			}
		}
		return result;
	}

	private boolean isProductCorrespondToUser(Product product, User user) {
		User productCreatedUse = product.getUser();
		if (user.equals(productCreatedUse)) {
			return true;
		}
		return false;

	}
	
	private boolean isProductBeDeletable(Product product, User user) {
		return isProductCorrespondToUser(product, user);
	}
	
	private boolean isProductBeUpdateable(Product product, User user) {
		return isProductCorrespondToUser(product, user);
	}
	
//	@RequestMapping(value = "/getProductById", method = RequestMethod.GET)
//	public String getProductById(@RequestParam String productKey,ModelMap map, HttpServletRequest request) {
//		Long productId = Long.valueOf(productKey);
//		return productService.getProductById(productId);
//	}
	
}
