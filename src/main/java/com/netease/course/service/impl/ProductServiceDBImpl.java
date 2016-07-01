package com.netease.course.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netease.course.dao.ProductDao;
import com.netease.course.meta.Product;
import com.netease.course.service.ProductService;

/**
 * @author: atwjsw
 * @Description: Service implementation for Product service interface. 
 * @Date: Apr 20, 2016 3:38:39 PM
 */
@Service
public class ProductServiceDBImpl implements ProductService {

	private Logger logger = Logger.getLogger(ProductServiceDBImpl.class);

	@Autowired
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> getProductList() {
		logger.debug("In ProductServiceDBImpl");
		List<Product> productList = null;
		try {
			productList = productDao.getProductList();
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return productList;
	}

	public Product getProduct(int id) {
		logger.debug("In ProductServiceDBImpl.getProduct");
		Product product = productDao.getProduct(id);
		logger.debug("In ProductServiceDBImpl.getProduct " + product.getDetail());
		return product;
	}

	public int buyProduct(int id, int personId, double price, long buyTime) {
		logger.debug("In ProductServiceDBImpl.buyProduct");
		return productDao.insertTrx(id, personId, price, buyTime);
	}

	public int deleteProduct(int id) {
		logger.debug("In ProductServiceDBImpl.deleteProduct");
		int recordCount = 0;
		try {
			recordCount = productDao.deleteProduct(id);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		return recordCount;
	}

	public int publicProduct(Product product) {
		logger.debug("In ProductServiceDBImpl.publicProduct " + product.getDetail());
		int record = 0;
		try {
			record = productDao.insertProduct(product);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		logger.debug("In ProductServiceDBImpl.publicProduct. New product id is " + product.getId());
		logger.debug("In ProductServiceDBImpl.publicProduct. record affected is " + record);
		return record;
	}

	public int editProduct(Product product) {

		logger.debug("In ProductServiceDBImpl.editProduct");

		int record = 0;
		try {
			record = productDao.updateProduct(product);
		} catch (Throwable e) {
			logger.error(e.getMessage());
		}
		logger.debug("In ProductServiceDBImpl.editProduct. record affected is " + record);
		return record;
	}

	public List<Product> getBuyListOrderByBuytime() {
		logger.debug("In ProductServiceDBImpl.getBuyListOrderByBuytime");
		return productDao.getBuyListOrderByBuytime();
	}
}
