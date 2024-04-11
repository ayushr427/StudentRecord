package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import com.mysql.cj.jdbc.Blob;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JTextField;

public class SearchByName extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel Name;
	private JLabel Roll;
	private JLabel Sap;
	private JLabel Email;
	private JLabel Phone;
	private JLabel Adress;
	private JLabel Photo;
	private JLabel Program,Section;
	private String url;
    private String username;
    private String password;
    private Connection connection;
    private JTextField textField;
/**
	 * Create the panel.
	 */
	public SearchByName() {
		initDB();
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setSize(900, 600);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(251, 100, 144, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Roll No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(251, 140, 144, 25);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SAP ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(251, 180, 144, 25);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(251, 220, 144, 25);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Phone No");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(251, 260, 144, 25);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Program");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(251, 300, 144, 25);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Section");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(251, 340, 144, 25);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Address");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(251, 380, 144, 25);
		add(lblNewLabel_7);
		
		Name = new JLabel();
		Name.setBounds(405, 104, 180, 25);
		add(Name);
		
		Roll = new JLabel();
		Roll.setBounds(405, 140, 180, 25);
		add(Roll);
		
		Sap = new JLabel();
		Sap.setBounds(405, 176, 180, 25);
		add(Sap);
		
		Email = new JLabel();
		Email.setBounds(405, 212, 180, 25);
		add(Email);
		
		Phone = new JLabel();
		Phone.setBounds(405, 260, 180, 25);
		add(Phone);
		
		Adress = new JLabel();
		Adress.setBounds(405, 380, 180, 25);
		add(Adress);
		
		Program = new JLabel();
		
		Program.setBounds(405, 303, 180, 25);
		add(Program);
		
		Section = new JLabel();
		Section.setBounds(405, 343, 180, 25);
		add(Section);
		
		Photo = new JLabel("");
		Photo.setBounds(686, 107, 150, 180);
		add(Photo);
		
		textField = new JTextField();
		textField.setBounds(293, 11, 200, 25);
		add(textField);
		textField.setColumns(10);
		
		JButton searchRollNo = new JButton("Search Roll No");
		searchRollNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	String rollNo = textField.getText();
	                if (!rollNo.isEmpty()) {
	                	searchStudentBySapId(rollNo);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Please enter a roll number.");
	                }
			}
		});
		searchRollNo.setBounds(555, 12, 150, 23);
		add(searchRollNo);
		
		JButton searchName = new JButton("Search Name");
		searchName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rollNo = textField.getText();
                if (!rollNo.isEmpty()) {
                	searchStudentByName(rollNo);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Name.");
                }
                
			}
		});
		searchName.setBounds(555, 41, 150, 23);
		add(searchName);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		clear.setBounds(731, 29, 89, 23);
		add(clear);

	}
	 // Method to clear all fields
    private void clearFields() {
        Name.setText("");
        Roll.setText("");
        Sap.setText("");
        Email.setText("");
        Phone.setText("");
        Adress.setText("");
        Program.setText("");
        Section.setText("");
        Photo.setIcon(null);
    }

   
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
    private void searchStudentByName(String sapId) {
        try {
            // Create a SQL statement
            Statement statement = connection.createStatement();

            // SQL query to search for a student by SAP ID
            String sql = "SELECT * FROM student WHERE name = '" + sapId + "'";

            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
            // Check if result set has any data
            if (resultSet.next()) {
                // Retrieve student details from the result set
            	
                String studentName = resultSet.getString("name");
                Name.setText(studentName);
                String rollNo = resultSet.getString("roll_no");
                Roll.setText(rollNo);
                String email = resultSet.getString("email");
                Email.setText(email);
                String phoneNo = resultSet.getString("phone_no");
                Phone.setText(phoneNo);
                String program = resultSet.getString("program");
                Program.setText(program);
                String section = resultSet.getString("section");
                Section.setText(section);
                String address = resultSet.getString("address");
                Adress.setText(address);
                String sap = resultSet.getString("sap_id");
                Sap.setText(sap);
                java.sql.Blob photoBlob = resultSet.getBlob("photo");
                if (photoBlob != null) {
                    byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    ImageIcon imageIcon = new ImageIcon(photoBytes);
                    // Scale image to fit label if needed
                    Photo.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(Photo.getWidth(), Photo.getHeight(), Image.SCALE_DEFAULT)));
                }
            } else {
                // No data found
                System.out.println("No student found with Name ID: " + sapId);
            }

            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while searching for the student.");
        }
    }

	// Method to search student based on SAP ID
 // Method to search student based on SAP ID
    private void searchStudentBySapId(String sapId) {
        try {
            // Create a SQL statement
            Statement statement = connection.createStatement();

            // SQL query to search for a student by SAP ID
            String sql = "SELECT * FROM student WHERE sap_id = '" + sapId + "'";

            // Execute the query
            ResultSet resultSet = statement.executeQuery(sql);
            // Check if result set has any data
            if (resultSet.next()) {
                // Retrieve student details from the result set
            	
                String studentName = resultSet.getString("name");
                Name.setText(studentName);
                String rollNo = resultSet.getString("roll_no");
                Roll.setText(rollNo);
                String email = resultSet.getString("email");
                Email.setText(email);
                String phoneNo = resultSet.getString("phone_no");
                Phone.setText(phoneNo);
                String program = resultSet.getString("program");
                Program.setText(program);
                String section = resultSet.getString("section");
                Section.setText(section);
                String address = resultSet.getString("address");
                Adress.setText(address);
                String sap = resultSet.getString("sap_id");
                Sap.setText(sap);
                java.sql.Blob photoBlob = resultSet.getBlob("photo");
                if (photoBlob != null) {
                    byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    ImageIcon imageIcon = new ImageIcon(photoBytes);
                    // Scale image to fit label if needed
                    Photo.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(Photo.getWidth(), Photo.getHeight(), Image.SCALE_DEFAULT)));
                }
            } else {
                // No data found
                System.out.println("No student found with SAP ID: " + sapId);
            }

            // Close the statement and result set
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while searching for the student.");
        }
    }


}
