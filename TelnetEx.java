import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class TelnetEx {
        public static void main(String[] args) {
          
         TelnetClient telnet = new TelnetClient();
          Scanner scanner = new Scanner(System.in);

try {
String server = "telehack.com";
int port  =  23;
telnet.connect(server,port);
System.out.println("Connected to telnet server: " + server);

InputStream in = telnet.getInputStream();
OutputStream out = telnet.getOutputStream();

new Thread (() -> {

try{
	int data;
	while((data=in.read()) !=-1) {
		System.out.print((char) data);
}
} catch (Exception e) {
	e.printStackTrace();
}
}).start();

while(true) {
	System.out.print("Enter command (type'Exit' to quit): ");
	String command = scanner.nextLine();

if(command.trim().equalsIgnoreCase("Exit")) {
break;
}
out.write((command+"\n").getBytes());
out.flush();
}
telnet.disconnect();
System.out.println("Disconnect from Telnet server.");
} catch (Exception e) {
	e.printStackTrace();
} finally {
	scanner.close();
}
}
}