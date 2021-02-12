package Huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanCode {

	public static void main(String[] args) {
		//����ѹ���ļ�
//		String srcFile = "D:\\���㷽��\\ͼ.gif";
//		String dstFile = "D:\\\\���㷽��\\\\ͼ.zip";
//		zipFile(srcFile, dstFile);
//		System.out.println("ѹ���ļ�ok~~");
		//���Խ�ѹ�ļ�
		String zipFile = "D:\\���㷽��\\ͼ.zip";
		String dstFile = "D:\\���㷽��\\ͼ2.gif";
		unZipFile(zipFile, dstFile);
		System.out.println("��ѹ�ļ�ok~~~");
		/*
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length);
		byte[] huffmanCodesBytes = huffmanZip(contentBytes);
		System.out.println("ѹ����Ľ���ǣ�" + Arrays.toString(huffmanCodesBytes) + "����=" + huffmanCodesBytes.length);
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
		System.out.println("ԭ�����ַ���=" + new String(sourceBytes));
		*/
		/*
		List<Node> nodes = getNodes(contentBytes);
		System.out.println("nodes=" + nodes);
		//���Դ����ĺշ�����
		System.out.println("�շ�����");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("ǰ�����");
		huffmanTreeRoot.preOeder();
		//�����Ƿ����ɹ���������
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("���ɵĺշ��������" + huffmanCodes);
		//����
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
		*/
	}
	//��дһ����������ɶ�ѹ���ļ��Ľ�ѹ
	/**
	 * 
	 * @param zipFile ׼����ѹ���ļ�
	 * @param dstFile ���ļ���ѹ���ĸ�·��
	 */
	public static void unZipFile(String zipFile, String dstFile) {
		//	�����ļ�������
		InputStream is = null;
		//����һ������������
		ObjectInputStream ois = null;
		//�����ļ��������
		OutputStream os =null;
		try {
			//�����ļ�������
			is = new FileInputStream(zipFile);
			//����һ����is�����Ķ���������
			ois = new ObjectInputStream(is);
			//��ȡbyte����,huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//��ȡ�շ��������
			Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();
			//����
			byte[] bytes = decode(huffmanCodes, huffmanBytes);
			//��bytesд��Ŀ���ļ�
			os = new FileOutputStream(dstFile);
			//д���ݵ��ļ���
			os.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				os.close();
				ois.close();
				is.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
	}
	//��дһ����������һ��	�ļ�����ѹ��
	/**
	 * 
	 * @param srcFile �������Ҫѹ�����ļ�
	 * @param dstFile ѹ�����ĸ�Ŀ¼��
	 */
	public static void zipFile(String srcFile,String dstFile) {
		//���������
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//��������������ȡ�ļ�
		FileInputStream is = null;
		try {
			is = new FileInputStream(srcFile);
			//����һ����ԭ�ļ���С��ͬ��byte[]
			byte[] b = new byte[is.available()];
			//��ȡ�ļ�
			is.read(b);
			//ֱ�Ӷ�Դ�ļ�����ѹ��
			byte[] huffmanBytes = huffmanZip(b);
			//����һ�������
			os = new FileOutputStream(dstFile);
			//����һ�����ļ������������ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//�Ѻշ����շ����������ֽ�����д��ѹ���ļ�
			oos.writeObject(huffmanBytes);
			//�Զ������ķ�ʽд��շ������룻�������ָ�Դ�ļ�
			//һ��Ҫ�Ѻշ�������д��ѹ���ļ�
			oos.writeObject(huffmanCodes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
				os.close();
				oos.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
	}
	//��ѹ�����ݵĽ���
	/**
	 * 
	 * @param huffmanCodes �շ��������map
	 * @param huffmanBytes �շ����õ����ֽ�����
	 * @return ԭ�����ַ�����Ӧ������
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		//�õ�hffmanBytes��Ӧ�Ķ������ַ���
		StringBuilder stringBuilder = new StringBuilder();
		//��byteת�ɶ������ַ���
		for(int i = 0; i< huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//�ж��ǲ������һ���ֽ�
			boolean flag = (i == huffmanBytes.length-1);
			stringBuilder.append(byteToBitString(!flag, b));
		}
		System.out.println("�շ����ֽ������Ӧ�Ķ������ַ���=" + stringBuilder.toString());
		//���ַ�������ָ���ĺշ���������н���
		//�Ѻշ���������е���
		Map<String, Byte> map = new HashMap<String, Byte>();
		for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		//����һ�����ϣ����byte
		List<Byte> list = new ArrayList<>();
		for(int i = 0; i < stringBuilder.length(); ) {//i��һ����������ɨ��stringBuilder��
			int count = 1;//һ��С�ļ�����
			boolean flag = true;
			Byte b = null;
			while(flag) {
				//����ȡ��key
				String key = stringBuilder.substring(i, i + count);//i��������count�ƶ�
				b = map.get(key);
				if(b == null) {//û��ƥ�䵽
					count ++;
				} else {//ƥ�䵽
					flag = false;
				}
			}
			list.add(b);
			i += count;//iֱ���ƶ���count
		}
		//��forѭ��������list���Ǵ�������е��ַ�
		//��list�����е����ݷ��뵽byte[]������
		byte[] b = new byte[list.size()];
		for(int i = 0; i< b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	//������ݽ�ѹ
	//��huffmanCodesBytes��ת�ɶ������ַ�����ת�ɶ�Ӧ���ַ���
	/**
	 * ���ܣ� ��һ��byteת��Ϊ�������ַ���
	 * @param b �����byte
	 * @param flag ��־�Ƿ���Ҫ����λ��true ��Ҫ��,��������һ���ֽڣ�����Ҫ����λ
	 * @return ��Ӧ�����Ƶ��ַ��������뷵�أ�
	 */
	private static String byteToBitString(boolean flag, byte b) {
		// ʹ��һ����������b
		int temp = b;// ��bת��int
		// ���Ϊ��������Ҫ��λ
		if (flag) {
			temp |= 256;// ��λ��
		}
		String str = Integer.toBinaryString(temp);// ���ص��Ƕ����ƵĲ���
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}
	//��װ�����ڵ���
	/**
	 * 
	 * @param bytes ԭʼ����
	 * @return ���ؾ����շ������봦�������飨ѹ��������飩
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		//
		List<Node> nodes = getNodes(bytes);
		//����nodes�����շ�����
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		//���ɹ���������	
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		//�������ɵĺշ�������ѹ�����õ�ѹ����ĺշ�����������
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	//�շ������봦����Byte[]
	/**
	 * 
	 * @param bytes  ԭʼ���ַ�����Ӧ��byte[]
	 * @param huffmanCodes ���ɺշ�������
	 * @return ���غշ���������byte[]
	 */
	private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes) {
		//����huffmanCodes��bytesת�ɺշ��������Ӧ���ַ���
		StringBuilder stringBuilder = new StringBuilder();
		//����byte
		for(byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		//System.out.println("����stringBuilder=" + stringBuilder.toString());
		//������ת��byte[]
		//ͳ�Ʒ��ص�byte[] huffmanCodeBytes�ĳ���
		int len;
		if(stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		//����һ���洢ѹ�����byte����
		byte[] huffmanCodeBytes = new byte[len];
		int index= 0;//��¼�ǵڼ���byte
		for(int i = 0; i < stringBuilder.length(); i += 8) {
			String strByte;
			if(i + 8 > stringBuilder.length()) {
				strByte = stringBuilder.substring(i);
			}else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			//��strByteת��byte�����뵽huffmanCodeBytes
			huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
			index ++;
		}
		return huffmanCodeBytes;
	}
	//���ɺշ�������
	//����������������map��Byte��String��
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	//���ɺշ��������ʱ����Ҫƴ��·��������һ��StringBuilder�洢ĳ��Ҷ�ӽڵ��·��
	static StringBuilder stringBuilder = new StringBuilder();
	//����getCodes��Ϊ�˵��÷���
	private static Map<Byte, String> getCodes(Node root){
		if(root == null) {
			return null;
		}
		//����root��������
		getCodes(root.left, "0", stringBuilder);
		//����root��������
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}
	/**
	 * ���ܣ�	�������node�ڵ������Ҷ�ӽڵ�ĺշ����ڵ�õ��������뵽huffmanCodes����
	 * @param node ����ڵ�
	 * @param code ·�������ӽڵ� 0 ���ӽڵ� 1
	 * @param stringBuilder ƴ��·��
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//��code���뵽stringBuilder2
		stringBuilder2.append(code);
		if(node != null) {//���node == null ������
			//�жϵ�ǰnode��Ҷ�ӽڵ㣬���Ƿ�Ҷ�ӽڵ�
			if(node.data == null) {//��Ҷ�ӽڵ�
				//�ݹ鴦��
				//����
				getCodes(node.left, "0", stringBuilder2);
				//����
				getCodes(node.right, "1", stringBuilder2);
			}else {//��Ҷ�ӽڵ�
				//�ҵ�ĳ��Ҷ�ӽڵ�����
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
	//ǰ���������
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOeder();
		}else {
			System.out.println("�շ�����Ϊ��");
		}
	}
	/**
	 * 
	 * @param bytes  �����ֽ�����
	 * @return
	 */
	private static List<Node> getNodes(byte[] bytes){
		//����һ��ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		//����bytes��ͳ��ÿ��Byte���ֵĴ���->map[key��value]
		Map<Byte,Integer> counts = new HashMap<>();
		for(byte b : bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b, 1);
			}else {
				counts.put(b, count + 1);
			}
		}
		//��ÿ����ֵ��ת��һ��Node���󣬲����뵽nodes����
		//����map
		for(Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
	//ͨ��List���ɺշ�����
	private static Node createHuffmanTree(List<Node> nodes) {
		while(nodes.size() > 1) {
			//���򣬴�С����
			Collections.sort(nodes);
			//ȡ����һ����С�Ķ�����
			Node leftNode = nodes.get(0);
			//ȡ���ڶ�����С�Ķ�����
			Node rightNode = nodes.get(1);
			//����һ���µĶ����������ĸ��ڵ�û��dataֻ��Ȩֵ
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			//����������Ŷ�������nodesɾ��
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//���µĶ���������
			nodes.add(parent);
		}
		//nodes���Ľڵ㣬���ǹ��������ĸ��ڵ�
		return  nodes.get(0);
	}

}
//����Node����Ȩֵ
class Node implements Comparable<Node>{
	Byte data;//������ݱ���Ascii��
	int weight;//Ȩֵ����ʾ�ַ����ֵĴ���
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		//��С������
		return this.weight - o.weight;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	//ǰ�����
	public void preOeder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOeder();
		}
		if(this.right != null) {
			this.right.preOeder();
		}
	}
	
}