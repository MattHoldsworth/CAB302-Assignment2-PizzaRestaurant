package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;


/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Matthew Holdsworth
 */
public class RestaurantCustomerTests {
	static final int NUM_ORDERS = 100;
	//This is the 8th order in the log file
	String logFile = ".\\logs\\20170103.txt";
	String orderString = "22:16:00,22:31:00,Olivia Williams,0771390439,PUC,0,0,PZM,3";
	PizzaRestaurant pizzaRestaurant;
	Customer order;
	
	@Before
	public void setUp() throws CustomerException, PizzaException, LogHandlerException {
		pizzaRestaurant = new PizzaRestaurant();
		order = LogHandler.createCustomer(orderString);
	}//end SetUp
	
	@Test
	public void initialState() {
		assertEquals(0, pizzaRestaurant.getNumCustomerOrders());
		assertEquals(0, pizzaRestaurant.getTotalDeliveryDistance(), 0);
	}
	
	@Test (expected = java.lang.IndexOutOfBoundsException.class)
	public void iniialArrayListState() throws CustomerException {
		pizzaRestaurant.getCustomerByIndex(0);
	}
	
	@Test
	public void testMultipleProcessLog() throws CustomerException, PizzaException, LogHandlerException {
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.processLog(logFile);
		assertEquals(NUM_ORDERS, pizzaRestaurant.getNumCustomerOrders());
	}
	
	@Test
	public void testIfReadProperly() throws CustomerException, PizzaException, LogHandlerException {
		pizzaRestaurant.processLog(logFile);
		Customer customer1 = LogHandler.createCustomer("20:05:00,20:26:00,Aiden Zhang,0161429209,DVC,-3,9,PZV,2");
		Customer customer2 = LogHandler.createCustomer("22:35:00,22:55:00,Aria Thompson,0695536923,DVC,6,0,PZV,6");
		Customer customer3 = LogHandler.createCustomer("19:39:00,20:06:00,Eli Walker,0106952291,DNC,8,1,PZM,2");
		Customer customer8 = LogHandler.createCustomer("22:16:00,22:31:00,Olivia Williams,0771390439,PUC,0,0,PZM,3");
		assertEquals(true, customer1.equals(pizzaRestaurant.getCustomerByIndex(0)));
		assertEquals(true, customer2.equals(pizzaRestaurant.getCustomerByIndex(1)));
		assertEquals(true, customer3.equals(pizzaRestaurant.getCustomerByIndex(2)));
		assertEquals(true, customer8.equals(pizzaRestaurant.getCustomerByIndex(7)));
	}
	
	@Test (expected = CustomerException.class)
	public void negativeIndex() throws PizzaException, CustomerException, LogHandlerException {
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.getCustomerByIndex(-1);
	}
	
	@Test (expected = CustomerException.class)
	public void indexMoreThanNumberOfOrders() throws PizzaException, CustomerException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.getCustomerByIndex(NUM_ORDERS);
	}
	
	@Test
	public void testDeliveryDistance() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		double distance = 0;
		for(int i=0; i < NUM_ORDERS; i++){
			distance += pizzaRestaurant.getCustomerByIndex(i).getDeliveryDistance();
		}
		assertEquals(distance, pizzaRestaurant.getTotalDeliveryDistance(),0);
	}
	
	@Test
	public void testReset() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.resetDetails();
		assertEquals(0, pizzaRestaurant.getNumCustomerOrders());
		assertEquals(0, pizzaRestaurant.getTotalDeliveryDistance(), 0);
	}
	
	@Test (expected = java.lang.IndexOutOfBoundsException.class)
	public void testResetArrayReset() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.resetDetails();
		pizzaRestaurant.getCustomerByIndex(0);
	}
	
}//end RestaurantCustomerTests
