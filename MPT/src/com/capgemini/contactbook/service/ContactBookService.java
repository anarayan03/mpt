package com.capgemini.contactbook.service;

import java.io.IOException;
import java.sql.SQLException;

import com.igate.contactbook.bean.EnquiryBean;
import com.igate.contactbook.exception.ContactBookException;

public interface ContactBookService
{
	public int addEnquiry(EnquiryBean enquiry) throws ContactBookException, ClassNotFoundException, IOException, SQLException;
	public EnquiryBean getEnquiryDetails(int EnquiryID) throws ContactBookException, ClassNotFoundException, IOException, SQLException;
	public boolean isValidEnquiry(EnquiryBean enqry) throws ContactBookException;
}
