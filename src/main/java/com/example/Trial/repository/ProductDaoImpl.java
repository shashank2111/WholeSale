package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

}
