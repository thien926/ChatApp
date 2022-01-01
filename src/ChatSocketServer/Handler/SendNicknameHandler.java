package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import org.json.JSONObject;

import ChatSocketServer.DataSocketServer;
import ChatSocketServer.SocketConnectionServer;

public class SendNicknameHandler {
	public static DataSocketServer dataSocket = new DataSocketServer();

    public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
            String username = data.getString("username");
            
            Map<String, Socket> socketClients = SocketConnectionServer.socketClients;
            
            String dataSend = "";
            if (socketClients.keySet().contains(username)) {
                dataSend = dataSocket.exportDataSendUsername(false, "Nickname đã tồn tại!");
            }
            else {
                socketClients.put(username, socket);
                dataSend = dataSocket.exportDataSendUsername(true, "");
            }
            
            out.write(dataSend);
            out.newLine();
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
