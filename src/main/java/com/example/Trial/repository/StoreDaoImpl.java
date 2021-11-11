package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.Category;

@Repository
public class StoreDaoImpl implements StoreDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Category> categoryRowMapper = new RowMapper<Category>() {
        @Override
        public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        	Category category = new Category();
            category.setCategoryID(resultSet.getInt("categoryID"));
            category.setCategoryname(resultSet.getString("categoryname"));
            category.setRoomno(resultSet.getInt("roomno"));
            
            return category;
        }
    };
	
	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		
		String sql = "select * from category";
		
		List<Category> allCategories = jdbcTemplate.query(sql,categoryRowMapper);
		
		return allCategories;
	}

	@Override
	public String getCategoryNamefromID(int categoryID) {
		// TODO Auto-generated method stub
		String sql = "select categoryname from category where categoryID = ?";
		Object[] args = {categoryID};
		String categorytype =  jdbcTemplate.queryForObject(sql, String.class,args);
		return categorytype;
	}

}
