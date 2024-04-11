package GUI;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Setting");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                dispose();
			}
		});
	
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
//		********************************************************************Product Panel******************************************************************************************
		
		AddStudent panel = new AddStudent();
		tabbedPane.addTab("ADD STUDENT", null, panel, null);
	
      
       
        // Add the table to a scroll pane
     
//		********************************************************************Bill History  Panel******************************************************************************************
		SearchByName panel_1 = new SearchByName();
		tabbedPane.addTab("SEARCH", null, panel_1, null);
		
		RegisteredStudent panel_2 = new RegisteredStudent();
		tabbedPane.addTab("Count", null, panel_2, null);
		
		UpdateDelete panel_3 = new UpdateDelete();
		tabbedPane.addTab("Update", null, panel_3, null);
		
		setVisible(true);
	}
	
}



