package asgn2Tests;

import java.io.BufferedReader;

import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.LogHandler;


/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Gyeongmin Jee
* 
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
	Pizza vegetarian;
	@Before
	public void setUp() throws PizzaException{
		orderTimeString = "19:00:00";
		deliveryTimeString = "19:20:00";
		pizzaCode = "PZV";
		quantity = "2";
		customerDetail = "Casey Jones,0123456789,DVC,5,5";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		
		LocalTime orderTime = LocalTime.parse(orderTimeString);
		LocalTime deliveryTime = LocalTime.parse(deliveryTimeString);
		Pizza vegetarian = new VegetarianPizza(2, orderTime, deliveryTime);
	}
	
	//Tests if correct pizza object is created.
	@Test
	public void createPizzaFromString() throws PizzaException, LogHandlerException{
		Pizza pizza;	
		pizza = LogHandler.createPizza(log);
		assertEquals(true, pizza.equals(vegetarian));
	}
	
	//----LogHandlerExceptionTests----//
	//Test empty string input.
	@Test (expected = LogHandlerException.class)
	public void emptyLine() throws PizzaException, LogHandlerException{
		log = "";
		LogHandler.createPizza(log);
	}
	
	//Test string without commas.
	@Test (expected = LogHandlerException.class)
	public void noComma() throws PizzaException, LogHandlerException{
		customerDetail = "CaseyJones0123456789DVC55";
		log = orderTimeString+deliveryTimeString+customerDetail+pizzaCode+quantity;
		LogHandler.createPizza(log);
	}
	
	//Test a string missing quantity
	@Test (expected = LogHandlerException.class)
	public void stringMissingQuantity() throws PizzaException, LogHandlerException{
		//The quantity is missing. Should throw a loghandlerexception.
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode;
		LogHandler.createPizza(log);
	}
	
	//Empty order time string is not allowed
	@Test (expected = LogHandlerException.class)
	public void emptyOrderTimeString() throws PizzaException, LogHandlerException{
		orderTimeString = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void singleValueOrderTime() throws PizzaException, LogHandlerException{
		orderTimeString = "19";
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
	
	@Test (expected = LogHandlerException.class)
	public void invalidOrderTimeSingleDigitHour() throws PizzaException, LogHandlerException{
		orderTimeString = "9:20:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void invalidOrderHour() throws PizzaException, LogHandlerException{
		orderTimeString = "30:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//Hour should not be negative
	@Test (expected = LogHandlerException.class)
	public void negativeOrderHour() throws PizzaException, LogHandlerException{
		orderTimeString = "-1:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void invalidOrderMinute() throws PizzaException, LogHandlerException{
		orderTimeString = "20:60:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void negativeOrderMinute() throws PizzaException, LogHandlerException{
		orderTimeString = "20:-10:00";
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
	
	//Empty delivery time string is not allowed
	@Test (expected = LogHandlerException.class)
	public void emptyDeliveryTime() throws PizzaException, LogHandlerException{
		deliveryTimeString = "";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void singleValueDeliveryTime() throws PizzaException, LogHandlerException{
		deliveryTimeString = "19";
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
	
	@Test (expected = LogHandlerException.class)
	public void invalidDeliveryTimeSingleDigitHour() throws PizzaException, LogHandlerException{
		deliveryTimeString = "9:20:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//The maximum time supported by LocalTime is 23:59:59
	@Test (expected = LogHandlerException.class)
	public void invalidDeliveryHour() throws PizzaException, LogHandlerException{
		deliveryTimeString = "30:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void negativeDeliveryHour() throws PizzaException, LogHandlerException{
		deliveryTimeString = "-10:00:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void invalidDeliveryMinute() throws PizzaException, LogHandlerException{
		deliveryTimeString = "19:60:00";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	@Test (expected = LogHandlerException.class)
	public void negativeDeliveryMinute() throws PizzaException, LogHandlerException{
		deliveryTimeString = "19:-10:00";
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
	
	//Parsing decimal to int will throw an exception
	@Test (expected = LogHandlerException.class)
	public void decimalQuantity() throws PizzaException, LogHandlerException{
		quantity = "5.5";
		log = orderTimeString+','+deliveryTimeString+','+customerDetail+','+pizzaCode+','+quantity;
		LogHandler.createPizza(log);
	}
	
	//----Semantic errors. PizzaExceptionTests----//
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
		pizzaCode = "pzv";
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
	public void	invalidPizzaCode() throws PizzaException, LogHandlerException{
		pizzaCode = "PZH";
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
		LogHandler.populatePizzaDataset(".\\logs\\NonExistentFileName");
	}
	
	//Throw an error if there's an empty string in the log file.
	@Test (expected = LogHandlerException.class)
	public void logFileWithEmptyString() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithEmptyString");
	}
	
	//Throw an error if there's a parameter missing in a string
	@Test (expected = LogHandlerException.class)
	public void logFileWithaMissingParameter() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithMissingParam");
	}
	
	//Throw an error if there's string without commas
	@Test (expected = LogHandlerException.class)
	public void logFileWithoutCommas() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithoutCommas");
	}
	
	//Throw an error if there's an empty order time
	@Test (expected = LogHandlerException.class)
	public void logFileWithEmptyOrderTime() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithEmptyOrderTime");
	}
	
	//Throw an error if there's an empty delivery time
	@Test (expected = LogHandlerException.class)
	public void logFileWithEmptyDeliveryTime() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithEmptyDeliveryTime");
	}
	
	//Throw an error if there's a non numeric quantity
	@Test (expected = LogHandlerException.class)
	public void logFileWithNonNumericQuantity() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithNonNumericQuantity");
	}
	
	//Throw an error if there's a semantic error
	@Test (expected = PizzaException.class)
	public void logFileWithOrderTimeBefore7() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithOrderTimeBefore7");
	}

	@Test (expected = PizzaException.class)
	public void logFileWithOrderTimeAfter11() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logWithOrderTimeAfter11");
	}
	
	@Test (expected = PizzaException.class)
	public void logFileWithInvalidPizzaCode() throws LogHandlerException, PizzaException{
		LogHandler.populatePizzaDataset(".\\logs\\logFileWithInvalidPizzaCode");
	}
	
	//Tests if populatePizzaDataset reads the order correctly
	@Test
	public void populatePizzaList() throws LogHandlerException, PizzaException{
		Pizza pizza1 = LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		Pizza pizza2 = LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1");
		Pizza pizza3 = LogHandler.createPizza("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3");
		ArrayList<Pizza> pizzaList;
		pizzaList = LogHandler.populatePizzaDataset(".\\logs\\20170101.txt");
		
		assertEquals(true, pizza1.equals(pizzaList.get(0)));
		assertEquals(true, pizza2.equals(pizzaList.get(1)));
		assertEquals(true, pizza3.equals(pizzaList.get(2)));
		assertEquals(3, pizzaList.size());
		assertEquals("Vegetarian", pizzaList.get(0).getPizzaType());
		assertEquals("Margherita", pizzaList.get(1).getPizzaType());
		assertEquals("Meat Lovers", pizzaList.get(2).getPizzaType());
	}
}





