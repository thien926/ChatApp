package ChatSocketServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import ChatSocketClient.DataSocketClient;
import ChatSocketServer.Handler.AcceptPairingHandler;
import ChatSocketServer.Handler.WaitingPairingHandler;
import ChatSocketServer.Handler.OutRoomHandler;
import ChatSocketServer.Handler.OutWaitingHandler;
import ChatSocketServer.Handler.SendMessageHandler;
import ChatSocketServer.Handler.SendNicknameHandler;

public class SocketConnectionServer {

    private static ServerSocket server = null;
    private static String socketHost = "localhost";
    private static int socketPort = 5001;
    public static Map<String, Socket> socketClients = new HashMap<String, Socket>();

    public SocketConnectionServer() {
    }

    public void startConnection() {
        try {
            server = new ServerSocket(socketPort);
            System.out.println("===== Server has started =====");

            Thread thread_go_chat = new Thread(new Runnable() {
                @Override
                public void run() {
                    new WaitingPairingHandler().getPair();
                }
            });
            thread_go_chat.start();
            
            while (true) {
                Socket socket = server.accept();
                System.out.println("connect - " + socket.toString());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handleClient(null, socket, in, out);
                    }
                });
                thread.start();
            }

        } catch (IOException e) {
//            System.err.println(e);
        }
    }

    public void handleClient(String username, Socket socket, BufferedReader in, BufferedWriter out) {
        try {
        	DataSocketServer dataSocket = new DataSocketServer();

            while (true) {
                String rawDateReceive = in.readLine();
//                System.out.println(rawDateReceive);
                if (rawDateReceive == null || rawDateReceive.trim().equals("")){
                    continue;
                }
                
                JSONObject dataReceive = dataSocket.importData(rawDateReceive);
                JSONObject data = dataReceive.getJSONObject("data");
                String type = dataReceive.getString("type");

                switch (type) {
	                case "send_username":
	                    new SendNicknameHandler().run(data, socket, in, out);
	                    break;
                    case "waiting_pairing":
                        new WaitingPairingHandler().run(data, in, out);
                        break;
                    case "out_waiting":
                        new OutWaitingHandler().run(data, socket, in, out);
                        break;
                    case "accept_pariring":
                        new AcceptPairingHandler().run(data, in, out);
                        break;  
                    case "send_message":
                        new SendMessageHandler().run(data, in, out);
                        break;
                    case "out_room":
                        new OutRoomHandler().run(data, socket, in, out);
                        break;
                    case "exit_app":
                    	String nickname = data.getString("username");
                        if (!nickname.equals("") && socketClients.containsKey(nickname)){
                            socketClients.remove(nickname);
                        }
                        String dataSend = dataSocket.exportDataExitApp();
                        out.write(dataSend);
                        out.newLine();
                        out.flush();
                        
                        in.close();
                        out.close();
                        socket.close();
                        break;
                        
                        
                        
                    case "stop":
                        in.close();
                        out.close();
                        socket.close();
                        break;
                }
            }
        } 
        catch (IOException e) {
            new AcceptPairingHandler().removeGroup(username);
            WaitingPairingHandler.userQueue.remove(username);
            socketClients.remove(username);
            System.err.println(e);
        }
    }

    public void stopConnection() {
        try {
            server.close();
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void updateSocketClients() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Map<String, Socket> userList = new SocketConnectionServer().getSocketClients();
                    System.out.println(userList.size());

                    for (Map.Entry<String, Socket> e : userList.entrySet()) {
                        Socket socketClient = e.getValue();
                        if (socketClient.isClosed()) {
                            socketClients.remove(e.getKey());
                        }
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }
        });
        thread.start();
    }

    public static Map<String, Socket> getSocketClients() {
        return socketClients;
    }

    public static void main(String[] args) {
    	SocketConnectionServer socket = new SocketConnectionServer();
        socket.startConnection();
    }
}

