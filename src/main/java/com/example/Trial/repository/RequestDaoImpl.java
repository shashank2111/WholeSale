package com.example.Trial.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.Trial.model.Request;

@Repository
public class RequestDaoImpl implements RequestDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<Request> requestRowMapper = new RowMapper<Request>() { 
        @Override
        public Request mapRow(ResultSet resultSet, int i) throws SQLException {
        	Request request = new Request();
        	request.setRequestID(resultSet.getInt("requestID"));
        	request.setRequestdate(resultSet.getString("requestdate"));
        	request.setIsinprocess(resultSet.getBoolean("isinprocess"));
        	request.setIsfulfilled(resultSet.getBoolean("isfulfilled"));
        	request.setEmployeeID(resultSet.getInt("employeeID"));
        	request.setAgentID(resultSet.getInt("agentID"));
        	request.setTotalamount(resultSet.getInt("totalamount"));
            return request;
        }
    };
	
    @Override
    @Transactional
	public int getCurrentRequestID(int agentID) {
		// TODO Auto-generated method stub
    	System.out.println("here");
		String sql = "select count(*) from request";	
    	int count = jdbcTemplate.queryForObject(sql,Integer.class);
    	System.out.println("count = " + Integer.toString(count));
    	if(count == 0) {
    		
    		String sql1 = "Insert into request(requestId,requestdate,isinprocess,isfulfilled,agentID) values (?,?,?,?,?)";
    		Date date = Calendar.getInstance().getTime();  
    		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
    		String strDate = dateFormat.format(date);
    		Object[] args = {1,strDate,false,false,agentID};
    		jdbcTemplate.update(sql1,args);
    		return 1;
    	}
		sql = "select * from request where requestID = (select MAX(requestID) from request)";
		
		Request rq = jdbcTemplate.queryForObject(sql, requestRowMapper);
		int maxId = rq.getRequestID();
		
		
		String sql1 = "Insert into request(requestId,requestdate,isinprocess,isfulfilled,agentID) values(?,?,?,?,?)";
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		String strDate = dateFormat.format(date);
		Object[] args = {maxId+1,strDate,false,false,agentID};
		jdbcTemplate.update(sql1,args);
		
		return maxId + 1;
	}

	@Override
	public int deleteRequest(int currentrequestID) {
		// TODO Auto-generated method stub
		
		String sql = "delete from request where requestID = ?";
		Object[] args = {currentrequestID};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public List<Request> getPendingRequeststome(int agentID) {
		// TODO Auto-generated method stub
		String sql ="Select * from request where agentID = ? and isinprocess = ?";
		Object[] args = {agentID,false};
		
		List<Request> PendingRequeststome = jdbcTemplate.query(sql,requestRowMapper,args);
		
		
		return PendingRequeststome;
	}

	@Override
	public List<Request> getunFulfilledRequestsbyme(int agentID) {
		// TODO Auto-generated method stub
		String sql ="select * from request where agentID = ? and  isinprocess = ? and isfulfilled = ?";
		Object[] args = {agentID, true , false};
		
		List<Request> unfulfilledRequestbyme = jdbcTemplate.query(sql,requestRowMapper,args);
		
		return unfulfilledRequestbyme;
	}

	@Override
	public int makeisinprocesstrue(int requestID) {
		// TODO Auto-generated method stub
		
		String sql ="update request set isinprocess = ? where requestID = ?";
		Object[] args = {true,requestID};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public List<Request> getAllRequestsisinprocessfalse() {
		// TODO Auto-generated method stub
		String sql ="select * from request where isinprocess = ?";
		Object[] args = {false};
		
		List<Request> requestsnotshipped = jdbcTemplate.query(sql,requestRowMapper,args);
		
		return requestsnotshipped;
	}

	@Override
	public List<Request> getAllRequestsisinprocesstrueisfulfilledfalse() {
		// TODO Auto-generated method stub
		String sql ="select * from request where isinprocess = ?  and isfulfilled = ?";
		Object[] args = {true,false};
		
		List<Request> requestsshippednotreceived = jdbcTemplate.query(sql, requestRowMapper,args);
		
		return requestsshippednotreceived;
	}

	@Override
	public int receiveImportsetisfulfilledtrue(int requestID) {
		// TODO Auto-generated method stub
		
		String sql="update request set isfulfilled = ? where requestId = ?";
		Object[] args = {true,requestID};
		
		int countOfRecord = jdbcTemplate.update(sql,args);
		
		return countOfRecord;
	}

	@Override
	public List<Request> getAllMyExports(int agentID) {
		// TODO Auto-generated method stub
		
		String sql = "select * from request where agentID = ?";
		Object[] args = {agentID};
		
		List<Request> allMyExports = jdbcTemplate.query(sql,requestRowMapper,args);
		
		return allMyExports;
	}

	@Override
	public List<Request> getAllImports() {
		// TODO Auto-generated method stub
		String sql = "select * from request";
		List<Request> allImports = jdbcTemplate.query(sql, requestRowMapper);
		return allImports;
	}


    

}
