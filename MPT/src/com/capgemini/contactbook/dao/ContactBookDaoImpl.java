package com.capgemini.contactbook.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.igate.contactbook.bean.EnquiryBean;
import com.igate.contactbook.exception.ContactBookException;
import com.igate.contactbook.util.DbConnection;

public class ContactBookDaoImpl implements ContactBookDao{

	@Override
	public int addEnquiry(EnquiryBean enquiry) throws ContactBookException, ClassNotFoundException, IOException, SQLException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		int enquiryId = 0;
		
		try
		{
			
			preparedStatement = connection.prepareStatement(QueryMapper.INSERT_QUERY);
			
			preparedStatement.setString(1, enquiry.getfName());
			preparedStatement.setString(2, enquiry.getlName());
			preparedStatement.setString(3, enquiry.getContactNo());
			preparedStatement.setString(4, enquiry.getpDomain());
			preparedStatement.setString(5, enquiry.getpLocation());
			
			
			preparedStatement.executeUpdate();
			
			resultSet = statement.executeQuery(QueryMapper.EXECUTE_QUERY);
			
			while(resultSet.next())
			{

				enquiryId = resultSet.getInt(1);
								
			}
			return enquiryId;
			
		}catch (Exception e)
		{
			
			System.out.println(e);
		}
		
		return enquiryId;
		
	}

	@Override
	public EnquiryBean getEnquiryDetails(int EnquiryID) throws ContactBookException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DbConnection.getConnection();
		EnquiryBean enquiryBean = new EnquiryBean();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		preparedStatement = connection.prepareStatement(QueryMapper.EXECUTE_QUERY_WITH_CONDITI);
		preparedStatement.setInt(1, EnquiryID);
		resultSet = preparedStatement.executeQuery();
		try
		{
		while(resultSet.next())
		{
			
			enquiryBean.setEnqryId(resultSet.getInt(1));
			enquiryBean.setfName(resultSet.getString(2));
			enquiryBean.setlName(resultSet.getString(3));
			enquiryBean.setContactNo(resultSet.getString(4));
			enquiryBean.setpDomain(resultSet.getString(5));
			enquiryBean.setpLocation(resultSet.getString(6));
			
		}
		}catch (Exception e)
		{
			
			System.out.println(e);
		}
		
		return enquiryBean;
	}

}
