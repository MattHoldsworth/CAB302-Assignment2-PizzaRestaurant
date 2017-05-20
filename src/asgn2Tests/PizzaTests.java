package asgn2Tests;

import static org.junit.Assert.*;


import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Pizzas.PizzaTopping;
/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Gyeongmin Jee
 *
 */
public class PizzaTests {
	static final int COOKING_TIME = 10;
	static final int MAXIMUM_DELIVERY_TIME = 60;
	static final int MAXIMUM_QUANTITY = 10;
	static final int MINIMUM_QUANTITY = 1;
	static final double MARGHERITA_COST = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost();
	static final double VEGETARIAN_COST = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost()
										+ PizzaTopping.EGGPLANT.getCost() + PizzaTopping.MUSHROOM.getCost()
										+ PizzaTopping.CAPSICUM.getCost();
	static final double MEATLOVERS_COST = PizzaTopping.TOMATO.getCost() + PizzaTopping.CHEESE.getCost()
										+ PizzaTopping.BACON.getCost() + PizzaTopping.PEPPERONI.getCost()
										+ PizzaTopping.SALAMI.getCost();
	
	static final double MARGHERITA_PRICE = 8;
	static final double VEGETARIAN_PRICE = 10;
	static final double MEATLOVERS_PRICE = 12;

	MargheritaPizza margherita;
	MeatLoversPizza meatLovers;
	VegetarianPizza vegetarian;	
	int quantity;
	String orderTimeString;
	LocalTime orderTime;
	LocalTime deliveryTime;
	
	@Before @Test
	public void setUp() throws PizzaException{
		quantity = 5;
		orderTimeString = "20:30:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(25);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Tests for zero quantity ordered
	@Test (expected = PizzaException.class)
	public void margheritaQuantityZero() throws PizzaException{
		quantity = 0;
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversQuantityZero() throws PizzaException{
		quantity = 0;
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianQuantityZero() throws PizzaException{
		quantity = 0;
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Tests for ordering more than 10
	@Test (expected = PizzaException.class)
	public void margheritaQuantityMoreThanTen() throws PizzaException{
		quantity = MAXIMUM_QUANTITY + 1;
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversQuantityMoreThanTen() throws PizzaException{
		quantity = MAXIMUM_QUANTITY + 1;
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianQuantityMoreThanTen() throws PizzaException{
		quantity = MAXIMUM_QUANTITY + 1;
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Border value tests for quantity
	@Test
	public void margheritaMinimumQuantity() throws PizzaException{
		margherita = new MargheritaPizza(MINIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MINIMUM_QUANTITY, margherita.getQuantity());
	}
	
	@Test
	public void meatLoversMinimumQuantity() throws PizzaException{
		meatLovers = new MeatLoversPizza(MINIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MINIMUM_QUANTITY, meatLovers.getQuantity());
	}
	
	@Test
	public void vegetarianMinimumQuantity() throws PizzaException{
		vegetarian = new VegetarianPizza(MINIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MINIMUM_QUANTITY, vegetarian.getQuantity());
	}
	
	@Test
	public void margheritaMaximumQuantity() throws PizzaException{
		margherita = new MargheritaPizza(MAXIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MAXIMUM_QUANTITY, margherita.getQuantity());
	}
	
	@Test
	public void meatLoversMaximumQuantity() throws PizzaException{
		meatLovers = new MeatLoversPizza(MAXIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MAXIMUM_QUANTITY, meatLovers.getQuantity());
	}
	
	@Test
	public void vegetarianMaximumQuantity() throws PizzaException{
		vegetarian = new VegetarianPizza(MAXIMUM_QUANTITY, orderTime, deliveryTime);
		assertEquals(MAXIMUM_QUANTITY, vegetarian.getQuantity());
	}
	
	//Testing that ordering before 7:00pm is not allowed
	@Test (expected = PizzaException.class)
	public void margheritaOrderBefore7() throws PizzaException{
		orderTimeString = "18:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversOrderBefore7() throws PizzaException{
		orderTimeString = "18:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianOrderBefore7() throws PizzaException{
		orderTimeString = "18:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Test that ordering after 11:00pm is not allowed and test tht delivery after 11:00pm is allowed
	@Test (expected = PizzaException.class)
	public void margheritaOrderAfter11() throws PizzaException{
		orderTimeString = "23:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversOrderAfter11() throws PizzaException{
		orderTimeString = "23:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianOrderAfter11() throws PizzaException{
		orderTimeString = "23:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(20);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Border value tests for order time
	@Test
	public void margheritaOrderAt7() throws PizzaException{
		orderTimeString = "19:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(25);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void meatLoversOrderAt7() throws PizzaException{
		orderTimeString = "19:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(25);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void vegetarianOrderAt7() throws PizzaException{
		orderTimeString = "19:00:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(25);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void margheritaOrderBefore11() throws PizzaException{
		orderTimeString = "22:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(35);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void meatLoversOrderBefore11() throws PizzaException{
		orderTimeString = "22:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(35);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void vegetarianOrderBefore11() throws PizzaException{
		orderTimeString = "22:59:00";
		orderTime = LocalTime.parse(orderTimeString);
		deliveryTime = orderTime.plusMinutes(35);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//A pizza takes at least 10 minutes to cook
	@Test (expected = PizzaException.class)
	public void margheritaDeliveryBefore10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME - 1);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversDeliveryBefore10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME - 1);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianDeliveryBefore10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME - 1);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Border test
	@Test
	public void margheritaDeliveryAfter10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void meatLoversDeliveryAfter10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void vegetarianDeliveryAfter10Minutes() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(COOKING_TIME);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Delivery time precedes order time
	@Test (expected = PizzaException.class)
	public void margheritaDeliveryBeforeOrder() throws PizzaException{
		deliveryTime = orderTime.minusMinutes(20);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversDeliveryBeforeOrder() throws PizzaException{
		deliveryTime = orderTime.minusMinutes(20);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianDeliveryBeforeOrder() throws PizzaException{
		deliveryTime = orderTime.minusMinutes(20);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//If the delivery is made after 1 hour
	@Test (expected = PizzaException.class)
	public void margheritaPizzaThrownOut() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void meatLoversPizzaThrownOut() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test (expected = PizzaException.class)
	public void vegetarianPizzaThrownOut() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//If the delivery is within 1 hour from when the pizza is ready
	@Test
	public void margheritaMaxDeliveryTime() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME - 1);
		margherita = new MargheritaPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void meatLoversMaxDeliveryTime() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME - 1);
		meatLovers = new MeatLoversPizza(quantity, orderTime, deliveryTime);
	}
	
	@Test
	public void vegetarianMaxDeliveryTime() throws PizzaException{
		deliveryTime = orderTime.plusMinutes(MAXIMUM_DELIVERY_TIME - 1);
		vegetarian = new VegetarianPizza(quantity, orderTime, deliveryTime);
	}
	
	//Tests cost per pizza 
	@Test
	public void margheritaCost(){
		margherita.calculateCostPerPizza();
		assertEquals(MARGHERITA_COST,margherita.getCostPerPizza(), 0);
	}
	
	@Test
	public void meatLoversCost(){
		meatLovers.calculateCostPerPizza();
		assertEquals(MEATLOVERS_COST, meatLovers.getCostPerPizza(), 0);
	}
	
	@Test
	public void vegetarianCost(){
		vegetarian.calculateCostPerPizza();
		assertEquals(VEGETARIAN_COST, vegetarian.getCostPerPizza(), 0);
	}
	
	//Make sure that cost calculation involves a simple assignment therefore using multiple calculatecost method returns the same value.
	@Test
	public void margheritaCostCalculation(){
		margherita.calculateCostPerPizza();
		margherita.calculateCostPerPizza();
		assertEquals(MARGHERITA_COST,margherita.getCostPerPizza(), 0);
	}
	
	@Test
	public void meatLoversCostCalculation(){
		meatLovers.calculateCostPerPizza();
		meatLovers.calculateCostPerPizza();
		assertEquals(MEATLOVERS_COST, meatLovers.getCostPerPizza(), 0);
	}
	
	@Test
	public void vegetarianCostCalculation(){
		vegetarian.calculateCostPerPizza();
		vegetarian.calculateCostPerPizza();
		assertEquals(VEGETARIAN_COST, vegetarian.getCostPerPizza(), 0);
	}
	
	//Test order cost calculation
	@Test
	public void margheritaOrderCost(){
		double orderCost = MARGHERITA_COST * quantity;
		assertEquals(orderCost, margherita.getOrderCost(), 0);
	}
	
	@Test
	public void meatLoversOrderCost(){
		double orderCost = MEATLOVERS_COST * quantity;
		assertEquals(orderCost, meatLovers.getOrderCost(), 0);
	}
	
	@Test
	public void vegetarianOrderCost(){
		double orderCost = VEGETARIAN_COST * quantity;
		assertEquals(orderCost, vegetarian.getOrderCost(), 0);
	}
	
	//Test price per pizza
	@Test
	public void margheritaPrice(){
		assertEquals(MARGHERITA_PRICE, margherita.getPricePerPizza(), 0);
	}
	
	@Test
	public void meatLoversPrice(){
		assertEquals(MEATLOVERS_PRICE, meatLovers.getPricePerPizza(), 0);
	}
	
	@Test
	public void vegetarianPrice(){
		assertEquals(VEGETARIAN_PRICE, vegetarian.getPricePerPizza(), 0);
	}
	
	//Test order price
	@Test
	public void margheritaOrderPrice(){
		double orderPrice = MARGHERITA_PRICE * quantity;
		assertEquals(orderPrice, margherita.getOrderPrice(), 0);
	}
	
	@Test
	public void meatLoversOrderPrice(){
		double orderPrice = MEATLOVERS_PRICE * quantity;
		assertEquals(orderPrice, meatLovers.getOrderPrice(), 0);
	}
	
	@Test
	public void vegetarianOrderPrice(){
		double orderPrice = VEGETARIAN_PRICE * quantity;
		assertEquals(orderPrice, vegetarian.getOrderPrice(), 0);
	}
	
	//Test order profits
	@Test
	public void margheritaOrderProfit(){
		double orderProfit = (MARGHERITA_PRICE - MARGHERITA_COST) * quantity;
		assertEquals(orderProfit, margherita.getOrderProfit(), 0);
	}
	
	@Test
	public void meatLoversOrderProfit(){
		double orderProfit = (MEATLOVERS_PRICE - MEATLOVERS_COST) * quantity;
		assertEquals(orderProfit, meatLovers.getOrderProfit(), 0);
	}
	
	@Test
	public void vegetarianOrderProfit(){
		double orderProfit = (VEGETARIAN_PRICE - VEGETARIAN_COST) * quantity;
		assertEquals(orderProfit, vegetarian.getOrderProfit(), 0);
	}
	
	//Tests if pizza contains the right toppings.
	@Test
	public void ToppingsInMargherita(){
		assertEquals(true, margherita.containsTopping(PizzaTopping.TOMATO));
		assertEquals(true, margherita.containsTopping(PizzaTopping.CHEESE));
	}
	
	@Test
	public void ToppingsInMeatLovers(){
		assertEquals(true, meatLovers.containsTopping(PizzaTopping.TOMATO));
		assertEquals(true, meatLovers.containsTopping(PizzaTopping.CHEESE));
		assertEquals(true, meatLovers.containsTopping(PizzaTopping.BACON));
		assertEquals(true, meatLovers.containsTopping(PizzaTopping.PEPPERONI));
		assertEquals(true, meatLovers.containsTopping(PizzaTopping.SALAMI));
	}
	
	@Test
	public void ToppingsInVegetarian(){
		assertEquals(true, vegetarian.containsTopping(PizzaTopping.TOMATO));
		assertEquals(true, vegetarian.containsTopping(PizzaTopping.CHEESE));
		assertEquals(true, vegetarian.containsTopping(PizzaTopping.EGGPLANT));
		assertEquals(true, vegetarian.containsTopping(PizzaTopping.MUSHROOM));
		assertEquals(true, vegetarian.containsTopping(PizzaTopping.CAPSICUM));
	}
	
	//Make sure pizzas do not contain other toppings
	@Test
	public void ToppingsNotInMargherita(){
		assertEquals(false, margherita.containsTopping(PizzaTopping.BACON));
		assertEquals(false, margherita.containsTopping(PizzaTopping.SALAMI));
		assertEquals(false, margherita.containsTopping(PizzaTopping.PEPPERONI));
		assertEquals(false, margherita.containsTopping(PizzaTopping.CAPSICUM));
		assertEquals(false, margherita.containsTopping(PizzaTopping.MUSHROOM));
		assertEquals(false, margherita.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	@Test
	public void ToppingsNotInMeatLovers(){
		assertEquals(false, meatLovers.containsTopping(PizzaTopping.CAPSICUM));
		assertEquals(false, meatLovers.containsTopping(PizzaTopping.MUSHROOM));
		assertEquals(false, meatLovers.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	@Test
	public void ToppingsNotInVegetarian(){
		assertEquals(false, margherita.containsTopping(PizzaTopping.BACON));
		assertEquals(false, margherita.containsTopping(PizzaTopping.SALAMI));
		assertEquals(false, margherita.containsTopping(PizzaTopping.PEPPERONI));
	}
	
	//Test if correct human readable pizza type is stored.
	@Test
	public void margheritaPizzaType(){
		assertEquals("Margherita", margherita.getPizzaType());
	}
	
	@Test
	public void meatLoversPizzaType(){
		assertEquals("Meat Lovers", meatLovers.getPizzaType());
	}
	
	@Test
	public void vegetarianPizzaType(){
		assertEquals("Vegetarian", vegetarian.getPizzaType());
	}
	
	//Pizza Abstract class should not have static fields. 
	@Test
	public void subClassIndependenceTest() throws PizzaException{
		int meatLoversQuantity = 8;
		int vegetarianQuantity = 9;

		meatLovers = new MeatLoversPizza(meatLoversQuantity, orderTime, deliveryTime);
		vegetarian = new VegetarianPizza(vegetarianQuantity, orderTime, 
										deliveryTime);
		
		assertEquals(quantity, margherita.getQuantity());
		assertEquals(vegetarianQuantity, vegetarian.getQuantity());
		assertEquals(meatLoversQuantity, meatLovers.getQuantity());		
	}
	
	//test whether each instances are independent of each other
	@Test
	public void classIndependenceTest() throws PizzaException{
		int extraQuantity = 3;
		MargheritaPizza margherita2 = new MargheritaPizza(quantity + extraQuantity, orderTime, deliveryTime);
		MeatLoversPizza meatLovers2 = new MeatLoversPizza(quantity + extraQuantity, orderTime, deliveryTime);
		VegetarianPizza vegetarian2 = new VegetarianPizza(quantity + extraQuantity, orderTime, deliveryTime);
		
		assertEquals(quantity, margherita.getQuantity());
		assertEquals(quantity, vegetarian.getQuantity());
		assertEquals(quantity, meatLovers.getQuantity());	
		assertEquals(quantity + extraQuantity, margherita2.getQuantity());
		assertEquals(quantity + extraQuantity, vegetarian2.getQuantity());
		assertEquals(quantity + extraQuantity, meatLovers2.getQuantity());	
	}
}







