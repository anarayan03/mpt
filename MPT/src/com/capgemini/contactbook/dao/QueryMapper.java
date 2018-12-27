package com.capgemini.contactbook.dao;

public interface QueryMapper
{
	public static String INSERT_QUERY = "insert into enquiry values(enquiries.nextval,?,?,?,?,?)";
	public static String EXECUTE_QUERY = "Select * from enquiry order by enqryId";
	public static String EXECUTE_QUERY_WITH_CONDITI = "select * from enquiry where enqryId = ?";
}
