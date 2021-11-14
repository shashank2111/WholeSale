package com.example.Trial.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Category;
import com.example.Trial.model.OrderDetailsView;
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
            product.setAlertstock(resultSet.getInt("alertstock"));
            product.setStock(resultSet.getInt("stock"));
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


	@Override
	public Product getProductfromProductID(int productID) {
		// TODO Auto-generated method stub
		String sql = "select * from product where productID = ?";
		Object[] args = {productID};
		Product product = jdbcTemplate.queryForObject(sql, productRowMapper,args);
		return product;
	}


	@Override
	public int deleteProductwithProductID(int productID) {
		// TODO Auto-generated method stub
		String sql = "delete from product where productID = ?";
		Object[] args = {productID};
		int countOfRecord = jdbcTemplate.update(sql,args);
		return countOfRecord;
	}


	@Override
	public int updateProduct(Product product) {
		// TODO Auto-generated method stub
		String sql = "update product set productname = ?,categoryID = ?,costprice =? , sellprice = ?, description = ? where productID = ?";
		Object[] args = {product.getProductname(),product.getCategoryID(),product.getCostprice(),product.getSellprice(),product.getDesc(),product.getProductID()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		return countOfRecord;
	}


	@Override
	public int updateQuantityProduct(List<OrderDetailsView> allOrderDetailsViews) {
		// TODO Auto-generated method stub
		String sql = "update product set quantity = quantity - ? where productID = ?";
	
		int[] cR = jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				// TODO Auto-generated method stub
				OrderDetailsView odview = allOrderDetailsViews.get(i);
				ps.setInt(1, odview.getQuantity());
				ps.setInt(2, odview.getProductID());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return allOrderDetailsViews.size();
			}
		});
		
		
		
		return 1;
	}


	@Override
	public List<Product> getAllAlertProducts() {
		// TODO Auto-generated method stub
		String sql = "select * from product where stock <= alertstock";
		
		List<Product> allAlertProducts = jdbcTemplate.query(sql, productRowMapper);
		return allAlertProducts;
	}

}
