package ChatSocketServer.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;
import ChatSocketServer.DataSocketServer;
import ChatSocketServer.Group;
import ChatSocketServer.SocketConnectionServer;

public class SendMessageHandler {
	public void run(JSONObject data, BufferedReader in, BufferedWriter out) {
		DataSocketServer dataSocket = new DataSocketServer();
        String user = data.getString("username");       
        String message = data.getString("message");

        Group group = new AcceptPairingHandler().getGroup(user);
        if (group != null){
            
            Map<String, Socket> userList = SocketConnectionServer.socketClients;
            Socket socketUser1 = userList.get(group.getUser1());
            Socket socketUser2 = userList.get(group.getUser2());
            String dataSend = dataSocket.exportDataSendMessage(user, message);
            try {
                BufferedWriter outUser1 = new BufferedWriter(new OutputStreamWriter(socketUser1.getOutputStream()));
                BufferedWriter outUser2 = new BufferedWriter(new OutputStreamWriter(socketUser2.getOutputStream()));
                outUser1.write(dataSend);
                outUser1.newLine();
                outUser1.flush();

                outUser2.write(dataSend);
                outUser2.newLine();
                outUser2.flush();

            } catch (IOException ex) {

            }
        }
    }
}
