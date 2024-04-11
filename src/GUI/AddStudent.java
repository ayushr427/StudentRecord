package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

public class AddStudent extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField Name;
	private JTextField Roll;
	private JTextField Sap;
	private JTextField Email;
	private JTextField Phone;
	private JTextField Adress;
	private JLabel Photo;
	private JComboBox Program,Section;
    private String url;
    private String username;
    private String password;

    // Database connection
    private Connection connection;
/**
	 * Create the panel.
	 */
	public AddStudent() {
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
		
		Name = new JTextField();
		Name.setBounds(405, 104, 180, 25);
		add(Name);
		Name.setColumns(10);
		
		Roll = new JTextField();
		Roll.setColumns(10);
		Roll.setBounds(405, 140, 180, 25);
		add(Roll);
		
		Sap = new JTextField();
		Sap.setColumns(10);
		Sap.setBounds(405, 176, 180, 25);
		add(Sap);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(405, 212, 180, 25);
		add(Email);
		
		Phone = new JTextField();
		Phone.setColumns(10);
		Phone.setBounds(405, 260, 180, 25);
		add(Phone);
		
		Adress = new JTextField();
		Adress.setColumns(10);
		Adress.setBounds(405, 380, 180, 25);
		add(Adress);
		
		Program = new JComboBox();
		Program.addItem("BCA");
		Program.addItem("B.TECH");
		Program.addItem("BBA");
		Program.addItem("B.Pharma");
		Program.addItem("BDS");
		Program.addItem("MCA");
		Program.addItem("M.TECH");
		Program.addItem("MBA");
		Program.addItem("M.Pharma");
		
		Program.setBounds(405, 303, 180, 25);
		add(Program);
		
		Section = new JComboBox();
		Section.addItem("A");
		Section.addItem("B");
		Section.addItem("C");
		Section.addItem("D");
		Section.addItem("E");
		Section.addItem("F");
		Section.addItem("G");
		Section.addItem("H");
		Section.addItem("I");
		Section.addItem("J");
		Section.addItem("K");
		Section.addItem("L");
		Section.addItem("M");
		
		
		Section.setBounds(405, 343, 180, 25);
		add(Section);
		
		Photo = new JLabel("");
		Photo.setBounds(686, 107, 150, 180);
		add(Photo);
		
		JButton Browse = new JButton("Browse ");
		Browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose Image");
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        // Read the selected image file
                        Image image = ImageIO.read(selectedFile);
                        // Resize the image to fit the Photo label
                        Image scaledImage = image.getScaledInstance(Photo.getWidth(), Photo.getHeight(), Image.SCALE_SMOOTH);
                        // Update the Photo label with the selected image
                        Photo.setIcon(new ImageIcon(scaledImage));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
			}
		});
		Browse.setBounds(723, 342, 89, 23);
		add(Browse);
		
		JButton AddStudent = new JButton("Save ");
		AddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveStudentData();
			}
		});
		AddStudent.setBounds(405, 450, 100, 25);
		add(AddStudent);

	}


	private void saveStudentData() {
	    try {
	        // Prepare SQL statement
	        String sql = "INSERT INTO student (sap_id ,name, roll_no,  email, phone_no, program, section, address, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(sql);

	        // Set parameters
	        statement.setString(1, Sap.getText());
	        statement.setString(2, Name.getText());
	        statement.setString(3, Roll.getText());
	        statement.setString(4, Email.getText());
	        statement.setString(5, Phone.getText());
	        statement.setString(6, Program.getSelectedItem().toString());
	        statement.setString(7, Section.getSelectedItem().toString());
	        statement.setString(8, Adress.getText());

	        // Set photo as medium blob
	        if (Photo.getIcon() != null) {
	            // Get the selected image icon
	            ImageIcon icon = (ImageIcon) Photo.getIcon();
	            // Get the image from the icon
	            Image image = icon.getImage();

	            // Convert ImageIcon to BufferedImage
	            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
	            bufferedImage.getGraphics().drawImage(image, 0, 0, null);

	            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
	                // Write the BufferedImage to a ByteArrayOutputStream
	                ImageIO.write(bufferedImage, "jpg", baos);
	                byte[] imageData = baos.toByteArray();

	                // Create a Blob object from the image data
	                Blob imageBlob = (Blob) connection.createBlob(); // Use MySQL Blob
	                imageBlob.setBytes(1, imageData);

	                // Set the Blob object as a blob parameter
	                statement.setBlob(9, imageBlob);
	            } catch (IOException | SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            statement.setNull(9, Types.BLOB); // No photo provided, set as NULL
	        }

	        // Execute statement
	        statement.executeUpdate();

	        // Close statement
	        statement.close();
	        resetFieldsAndShowSuccessMessage();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
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
	            createStudentTableIfNotExists();
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 private void createStudentTableIfNotExists() {
		    try {
		        // Check if the table exists
		        String checkTableQuery = "SELECT 1 FROM student LIMIT 1";
		        connection.prepareStatement(checkTableQuery).executeQuery();

		        // If the table exists, return
		        return;
		    } catch (SQLException e) {
		        // Table does not exist, create it
		        try {
		            String createTableQuery = "CREATE TABLE student (" +
		                    "sap_id VARCHAR(20) PRIMARY KEY," + // Making sap_id the primary key
		                    "name VARCHAR(255)," +
		                    "roll_no VARCHAR(20)," +
		                    "email VARCHAR(255)," +
		                    "phone_no VARCHAR(20)," +
		                    "program VARCHAR(50)," +
		                    "section VARCHAR(10)," +
		                    "address VARCHAR(255)," +
		                    "photo LONGBLOB" +
		                    ")";
		            connection.prepareStatement(createTableQuery).executeUpdate();
		            System.out.println("Student table created successfully.");
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		}
	 private void resetFieldsAndShowSuccessMessage() {
		    // Clear all text fields
		    Name.setText("");
		    Roll.setText("");
		    Sap.setText("");
		    Email.setText("");
		    Phone.setText("");
		    Adress.setText("");
		    // Reset program and section JComboBoxes to default selection
		    Program.setSelectedIndex(0);
		    Section.setSelectedIndex(0);
		    // Remove the displayed photo
		    Photo.setIcon(null);

		    // Show success message
		    JOptionPane.showMessageDialog(this, "Student added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	 

}
