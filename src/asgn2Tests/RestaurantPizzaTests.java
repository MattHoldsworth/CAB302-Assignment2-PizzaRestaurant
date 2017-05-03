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
	static final int ORDERNUMBERS = 100;
	//This is the 8th order in the log file
	String logFile = ".\\logs\\20170103.txt";
	String orderString = "22:16:00,22:31:00,Olivia Williams,0771390439,PUC,0,0,PZM,3";
	int orderNumber = 8;
	Pizza order;
	PizzaRestaurant pizzaRestaurant;
	
	//Setup with a working log file
	@Before
	public void setUp() throws CustomerException, PizzaException, LogHandlerException{
		PizzaRestaurant pizzaRestaurant = new PizzaRestaurant();
		pizzaRestaurant.processLog(logFile);
		
		Pizza order = LogHandler.createPizza(orderString);
	}
	
	//Test if correct number of order is returned
	@Test
	public void testNumberOfOrders(){
		assertEquals(ORDERNUMBERS, pizzaRestaurant.getNumPizzaOrders());
	}
	
	//Test if correct pizza order is returned
	@Test
	public void getPizzaByIndexTest() throws PizzaException{
		assertEquals(true, order.equals(pizzaRestaurant.getPizzaByIndex(orderNumber)));
	}
	
	//Wrong index provided
	@Test
	public void negativeIndex() throws PizzaException{
		pizzaRestaurant.getPizzaByIndex(-1);
	}
	
	//Index more than the number of orders provided
	@Test
	public void indexMoreThanNumberOfOrders() throws PizzaException{
		pizzaRestaurant.getPizzaByIndex(ORDERNUMBERS + 1);
	}
	
	@Test
	public void testProfit(){
		
	}
}







