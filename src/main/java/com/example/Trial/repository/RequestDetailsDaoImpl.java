package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.Trial.model.RequestDetails;

@Repository
public class RequestDetailsDaoImpl implements RequestDetailsDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<RequestDetails> requestDetailsRowMapper = new RowMapper<RequestDetails>() {
		@Override
		public RequestDetails mapRow(ResultSet resultSet, int i) throws SQLException{
			RequestDetails rqdetails = new RequestDetails();
			rqdetails.setProductID(resultSet.getInt("productID"));
			rqdetails.setRequestDetailsID(resultSet.getInt("requestdetailsID"));
			rqdetails.setRequestID(resultSet.getInt("requestID"));
			rqdetails.setQuantity(resultSet.getInt("quantity"));
			rqdetails.setProductprice(resultSet.getInt("productprice"));
			return rqdetails;
		}
	};
	
	
	@Override
	public int addRequestDetail(RequestDetails rqdetail) {
		// TODO Auto-generated method stub
		String sql = "Insert into requestdetails(requestID,productID,quantity,productprice) values(?,?,?,?)";
		Object[] args = {rqdetail.getRequestID(),rqdetail.getProductID(),rqdetail.getQuantity(),rqdetail.getProductprice()};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public List<RequestDetails> findAllDetailswithRequestID(int requestID) {
		// TODO Auto-generated method stub
		String sql = "select * from requestdetails where requestID = ?";
		Object[] args = {requestID};
		
		List<RequestDetails> rqdetails = jdbcTemplate.query(sql, requestDetailsRowMapper,args);
		
		return rqdetails;
	}

	@Override
	public int deleteRequestDetail(RequestDetails rqdetail) {
		// TODO Auto-generated method stub
		String sql = "Delete from requestdetails where requestdetailsID = ?";
		Object[] args = {rqdetail.getRequestDetailsID()};
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public int deleteAllDetailswithRequestID(int currentrequestID) {
		// TODO Auto-generated method stub
		
		String sql = "delete from requestdetails where requestID = ?";
		Object[] args = {currentrequestID};
		
		int cor = jdbcTemplate.update(sql,args);
		
		return cor;
	}

}
