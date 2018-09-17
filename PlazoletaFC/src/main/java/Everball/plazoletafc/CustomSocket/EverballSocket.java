package Everball.plazoletafc.CustomSocket;

import Everball.plazoletafc.Algorithm.PlazoletaFCManager;
import Everball.plazoletafc.Models.FieldInfo;
import Everball.plazoletafc.Models.ServerState;
import Everball.plazoletafc.Utils.Tuple;
import com.google.gson.Gson;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class EverballSocket {
    private Socket mSocket;
    private PlazoletaFCManager mPlazoletaFCManager;

    public void initializeSocket(String uri){
        try {
            mSocket = IO.socket(uri);

            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                public void call(Object... args) {
                    JSONObject join = new JSONObject();
                    try {
                        System.out.println ("Usuario:");
                        Scanner sc = new Scanner(System.in);
                        String cadena = sc.nextLine();
                        System.out.println ("Contraseña");
                        sc = new Scanner(System.in);
                        String cadena2 = sc.nextLine();
                        join.put("name", cadena);
                        join.put("password", cadena2);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    mSocket.emit("login", join);
                }
            }).on("server_message", new Emitter.Listener() {

                public void call(Object... args) {

                    String data = (String)args[0];
                    if(data.contains("Logged in as")) {
                        JSONObject room = new JSONObject();

                        try {
                            System.out.println ("Nombre de sala:");
                            Scanner sc = new Scanner(System.in);
                            String cadena = sc.nextLine();
                            System.out.println ("Contraseña");
                            sc = new Scanner(System.in);
                            String cadena2 = sc.nextLine();
                            room.put("name", cadena);
                            room.put("password", cadena2);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        mSocket.emit("join_room", room);
                    }
                }

            }).on("match_start", new Emitter.Listener() {

                public void call(Object... args) {
                    Gson gson = new Gson();
                    mPlazoletaFCManager = new PlazoletaFCManager(gson.fromJson(args[0].toString(), FieldInfo.class));
                }

            }).on("server_state", new Emitter.Listener() {

                public void call(Object... args) {
                    Gson gson = new Gson();
                    if(mPlazoletaFCManager != null){
                        mPlazoletaFCManager.newServerState(gson.fromJson(args[0].toString(), ServerState.class));
                        Tuple<Boolean, ArrayList<JSONObject>> tuple = mPlazoletaFCManager.doActions();

                        if(tuple.getItem1()){
                            for(JSONObject action: tuple.getItem2()){
                                mSocket.emit("client_input", action);
                            }
                        }
                    }
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                public void call(Object... args) {}

            });
        } catch (URISyntaxException use){
            System.out.println(use.getMessage());
        }
    }

    public void connect(){
        mSocket.connect();
    }
}
