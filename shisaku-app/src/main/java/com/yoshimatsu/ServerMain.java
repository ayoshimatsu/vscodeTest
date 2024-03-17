package com.yoshimatsu;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    
    public static void main( String[] args ) {
        System.out.println( "Hello Server!" );

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            ServerSocketThread thread = new ServerSocketThread();
            thread.start();

        } catch (IOException e) {
            System.out.println(e);
        }


        System.out.println( "Hello Server!" );
    }
    
}
