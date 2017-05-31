package asgn2Customers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asgn2Exceptions.CustomerException;

/** An abstract class to represent a customer at the Pizza Palace restaurant.
 *  The Customer class is used as a base class of PickUpCustomer, 
 *  DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses overwrites
 *  the abstract method getDeliveryDistance. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.  
 * 
 * @author Gyeongmin Jee
*/
public abstract class Customer {

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
	 * @throws CustomerException if 
	 * 1. the length of the name is below 0 or more than 20,
	 * 2. the name contains characters other than alphabet and spaces,
	 * 3. the name contains only white spaces
	 * 4. the type is not one of Pick Up, Driver Delivery or Drone Delivery
	 * 5. the mobile number length is not 10, does not begin with 0 or contains characters other than numbers
	 * 6. the location does not match the type
	 * 7. the location is beyond valid distance from the restaurant
	 */
	
	public Customer(String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException{
		//Patterns for string input validation
		String nameRegex = "[a-zA-Z' ]*[a-zA-Z]+[a-zA-Z ]*";
    	Pattern namePattern = Pattern.compile(nameRegex);
    	Matcher nameMatcher = namePattern.matcher(name);
		String mobileRegex = "[0-9]+";
		Pattern mobilePattern = Pattern.compile(mobileRegex);
    	Matcher mobileMatcher = mobilePattern.matcher(mobileNumber);
		//Throw exception if length of the name is 0, or more than 20
		if ((name.length()<MIN_NAME_LENGTH) || (name.length()>MAX_NAME_LENGTH)){
			throw new CustomerException("Invalid customer name length");
		//Throw exception if the name contains characters other than alphabet or spaces and when there are only white spaces
		} else if (!nameMatcher.matches()){
			throw new CustomerException("Invalid customer name: Contains non alphabet characters");
		//Throw exception if the the type is not valid
		} else if (!type.equals("Pick Up") && !type.equals("Driver Delivery") && !type.equals("Drone Delivery")){
			throw new CustomerException("Invalid customer type");
		//if the length of the mobile number is not 10, or if the mobile number does not start with '0' or if the string contains non-numeric characters
		} else if (mobileNumber.length()!= MOBILE_NUM_LENGTH || mobileNumber.toCharArray()[0] != '0' || !mobileMatcher.matches()){
			throw new CustomerException("Invalid mobile number");
		} else if (type == "Pick Up"){
			if (locationX != 0 || locationY != 0){
				throw new CustomerException("Invalid pick up customer location");
			}
		} else if (type == "Driver Delivery"){
			if (locationX == 0 && locationY == 0){
				throw new CustomerException("Customer name: " + name + " Invalid driver delivery customer location");
			}
		} else if (type == "Drone Delivery"){
			if (locationX == 0 && locationY == 0){
				throw new CustomerException("Invalid drone delivery customer location");
			}
		}
		//Test that the location is not beyond acceptable range.
		if (locationX > 10 || locationX < -10 || locationY > 10 || locationY < -10){
			throw new CustomerException("Customer located more than 10 blocks north/south/west/east of restaurant");
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
