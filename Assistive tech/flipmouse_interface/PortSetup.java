package flipmouse_interface;

import com.fazecast.jSerialComm.*;
import javax.swing.*;


public class PortSetup extends JComboBox<String> {

	private static final long serialVersionUID = 1L;
  /**Deserialization uses this number to ensure that a loaded class corresponds exactly to a serialized object. 
	If no match is found, then an InvalidClassException is thrown.
  **/
	public void refreshPort() {
		
		this.removeAllItems();
		SerialPort ports[] = SerialPort.getCommPorts();
		for(SerialPort port : ports) {
			this.addItem(port.getSystemPortName());
		}
		
	}
	
		
	}

	
		

