package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Matthew Holdsworth
 *
 */
public class CustomerFactoryTests {
	
	static final String DRIVER = "DVC";
	static final String DRONE = "DNC";
	static final String PICKUP = "PUC";
	static final int RESTAURANT_X = 0;
	static final int RESTAURANT_Y = 0;
	
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	String type;
	
	DriverDeliveryCustomer driverDeliveryCustomer;
	DroneDeliveryCustomer droneDeliveryCustomer;
	PickUpCustomer pickUpCustomer;
	
	@Before
	public void setUp() throws CustomerException {
		name = "Bob";
		mobileNumber = "0123456789";
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		pickUpCustomer = new PickUpCustomer(name, mobileNumber, locationX, locationY);
		locationX = 1;
		locationY = 1;	
		driverDeliveryCustomer = new DriverDeliveryCustomer(name, mobileNumber, locationX, locationY);
		droneDeliveryCustomer = new DroneDeliveryCustomer(name, mobileNumber, locationX, locationY);
		
	}
	
	//Test if error is thrown if an empty string is passed
	@Test (expected = CustomerException.class)
	public void emptyCodeString() throws CustomerException {
		CustomerFactory.getCustomer("", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void singleCharacterString() throws CustomerException {
		CustomerFactory.getCustomer("D", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void fourCharacterString() throws CustomerException {
		CustomerFactory.getCustomer("DVCC", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidCharacterString() throws CustomerException {
		CustomerFactory.getCustomer("DVM", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void lowerCaseCharacterString() throws CustomerException {
		CustomerFactory.getCustomer("dvc", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void numericString() throws CustomerException {
		CustomerFactory.getCustomer("123", name, mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void spaceBetweenString() throws CustomerException {
		CustomerFactory.getCustomer("D C", name, mobileNumber, locationX, locationY);
	}
	
	//Test if correct objects are instantiated
	@Test
	public void instantiateDriverCustomer() throws CustomerException {
		Customer driverDelivery2 = CustomerFactory.getCustomer(DRIVER, name, mobileNumber, locationX, locationY);
		assertEquals(true, driverDeliveryCustomer.equals(driverDelivery2));
	}
	
	@Test
	public void instantiateDroneCustomer() throws CustomerException {
		Customer droneDelivery2 = CustomerFactory.getCustomer(DRONE, name, mobileNumber, locationX, locationY);
		assertEquals(true, droneDeliveryCustomer.equals(droneDelivery2));
	}
	
	@Test
	public void instantiatePickUpCustomer() throws CustomerException {
		locationX = RESTAURANT_X;
		locationY = RESTAURANT_Y;
		Customer pickUpCustomer2 = CustomerFactory.getCustomer(PICKUP, name, mobileNumber, locationX, locationY);
		assertEquals(true, pickUpCustomer.equals(pickUpCustomer2));
	}
	
	//Test that customerException propagates from customer classes
	@Test (expected = CustomerException.class)
	public void invalidName() throws CustomerException{
		CustomerFactory.getCustomer(DRONE, "  ", mobileNumber, locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidMobile() throws CustomerException{
		CustomerFactory.getCustomer(DRONE, name, "3456789", locationX, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidLocationX() throws CustomerException{
		CustomerFactory.getCustomer(DRONE, name, mobileNumber, -20, locationY);
	}
	
	@Test (expected = CustomerException.class)
	public void invalidLocationY() throws CustomerException{
		CustomerFactory.getCustomer(DRONE, name, mobileNumber, locationX, 20);
	}
}
