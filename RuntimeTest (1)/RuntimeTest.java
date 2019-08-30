package blockingqueue;

import java.io.IOException;

public class RuntimeTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		Process pc = null;
		try {
			//�ܺ� ���μ��� ����
			pc = rt.exec("C:\\Program Files\\Microsoft Office\\Office15\\Excel.exe");
			System.out.println("MicroSoft Excel Excute!!");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//��ɾ� ����� ���� ���
			pc.waitFor(); 
			//��ɾ� ����� ���� ���μ��� ����
			pc.destroy();
		}
	}
}
