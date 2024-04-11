package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class RegisteredStudent extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private Connection connection;
    private String url;
    private String username;
    private String password;
    JLabel Count;
    public RegisteredStudent() {
        setSize(900, 600);
        setLayout(null);
        
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("BCA");
        comboBox.addItem("B.TECH");
        comboBox.addItem("BBA");
        comboBox.addItem("B.Pharma");
        comboBox.addItem("BDS");
        comboBox.addItem("MCA");
        comboBox.addItem("M.TECH");
        comboBox.addItem("MBA");
        comboBox.addItem("M.Pharma");
        comboBox.addItem("ALL");
        comboBox.setBounds(243, 11, 180, 25);
        add(comboBox);
        
        JButton search = new JButton("Search Student");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentsByProgram((String) comboBox.getSelectedItem());
            }
        });
        search.setBounds(456, 12, 120, 23);
        add(search);
        
        table = new JTable();
        table.setBounds(25, 80, 850, 350);
        add(table);
        
        Count = new JLabel("");
        Count.setBounds(450, 441, 300, 25);
        add(Count);
        
        // Initialize the database connection
        initDB();
    }
    
    // Method to establish the database connection
	 private void initDB() {
	        try {
	            // Load properties file
	            Properties prop = new Properties();
	            prop.load(new FileInputStream("config.properties"));

	            // Retrieve connection details
	            url = prop.getProperty("db.url");
	            username = prop.getProperty("db.username");
	            password = prop.getProperty("db.password");

	            // Establish database connection
	            connection = DriverManager.getConnection(url, username, password);
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	        }
	    }

    // Method to search students by program and populate the table
    private void searchStudentsByProgram(String program) {
        try {
            // Create a SQL statement
        	PreparedStatement statement;
        	if(program.equals("ALL")) {
        		System.out.println("all is called");
        		 String sql = "SELECT * FROM student";
                 statement = connection.prepareStatement(sql);
            }
            else {
            	 String sql = "SELECT * FROM student WHERE program = ?";
                 statement = connection.prepareStatement(sql);
                 statement.setString(1, program);
            }
           
            
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Create a table model to hold the data
            DefaultTableModel model = new DefaultTableModel();
            table.setModel(model);
            
            // Add column names to the model
            model.addColumn("Name");
            model.addColumn("Roll No");
            model.addColumn("SAP ID");
            model.addColumn("Email");
            model.addColumn("Phone No");
            model.addColumn("Address");
            model.addColumn("Program");
            model.addColumn("Section");
            int count =0;
            // Add rows to the model from the result set
            System.out.println();
            while (resultSet.next()) {
            	count++;
                Object[] row = {
                    resultSet.getString("name"),
                    resultSet.getString("roll_no"),
                    resultSet.getString("sap_id"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_no"),
                    resultSet.getString("address"),
                    resultSet.getString("program"),
                    resultSet.getString("section")
                };
                model.addRow(row);
            }
            if(program.equals("ALL")){
            	Count.setText("Total Number of Students Are : "+count);
            }
            else {
            	Count.setText("Number of Student registered in "+program+" : "+count);
            }
            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
