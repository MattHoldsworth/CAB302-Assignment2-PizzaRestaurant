package asgn2Tests;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Matthew Holdsworth
 * 
 *
 */
public class CustomerTests {
	final static int MIN_NAME_LENGTH = 1;
	final static int MAX_NAME_LENGTH = 20;
	final static int MOBILE_NUM_LENGTH = 10;
	final static int MIN_LOCATION_X = -10;
	final static int MAX_LOCATION_X = 10;
	final static int MAX_LOCATION_Y = 10;
	final static int MIN_LOCATION_Y = -10;
	final static int RESTAURANT_X = 0;
	final static int RESTAURANT_Y = 0;
	
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	
	DriverDeliveryCustomer driverDeliveryCustomer;
	DroneDeliveryCustomer droneDeliveryCustomer;
	PickUpCustomer pickUpCustomer;
	
	@Before @Test
	public void setUp() throws CustomerException {
		name = "Apple";
		mobileNumber = "0123456789";
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		locationX = 5;
		locationY = 5;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	/*
	 For: driverNameMaximum, droneNameMaximum, pickUpNamerMaximum
	 Should update the name variable to match length of MAX_NAME_LENGTH. 
	 The setup method initializes the name variable to three characters.
	 */
	@Test
	public void driverNameMaximum() throws CustomerException {
		name = "Twentycharactersssss";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	@Test
	public void droneNameMaximum() throws CustomerException {
		name = "Twentycharactersssss";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	@Test
	public void pickUpNameMaximum() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		name = "Twentycharactersssss";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, pickUpCustomer.getName().length());
	}
	
	@Test
	public void driverNameMinimum() throws CustomerException {
		name = "T";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	//fail
	@Test
	public void droneNameMinimum() throws CustomerException {
		name = "T";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	//fail
	@Test
	public void pickUpNameMinimum() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		name = "T";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, pickUpCustomer.getName().length());
	}
	
	
	//Border value tests for name
	@Test (expected = CustomerException.class)
	public void NoNameDriverCustomer() throws CustomerException {
		name = "";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NoNameDroneCustomer() throws CustomerException {
		name = "";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NoNamePickUpCustomer() throws CustomerException {
		name = "";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void whiteSpacesOnlyNameDriverCustomer() throws CustomerException {
		name = "       ";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void whiteSpacesOnlyNameDroneCustomer() throws CustomerException {
		name = "       ";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void whiteSpacesOnlyNamePickUpCustomer() throws CustomerException {
		name = "       ";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyLettersDriverCustomer() throws CustomerException {
		name = "aaaaaaaaaaaaaaaaaaaaa";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyLettersDroneCustomer() throws CustomerException {
		name = "aaaaaaaaaaaaaaaaaaaaa";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyLettersPickUpCustomer() throws CustomerException {
		name = "aaaaaaaaaaaaaaaaaaaaa";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}

	//Border value tests for mobile number
	@Test (expected = CustomerException.class)
	public void NotEnoughNumbersDriverCustomer() throws CustomerException {
		mobileNumber = "012345678";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NotEnoughNumbersDroneCustomer() throws CustomerException {
		mobileNumber = "012345678";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NotEnoughNumbersPickUpCustomer() throws CustomerException {
		mobileNumber = "012345678";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyNumbersDriverCustomer() throws CustomerException {
		mobileNumber = "01234567890";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyNumbersDroneCustomer() throws CustomerException {
		mobileNumber = "01234567890";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void TooManyNumbersPickUpCustomer() throws CustomerException {
		mobileNumber = "01234567890";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	//Tests for non-numeric characters in mobile number
	@Test (expected = CustomerException.class)
	public void NotNumbersDriverCustomer() throws CustomerException {
		mobileNumber = "ABCDEFGHIJ";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NotNumbersDroneCustomer() throws CustomerException {
		mobileNumber = "ABCDEFGHIJ";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void NotNumbersPickUpCustomer() throws CustomerException {
		mobileNumber = "ABCDEFGHIJ";
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	//Tests for mobile number length
	@Test
	public void driverMobileNumberLengthStored() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, driverDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void droneMobileNumberLengthStored() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, droneDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void pickUpMobileNumberLengthStored() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, pickUpCustomer.getMobileNumber().length());
	}
	
	//Border value tests for locationX
	@Test
	public void driverLocationXMinimum() throws CustomerException {
		locationX = MIN_LOCATION_X;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneLocationXMinimum() throws CustomerException {
		locationX = MIN_LOCATION_X;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_X, droneDeliveryCustomer.getLocationX());
	}

	/*
	 Pick up customer's location should always be 0, 0
	 */
	@Test
	public void pickUpLocationXMinimum() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_X, pickUpCustomer.getLocationX());
	}
	
	@Test
	public void driverLocationXMaximum() throws CustomerException {
		locationX = MAX_LOCATION_X;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneLocationXMaximum() throws CustomerException {
		locationX = MAX_LOCATION_X;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, droneDeliveryCustomer.getLocationX());
	}

	@Test
	public void pickUpLocationXMaximum() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_X, pickUpCustomer.getLocationX());
	}
	
	//Border value tests for locationY
	@Test
	public void driverLocationYMinimum() throws CustomerException {
		locationY = MIN_LOCATION_Y;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMinimum() throws CustomerException {
		locationY = MIN_LOCATION_Y;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void pickUpLocationYMinimum() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_Y, pickUpCustomer.getLocationY());
	}
	
	@Test
	public void driverLocationYMaximum() throws CustomerException {
		locationY = MAX_LOCATION_Y;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMaximum() throws CustomerException {
		locationY = MAX_LOCATION_Y;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	//Tests for valid customer types
	@Test
	public void driverDeliveryCustomerType() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Driver Delivery", driverDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void droneDeliveryCustomerType() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Drone Delivery", droneDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void pickUpCustomerType() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Pick Up", pickUpCustomer.getCustomerType());
	}
	
	//Tests for out of range deliveries
	@Test (expected = CustomerException.class)
	public void driverDeliveryLocationXOutOfRange() throws CustomerException {
		locationX = MAX_LOCATION_X + 1;
		locationY = 5;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void droneDeliveryLocationXOutOfRange() throws CustomerException {
		locationX = MAX_LOCATION_X + 1;
		locationY = 5;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void driverDeliveryLocationYOutOfRange() throws CustomerException {
		locationX = 5;
		locationY = MAX_LOCATION_Y + 1;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void droneDeliveryLocationYOutOfRange() throws CustomerException {
		locationX = 5;
		locationY = MAX_LOCATION_Y + 1;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
	}

	@Test
	public void pickUpDeliveryDistanceZero() throws CustomerException {
		assertEquals (0, pickUpCustomer.getDeliveryDistance(), 0);
	}
	
	//A test to make sure that multiple instances do not share the same variables
	@Test
	public void classIndependenceTest() throws CustomerException{
		String name2 = "Banana";
		String mobileNumber2 = "0987654321";
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		PickUpCustomer pickUpCustomer2 = new PickUpCustomer(name2, mobileNumber2, locationX, locationY);
		locationX = 8;
		locationY = 8;
		DriverDeliveryCustomer driverDeliveryCustomer2 = new DriverDeliveryCustomer(name2, mobileNumber2, locationX, locationY);
		DroneDeliveryCustomer droneDeliveryCustomer2 = new DroneDeliveryCustomer(name2, mobileNumber2, locationX, locationY);
		
		//Make sure names are different
		assertEquals(name2, pickUpCustomer2.getName());
		assertEquals(name, pickUpCustomer.getName());
		assertEquals(name2, driverDeliveryCustomer2.getName());
		assertEquals(name, driverDeliveryCustomer.getName());
		assertEquals(name2, droneDeliveryCustomer2.getName());
		assertEquals(name, droneDeliveryCustomer.getName());
		
		//Make sure mobile numbers are different
		assertEquals(mobileNumber2, pickUpCustomer2.getMobileNumber());
		assertEquals(mobileNumber, pickUpCustomer.getMobileNumber());
		assertEquals(mobileNumber2, driverDeliveryCustomer2.getMobileNumber());
		assertEquals(mobileNumber, driverDeliveryCustomer.getMobileNumber());
		assertEquals(mobileNumber2, droneDeliveryCustomer2.getMobileNumber());
		assertEquals(mobileNumber, droneDeliveryCustomer.getMobileNumber());
	}
}
