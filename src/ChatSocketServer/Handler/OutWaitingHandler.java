package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

import ChatSocketServer.DataSocketServer;
import ChatSocketServer.SocketConnectionServer;

public class OutWaitingHandler {
	public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            String username = data.getString("username");

            WaitingPairingHandler.userQueue.remove(username);
            if (SocketConnectionServer.socketClients.containsKey(username)){
            	SocketConnectionServer.socketClients.remove(username);
            }
            WaitingPairingHandler.denyUsers.put(username, new ArrayList<>());
            DataSocketServer dataSocket = new DataSocketServer();
            String dataSend = dataSocket.exportDataOutWaiting();
            out.write(dataSend);
            out.newLine();
            out.flush();
            
        } catch (IOException ex) {

        }
    }
}
