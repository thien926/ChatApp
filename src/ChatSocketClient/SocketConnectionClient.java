package ChatSocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SocketConnectionClient {
	private static Socket socket = null;
    private static BufferedReader in = null;
    private static BufferedWriter out = null;
    private static String socketHost = "localhost";    
    private static int socketPort = 5001;
    private static Map <String, SocketHandlerClient> actions = new HashMap<String, SocketHandlerClient>();
//    private static ArrayList<Thread> events = new ArrayList<Thread>();

    public SocketConnectionClient() {}
    
    public void startConnection(){
        try {
            socket = new Socket(socketHost, socketPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("===== Connected to server =====");
            
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    handleServer(socket, in, out);
                }
            });  
            thread.start();
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void handleServer(Socket socket, BufferedReader in, BufferedWriter out){
        try {
        	DataSocketClient dataSocket = new DataSocketClient();
            while (true){
                String rawDataReceive = in.readLine();
                if (rawDataReceive == null){
                    continue;
                }
                
                System.out.println("Receive" + rawDataReceive);
                JSONObject dataReceive = dataSocket.importData(rawDataReceive);
                JSONObject data = dataReceive.getJSONObject("data");
                String type = dataReceive.getString("type");

                switch (type) {
	                case "send_nickname_response":
	                    actions.get("send_nickname_response").onHandle(data, in, out);
	                    break;
	                case "send_invitation":
//                      System.out.println("send_invitation");
                      actions.get("send_invitation").onHandle(data, in, out);
                      break;
	                case "accept_pariring": 
	                	actions.get("accept_pariring").onHandle(data, in, out);
	                	break;
	                case "start_message":
	                	actions.get("start_message").onHandle(data, in, out);
	                	break;
	                case "exit_app_response":
                        actions.get("exit_app_response").onHandle(data, in, out);
                        break;	
	                case "send_message":
//                      System.out.println("send_message");
                      actions.get("send_message").onHandle(data, in, out);
                      break;
	                case "out_room_response":
                        actions.get("out_room_response").onHandle(data, in, out);
                        break;
	                	
                    
                    case "stop":
//                        System.out.println("July");
                        in.close();
                        out.close();
                        socket.close();
                        break;
                }
            }
        } catch (IOException e) { System.err.println(e); }
    }
    
    public void addListenConnection(String actionID, SocketHandlerClient handler){
        actions.put(actionID, handler);
    }
            
    public void stopConnection(){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("===== Closed connection to server =====");
        } catch (IOException e) { System.err.println(e); }
    }
    
    
        
   public void sendData(String data){
        try {
            out.write(data);
            out.newLine();
            out.flush();
        } catch (IOException e) { System.err.println(e); }
    }

//	public static Socket getSocket() {
//		return socket;
//	}
//
//	public static void setSocket(Socket socket) {
//		SocketConnectionClient.socket = socket;
//	}
}
