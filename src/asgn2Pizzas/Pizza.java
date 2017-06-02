package asgn2Pizzas;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Exceptions.PizzaException;


/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Matthew Holdsworth
 *
 */
public abstract class Pizza  {
	//Total profit made on the order
	private double profit;
	//The cost of an order
	private double cost;
	//Fields supplied by the subclass
	private double price;
	private String type;
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	//Strings of each time for string comparison
	private String orderTimeString;
	private String deliveryTimeString;
	//Minimum time for an order
	private String minOrderTime = "19:00:00";
	//Maximum time for an order
	private String maxOrderTime = "22:59:00";
	//Minimum and maximum order time strings as LocalTime type
	private LocalTime minimum = LocalTime.parse(minOrderTime);
	private LocalTime maximum = LocalTime.parse(maxOrderTime);
	//Boolean to determine if a topping is on a pizza
	private boolean onPizza;
	//Array list accessed by the subclass
	protected ArrayList<PizzaTopping> toppings = new ArrayList<PizzaTopping>();
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if:
	 * 1. the quantity is less than 1 or greater than 10
	 * 2. the order time and delivery times aren't set
	 * 3. the order time is before 19:00:00
	 * 4. the order time is after 23:59:00
	 * 5. the order time is after the delivery time
	 * 6. the order time plus 10 minutes is after the delivery time as it takes 10 minutes to cook
	 * 7. the delivery time is one hour after the order time
	 */
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException {
		//Conversion to strings
		orderTimeString = orderTime.toString();
		deliveryTimeString = deliveryTime.toString();
		//Throw exception if quantity is less than 1 or greater than 10, if the string is empty, or if it is at an unacceptable time
		if ((quantity < 1) || (quantity > 10) || (orderTimeString.isEmpty()) || (deliveryTimeString.isEmpty()) ||
				(orderTime.isBefore(minimum)) ||(orderTime.isAfter(maximum)) ||(orderTime.isAfter(deliveryTime)) ||
				(orderTime.plusMinutes(10).isAfter(deliveryTime)) || (deliveryTime.isAfter(orderTime.plusMinutes(59)))) {
			throw new PizzaException();
		} else {
			//Set the fields (with parsing) to the parameters passed by the subclass
			this.quantity = quantity;
			this.orderTime = LocalTime.parse(orderTimeString);
			this.deliveryTime = LocalTime.parse(deliveryTimeString);
			this.type = type;
			this.price = price;
		}//end if-else
	}//end constructor

	/**
	 * Calculates how much a pizza would cost to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza() {
		//Sets the cost field to zero
		this.cost = 0;
		//For each topping in the arraylist toppings,
		//get the cost associated with it and add it to cost
		for (PizzaTopping topping : toppings) {
			this.cost += topping.getCost();
		}//end for loop
	}//end CalculateCostPerPizza
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		return this.cost;
	}//end

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		return this.price;
	}//end

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		//Calculates a new cost per pizza
		calculateCostPerPizza();
		//Returns cost per pizza multiplied by quantity
		return this.cost * this.quantity;
	}//end GetOrderCost
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		return this.price * this.quantity;
	}//end
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		return profit = (getOrderPrice() - getOrderCost());				
	}//end

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		//Initializes onPizza boolean variable
		onPizza = true;
		//If the arraylist toppings contains the topping passed,
		//return onPizza, else return !onPizza
		if (toppings.contains(topping)) {
			return onPizza;
		} else {
			return !onPizza;
		}//end if-else
	}//end ContainsTopping(PizzaTopping)
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return this.quantity;
	}//end 

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return this.type;
	}//end

	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}//end
	
}//end Pizza class