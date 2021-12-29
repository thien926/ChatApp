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
                String user_1 = group.getUser1();
                String user_2 = group.getUser2();
                Socket socket_1 = userList.get(user_1);
                Socket socket_2 = userList.get(user_2);
                    
                dataSend = dataSocket.exportDataOutRoom();

                BufferedWriter out_socket_1 = new BufferedWriter(new OutputStreamWriter(socket_1.getOutputStream()));
                out_socket_1.write(dataSend);
                out_socket_1.newLine();
                out_socket_1.flush();

                BufferedWriter out_socket_2 = new BufferedWriter(new OutputStreamWriter(socket_2.getOutputStream()));
                out_socket_2.write(dataSend);
                out_socket_2.newLine();
                out_socket_2.flush();

                new AcceptPairingHandler().removeGroup(user_1);

            }
            
        } catch (IOException ex) {

        }
    }
}
