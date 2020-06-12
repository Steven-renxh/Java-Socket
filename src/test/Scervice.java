package test;
import java.io.*;
import java.net.*;

public class Scervice {
	public static void main(String args[]) {
		System.out.println("Server");
		
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		int connects = 0;
		try {
			serverSocket = new ServerSocket(82);		//端口：82
			//clientSocket = serverSocket.accept();	//等待连接
			while(true) {
				connects++;
				System.out.println("--------------------等待连接--------------------------");
				clientSocket = serverSocket.accept();	//等待连接
				System.out.println("第 " + connects + " 次连接");
				ChatThread st=new ChatThread(clientSocket);
				//new Thread(new ChatThread(clientSocket)).start();
				st.start();
			}
		} catch(IOException ioe) {
			System.out.println("Error: " + ioe);
		}
	}
	
}
