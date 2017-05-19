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
	String sampleLog;
	
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
		
	}
	
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
		sampleLog = "leftPizzaDetails + name + mobileNumber + locationX + locationY + rightPizzaDetails";
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingName() throws CustomerException, LogHandlerException {
		sampleLog = leftPizzaDetails+','+mobileNumber+','+customerCode+','+
				locationX+','+locationY+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingMobileNumber() throws CustomerException, LogHandlerException {
		sampleLog = leftPizzaDetails+','+name+','+customerCode+','+
				locationX+','+locationY+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingLocationX() throws CustomerException, LogHandlerException {
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				locationY+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void missingLocationY() throws CustomerException, LogHandlerException {
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				locationX+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericLocationX() throws CustomerException, LogHandlerException {
		xcoord = "a";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericLocationY() throws CustomerException, LogHandlerException {
		ycoord = "a";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void decimalLocationX() throws CustomerException, LogHandlerException {
		xcoord = "5.55";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void decimalLocationY() throws CustomerException, LogHandlerException {
		ycoord = "5.55";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void outOfRangeLocationX() throws CustomerException, LogHandlerException {
		xcoord = "15";
		ycoord = "5";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void outOfRangeLocationY() throws CustomerException, LogHandlerException {
		xcoord = "5";
		ycoord = "15";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericName() throws CustomerException, LogHandlerException {
		name = "123";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void tooManyLettersInName() throws CustomerException, LogHandlerException {
		name = "Over Twentycharacters";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericCustomerCode() throws CustomerException, LogHandlerException {
		customerCode = "123";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void incorrectCustomerCode() throws CustomerException, LogHandlerException {
		customerCode = "DVM";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void tooManyLettersInCode() throws CustomerException, LogHandlerException {
		customerCode = "DVCC";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void notEnoughLettersInCode() throws CustomerException, LogHandlerException {
		customerCode = "DV";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void notMobileStartingWithZero() throws CustomerException, LogHandlerException {
		mobileNumber = "1234567890";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void notEnoughMobileNumbers() throws CustomerException, LogHandlerException {
		mobileNumber = "012345678";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void tooManyMobileNumbers() throws CustomerException, LogHandlerException {
		mobileNumber = "01234567890";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericMobileNumber() throws CustomerException, LogHandlerException {
		mobileNumber = "012345678o";
		sampleLog = leftPizzaDetails+','+name+','+mobileNumber+','+customerCode+','+
				xcoord+','+ycoord+','+rightPizzaDetails;
		LogHandler.createCustomer(sampleLog);
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
}
