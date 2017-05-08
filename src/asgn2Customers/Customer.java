package asgn2Customers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import asgn2Exceptions.CustomerException;

/** An abstract class to represent a customer at the Pizza Palace restaurant.
 *  The Customer class is used as a base class of PickUpCustomer, 
 *  DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses overwrites
 *  the abstract method getDeliveryDistance. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.  
 * 
 * @author Person B
*/
public abstract class Customer {


	/**
	 *  This class represents a customer of the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.2. 
	 *  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment Specification
	 *  are violated. 
	 *  
  	 * <P> PRE: True
  	 * <P> POST: All field values are set
  	 * 
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY - The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param type - A human understandable description of this Customer type
	 * @throws CustomerException if supplied parameters are invalid 
	 * 
	 */
	final static int MIN_NAME_LENGTH = 1;
	final static int MAX_NAME_LENGTH = 20;
	final static int MOBILE_NUM_LENGTH = 10;
	final static double RESTAURANT_X = 0;
	final static double RESTAURANT_Y = 0;
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	String type;
	final static Set<String> TYPES= new HashSet<String>(Arrays.asList(new String[]{"Pick Up", "Driver Delivery", "Drone Delivery"}));
	
	public Customer(String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException{
		String nameRegex = "[a-zA-Z]+";
		String mobileRegex = "[0-9]+";
			//if length of the name is 0, or more than 20, or only contains space(or has non-alphabet characters), 
		if ((name.length()<MIN_NAME_LENGTH) || (name.length()>MAX_NAME_LENGTH) || !name.matches(nameRegex)){
			throw new CustomerException("Invalid customer name");
			//
		} else if (!TYPES.contains(type)){
			throw new CustomerException("Invalid customer type");
			//if the length of the mobile number is not 10, or if the mobile number does not start with '0' or if the string contains non-numeric characters
		} else if (mobileNumber.length()!= MOBILE_NUM_LENGTH || mobileNumber.toCharArray()[0] != '0' || !type.matches(mobileRegex)){
			throw new CustomerException("Invalid mobile number");
		}
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.locationX = locationX;
		this.locationY = locationY;
		this.type = type;
	}
	
	/**
	 * Returns the Customer's name.
	 * @return The Customer's name.
	 */
	public final String getName(){
		return this.name;
	}
	
	/**
	 * Returns the Customer's mobile number.
	 * @return The Customer's mobile number.
	 */
	public final String getMobileNumber(){
		return this.mobileNumber;
	}

	/**
	 * Returns a human understandable description of the Customer's type. 
	 * The valid alternatives are listed in Section 5.2 of the Assignment Specification. 
	 * @return A human understandable description of the Customer's type.
	 */
	public final String getCustomerType(){
		return this.type;
	}
	
	/**
	 * Returns the Customer's X location which is the number of blocks East or West 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's X location
	 */
	public final int getLocationX(){
		return this.locationX;
	}

	/**
	 * Returns the Customer's Y location which is the number of blocks North or South 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's Y location
	 */
	public final int getLocationY(){
		return this.locationY;
	}

	/**
	 * An abstract method that returns the distance between the Customer and 
	 * the restaurant depending on the mode of delivery. 
	 * @return The distance between the restaurant and the Customer depending on the mode of delivery.
	 */
	public abstract double getDeliveryDistance();

	
	
	/**
	 * Compares *this* Customer object with an instance of an *other* Customer object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 *  You do not need to test this method.
	 * 
	 * @return true if *this* Customer object and the *other* Customer object have the same values returned for 	
	 * getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
	 */
	@Override
	public boolean equals(Object other){
		Customer otherCustomer = (Customer) other;

		return ( (this.getName().equals(otherCustomer.getName()))  &&
			(this.getMobileNumber().equals(otherCustomer.getMobileNumber())) && 
			(this.getLocationX() == otherCustomer.getLocationX()) && 
			(this.getLocationY() == otherCustomer.getLocationY()) && 
			(this.getCustomerType().equals(otherCustomer.getCustomerType())) );			
	}

}
