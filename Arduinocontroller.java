package src.main.java.pmf.controller;
import com.fazecast.jSerialComm.SerialPort;

public class Arduinocontroller {
    public static void main(String[]args){
        SerialPort[]ports= SerialPort.getCommPorts();
        SerialPort arduinoPort=ports[0];
        arduinoPort.setComPortParameters(9600,8,1,0);
        arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER,0,0);
        arduinoPort.openPort();
        //lire les données d'arduino
        byte[] readBuffer=new byte[100];
        while(true){
            while(arduinoPort.bytesAvailable()==0){
                try{
                    Thread.sleep(20);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            int numBytes=arduinoPort.readBytes(readBuffer,readBuffer.length);
            String receivedData=new String(readBuffer,0,numBytes);
            int receivedValue=Integer.parseInt(receivedData.trim());
            System.out.println("Received value:"+ receivedValue);

        }
        /*Write data to Arduino
        int value=100;
        String arduinoData=String.valueOf(value);
        arduinoPort.writeBytes(arduinoData.getBytes(),arduinoData.length());
        try{
            OutputStream output=arduinoPort.getOutputStream();
            output.write(value);
            output.flush();
         }Catch (IOException e){
         e.printStackTrace();}

         */
    }
}
