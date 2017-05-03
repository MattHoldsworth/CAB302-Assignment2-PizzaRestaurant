package asgn2Tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;


/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Gyeongmin Jee
* 
*/

/*
 * 1. Invalid input file name (populatePizzaDataset)
 * 
 * 2. Invalid type for each field.
 * string for order time and deliveryTime must be parsed as LocalTime
 * The string for pizza code does not need parsing but must be one of PZV PZM PZL
 * The quantity must be parsed as an int
 */

//Note that the objects in both ArrayLists should in
//the same order that they appear in the log file

public class LogHandlerPizzaTests {
	String orderTimeString;
	String deliveryTimeString;
	String pizzaCode;
	String quantity;
	String customerDetail;
	String log;
	
	@Before
	public void setUp(){
		orderTimeString = "19:00:00";
		deliveryTimeString = "19:20:00";
		pizzaCode = "PZV";
		quantity = "2";
		customerDetail = "Casey Jones,0123456789,DVC,5,5";
	}
	
	@Test
	public void createPizzaFromString() throws PizzaException, LogHandlerException{
		Pizza pizza;
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		pizza = LogHandler.createPizza(log);
		assertEquals(2, pizza.getQuantity());
		assertEquals("Vegetarian", pizza.getPizzaType());
	}
	
	//----LogHandlerExceptionTests----//
	//Empty order time is not allowed
	@Test (expected = LogHandlerException.class)
	public void emptyOrderTimeString() throws PizzaException, LogHandlerException{
		orderTimeString = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The order time string must has format HH:mm:ss
	@Test (expected = LogHandlerException.class)
	public void invalidOrderTimeFormat() throws PizzaException, LogHandlerException{
		orderTimeString = "19:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void invalidOrderTime() throws PizzaException, LogHandlerException{
		orderTimeString = "30:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//Non numeric values are not allowed
	@Test (expected = LogHandlerException.class)
	public void nonNumericOrderTimeFormat() throws PizzaException, LogHandlerException{
		orderTimeString = "a:b:c";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//Empty delivery time is not allowed
	@Test (expected = LogHandlerException.class)
	public void emptyDeliveryTime() throws PizzaException, LogHandlerException{
		deliveryTimeString = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The delivery time string must has format HH:mm:ss
	@Test (expected = LogHandlerException.class)
	public void invalidDeliveryTimeFormat() throws PizzaException, LogHandlerException{
		deliveryTimeString = "19:20";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void invalidDeliveryTime() throws PizzaException, LogHandlerException{
		deliveryTimeString = "30:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//Delivery time should not be non-numeric
	@Test (expected = LogHandlerException.class)
	public void nonNumericDeliveryTimeFormat() throws PizzaException, LogHandlerException{
		deliveryTimeString = "a:b:c";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}

	@Test (expected = LogHandlerException.class)
	public void emptyQuantity() throws PizzaException, LogHandlerException{
		quantity = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void nonNumericQuantity() throws PizzaException, LogHandlerException{
		quantity = "a";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//----PizzaExceptionTests----//
	//Correct format but violates constraints
	@Test (expected = PizzaException.class)
	public void orderBefore7() throws PizzaException, LogHandlerException{
		orderTimeString = "18:30:00";
		deliveryTimeString = "18:50:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void orderAfter11() throws PizzaException, LogHandlerException{
		orderTimeString = "23:10:00";
		deliveryTimeString = "23:30:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void deliveryBefore10Minutes() throws PizzaException, LogHandlerException{
		orderTimeString = "20:10:00";
		deliveryTimeString = "20:15:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void deliveryAfterAnHour() throws PizzaException, LogHandlerException{
		deliveryTimeString = "20:40:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void	lowerCasePizzaCode() throws PizzaException, LogHandlerException{
		pizzaCode = "abc";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void	numericPizzaCode() throws PizzaException, LogHandlerException{
		pizzaCode = "123";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void	emptyPizzaCode() throws PizzaException, LogHandlerException{
		pizzaCode = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void	pizzaCodeFourLetters() throws PizzaException, LogHandlerException{
		pizzaCode = "PZVW";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void quantityMoreThanTen() throws PizzaException, LogHandlerException{
		quantity = "12";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = PizzaException.class)
	public void zeroQuantity() throws PizzaException, LogHandlerException{
		quantity = "0";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//Throw an error if the file does not exist.
	@Test (expected = LogHandlerException.class)
	public void fileNotFound() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset("NonExistentFileName");
	}
	
	//Tests if populatePizzaDataset reads the order correctly
	@Test
	public void populatePizzaList() throws LogHandlerException, PizzaException{
		ArrayList<Pizza> pizzaList;
		pizzaList = LogHandler.populatePizzaDataset(".\\logs\\20170101.txt");
		assertEquals(3, pizzaList.size());
		assertEquals(2, pizzaList.get(0).getQuantity());
		assertEquals("Vegetarian", pizzaList.get(0).getPizzaType());
		assertEquals(1, pizzaList.get(1).getQuantity());
		assertEquals("Margherita", pizzaList.get(1).getPizzaType());
		assertEquals(3, pizzaList.get(2).getQuantity());
		assertEquals("Meat Lovers", pizzaList.get(2).getPizzaType());
	}
}





