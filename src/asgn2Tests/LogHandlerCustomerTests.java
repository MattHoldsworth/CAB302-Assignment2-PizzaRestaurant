package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Matthew Holdsworth
 */
public class LogHandlerCustomerTests {
	String log;
	String name;
	String mobileNumber;
	String xcoord;
	String ycoord;
	String customerCode;
	String leftPizzaDetails;
	String rightPizzaDetails;
	int locationX;
	int locationY;
	
	Customer customer;
	Customer driverDelivery;
	
	@Before
	public void setUp() throws CustomerException {
		name = "Casey Jones";
		mobileNumber = "0123456789";
		xcoord = "5";
		ycoord = "5";
		customerCode = "DVC";
		leftPizzaDetails = "19:00:00,19:20:00";
		rightPizzaDetails = "PZV,2";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		locationX = Integer.parseInt(xcoord);
		locationY = Integer.parseInt(ycoord);
		driverDelivery = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);	
	}//end SetUp
	
	@Test
	public void createCustomerString() throws CustomerException, LogHandlerException {
		customer = LogHandler.createCustomer(log);
		assertEquals(true, customer.equals(driverDelivery));
	}
	
	//Log HandlerExceptionTests
	@Test (expected = LogHandlerException.class)
	public void noInput() throws CustomerException, LogHandlerException {
		log = "";
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void noCommas() throws CustomerException, LogHandlerException {
		log = leftPizzaDetails+name+mobileNumber+customerCode+
				xcoord+ycoord+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingName() throws CustomerException, LogHandlerException {
		log = leftPizzaDetails+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingMobileNumber() throws CustomerException, LogHandlerException {
		log = leftPizzaDetails+','+name+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingLocationX() throws CustomerException, LogHandlerException {
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				locationY+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingLocationY() throws CustomerException, LogHandlerException {
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				locationX+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericLocationX() throws CustomerException, LogHandlerException {
		xcoord = "a";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericLocationY() throws CustomerException, LogHandlerException {
		ycoord = "a";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void decimalLocationX() throws CustomerException, LogHandlerException {
		xcoord = "5.55";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void decimalLocationY() throws CustomerException, LogHandlerException {
		ycoord = "5.55";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void outOfRangeLocationX() throws CustomerException, LogHandlerException {
		xcoord = "15";
		ycoord = "5";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void outOfRangeLocationY() throws CustomerException, LogHandlerException {
		xcoord = "5";
		ycoord = "15";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void nonNumericName() throws CustomerException, LogHandlerException {
		name = "123";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void tooManyLettersInName() throws CustomerException, LogHandlerException {
		name = "Over Twentycharacters";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void nonNumericCustomerCode() throws CustomerException, LogHandlerException {
		customerCode = "123";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void incorrectCustomerCode() throws CustomerException, LogHandlerException {
		customerCode = "DVM";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void tooManyLettersInCode() throws CustomerException, LogHandlerException {
		customerCode = "DVCC";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void notEnoughLettersInCode() throws CustomerException, LogHandlerException {
		customerCode = "DV";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void notMobileStartingWithZero() throws CustomerException, LogHandlerException {
		mobileNumber = "1234567890";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void notEnoughMobileNumbers() throws CustomerException, LogHandlerException {
		mobileNumber = "012345678";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void tooManyMobileNumbers() throws CustomerException, LogHandlerException {
		mobileNumber = "01234567890";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	@Test (expected = CustomerException.class)
	public void nonNumericMobileNumber() throws CustomerException, LogHandlerException {
		mobileNumber = "012345678o";
		log = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(log);
	}
	
	//Throw an error if the file does not exist.
	@Test (expected = LogHandlerException.class)
	public void fileNotFound() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\NonExistentFileName");
	}
		
	//Throw an error if there's an empty string in the log file.
	@Test (expected = LogHandlerException.class)
	public void logFileWithEmptyString() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithEmptyString");
	}
		
	//Throw an error if there's a parameter missing in a string
	@Test (expected = LogHandlerException.class)
	public void logFileWithaMissingParameter() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithMissingParam");
	}
		
	//Throw an error if there's string without commas
	@Test (expected = LogHandlerException.class)
	public void logFileWithoutCommas() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithoutCommas");
	}
	
	//Throw an error if there's a semantic error in the file
	@Test(expected = CustomerException.class)
	public void logFileWithInvalidCustomerName() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithInvalidCustomerName");
	}
	
	@Test(expected = CustomerException.class)
	public void logFileWithInvalidMobileNumber() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithInvalidMobileNumber");
	}
	
	@Test(expected = CustomerException.class)
	public void logFileWithInvalidLocationX() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithInvalidLocationX");
	}
	
	@Test(expected = CustomerException.class)
	public void logFileWithInvalidLocationY() throws LogHandlerException, CustomerException{
		LogHandler.populateCustomerDataset(".\\logs\\logWithInvalidLocationY");
	}
	
}//end LogHandlerCustomerTests
