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
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	
	private PizzaRestaurant restaurant;
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	final static int WIDTH = 900;
	final static int HEIGHT = 400;

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
	public PizzaGUI(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BorderLayout());
		
		//Table
		String[] columns = {"Customer Name","Mobile","Type","X/Y location","Delivery Distance"};
		DefaultTableModel customerModel = new DefaultTableModel(columns, 0);
		customerTable = new JTable(customerModel);
		displayPanel.add(new JScrollPane(customerTable), BorderLayout.WEST);
		String[] columns2 = {"Pizza Type","Quantity","Order Price","Order Cost","Order Profit"};
		DefaultTableModel pizzaModel = new DefaultTableModel(columns2, 0);
		pizzaTable = new JTable(pizzaModel);
		displayPanel.add(new JScrollPane(pizzaTable), BorderLayout.EAST);
		
		//Total value
		JPanel totalValuePanel = new JPanel();
		totalValuePanel.setLayout(new FlowLayout());
		JLabel totalDist = new JLabel("Total Distance:");
		JLabel totalProf = new JLabel("Total Profit:");
		totalDistance = new JTextField(10);
		totalProfit = new JTextField(10);
		totalValuePanel.add(totalDist);
		totalValuePanel.add(totalDistance);
		totalValuePanel.add(totalProf);
		totalValuePanel.add(totalProfit);
		displayPanel.add(totalValuePanel, BorderLayout.SOUTH);		
		add(displayPanel, BorderLayout.CENTER);
		
		//button
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

	
	@Override
	public void run() {
		restaurant = new PizzaRestaurant();
	}


	@Override
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
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			String filenameString = file.getName();
			String path = ".\\logs\\" + filenameString;
				try {
					restaurant.processLog(path);	
				} catch (CustomerException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Customer data error",
						    JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					System.exit(0);
				} catch (PizzaException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Pizza data error",
						    JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					System.exit(0);
				} catch (LogHandlerException e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Log error",
						    JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					System.exit(0);
				}
		} else if (returnVal == JFileChooser.CANCEL_OPTION){
		}
	}
	
	public void updateTables(){
		try {
			ArrayList<Object> data = new ArrayList<Object>();
			customerModel = (DefaultTableModel)customerTable.getModel();
			pizzaModel = (DefaultTableModel)pizzaTable.getModel();
			int customerOrders, pizzaOrders;
			if ((customerOrders = restaurant.getNumCustomerOrders()) != 0) {
				for (int i=0; i< customerOrders; i++){
					Customer customer = restaurant.getCustomerByIndex(i);
					data.add(customer.getName());
					data.add(customer.getMobileNumber());
					data.add(customer.getCustomerType());
					data.add(customer.getLocationX()+"/"+customer.getLocationY());
					data.add(customer.getDeliveryDistance());
					Object[] row = new Object[data.size()];
					row = data.toArray(row);
					customerModel.addRow(row);
					data.clear();
				}
				
				if ((pizzaOrders = restaurant.getNumPizzaOrders()) != 0) {
					for(int i=0; i < pizzaOrders; i++){
						Pizza pizza = restaurant.getPizzaByIndex(i);
						data.add(pizza.getPizzaType());
						data.add(pizza.getQuantity());
						data.add(pizza.getOrderPrice());
						data.add(pizza.getOrderCost());
						data.add(pizza.getOrderProfit());
						Object[] row = new Object[data.size()];
						row = data.toArray(row);
						pizzaModel.addRow(row);
						data.clear();
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "You must load a log file first", "Error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (CustomerException e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error displaying customer data",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		} catch (PizzaException e){
			
		} catch (Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error displaying data",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public void updateTotal(){
		//If log file has been loaded and processed get the total. Else, throw an error message
		if (((restaurant.getNumCustomerOrders()) != 0) || ((restaurant.getNumPizzaOrders()) != 0)){
			//Update total delivery distance
			totalDistance.setText(String.valueOf(restaurant.getTotalDeliveryDistance()));
			//Update total profit
			totalProfit.setText(String.valueOf(restaurant.getTotalProfit()));
		} else {
			JOptionPane.showMessageDialog(this,"You must load a log file first", "Error",
				    JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void reset(){
		if (((restaurant.getNumCustomerOrders()) != 0) || ((restaurant.getNumPizzaOrders()) != 0)){
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
		} else {
			JOptionPane.showMessageDialog(this,"You must load a log file first", "Error",
				    JOptionPane.ERROR_MESSAGE);
		}	
	}
}
