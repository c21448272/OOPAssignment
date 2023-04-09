package Search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Scanner;

//control will be the subclass of JFrame(it will inherit everything from the JFrame class)
public class control extends JFrame implements ActionListener{
	
	// Declare instance variables for the GUI components
	JButton button1;
	JButton button2;
	JButton button3;

	JLabel label3;
	
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	
	ImageIcon image1;
	
	JFrame frame;
	
	JMenuBar mbar;
	JMenu fileMenu;
	JMenu searchMenu;
	
	JMenuItem openItem;
	JMenuItem exitItem;
	JMenuItem searchItem;
	
	int optionPane;
	
    String searchTerm;
	JTextField textfield;
	JTextArea resultArea;
	JScrollPane scroll;
 
	control(){
		    
		// Create the menu bar and menus
		mbar = new JMenuBar();
		fileMenu = new JMenu("File");
		searchMenu = new JMenu("Search");
		
		// Create the menu items and add them to the menus
		openItem = new JMenuItem("Open file");
		exitItem = new JMenuItem("Exit");
		searchItem = new JMenuItem("Search");
		
		openItem.addActionListener(this);
		exitItem.addActionListener(this);
		searchItem.addActionListener(this);
		
		image1 = new ImageIcon("logo.png"); //create an image icon
		
		// Create the result area for displaying search results
		resultArea = new JTextArea(8, 50);
		resultArea.setVisible(false);
		
		
		// Create the buttons and label for the GUI
		button1 = new JButton();
		button1.setText("Click to start Engine");
		button1.setBounds(250, 250, 350, 350);
		button1.addActionListener(this);
		button1.setForeground(Color.black);
		button1.setIconTextGap(-300);
		button1.setVisible(true);
		
		button2 = new JButton();
		button2.setText("Click to Exit Engine");
		button2.setBounds(250, 250, 350, 350);
		button2.addActionListener(this);
		button2.setForeground(Color.black);
		button2.setIconTextGap(-190);
		button2.setVisible(true);
		
		button3 = new JButton();
		button3.setText("Search");
		button3.addActionListener(this);
		button3.setBounds(0,0,10, 5);
		
		// Create the panels for holding the GUI components
		panel = new JPanel();//after button is clicked
		panel.setBackground(Color.black);
		panel.setVisible(false);
		
		panel2 = new JPanel();//after search bar is clicked
		panel2.setBackground(Color.gray);
		panel2.setVisible(false);
	
		
		panel3 = new JPanel();//added to the frame
		panel3.setBackground(Color.black);
		panel3.setVisible(true);
		
		label3 = new JLabel();
		label3.setText("Click 'file' for options. Click 'Search' to look for a term");
		label3.setFont(new Font("Eurostile",Font.PLAIN,15));//set font of text
		label3.setVerticalAlignment(JLabel.NORTH);//set position of label + image within label
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setForeground(Color.white);//set colour of text 
		label3.setVisible(false);
		
		frame = new JFrame(); // creates a frame
		frame.setTitle("Search Engine");// sets title of frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit out of application
		frame.setSize(1900, 1900); //sets the x-dimension and y-dimension of frame
		frame.setVisible(true); // make frame visible
		frame.setIconImage(image1.getImage());//change icon of frame
		frame.add(panel3);
		panel3.add(button1);// add button to the frame
		panel3.add(button2);
		panel2.add(resultArea);

		
		panel3.setLayout(new FlowLayout());
		frame.setJMenuBar(mbar);
		
		mbar.add(fileMenu);
		mbar.add(searchMenu);
		mbar.setVisible(false);
		
		fileMenu.add(openItem);
		fileMenu.add(exitItem);
		searchMenu.add(searchItem);
		
		textfield = new JTextField();
		textfield.setPreferredSize(new Dimension(250, 40));
		textfield.setAlignmentX(100);
		textfield.setVisible(false);
		
		
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// this Frame will listen for events
		if(e.getSource()==button1) {
			frame.add(panel);
			panel.add(label3);
			panel.setVisible(true);
			label3.setVisible(true);
			panel3.setVisible(false);
			mbar.setVisible(true);
		}
		if(e.getSource()==button2) {
			
			//JOptionPane = pop dialog box that informs users of something	
			optionPane = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 1);
			if(optionPane == 0) {
				JOptionPane.showMessageDialog(frame, "Have a good day" );
				System.exit(0);
			}
			if(optionPane == 1) {
				JOptionPane.showMessageDialog(frame, "Woah, almost left us" );
			}
		}
		
		
		if (e.getSource() == searchItem) {
		    panel2.add(button3);
		    panel2.add(textfield);
		    frame.add(panel2);
		    resultArea.setVisible(true);
		    panel2.setVisible(true);
		    textfield.setVisible(true);
		    panel.setVisible(false);
		}

		if (e.getSource() == button3) {
		    try {
				page();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		
		if (e.getSource() == openItem) {
		    JFileChooser fileChooser = new JFileChooser();
		    fileChooser.setCurrentDirectory(new File("files"));
		    int response = fileChooser.showOpenDialog(null);

		    if (response == JFileChooser.APPROVE_OPTION) {
		        File file = fileChooser.getSelectedFile();
		        try {
		            FileReader fileReader = new FileReader(file);
		            BufferedReader bufferedReader = new BufferedReader(fileReader);
		            String line;
		            while ((line = bufferedReader.readLine()) != null) {
		                JOptionPane.showMessageDialog(null,line,"File", JOptionPane.INFORMATION_MESSAGE);
		                
		            }
		            bufferedReader.close();
		        } catch (IOException ex) {
		            // Handle the exception
		            ex.printStackTrace();
		        }
		    }
		}
		
		if(e.getSource()==exitItem) {
			optionPane = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, 1);
			if(optionPane == 0) {
				JOptionPane.showMessageDialog(frame, "Have a good day" );
				System.exit(0);
			}
			if(optionPane == 1) {
				JOptionPane.showMessageDialog(frame, "Woah, almost left us" );
			}
		}
	}
	public void page() throws FileNotFoundException {
		searchTerm = textfield.getText(); // Get the search term from the textfield
	    File folder = new File("files");
	    File[] files = folder.listFiles();
	    boolean foundMatch = false; // Flag to indicate if a match was found
	    
	    int count = 0;
	    for (File file: files) {
	        if (file.isFile()) {
	            Scanner s1 = new Scanner(file);
	     
	            // Read each line of the file until the end of the file
	            while (s1.hasNext()) {
	                String line = s1.next();
	                String[] words = line.split(" "); // Split the line into words
	                for (int i = 0; i < words.length; i++) {
	                    if (words[i].toLowerCase().contains(searchTerm.toLowerCase().trim())) {// ignores leading/trailing whitespace
	                        foundMatch = true;
	                        count++;
	                    }
	                }
	            }

	            
	            if (!foundMatch) { // If no match is found, display an error message
	                JOptionPane.showMessageDialog(null, "No matches found in " + file.getName());
	            } else if (count > 0) {
	            	// Display the number of matches found for the file
	                resultArea.append(file.getName() + ": " + count + " matches\n");
	            }
	            foundMatch = false;    
	            count = 0;
	        }
	    }//end for loop
	    	              
	}//end void
}//end control