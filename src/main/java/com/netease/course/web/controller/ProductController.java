package com.netease.course.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netease.course.meta.Product;
import com.netease.course.meta.Result;
import com.netease.course.meta.User;
import com.netease.course.service.ProductService;
import com.netease.course.service.impl.ProductServiceDBImpl;

/**
 * @author: atwjsw
 * @Description: web controller for product handling web access.
 * @Date: Apr 20, 2016 3:51:10 PM
 */
@Controller
public class ProductController {

	private ProductService productService;

	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	public void setProductService(ProductServiceDBImpl productServiceDBImpl) {
		this.productService = productServiceDBImpl;
	}

	@RequestMapping("/")
	public String doIndex(ModelMap map, HttpSession session) throws IOException {

		logger.debug("In index " + productService.getProductList().size());
		map.addAttribute("user", session.getAttribute("user"));
		map.addAttribute("productList", productService.getProductList());
		return "index";
	}

	@RequestMapping("/show")
	public String doShow(@RequestParam(value = "id", required = false) Integer id, ModelMap map, HttpSession session)
			throws IOException {
		
		map.addAttribute("product", productService.getProduct(id));
		map.addAttribute("user", session.getAttribute("user"));
		return "show";

	}

	@RequestMapping("/api/buy")
	@ResponseBody

	public Result doApiBuy(@RequestParam(value = "id", required = false) Integer id, ModelMap map, HttpSession session)
			throws IOException {

		logger.debug("/api/buy id=" + id);
		Product product = null;
		int rowAffected = 0;
		Result rs = new Result();
		rs.setCode(400);
		rs.setMessage("购买失败");
		rs.setResult(false);
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 0 && id != null) {
			product = productService.getProduct(id);
			// 已购买的产品不可通过api/buy接口直接购买。
			if (!product.getIsBuy()) {
				rowAffected = productService.buyProduct(id, user.getId(), product.getPrice(),
						System.currentTimeMillis());
				// 产品成功购买后将返回结果设为200和true
				if (rowAffected == 1) {
					rs.setCode(200);
					rs.setResult(true);
				}
			}
		}
		return rs;

	}

	@RequestMapping("/account")
	public String doAccount(ModelMap map, HttpSession session) throws IOException {

		logger.debug("/account");
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 0) {
			map.addAttribute("buyList", productService.getBuyListOrderByBuytime());
			map.addAttribute("user", session.getAttribute("user"));
			return "account";
		} else {
			session.removeAttribute("user");
			return "login";
		}
	}

	@RequestMapping("/api/delete")
	@ResponseBody
	public Result doApiDelete(@RequestParam(value = "id", required = false) Integer id, ModelMap map,
			HttpSession session) throws IOException {

		logger.debug("/api/delete id=" + id);
		Product product = null;
		int rowAffected = 0;
		Result rs = new Result();
		rs.setCode(400);
		rs.setMessage("删除失败");
		rs.setResult(false);
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 1 && id != null) {
			product = productService.getProduct(id);
			// 已出售的产品不可通过api/delete接口直接购买。
			if (!product.getIsSell()) {
				rowAffected = productService.deleteProduct(id);
				// 产品删除成功将返回结果设为200和true
				if (rowAffected == 1) {
					map.addAttribute("user", user);
					map.addAttribute("productList", productService.getProductList());
					rs.setCode(200);
					rs.setResult(true);
				}
			}
		}
		return rs;
	}

	@RequestMapping("/edit")
	public String doEdit(@RequestParam(value = "id", required = false) Integer id, ModelMap map, HttpSession session)
			throws IOException {

		System.out.println("/edit id=" + id);
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 1 && id != null) {
			map.addAttribute("user", user);
			map.addAttribute("product", productService.getProduct(id));
			return "edit";
		} else {
			session.removeAttribute("user");
			return "login";
		}
	}

	@RequestMapping("/public")
	public String doPublic(HttpSession session) throws IOException {

		logger.debug("/public");
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 1) {
			return "public";
		} else {
			session.removeAttribute("user");
			return "login";
		}
	}

	@RequestMapping("/publicSubmit")
	public String doPublicSubmit(Product product, ModelMap map, HttpSession session) throws IOException {

		User user = (User) session.getAttribute("user");
		logger.debug("/publicSubmit" + product.getTitle());
		if (user != null && user.getUsertype() == 1) {

			productService.publicProduct(product);
			if (product.getId() != 0) {
				map.addAttribute("product", product);
			} else {
				map.remove("product");
			}
			logger.debug("/publicSubmit generated Id=" + product.getId());
			return "publicSubmit";
		} else {
			session.removeAttribute("user");
			return "login";
		}
	}

	@RequestMapping("/editSubmit")
	public String doEditSubmitContent(Product product, ModelMap map, HttpSession session) throws IOException {

		logger.debug("/editSubmit id=" + product.getId());

		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsertype() == 1) {

			int record = productService.editProduct(product);
			if (record != 0) {
				map.addAttribute("product", product);
			} else {
				map.remove("product");
			}
			return "editSubmit";
		} else {
			session.removeAttribute("user");
			return "login";
		}

	}

}
