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
 * @author Person A
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
		name = "Bob";
		mobileNumber = "1234567890";
		locationX = 5;
		locationY = 5;
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
	}
	
	//Tests for no name entered
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
	
	//Border value tests for name
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
	
	@Test
	public void driverNameMaximum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	@Test
	public void droneNameMaximum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	@Test
	public void pickUpNamerMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_NAME_LENGTH, pickUpCustomer.getName().length());
	}
	
	@Test
	public void driverNameMinimum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, driverDeliveryCustomer.getName().length());
	}
	
	@Test
	public void droneNameMinimum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, droneDeliveryCustomer.getName().length());
	}
	
	@Test
	public void pickUpNameMinimum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_NAME_LENGTH, pickUpCustomer.getName().length());
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
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, driverDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void droneMobileNumberMaximum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, droneDeliveryCustomer.getMobileNumber().length());
	}
	
	@Test
	public void pickUpMobileNumberMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MOBILE_NUM_LENGTH, pickUpCustomer.getMobileNumber().length());
	}
	
	//Border value tests for locationX
	@Test
	public void driverLocationXMinimum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneLocationXMinimum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_X, droneDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void pickUpLocationXMinimum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_X, pickUpCustomer.getLocationX());
	}
	
	@Test
	public void driverLocationXMaximum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneLocationXMaximum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, droneDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void pickUpLocationXMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_X, pickUpCustomer.getLocationX());
	}
	
	//Border value tests for locationY
	@Test
	public void driverLocationYMinimum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMinimum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void pickUpLocationYMinimum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MIN_LOCATION_Y, pickUpCustomer.getLocationY());
	}
	
	@Test
	public void driverLocationYMaximum() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, driverDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void droneLocationYMaximum() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, droneDeliveryCustomer.getLocationY());
	}
	
	@Test
	public void pickUpLocationYMaximum() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (MAX_LOCATION_Y, pickUpCustomer.getLocationY());
	}
	
	//Tests for restaurant location
	@Test
	public void driverDeliveryRestaurantLocation() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_Y, driverDeliveryCustomer.getLocationY());
		assertEquals (RESTAURANT_X, driverDeliveryCustomer.getLocationX());
	}
	
	@Test
	public void droneDeliveryRestaurantLocation() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals (RESTAURANT_Y, droneDeliveryCustomer.getLocationY());
		assertEquals (RESTAURANT_X, droneDeliveryCustomer.getLocationX());
	}
	
	//Test for not restaurant location for pick up customers
	@Test
	public void pickUpNotRestaurantLocation() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertNotEquals (RESTAURANT_Y, pickUpCustomer.getLocationY());
		assertNotEquals (RESTAURANT_X, pickUpCustomer.getLocationX());
	}
	
	//Tests for valid customer types
	@Test
	public void driverDeliveryCustomerType() throws CustomerException {
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertNotEquals ("Driver Delivery", driverDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void droneDeliveryCustomerType() throws CustomerException {
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertNotEquals ("Drone Delivery", droneDeliveryCustomer.getCustomerType());
	}
	
	@Test
	public void pickUpCustomerType() throws CustomerException {
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		assertNotEquals ("Pick Up", pickUpCustomer.getCustomerType());
	}
	
	//Tests for out of range deliveries
	@Test
	public void driverDeliveryCustomerOutOfRange() throws CustomerException {
		double distance = Math.abs(locationX - RESTAURANT_X) + Math.abs(locationY - RESTAURANT_Y);
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ((int)distance, (int)driverDeliveryCustomer.getDeliveryDistance());
	}
	
	@Test
	public void droneDeliveryCustomerOutOfRange() throws CustomerException {
		double xDiff = Math.pow((locationX - RESTAURANT_X), 2);
		double yDiff = Math.pow((locationY - RESTAURANT_Y), 2);
		double distance = Math.sqrt(xDiff + yDiff);
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		assertEquals ((int)distance, (int)droneDeliveryCustomer.getDeliveryDistance());
	}
	
	


}
