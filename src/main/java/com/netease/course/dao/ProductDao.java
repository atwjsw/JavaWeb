package com.netease.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.netease.course.meta.Product;

/**
 * @author: atwjsw
 * @Description: Data access object for content product related database
 *               operation.
 * @Date: Apr 20, 2016 3:20:10 PM
 */
public interface ProductDao {

	@Select("select content.id as id, content.price as price, trx.price as buyPrice, title, icon as image, abstract as summary, text as detail, personId, time as buyTime, if(personId=1,1,0) as isBuy, if(personId=1,1,0) as isSell from content left join trx on content.id=trx.contentId order by id asc")
	public List<Product> getProductList();

	@Select("select content.id as id, content.price as price, trx.price as buyPrice, title, icon as image, abstract as summary, text as detail, personId, time as buyTime, if(personId=1,1,0) as isBuy, if(personId=1,1,0) as isSell from content left join trx on content.id=trx.contentId where personId=1 order by buytime asc")
	public List<Product> getBuyListOrderByBuytime();

	@Select("select content.id as id, content.price as price, trx.price as buyPrice, title, icon as image, abstract as summary, text as detail, personId, time as buyTime, if(personId=1,1,0) as isBuy, if(personId=1,1,0) as isSell from content left join trx on content.id=trx.contentId where content.id=#{id}")
	public Product getProduct(int id);

	@Insert("insert into trx (contentId, personId, price, time)  values (#{id}, #{personId}, #{buyPrice}, #{buyTime})")
	public int insertTrx(@Param("id") int id, @Param("personId") int personId, @Param("buyPrice") double buyPrice,
			@Param("buyTime") long buyTime);

	@Delete("delete from content where id=#{id}")
	public int deleteProduct(int id);

	@Insert("insert into content (title, abstract, text, price, icon) values(#{title}, #{summary}, #{detail}, #{price}, #{image})")
	@SelectKey(statement = "SELECT max(id) from content", keyProperty = "id", before = false, resultType = int.class)
	public int insertProduct(Product product);

	@Update("Update content set title=#{title}, abstract=#{summary}, text=#{detail}, price=#{price}, icon=#{image} where id=#{id}")
	public int updateProduct(Product product);

}
