package pmf.controller;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
public class Port {
	private pmf.controller.controller controller;
	public Port(pmf.controller.controller controller) {
		this.controller=controller;
		new ObjectMapper();
		new ArrayList<>();
	}
	public List<String> getAvailablePorts() {
        List<String> portNames = new ArrayList<>();
        Object CommPortIdentifier = null;
        Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portNames.add(portId.getName());
            }
        }
        return portNames;
    }

    public void initialize(String portName) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
                if (commPort instanceof SerialPort) {
                	SerialPort serialPort = (SerialPort) commPort;
                	serialPort.addEventListener(new pmf.controller.SerialEventListener(serialPort));
                	serialPort.notifyOnDataAvailable(true);
                	serialPort.setSerialPortParams(
                            9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE
                    );
                    controller.updatePortName(portName);
                } else {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

}
