package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;


/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Gyeongmin Jee
 *
 */

//
//Test whether pizza restaurant object has value initialized to empty state upon instantiation
//Test getNumPizzaOrders()
//Test  getPizzaByIndex(int index). Test index related exceptions.
//Test getTotalProfit() 

//Test processLog(java.lang.String filename). Test whether it throws logHandlerException when filename isn't valid.
//test it returns true upon success and false if something went wrong
//resetDetails() Test whether arrays are reset. 

public class RestaurantPizzaTests {
	static final int NUM_ORDERS = 100;
	//This is the 8th order in the log file
	String logFile = ".\\logs\\20170103.txt";
	String orderString = "22:16:00,22:31:00,Olivia Williams,0771390439,PUC,0,0,PZM,3";
	Pizza order;
	PizzaRestaurant pizzaRestaurant;
	
	//Setup with a working log file
	@Before
	public void setUp() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant = new PizzaRestaurant();
		order = LogHandler.createPizza(orderString);
	}
	
	@Test
	public void initialState(){
		assertEquals(0, pizzaRestaurant.getNumPizzaOrders());
		assertEquals(0, pizzaRestaurant.getTotalProfit(), 0);
	}
	
	//When initialized, the array is empty thus throws an error.
	@Test (expected = PizzaException.class)
	public void initialArrayListState() throws PizzaException{
		pizzaRestaurant.getPizzaByIndex(0);
	}
	
	//Test if correct number of order is returned
	@Test
	public void testNumberOfOrders() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		assertEquals(NUM_ORDERS, pizzaRestaurant.getNumPizzaOrders());
	}
	
	//Test if correct number of orders is returned after processing multiple logs
	@Test
	public void testMultipleProcessLog() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.processLog(logFile);
		assertEquals(NUM_ORDERS, pizzaRestaurant.getNumPizzaOrders());
	}
	
	//Test if file is read correctly in order
	@Test
	public void testIfReadProperly() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		Pizza pizza1 = LogHandler.createPizza("20:05:00,20:26:00,Aiden Zhang,0161429209,DVC,-3,9,PZV,2");
		Pizza pizza2 = LogHandler.createPizza("22:35:00,22:55:00,Aria Thompson,0695536923,DVC,6,0,PZV,6");
		Pizza pizza3 = LogHandler.createPizza("19:39:00,20:06:00,Eli Walker,0106952291,DNC,8,1,PZM,2");
		Pizza pizza8 = LogHandler.createPizza("22:16:00,22:31:00,Olivia Williams,0771390439,PUC,0,0,PZM,3");
		assertEquals(true, pizza1.equals(pizzaRestaurant.getPizzaByIndex(0)));
		assertEquals(true, pizza2.equals(pizzaRestaurant.getPizzaByIndex(1)));
		assertEquals(true, pizza3.equals(pizzaRestaurant.getPizzaByIndex(2)));
		assertEquals(true, pizza8.equals(pizzaRestaurant.getPizzaByIndex(7)));
	}
	
	//Wrong index provided
	@Test (expected = PizzaException.class)
	public void negativeIndex() throws PizzaException, CustomerException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.getPizzaByIndex(-1);
	}
	
	//Index more than the number of orders provided. Zero based index.
	@Test (expected = PizzaException.class)
	public void indexMoreThanNumberOfOrders() throws PizzaException, CustomerException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.getPizzaByIndex(NUM_ORDERS);
	}
	
	@Test
	public void testProfit() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		int profit = 0;
		for(int i=0; i < NUM_ORDERS; i++){
			profit += pizzaRestaurant.getPizzaByIndex(i).getOrderProfit();
		}
		assertEquals(profit, pizzaRestaurant.getTotalProfit(),0);
	}
	
	@Test
	public void testReset() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.resetDetails();
		assertEquals(0, pizzaRestaurant.getNumPizzaOrders());
		assertEquals(0, pizzaRestaurant.getTotalProfit(), 0);
	}
	
	@Test (expected = PizzaException.class)
	public void testResetArrayReset() throws CustomerException, PizzaException, LogHandlerException{
		pizzaRestaurant.processLog(logFile);
		pizzaRestaurant.resetDetails();
		pizzaRestaurant.getPizzaByIndex(0);
	}
	
	/*Log related parsing problems*/
	
	//Test invalid file name
	@Test (expected = LogHandlerException.class)
	public void fileNotFound() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("NonExistentFileName");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithEmptyString() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithEmptyString");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithMissingParameter() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithMissingParam");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithoutCommas() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithoutCommas");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithEmptyOrderTime() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithEmptyOrderTime");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithEmptyDeliveryTime() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithEmptyOrderTime");
	}
	
	@Test (expected = LogHandlerException.class)
	public void fileWithNonNumericQuantity() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithNonNumericQuantity");
	}
	
	/*Semantic errors*/
	
	@Test (expected = PizzaException.class)
	public void fileWithInvalidPizzaCode() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logFileWithInvalidPizzaCode");
	}
	
	@Test (expected = PizzaException.class)
	public void fileWithOrderTimeAfter11() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithOrderTimeAfter11");
	}
	
	@Test (expected = PizzaException.class)
	public void fileWithOrderTimeBefore7() throws LogHandlerException, PizzaException, CustomerException{
		pizzaRestaurant.processLog("logWithOrderTimeBefore7");
	}
}







