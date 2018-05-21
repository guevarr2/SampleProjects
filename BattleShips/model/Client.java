package model;
import java.io.*;
import java.net.*;

public class Client extends Server {
	
	public Client() {
		super();
	}
	
	public Client(String ip, int port) {
		super(ip, port);
	}

@Override	
	public boolean connect() {
		try {
			dataSocket = new Socket(ip, port);
			connected = true;
			inStream = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			outStream = new DataOutputStream(dataSocket.getOutputStream());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
@Override
public boolean amIServer() {
	return false;
}		
}
