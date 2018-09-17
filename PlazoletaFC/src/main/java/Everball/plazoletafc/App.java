package Everball.plazoletafc;

import Everball.plazoletafc.CustomSocket.EverballSocket;

import java.util.Scanner;

public class App {
    public static void main( String[] args )
    {
        System.out.println ("URL server:");
        Scanner sc = new Scanner(System.in);
        //Console consola = System.console();
        String cadena = sc.nextLine();
        EverballSocket socket = new EverballSocket();
        socket.initializeSocket(cadena);
        socket.connect();
    }
}
