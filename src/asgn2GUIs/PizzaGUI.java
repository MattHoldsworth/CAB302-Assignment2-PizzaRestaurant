package asgn2GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;


/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a 멶ummy� class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Matthew Holdsworth and Gyeongmin Jee
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	
	private PizzaRestaurant restaurant;
		
	final static int WIDTH = 900;
	final static int HEIGHT = 400;
	//A format to limit the decimal places for float values
	final static DecimalFormat decimalFormat = new DecimalFormat("#0.00"); 
	//A boolean value to indicate whether the log file has been loaded properly
	Boolean loaded = false;
	JTable customerTable;
	JTable pizzaTable;	
	DefaultTableModel customerModel;
	DefaultTableModel pizzaModel;	
	JTextField totalDistance;
	JTextField totalProfit;	
	JButton open;
	JButton view;
	JButton total;
	JButton reset;
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());
		
		//Create heading for the two tables
		JPanel headingPanel = new JPanel();
		JLabel customerHeading = new JLabel("Customer Details");
		JLabel pizzaHeading = new JLabel("Pizza Details");
		headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.X_AXIS));
		headingPanel.add(Box.createHorizontalGlue());
		headingPanel.add(customerHeading);
		headingPanel.add(Box.createHorizontalGlue());
		headingPanel.add(Box.createHorizontalGlue());
		headingPanel.add(pizzaHeading);
		headingPanel.add(Box.createHorizontalGlue());
		displayPanel.add(headingPanel, BorderLayout.NORTH);
		
		//Create a model for the jtable that disables users from editing the table contents
		class myCustomerModel extends DefaultTableModel{
			public myCustomerModel(String[] columns, int i){
				super(columns, i);
			}
			public boolean isCellEditable(int row, int column){  
	          return false;  
			}
		}
		
		//Create customer and pizza order tables
		String[] columns = {"Customer Name","Mobile","Type","X/Y location","Delivery Distance"};
		customerTable = new JTable(new myCustomerModel(columns, 0));
		displayPanel.add(new JScrollPane(customerTable), BorderLayout.WEST);
		String[] columns2 = {"Pizza Type","Quantity","Order Price","Order Cost","Order Profit"};
		pizzaTable = new JTable(new myCustomerModel(columns2, 0));
		displayPanel.add(new JScrollPane(pizzaTable), BorderLayout.EAST);
		
		//Create total value fields
		JPanel totalValuePanel = new JPanel();
		totalValuePanel.setLayout(new FlowLayout());
		JLabel totalDist = new JLabel("Total Distance:");
		JLabel totalProf = new JLabel("Total Profit:");
		totalDistance = new JTextField(10);
		totalDistance.setEditable(false);
		totalProfit = new JTextField(10);
		totalProfit.setEditable(false);
		totalValuePanel.add(totalDist);
		totalValuePanel.add(totalDistance);
		totalValuePanel.add(totalProf);
		totalValuePanel.add(totalProfit);
		displayPanel.add(totalValuePanel, BorderLayout.SOUTH);		
		add(displayPanel, BorderLayout.CENTER);
		
		//Create buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());	
		open = new JButton("open");
		open.addActionListener(this);
		view = new JButton("view");
		view.addActionListener(this);
		total = new JButton("total");
		total.addActionListener(this);
		reset = new JButton("reset");
		reset.addActionListener(this);
		buttonPanel.add(open);
		buttonPanel.add(view);
		buttonPanel.add(total);
		buttonPanel.add(reset);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setSize(new Dimension(WIDTH, HEIGHT));
		setLocation(new Point(200, 200));
	    pack();
		setVisible(true);
	}

	
	public void run() {
		restaurant = new PizzaRestaurant();
	}


	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		if (buttonString == "open"){
			openFile();			
		} else if (buttonString == "view"){
			updateTables();
		} else if (buttonString == "total"){
			updateTotal();
		} else if (buttonString == "reset"){
			reset();
		}
	}

	public void openFile(){
		if (!loaded){		
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				String filenameString = file.getName();
				String path = ".\\logs\\" + filenameString;
					try {
						restaurant.processLog(path);	
						//Display a success message and set loaded to true
						JOptionPane.showMessageDialog(this, "Successfully loaded a log file");
						loaded = true;
					} catch (CustomerException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Customer data error",
							    JOptionPane.ERROR_MESSAGE);
						loaded = false;
					} catch (PizzaException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Pizza data error",
							    JOptionPane.ERROR_MESSAGE);
						loaded = false;
					} catch (LogHandlerException e) {
						JOptionPane.showMessageDialog(this, e.getMessage(), "Log error",
							    JOptionPane.ERROR_MESSAGE);
						loaded = false;
					}
			}
		//If a log file is already loaded, show an error message
		} else {
			JOptionPane.showMessageDialog(this, "Please reset before loading another file", "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void updateTables(){
		try {
			ArrayList<Object> data = new ArrayList<Object>();
			int customerOrders, pizzaOrders;
			//Get reference to the model for customer table and pizza table to edit.
			customerModel = (DefaultTableModel)customerTable.getModel();
			pizzaModel = (DefaultTableModel)pizzaTable.getModel();
			if (loaded) {
				//Fill customer table
				if ((customerOrders = restaurant.getNumCustomerOrders()) != 0){
					for (int i=0; i< customerOrders; i++){
						Customer customer = restaurant.getCustomerByIndex(i);
						data.add(customer.getName());
						data.add(customer.getMobileNumber());
						data.add(customer.getCustomerType());
						data.add(customer.getLocationX()+"/"+customer.getLocationY());
						//Round the distance to 2 decimal places
						String distanceFormatted = decimalFormat.format(customer.getDeliveryDistance());
						data.add(distanceFormatted + " blocks");
						Object[] row = new Object[data.size()];
						row = data.toArray(row);
						customerModel.addRow(row);
						data.clear();
					}
				}
				//Fill pizza table
				if ((pizzaOrders = restaurant.getNumPizzaOrders()) != 0) {
					for(int i=0; i < pizzaOrders; i++){
						Pizza pizza = restaurant.getPizzaByIndex(i);
						data.add(pizza.getPizzaType());
						data.add(pizza.getQuantity());
						data.add(pizza.getOrderPrice());
						String costFormatted = decimalFormat.format(pizza.getOrderCost());
						data.add("$"+costFormatted);
						String profitFormatted = decimalFormat.format(pizza.getOrderProfit());
						data.add("$"+profitFormatted);
						Object[] row = new Object[data.size()];
						row = data.toArray(row);
						pizzaModel.addRow(row);
						data.clear();
					}
				}
				JOptionPane.showMessageDialog(this, "Successfully displayed the data");
			//If log file is not loaded, throw an error message
			} else {
				JOptionPane.showMessageDialog(this, "You must load a log file first", "Error",
					    JOptionPane.ERROR_MESSAGE);
			}			
		} catch (CustomerException e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error displaying customer data",
				    JOptionPane.ERROR_MESSAGE);
			loaded = false;
		} catch (PizzaException e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error displaying Pizza data",
				    JOptionPane.ERROR_MESSAGE);
			loaded = false;
		} catch (Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error displaying data.",
				    JOptionPane.ERROR_MESSAGE);
			loaded = false;
		}
	}
	
	public void updateTotal(){
		if (loaded){
			//Update total delivery distance
			String totalDistanceFormatted = decimalFormat.format(restaurant.getTotalDeliveryDistance());
			totalDistance.setText(totalDistanceFormatted + " blocks");
			//Update total profit
			String totalProfitFormatted = decimalFormat.format(restaurant.getTotalProfit());
			totalProfit.setText('$'+totalProfitFormatted);
			JOptionPane.showMessageDialog(this, "Successfully calculated the total");
		} else {
			JOptionPane.showMessageDialog(this,"You must load a log file first", "Error",
				    JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void reset(){
		if (loaded){
			restaurant.resetDetails();
			if (customerModel.getRowCount() > 0 && pizzaModel.getRowCount() > 0){
				for (int i= customerModel.getRowCount()-1; i > -1 ; i--){
					customerModel.removeRow(i);
				}
				
				for (int i=pizzaModel.getRowCount()-1; i > -1 ; i--){
					pizzaModel.removeRow(i);
				}
			}
			totalDistance.setText("");
			totalProfit.setText("");
			JOptionPane.showMessageDialog(this, "Reset successful");
			loaded = false;
		//If not loaded, throw an error message
		} else {
			JOptionPane.showMessageDialog(this,"You must load a log file first", "Error",
				    JOptionPane.ERROR_MESSAGE);
		}	
	}
}
