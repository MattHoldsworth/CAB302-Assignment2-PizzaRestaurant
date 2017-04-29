package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;


/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Gyeongmin Jee
 * 
 */
public class PizzaFactoryTests {
	static final String MARGHERITA = "PZM";
	static final String VEGETARIAN = "PZV";
	static final String MEATLOVERS = "PZL";

	int quantity;
	String orderTimeString;
	LocalTime orderTime;
	LocalTime deliveryTime;
	
	MargheritaPizza margherita;
	MeatLoversPizza meatLovers;
	VegetarianPizza vegetarian;
	
	@Before
	public void setUp() throws PizzaException{
		quantity = 5;
		orderTimeString = "21:30:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(29);
		
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Test if exception is thrown when invalid code strings are passed as input.
	@Test (expected = PizzaException.class)
	public void emptyCodeString() throws PizzaException{
		PizzaFactory.getPizza("", quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void fourLetters() throws PizzaException{
		PizzaFactory.getPizza("PZMM", quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void invalidCodeString() throws PizzaException{
		PizzaFactory.getPizza("PZW", quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void lowerCaseCodeString() throws PizzaException{
		PizzaFactory.getPizza("pzm", quantity, orderTime, deliveryTime);
	}
	
	//Test if correct subclass pizza objects are instantiated
	@Test
	public void instantiateMargherita() throws PizzaException{
		Pizza margherita2 = PizzaFactory.getPizza(MARGHERITA, quantity, orderTime, deliveryTime);
		assertEquals(true, margherita.equals(margherita2));
	}
	
	@Test
	public void instantiateMeatLovers() throws PizzaException{
		Pizza meatLovers2 = PizzaFactory.getPizza(MEATLOVERS, quantity, orderTime, deliveryTime);
		assertEquals(true, meatLovers.equals(meatLovers2));
	}
	
	@Test
	public void instantiateVegetarian() throws PizzaException{
		Pizza vegetarian2 = PizzaFactory.getPizza(VEGETARIAN, quantity, orderTime, deliveryTime);
		assertEquals(true, vegetarian.equals(vegetarian2));
	}
	
	//Make sure PizzaFactory rethrows the PizzaException from pizza objects
	@Test (expected = PizzaException.class)
	public void quantityMoreThanTen() throws PizzaException{
		quantity = 11;
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
}






