package com.capgemini.contactbook.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.contactbook.dao.ContactBookDao;
import com.capgemini.contactbook.dao.ContactBookDaoImpl;
import com.igate.contactbook.bean.EnquiryBean;
import com.igate.contactbook.exception.ContactBookException;

public class ContactBookServiceImpl implements ContactBookService {

	ContactBookDao contactBookDao = new ContactBookDaoImpl();
	
	@Override
	public int addEnquiry(EnquiryBean enquiry) throws ContactBookException, ClassNotFoundException, IOException, SQLException {
		
		int enquryIdSeq;
		enquryIdSeq = contactBookDao.addEnquiry(enquiry);
		
		return enquryIdSeq;
	}

	@Override
	public EnquiryBean getEnquiryDetails(int EnquiryID) throws ContactBookException, ClassNotFoundException, IOException, SQLException 
	{
		EnquiryBean enquiryBean = contactBookDao.getEnquiryDetails(EnquiryID);
		return enquiryBean;
	}

	@Override
	public boolean isValidEnquiry(EnquiryBean enqry) throws ContactBookException
	{
		
		
		List <String> validationError  = new ArrayList<String>();
		
		if(!(isValidContactNo(enqry.getContactNo())))
		{
			validationError.add("\n Mobile Number should be of 10 digits ");
		}
		
		if(!(isValidFirstName(enqry.getfName())))
		{
			validationError.add("\n First Name should be alphabet only ");
		}
		
		if(!(isValidLastName(enqry.getlName())))
		{
			validationError.add("\n Last Name should be alphabet only ");
		}
		
		if(!(isValidPLocation(enqry.getpLocation())))
		{
			validationError.add("\n Location name should be alphabet only ");
		}
		
		if(!(isValidPDomain(enqry.getpDomain())))
		{
			validationError.add("\n Domain name should be alphabet only ");
		}
		
		if(!(validationError.isEmpty()))
		{
			throw new ContactBookException(validationError+" ");
		}
		
		return true;
	
	}
	private boolean isValidPDomain(String getpDomain) 
	{
		Pattern apattern = Pattern.compile("^[A-Za-z]{2,}$");
		Matcher amatcher = apattern.matcher(getpDomain);
		return amatcher.matches();
	}

	private boolean isValidPLocation(String getpLocation)
	{
		Pattern apattern = Pattern.compile("^[A-Za-z]{2,}$");
		Matcher amatcher = apattern.matcher(getpLocation);
		return amatcher.matches();
	}

	private boolean isValidLastName(String getlName) {
		Pattern apattern = Pattern.compile("^[A-Za-z]{2,}$");
		Matcher amatcher = apattern.matcher(getlName);
		return amatcher.matches();
	}

	private boolean isValidFirstName(String getfName) {
		Pattern apattern = Pattern.compile("^[A-Za-z]{2,}$");
		Matcher amatcher = apattern.matcher(getfName);
		return amatcher.matches();
	}

	private boolean isValidContactNo(String contactNo) {
		Pattern apattern = Pattern.compile("^[6-9][0-9]{9}");
		Matcher amatcher = apattern.matcher(contactNo);
		return amatcher.matches();
	}

	public boolean isValidEnquiryId(int enqryId)
	{
		if(enqryId >=1001 && enqryId <=9999)
		{
			return true;
		}
		else
			return false;
	}
	
}
