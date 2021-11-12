package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Category;
import com.example.Trial.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            Product product = new Product();
            product.setProductID(resultSet.getInt("productID"));
            product.setProductname(resultSet.getString("productname"));
            product.setDesc(resultSet.getString("description"));
            product.setCostprice(resultSet.getInt("costprice"));
            product.setSellprice(resultSet.getInt("sellprice"));
            product.setBrand(resultSet.getString("brand"));
            product.setCategoryID(resultSet.getInt("categoryID"));
            return product;
        }
    };
	
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		
		
		String sql = "Select * from product";
		List<Product> allProducts = jdbcTemplate.query(sql, productRowMapper);
		
		
		return allProducts; 
	}


	@Override
	public List<Product> getAllProductswithCategoryID(int categoryID) {
		// TODO Auto-generated method stub
		String sql = "select * from product where categoryID = ?";
		Object[] args = {categoryID};
		
	 	List<Product> allProducts =  jdbcTemplate.query(sql, productRowMapper,args);
		return allProducts;
	}


	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		String sql = "Insert into product(productID,productname,description,costprice,sellprice,brand,categoryID) Values(?,?,?,?,?,?,?)";
		Object[] args = {product.getProductID(),product.getProductname(),product.getDesc(),product.getCostprice(),product.getSellprice(),product.getBrand(),product.getCategoryID()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return product.getProductID();
	}


	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		String sql = "Insert into category (categoryname,roomno) values(?,?)";
		Object[] args = {category.getCategoryname(),category.getRoomno()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}


	@Override
	public List<Product> findAllProductsbyProduct(Product product) {
		// TODO Auto-generated method stub
		String sql ="select * from product where productname = ? or productId = ?";
		Object[] args = {product.getProductname(),product.getProductID()};
		
		List<Product> allProducts = jdbcTemplate.query(sql,productRowMapper,args);
		return allProducts;
	}

}
