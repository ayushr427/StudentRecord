package GUI;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UpdateDelete extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField Name;
    private JTextField Roll;
    private JTextField Sap;
    private JTextField Email;
    private JTextField Phone;
    private JTextField Address;
    private JLabel Photo;
    private JComboBox<String> Program, Section;
    private String url;
    private String username;
    private String password;
    private Connection connection;
    private JTextField searchField;

    public UpdateDelete() {
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
        Sap.setEditable(false); // Disable SAP ID field
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

        Address = new JTextField();
        Address.setColumns(10);
        Address.setBounds(405, 380, 180, 25);
        add(Address);

        Program = new JComboBox<>();
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

        Section = new JComboBox<>();
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

        JButton UpdateStudent = new JButton("Update");
        UpdateStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudentData();
            }
        });
        UpdateStudent.setBounds(300, 450, 100, 25);
        add(UpdateStudent);

        searchField = new JTextField();
        searchField.setColumns(10);
        searchField.setBounds(192, 24, 180, 25);
        add(searchField);

        JButton search = new JButton("Search By SAP");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentBySAP();
            }
        });
        search.setBounds(438, 25, 150, 23);
        add(search);

        JButton Delete = new JButton("Delete");
        Delete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		deleteStudentBySAP();
        	}
        });
        Delete.setBounds(518, 451, 100, 25);
        add(Delete);

    }

    private void searchStudentBySAP() {
        String sapId = searchField.getText();
        try {
            String query = "SELECT * FROM student WHERE sap_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sapId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Sap.setText(resultSet.getString("sap_id"));
                Name.setText(resultSet.getString("name"));
                Roll.setText(resultSet.getString("roll_no"));
                Email.setText(resultSet.getString("email"));
                Phone.setText(resultSet.getString("phone_no"));
                Program.setSelectedItem(resultSet.getString("program"));
                Section.setSelectedItem(resultSet.getString("section"));
                Address.setText(resultSet.getString("address"));
                // Display the photo if available
                Blob photoBlob = (Blob) resultSet.getBlob("photo");
                if (photoBlob != null) {
                    byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
                    ImageIcon icon = new ImageIcon(photoBytes);
                    Photo.setIcon(icon);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Student not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateStudentData() {
        try {
            String sapId = Sap.getText();
            String name = Name.getText();
            String roll = Roll.getText();
            String email = Email.getText();
            String phone = Phone.getText();
            String program = (String) Program.getSelectedItem();
            String section = (String) Section.getSelectedItem();
            String address = Address.getText();
            Blob photoBlob = null; // Initialize to null
            if (Photo.getIcon() != null) {
                ImageIcon icon = (ImageIcon) Photo.getIcon();
                Image image = icon.getImage();
                BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
                bufferedImage.getGraphics().drawImage(image, 0, 0, null);
                try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                    ImageIO.write(bufferedImage, "jpg", baos);
                    byte[] imageData = baos.toByteArray();
                    photoBlob = connection.createBlob();
                    photoBlob.setBytes(1, imageData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String query = "UPDATE student SET name=?, roll_no=?, email=?, phone_no=?, program=?, section=?, address=?, photo=? WHERE sap_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, roll);
            statement.setString(3, email);
            statement.setString(4, phone);
            statement.setString(5, program);
            statement.setString(6, section);
            statement.setString(7, address);
            if (photoBlob != null) {
                statement.setBlob(8, photoBlob);
            } else {
                statement.setNull(8, Types.BLOB);
            }
            statement.setString(9, sapId);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update student!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
	        } catch (IOException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 private void deleteStudentBySAP() {
		    String sapId = searchField.getText();
		    try {
		        String query = "DELETE FROM student WHERE sap_id=?";
		        PreparedStatement statement = connection.prepareStatement(query);
		        statement.setString(1, sapId);
		        int rowsDeleted = statement.executeUpdate();

		        if (rowsDeleted > 0) {
		            JOptionPane.showMessageDialog(this, "Student deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
		            // Clear the fields after successful deletion
		            Sap.setText("");
		            Name.setText("");
		            Roll.setText("");
		            Email.setText("");
		            Phone.setText("");
		            Program.setSelectedIndex(0);
		            Section.setSelectedIndex(0);
		            Address.setText("");
		            Photo.setIcon(null);
		        } else {
		            JOptionPane.showMessageDialog(this, "Failed to delete student!", "Error", JOptionPane.ERROR_MESSAGE);
		        }

		        statement.close();
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		}


  
}
