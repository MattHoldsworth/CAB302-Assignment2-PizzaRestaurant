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
		name = "Twentycharactersssss";
		mobileNumber = "0123456789";
		locationX = 1;
		locationY = 1;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	/*
	 For: driverNameMaximum, droneNameMaximum, pickUpNamerMaximum
	 Should update the name variable to match length of MAX_NAME_LENGTH. 
	 The setup method initializes the name variable to three characters.
	 */
	@Test
	public void driverNameMaximum() throws CustomerException {
		locationX = 1;
		locationY = 1;
		name = "Twentycharactersssss";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	@Test
	public void droneNameMaximum() throws CustomerException {
		locationX = 1;
		locationY = 1;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	@Test
	public void pickUpNameMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, pickUpCustomer.getName().length());
	}
	
	/*
	 For: driverNameMinimum, droneNameMinimum, pickUpNameMinimum
	 Should update the name variable to match the length of MIN_NAME_LENGTH
	 */
	//fail
	@Test
	public void driverNameMinimum() throws CustomerException {
		locationX = 1;
		locationY = 1;
		name = "T";
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	//fail
	@Test
	public void droneNameMinimum() throws CustomerException {
		locationX = 1;
		locationY = 1;
		name = "T";
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	//fail
	@Test
	public void pickUpNameMinimum() throws CustomerException {
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
	
	//Tests for required mobile number length
	@Test
	public void driverMobileNumberMaximum() throws CustomerException {
		mobileNumber = "0123456789";
		locationX = 1;
		locationY = 1;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, driverDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void droneMobileNumberMaximum() throws CustomerException {
		mobileNumber = "0123456789";
		locationX = 1;
		locationY = 1;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, droneDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void pickUpMobileNumberMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, pickUpCustomer.getMobileNumber().length());
	}
	
	/*
	 For driverLocationXMinimum, droneLocationXMinimum
	 Should change the locationX variable here to match the value of MIN_LOCATION_X
	 */
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
		locationX = 0;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_X, pickUpCustomer.getLocationX());
	}
	
	/*
	 For: driverLocationXMaximum, droneLocationXMaximum
	 Should change locationX variable to match the value of MAX_LOCATION_X
	 */
	@Test
	public void driverLocationXMaximum() throws CustomerException {
		locationX = 10;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneLocationXMaximum() throws CustomerException {
		locationX = 10;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, droneDeliveryCustomer.getLocationX());
	}
	/*
	 Pick up customer's location = 0, 0
	 */
	@Test
	public void pickUpLocationXMaximum() throws CustomerException {
		locationX = 0;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_X, pickUpCustomer.getLocationX());
	}
	
	/*
	 Same as above
	 */
	//Border value tests for locationY
	@Test
	public void driverLocationYMinimum() throws CustomerException {
		locationY = -10;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMinimum() throws CustomerException {
		locationY = -10;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void pickUpLocationYMinimum() throws CustomerException {
		locationY = 0;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_Y, pickUpCustomer.getLocationY());
	}
	
	@Test
	public void driverLocationYMaximum() throws CustomerException {
		locationY = 10;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMaximum() throws CustomerException {
		locationY = 10;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	//Tests for valid customer types
	@Test
	public void driverDeliveryCustomerType() throws CustomerException {
		locationX = 1;
		locationY = 1;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Driver Delivery", driverDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void droneDeliveryCustomerType() throws CustomerException {
		locationX = 1;
		locationY = 1;
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Drone Delivery", droneDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void pickUpCustomerType() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ("Pick Up", pickUpCustomer.getCustomerType());
	}
	
	//Tests for out of range deliveries
	@Test
	public void driverDeliveryCustomerOutOfRange() throws CustomerException {
		locationX = 11;
		locationY = 11;
		double xDiff = Math.pow((locationX - RESTAURANT_X), 2);
		double yDiff = Math.pow((locationY - RESTAURANT_Y), 2);
		double distance = Math.sqrt(xDiff + yDiff);
		
		/*
		 You do not need to instantiate the customer object again as it has already been defined in the @setup method
		
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);

		 There's an assertEquals overloaded method for double values
		 
		assertEquals ((int)distance, (int)driverDeliveryCustomer.getDeliveryDistance());
		Use this instead. The third argument of 0 indicates that you want the exact match.
		*/
		assertNotEquals(distance, driverDeliveryCustomer.getDeliveryDistance(), 0);
	}
	
	@Test
	public void droneDeliveryCustomerOutOfRange() throws CustomerException {
		locationX = 11;
		locationY = 11;
		double xDiff = Math.pow((locationX - RESTAURANT_X), 2);
		double yDiff = Math.pow((locationY - RESTAURANT_Y), 2);
		double distance = Math.sqrt(xDiff + yDiff);
		/*
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ((int)distance, (int)droneDeliveryCustomer.getDeliveryDistance());	
		*/
		assertNotEquals (distance, droneDeliveryCustomer.getDeliveryDistance(), 0);	
	}
	
	/*
	 Should test that pickup customer's getDeliveryDistance() returns 0. 
	 */
	@Test
	public void pickUpDeliveryDistanceZero() throws CustomerException {
		locationX = 0;
		locationY = 0;
		double xDiff = Math.pow((locationX - RESTAURANT_X), 2);
		double yDiff = Math.pow((locationY - RESTAURANT_Y), 2);
		double distance = Math.sqrt(xDiff + yDiff);
		assertEquals (distance, pickUpCustomer.getDeliveryDistance(), 0);
	}

}
