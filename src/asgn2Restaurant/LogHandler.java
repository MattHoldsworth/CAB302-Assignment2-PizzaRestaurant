package asgn2Restaurant;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {
	
	final static int LOG_STRING_NUM_PARAMETERS = 9;
	final static String COMMA = ",";
	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * @throws FileNotFoundException 
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{		 
		 try{
			 ArrayList<Customer> customers = new ArrayList<Customer>();
			 BufferedReader br = new BufferedReader(new FileReader(filename));
			 String line = br.readLine(); 
			 while (line != null){
				 customers.add(createCustomer(line));
				 line = br.readLine();	 
			 }
			 br.close();
			 return customers;
		 } catch (CustomerException e){
			 throw new CustomerException(e.getMessage());
		 } catch (Exception e){
			 throw new LogHandlerException(e.getMessage());
		 }
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException {
		try {
			ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String input = reader.readLine();
			while (input != null) {
				pizzas.add(createPizza(input));
				input = reader.readLine();
			}
			reader.close();
			return pizzas;
		} catch (PizzaException e) {
			throw new PizzaException(e.getMessage());
		} catch (Exception e) {
			throw new LogHandlerException(e.getMessage());
		}
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		if (line == "" || !line.contains(COMMA)){
			throw new LogHandlerException("The line is empty or is not comma separated");
		}
		String[] parameters = line.split(COMMA);
		if (parameters.length != LOG_STRING_NUM_PARAMETERS){
			throw new LogHandlerException("One of the line does not contain the right number of parameters");
		}
		
		try {
			String name = parameters[2];
			String mobile = parameters[3];
			String code = parameters[4];
			int locX = Integer.parseInt(parameters[5]);
			int locY = Integer.parseInt(parameters[6]);
			
			Customer newCustomer = CustomerFactory.getCustomer(code, name, mobile, locX, locY);
			return newCustomer;
		} catch (CustomerException e){
			throw new CustomerException(e.getMessage());
		} catch (Exception e){
			//Parsing related exceptions (e.g. locationX or locationY containing non-numeric characters)
			throw new LogHandlerException("Parsing error. Incorrect locationX or locationY");
		}
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		if (line == "" || !line.contains(COMMA)){
			throw new LogHandlerException("The line is empty or is not comma separated");
		}
		String[] parameters = line.split(COMMA);
		if (parameters.length != LOG_STRING_NUM_PARAMETERS){
			throw new LogHandlerException("A line does not contain the right number of parameters");
		}
		try {
			String pizzaCode = parameters[7];
			int quantity = Integer.parseInt(parameters[8]);
			LocalTime orderTime = LocalTime.parse(parameters[0]);
			LocalTime deliveryTime = LocalTime.parse(parameters[1]);
			
			Pizza newPizza = PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
			return newPizza;
		} catch (PizzaException e) {
			throw new PizzaException(e.getMessage());
		} catch (Exception e) {
			throw new LogHandlerException("Parsing error. Incorrect orderTime, deliveryTime or quantity");
		}
	}

}
