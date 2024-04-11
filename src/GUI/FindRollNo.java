package GUI;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;

public class FindRollNo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField Name;
	private JTextField Roll;
	private JTextField Sap;
	private JTextField Email;
	private JTextField Phone;
	private JTextField Adress;
	private JLabel Photo;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public FindRollNo() {
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
		
		JComboBox Program = new JComboBox();
		Program.addItem("BCA");
		Program.addItem("B.TECT");
		Program.addItem("BBA");
		Program.addItem("B.Pharma");
		Program.addItem("BDS");
		Program.addItem("MCA");
		Program.addItem("M.TECH");
		Program.addItem("MBA");
		Program.addItem("M.Pharma");
		
		Program.setBounds(405, 303, 180, 25);
		add(Program);
		
		JComboBox Section = new JComboBox();
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
			}
		});
		AddStudent.setBounds(405, 450, 100, 25);
		add(AddStudent);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(439, 37, 85, 21);
		add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("Search by Rollno");
		lblNewLabel_8.setBounds(315, 15, 106, 13);
		add(lblNewLabel_8);
		
		textField = new JTextField();
		textField.setBounds(286, 38, 144, 19);
		add(textField);
		textField.setColumns(10);

	}
}
