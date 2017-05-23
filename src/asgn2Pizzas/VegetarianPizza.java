package asgn2Pizzas;

import java.time.LocalTime;


import asgn2Exceptions.PizzaException;


/**
 * 
 *  A class that represents a vegetarian pizza made at the Pizza Palace restaurant. 
 *  The vegetarian pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Matthew Holdsworth
 *
 */
public class VegetarianPizza extends Pizza {
	//The type of pizza
	private static String type = "Vegetarian";
	//The price of the pizza paid by the customer "$"
	private static double price = 10;
	
	/**
	 * 
	 *  This class represents a vegetarian pizza made at the  Pizza Palace restaurant. The vegetarian pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
     * <P> PRE: TRUE
	 * <P> POST: All field values including the cost per pizza are set
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 *
	 */
	public VegetarianPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		//Calls the supercalss method using supplied parameters and fields
		super(quantity, orderTime, deliveryTime, type, price);
		//Clears the toppings array and adds the new toppings to it
		toppings.clear();
		toppings.add(PizzaTopping.TOMATO);
		toppings.add(PizzaTopping.CHEESE);
		toppings.add(PizzaTopping.EGGPLANT);
		toppings.add(PizzaTopping.MUSHROOM);
		toppings.add(PizzaTopping.CAPSICUM);
	}//end constructor
	
}//end VegetarianPizza