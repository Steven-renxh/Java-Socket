package test;
import java.io.*;
import java.net.*;
public class Client {
	public static void main(String[] args) {
		System.out.println("������������������ӡ�����");
		try {
			Socket clientSocket = new Socket("192.168.10.1", 82);
			System.out.println("Client1:" + clientSocket);
 
			DataInputStream dataIS = new DataInputStream(clientSocket.getInputStream());
			InputStreamReader inSR = new InputStreamReader(dataIS, "UTF-8");
			BufferedReader br = new BufferedReader(inSR);
			
			DataOutputStream dataOS = new DataOutputStream(clientSocket.getOutputStream());
			OutputStreamWriter outSW = new OutputStreamWriter(dataOS, "UTF-8");
			BufferedWriter bw = new BufferedWriter(outSW);
 
			//������Ϣ
			byte bytes[] = new byte[100];
			while(true) {
				System.out.println("----------------------------------");
				System.in.read(bytes);
				String str = new String(bytes);
				str = str.trim();
				if (str == "exit") {
					break;
				}
 
				//��������
				bw.write(str + "\r\n");		//���Ϸ��з����Ա���������ж�ȡ
				bw.flush();
				//��������
				while((str = br.readLine()) != null) {
					str = str.trim();
					System.out.println("�������ظ���" + str);
					break;
				}
 
			}
			
			inSR.close();
			dataIS.close();
			dataOS.close();
			clientSocket.close();
		} catch(UnknownHostException uhe) {
			System.out.println("Error:" + uhe.getMessage());
		} catch(ConnectException ce) {
			System.out.println("Error:" + ce.getMessage());
		} catch(IOException ioe) {
			System.out.println("Error:" + ioe.getMessage());
		} finally {
		}
	}
}
