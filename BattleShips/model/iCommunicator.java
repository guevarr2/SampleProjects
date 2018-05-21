package model;

public interface iCommunicator {
	public boolean connect();
	public String sendMove(String move);
	public String getMove();
	public boolean sendResponse(String response);
	public void disconnect();
	public String readLine();
	public boolean writeLine(String line);
	public boolean tryServer();
	public boolean amIServer();
	public boolean isConnected();
}
