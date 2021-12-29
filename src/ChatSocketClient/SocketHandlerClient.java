package ChatSocketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import org.json.JSONObject;

public abstract class SocketHandlerClient {
    public void onHandle(JSONObject data, BufferedReader in, BufferedWriter out) {}
}