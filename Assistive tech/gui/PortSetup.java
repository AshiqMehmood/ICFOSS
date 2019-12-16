/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.icfoss.gui;

/**
 *
 * @author ashiq
 */
import com.fazecast.jSerialComm.*;
import javax.swing.*;


public class PortSetup{
static SerialComm  arduino;
	private static final long serialVersionUID = 1L;
  /**De serialization uses this number to ensure that a loaded class corresponds exactly to a serialized object. 
	If no match is found, then an InvalidClassException is thrown.
  **/
	public void refreshPort(JComboBox<String> cb) {
		 String value;
		cb.removeAllItems();
		SerialPort ports[] = SerialPort.getCommPorts();
		for(SerialPort port : ports) {
		cb.addItem(port.getSystemPortName());            
                        
		}
		
	}
	
		
	}
