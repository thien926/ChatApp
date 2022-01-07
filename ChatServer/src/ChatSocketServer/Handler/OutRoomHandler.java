package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;

import org.json.JSONObject;

import ChatSocketServer.DataSocketServer;
import ChatSocketServer.Group;
import ChatSocketServer.SocketConnectionServer;

public class OutRoomHandler {
	public void run(JSONObject data, Socket socket, BufferedReader in, BufferedWriter out){
		try {
            String username = data.getString("username");

            Group group = new AcceptPairingHandler().getGroup(username);
            DataSocketServer dataSocket = new DataSocketServer();
            Map<String, Socket> userList = SocketConnectionServer.socketClients;
            String dataSend;
            
            if (group != null){
                String user1 = group.getUser1();
                String user2 = group.getUser2();
                Socket socket1 = userList.get(user1);
                Socket socket2 = userList.get(user2);
                    
                dataSend = dataSocket.exportDataOutRoom();

                BufferedWriter outSocket1 = new BufferedWriter(new OutputStreamWriter(socket1.getOutputStream()));
                outSocket1.write(dataSend);
                outSocket1.newLine();
                outSocket1.flush();

                BufferedWriter outSocket2 = new BufferedWriter(new OutputStreamWriter(socket2.getOutputStream()));
                outSocket2.write(dataSend);
                outSocket2.newLine();
                outSocket2.flush();

                // Xóa group chat
                new AcceptPairingHandler().removeGroup(user1);

            }
            
        } catch (IOException ex) {

        }
    }
}
