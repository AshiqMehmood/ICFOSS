package flipmouse_interface;

import java.util.Scanner;

import java.awt.Dimension;
import java.io.PrintWriter;
import com.fazecast.jSerialComm.*;

public class SerialComm {
	private SerialPort serPort;
	private String portDescription;
	private int baudRate;
	
	public SerialComm(String portDescription, int baudRate) {
		this.portDescription = portDescription;
		serPort = SerialPort.getCommPort(this.portDescription);
		this.baudRate = baudRate;
		serPort.setBaudRate(this.baudRate);
		
	}
	
	public boolean Open_Connection() {
		if(serPort.openPort()) {
			try {Thread.sleep(100);} catch(Exception e){}
			return true;
		}
		else
		{
			AlertWindow box = new AlertWindow(new Dimension(400,100),"Error Connecting", "Try Another port");
			box.display();
			return false;
		}
	}
	
	public void close_Connection() {
		serPort.closePort();
	}
	public void set_portDesciption(String portDescription) {
		this.portDescription = portDescription ;
		serPort = SerialPort.getCommPort(this.portDescription);
		
	}
	public void set_BaudRate(int baudRate) {
		this.baudRate = baudRate ;
		serPort.setBaudRate(this.baudRate);
	}
	public String get_portDescription() {
		return portDescription;
	}
	public SerialPort get_Serial() {
		return serPort;
	}
	public int get_Baud() {
		int rate = serPort.getBaudRate();
		return rate;
	}
	

	
	public String serialRead() { //limit value sets the default amount of data entering the serialport 
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
		String out = "";
		Scanner in = new Scanner(serPort.getInputStream());
			try {
				Thread.sleep(10);
						while(in.hasNext()) { 
								out += (in.next() + "\n");				
						    }
						in.close();
				
			    }
			catch (Exception e) { e.printStackTrace(); }
		return out;
	}
	
	public String serialRead(int limit) { //limit value sets the default amount of data entering the serialport 
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING,0,0);
		String out = "";
		int count= 0;
		Scanner in = new Scanner(serPort.getInputStream());
			try {
						while(in.hasNext() && count <= limit) { //if input value has another token, and is not greater than limit, then add it to output
								out += (in.next() + "\n");
								count ++;
						    }
						in.close();
				
			    }
			catch (Exception e) { e.printStackTrace(); }
		return out;
	}
	
	public void serialWrite(String value) { //writes an entire string at once
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
			try {
				Thread.sleep(5);
			} catch(Exception e) {}
			
		PrintWriter pOut = new PrintWriter(serPort.getOutputStream());
		pOut.print(value);
		pOut.flush();
	}
	
	public void serialWrite(String value, int numChar, int delay )
	{
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER,0,0);
		try {
			Thread.sleep(5);			
		} catch(Exception e) {}
		
		PrintWriter pOut = new PrintWriter(serPort.getOutputStream());
		for(int i =0 ; i< value.length(); i+= numChar) {
			pOut.write(value.substring(i, i+numChar));
			pOut.flush();
			System.out.println(value.substring(i,i+numChar));
			try {
				Thread.sleep(delay);
			}catch(Exception e) {}
			
		}
	  pOut.write(numChar);
	  pOut.flush();
	}
	
	public void serialWrite(char c) {
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0,0);
		try {
			Thread.sleep(5);
		}catch(Exception e) {}
		PrintWriter pOut = new PrintWriter(serPort.getOutputStream());
		pOut.write(c);
		pOut.flush();
	}
	
	public void serialWrite(char c, int delay) {
		serPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		try {
			Thread.sleep(5);
		}catch(Exception e) {}
	PrintWriter pOut = new PrintWriter(serPort.getOutputStream());
	pOut.write(c);
	pOut.flush();
	}
	
	
}
