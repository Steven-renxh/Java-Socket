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
			serverSocket = new ServerSocket(82);		//�˿ڣ�82
			//clientSocket = serverSocket.accept();	//�ȴ�����
			while(true) {
				connects++;
				System.out.println("--------------------�ȴ�����--------------------------");
				clientSocket = serverSocket.accept();	//�ȴ�����
				System.out.println("�� " + connects + " ������");
				ChatThread st=new ChatThread(clientSocket);
				//new Thread(new ChatThread(clientSocket)).start();
				st.start();
			}
		} catch(IOException ioe) {
			System.out.println("Error: " + ioe);
		}
	}
	
}
