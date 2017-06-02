package asgn2Pizzas;

import java.time.LocalTime;


import asgn2Exceptions.PizzaException;

/**
 * 
 *  A class that represents a meat lovers pizza made at the Pizza Palace restaurant. 
 *  The meat lovers pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Matthew Holdsworth
 *
 */
public class MeatLoversPizza extends Pizza {
	//The type of pizza
	private static String type = "Meat Lovers";
	//The price of the pizza paid by the customer "$"
	private static double price = 12;

/**
 * 
 *  This class represents a meat lovers pizza made at the  Pizza Palace restaurant. The meat lovers pizza has certain
 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
 * 
	 * <P> PRE: TRUE
	 * <P> POST: All field values including the cost per pizza are set
 *
 * @param quantity - The number of pizzas ordered 
 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
 * @param deliveryTime - The time that the pizza was delivered to the customer
 * @throws PizzaException if:
	 * 1. the quantity is less than 1 or greater than 10
	 * 2. the order time and delivery times aren't set
	 * 3. the order time is before 19:00:00
	 * 4. the order time is after 23:59:00
	 * 5. the order time is after the delivery time
	 * 6. the order time plus 10 minutes is after the delivery time as it takes 10 minutes to cook
	 * 7. the delivery time is one hour after the order time
	 */
	public MeatLoversPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		//Calls the supercalss method using supplied parameters and fields
		super(quantity, orderTime, deliveryTime, type, price);
		//Clears the toppings array and adds the new toppings to it
		toppings.clear();
		toppings.add(PizzaTopping.TOMATO);
		toppings.add(PizzaTopping.CHEESE);
		toppings.add(PizzaTopping.BACON);
		toppings.add(PizzaTopping.PEPPERONI);
		toppings.add(PizzaTopping.SALAMI);
	}//end constructor
	
}//end MeatLoversPizza