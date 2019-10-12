package flipmouse_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Main{
	static int baudValue;
	
	static SerialComm arduino;
	static JFrame frame = new JFrame();
	static JLabel config = new JLabel("Configuration Setup of Flipmouse");
	//static JLabel baud = new JLabel("Baud Rate");
	static JButton start= new JButton("Start/Refresh");
	static JButton connect = new JButton("Connect");
	static JButton sip_puff = new JButton("Sip & Puff");
	static JButton force_calib = new JButton("Force caliberation");
	static PortSetup portList = new PortSetup();
	static JPanel topPanel = new JPanel();
	static JComboBox<String> cb; 
	static JButton know = new JButton("Get Baud Rate of Connected Device");
	static JTextArea console = new JTextArea("hello i am console");
	static JPanel pane = new JPanel();
	static JPanel pane1 = new JPanel();
	//static JButton on = new JButton("ON");
	//static JButton off = new JButton("OFF");
	static JPanel mainPanel = new JPanel();
	 protected JCheckBox addTimeStampBox;
	

	public static void main(String[] args) {

		
		//frame.setLayout(new BorderLayout());
	    //JLabel background=new JLabel(new ImageIcon("/home/ashiq/Downloads/bg1.jpeg"));
	    //frame.add(background);
	    //background.setLayout(new FlowLayout());
	   
		menuBar();	
		setGui();
		frame.setResizable(false);
		
		sip_puff.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e) {
				sendCommand("*#*sp");
			
			}
		});
	
       force_calib.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e) {
				sendCommand("exit");
				
			}
		});
       
       know.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e) {
				 // console.setText("Baud Rate of connected Device is : " + Integer.toString(arduino.get_Baud()));
				//redirectSystemStreams();
				console.setText(arduino.serialRead());
			}
		});
      
    
     
		
       
	}
	
	
	public static void menuBar() { 
		
		portList.refreshPort(); //to get all available ports
			
		baudRateList();
				
				start.addActionListener(new ActionListener(){
					@Override public void actionPerformed(ActionEvent e) {
						
							portList.refreshPort();
							
				}
					
				});
				connect.addActionListener(new ActionListener(){
					
					@Override public void actionPerformed(ActionEvent e) {
						if(connect.getText().equals("Connect")) {
							arduino = new SerialComm(portList.getSelectedItem().toString(), baudValue);
							if(arduino.Open_Connection()) {
								connect.setText("Disconnect");
								buttonEnabler(true, false);
								
						
							}			
				      }
						else {
							arduino.close_Connection();
							connect.setText("Connect");
							buttonEnabler(false,true);
							
						}
				 
			     }
		       });				
				
	      }
		
				public static void setGui() {
					
					
					frame.setTitle("Flipmouse GUI");
					frame.setSize(1000,600);	
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setForeground(Color.black);
					topPanel.setBackground(Color.black);
					mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
					

					sip_puff.setEnabled(false);
					force_calib.setEnabled(false);
					console.setEnabled(false);
					know.setEnabled(false);
					
					config.setForeground(Color.white);								
					console.setForeground(Color.black);
					
					topPanel.add(start);
					topPanel.add(config);
					topPanel.add(connect);
					topPanel.add(portList);
					topPanel.add(cb);
					topPanel.add(know);
					topPanel.add(sip_puff);
					topPanel.add(force_calib);
					pane.add(console);
					
					
					mainPanel.add(topPanel);		
					mainPanel.add(pane);
					frame.add(mainPanel);
					//frame.pack();
					//frame.getContentPane();
					frame.setVisible(true);
					
					
					
				}
				
			public static void buttonEnabler(boolean a, boolean b) { //to switch between Connect and Disconnect methods without conflict
				sip_puff.setEnabled(a);
				force_calib.setEnabled(a);
				start.setEnabled(b);
				portList.setEnabled(b);
				console.setEnabled(a);
				know.setEnabled(a);
		
				
			}
			
			public static void sendCommand(String value) { //function to send commands directly
				
				arduino.serialWrite(value);
			}
			
			public static void baudRateList() {
			
				String availableBaudRate[] = {"4200","9600","14400","19200","28800","38400","57600","115200"};					
			        cb = new JComboBox<String>(availableBaudRate);		       
			        cb.addActionListener(new ActionListener() {

						@Override
						    public void actionPerformed(ActionEvent e) {
							
							String selected = cb.getSelectedItem().toString();
						    baudValue = Integer.parseInt(selected);
						    }      
							 
						});	
				
		
			}
			/**public static  void updateTextArea(final String text) {
				  SwingUtilities.invokeLater(new Runnable() {
				  public void run() {
					console.append(text);
				    }
				  });
				}
				 
			public static void redirectSystemStreams() {
				  OutputStream out = new OutputStream() {
				    @Override
				    public void write(int b) throws IOException {
				      updateTextArea(String.valueOf((char) b));
				    }
				 
				    @Override
				    public void write(byte[] b, int off, int len) throws IOException {
				      updateTextArea(new String(b, off, len));
				    }
				 
				    @Override
				    public void write(byte[] b) throws IOException {
				      write(b, 0, b.length);
				    }
				  };			
				  System.setOut(new PrintStream(out, true));
				  System.setErr(new PrintStream(out, true));
				}
	**/

	        
  }

