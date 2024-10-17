import com.fazecast.jSerialComm.SerialPort;
import com.sun.nio.sctp.SctpChannel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SerialPort sp = SerialPort.getCommPort("/dev/cu.usbmodem1101");
        sp.setComPortParameters(9600,8,1,0);
        sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
        String[] cmds = {
                "switch1",
                "switch2",
                "switch3",
                "switch4",
                "switch0"

        };
        if (sp.openPort()) {
            System.out.println("Port is opened");
        } else {
            System.out.println("Port is not opened");
            return;
        }
        for (int i = 0; i < 5; ++i) {
            sp.getOutputStream().write(cmds[i].getBytes());
            sp.getOutputStream().flush();
            System.out.println("Sent command: "+cmds[i]);
            Thread.sleep(1000);
        }

        if (sp.closePort()) {
            System.out.println("Port is closed");
        } else {
            System.out.println("Port is not closed");
        }
    }
}