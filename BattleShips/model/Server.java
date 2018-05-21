package model;
import java.io.*;
import java.net.*;

public class Server implements iCommunicator {
	int port;
	String ip = "127.0.0.1";
	BufferedReader inStream;
	DataOutputStream outStream;
	ServerSocket welcomeSocket;
	Socket dataSocket;
	boolean connected = false;

	public Server(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public Server(int port) {		
		this.port = port;
	}
	
	public Server() {
		this.port = 7966;
	}
	
	public boolean connect() {
		try {
			if (welcomeSocket == null)
				welcomeSocket = new ServerSocket(port);
			dataSocket  = welcomeSocket.accept();
			connected = true;
			inStream = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
			outStream = new DataOutputStream(dataSocket.getOutputStream());
			welcomeSocket.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}	
		
	public String getMove() {
		return readLine();
	}
	
	public boolean sendResponse(String response) {
		return writeLine(response);
	}
	
	public String sendMove(String move) {
		writeLine(move);
		return readLine();
	}
	
	public void disconnect() {
		try {
			dataSocket.close();
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readLine() {
		try {
			if(!connected)
				connect();
			return inStream.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean writeLine(String line) {
		try {
			if(!connected)
				connect();
			outStream.writeBytes(line + "\n");
			outStream.flush();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean tryServer() {
		try {
			welcomeSocket = new ServerSocket(port);
		} catch (IOException e) {
			welcomeSocket = null;
			return false;
		}
		return true;
	}

	@Override
	public boolean amIServer() {
		return true;
	}

	@Override
	public boolean isConnected() {
		return connected;
	}		
}
