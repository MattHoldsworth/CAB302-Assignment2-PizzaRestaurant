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
	}
	
	@Test
	public void initialState() {
		assertEquals(0, pizzaRestaurant.getNumCustomerOrders());
		assertEquals(0, pizzaRestaurant.getTotalDeliveryDistance(), 0);
	}
	
	@Test (expected = java.lang.IndexOutOfBoundsException.class)
	public void iniialArrayListState() throws CustomerException {
		pizzaRestaurant.getCustomerByIndex(0);
	}
	
}
