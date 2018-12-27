package com.capgemini.contactbook.ui;

import java.util.Scanner;

import com.capgemini.contactbook.service.ContactBookService;
import com.capgemini.contactbook.service.ContactBookServiceImpl;
import com.igate.contactbook.bean.EnquiryBean;
import com.igate.contactbook.exception.ContactBookException;

public class Client
{
	
	static Scanner scanner = new Scanner(System.in);
	static ContactBookService contactBookService = null;
	static ContactBookServiceImpl contactBookServiceImpl = null;
	
	public static void main(String[] args) 
	{
		
		EnquiryBean enquiryBean = null;
		int enqryId = 0;
		String fName = null;
		String lName = null;
		int option = 0;
		
		while(true)
		{
			System.out.println();
			System.out.println();
			System.out.println("***********Global Recruitments*********");
			System.out.println("\nChoose an operation ");
			System.out.println("1. Enter Enquiry Details ");
			System.out.println("2. View Enquiry Details on Id");
			System.out.println("0. Exit");
			System.out.println("*****************************************");
			System.out.println("\nPlease enter a choice");
			System.out.println("\n*****************************************");
			
			try
			{
				
				option = scanner.nextInt();
				
				switch (option)
				{
				case 1 :
					
					while(enquiryBean==null)
					{
						enquiryBean = populateEnquiryBean();
					}
					
					try
					{
		
						contactBookServiceImpl = new ContactBookServiceImpl();
						enqryId = contactBookServiceImpl.addEnquiry(enquiryBean);
						System.out.println("Thank you"+enquiryBean.getfName()+" "+enquiryBean.getlName()+" your Unique Id is "+ enqryId+ " we will contact you shortly.");
					}catch (ContactBookException e) {
						System.out.println("im here");
						System.out.println(e);
					}
					finally 
					{
						enqryId = 0;
						contactBookServiceImpl = null;
						enquiryBean = null;
					}
					
					break;

				case 2 :
					
					System.out.println("Enter the Enquiry No. :");
					enqryId = scanner.nextInt();
					
					try
					{
						
						
						enquiryBean = new EnquiryBean();
						contactBookService = new ContactBookServiceImpl();
						contactBookServiceImpl = new ContactBookServiceImpl();
						
						if(contactBookServiceImpl.isValidEnquiryId(enqryId))
						{
							enquiryBean = contactBookService.getEnquiryDetails(enqryId);
							if(!(enquiryBean.getfName()==null))
							{
								//logger.info("Book found:");
								System.out.println(enquiryBean);
							}
							else
								//logger.info("Book not found");
								System.out.println("Sorry no details found!! ");
						}
						else
						{
							System.out.println("Enquiry Id is not Valid");
						}
					}catch (ContactBookException e) {
						
						System.out.println(e);
					}
					
					break;
					
				case 0 :
					
					System.out.println("Thank you selecting us!!");
					System.exit(0);
					
				default :
					
					System.out.println("Wrong choice");
					System.exit(0);
				}
				
			}catch (Exception e)
			{
				System.out.println(e);
			}
		}
		
	}

	private static EnquiryBean populateEnquiryBean()
	{
		
		EnquiryBean enquiryBean = new EnquiryBean();
		System.out.println("Enter Details :");
		
		System.out.println("Enter First Name :");
		enquiryBean.setfName(scanner.next());
		
		System.out.println("Enter Last Name :");
		enquiryBean.setlName(scanner.next());
		
		System.out.println("Enter Contact Number :");
		enquiryBean.setContactNo(scanner.next());
		
		System.out.println("Enter Preferred Domain :");
		enquiryBean.setpDomain(scanner.next());
		
		System.out.println("Enter Location :");
		enquiryBean.setpLocation(scanner.next());
		
		contactBookServiceImpl = new ContactBookServiceImpl();
		
		try
		{
			
			contactBookServiceImpl.isValidEnquiry(enquiryBean);
			return enquiryBean;
		}catch (ContactBookException e)
		{
			System.out.println("Invalid Data");
			System.err.println(e);
			System.exit(0);
		}
		
		return null;
	}
}
