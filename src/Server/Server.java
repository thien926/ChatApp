package Server;

import ChatSocketServer.SocketConnectionServer;

public class Server {
	public static void main(String[] args) {
		SocketConnectionServer socket = new SocketConnectionServer();
        socket.startConnection();
	}
}
