/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icfoss.gui;

import com.fazecast.jSerialComm.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author ashiq
 */
public class SerialComm {
    private SerialPort serPort;
    private String portDescription;
    private int baudRate;
    
    public SerialComm(String portDescription, int baudRate){
        this.portDescription = portDescription;
        serPort = SerialPort.getCommPort(this.portDescription);
        this.baudRate = baudRate;
        serPort.setBaudRate(this.baudRate);
      
    }
    public boolean openConnection() {
		if(serPort.openPort()) {
			try {Thread.sleep(100);} 
			catch(Exception e){}
			return true;
		}
		else
		{	
			return false;
		}
	}
    public void closeConnection() {
	if(serPort.isOpen()){
            serPort.closePort();
            //System.out.println(">> Port is now closed....");
        }	
        else{
            System.out.println(">> Port already closed");
        }
        
    }
    
    public void serialWrite(String value){
        serPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        try{
            Thread.sleep(5);
        }
        catch(Exception ex){ }
            
       PrintWriter pOut = new PrintWriter(serPort.getOutputStream());
       pOut.write(value);
       pOut.flush();
      
        
    }
    
     public void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
              // int count =0;
                    //System.out.println("Time now is " + (new Date()));
                     Scanner data = new Scanner(serPort.getInputStream());
                     while(data.hasNextLine()) {
                            // int number = 0;
                             //try{number = Integer.parseInt(data.nextLine());}catch(Exception ex){}
                             //slider.setValue(number);
                             System.out.println(data.nextLine());
                            
                             //count = count +1;
                     }
                	           
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
        });   
        thread.start();
    }
    
}
