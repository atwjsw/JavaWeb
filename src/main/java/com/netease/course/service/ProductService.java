package com.netease.course.service;

import java.util.List;

import com.netease.course.meta.Product;

/**
 * @author: atwjsw
 * @Description: Service interface for content product related service
 * @Date: Apr 20, 2016 3:34:41 PM
 */
public interface ProductService {

	public List<Product> getProductList();

	public Product getProduct(int id);

	public int buyProduct(int id, int personId, double price, long buyTime);

	public int deleteProduct(int id);

	public int publicProduct(Product product);

	public int editProduct(Product product);

	public List<Product>  getBuyListOrderByBuytime();

}
