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
	 
	// ��д���췽�������Դ���socket����
	public ChatThread(Socket socket) {
		this.socket = socket;
	}
	public  void run() {
		System.out.println("������");
		
		InputStreamReader inSR = null;
		OutputStreamWriter outSW = null;
		try {
			//��ȡ����
			try {
				inSR = new InputStreamReader(socket.getInputStream(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(inSR);
			
			try {
				outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			BufferedWriter bw = new BufferedWriter(outSW);
			
			String str = "";
			while((str = br.readLine()) != null) {
				str = str.trim();
				System.out.println("�յ��ͻ�����Ϣ��" + str);
				if(str.equals("���")) {
					bw.write("�úܺ÷ǳ��ã�");	
					bw.flush();
				}else if(str.equals("����˭")) {
					bw.write("����һ����������ˡ�");
					bw.flush();
				}else if(str.equals("�Ұ���")) {
					bw.write("��Ҫ������˵�Ļ���˵�ˣ��һ��û���ӵġ�");
					bw.flush();
				}else {
					//pw.println("sorry,I don't understand!");
					bw.write("�Բ���,�Ҳ�����⡣");
					bw.flush();
				}
				bw.write("\r\n");
				//bw.write("���յ���Ϣ��" + str + " \r\n");	//��ͻ��˷�����Ϣ�����Ϸ��з��Ա�ͻ��˽���
				bw.flush();
			}
 
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			//System.out.println("Cleaning up connection: " + client);
			try {
				inSR.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			try {
				outSW.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		System.out.println("�ѶϿ�");
	}
}
