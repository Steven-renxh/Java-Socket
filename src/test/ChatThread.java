package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatThread extends Thread{
	Socket socket = null;
	 
	// 重写构造方法，可以传入socket对象
	public ChatThread(Socket socket) {
		this.socket = socket;
	}
	public  void run() {
		System.out.println("已链接");
		
		InputStreamReader inSR = null;
		OutputStreamWriter outSW = null;
		try {
			//读取数据
			try {
				inSR = new InputStreamReader(socket.getInputStream(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(inSR);
			
			try {
				outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(outSW);
			
			String str = "";
			while((str = br.readLine()) != null) {
				str = str.trim();
				System.out.println("收到客户端消息：" + str);
				if(str.equals("你好")) {
					bw.write("好很好非常好！");	
					bw.flush();
				}else if(str.equals("你是谁")) {
					bw.write("我是一个聊天机器人。");
					bw.flush();
				}else if(str.equals("我爱你")) {
					bw.write("不要把我想说的话先说了，我会很没面子的。");
					bw.flush();
				}else {
					//pw.println("sorry,I don't understand!");
					bw.write("对不起,我不能理解。");
					bw.flush();
				}
				bw.write("\r\n");
				//bw.write("已收到信息：" + str + " \r\n");	//向客户端反馈消息，加上分行符以便客户端接收
				bw.flush();
			}
 
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//System.out.println("Cleaning up connection: " + client);
			try {
				inSR.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				outSW.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		System.out.println("已断开");
	}
}
