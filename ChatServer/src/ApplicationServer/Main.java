package ApplicationServer;

import ChatSocketServer.SocketConnectionServer;

public class Main {
	public static void main(String[] args) {
    	SocketConnectionServer socket = new SocketConnectionServer();
        socket.startConnection();
    }
}
