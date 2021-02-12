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
		//测试压缩文件
//		String srcFile = "D:\\计算方法\\图.gif";
//		String dstFile = "D:\\\\计算方法\\\\图.zip";
//		zipFile(srcFile, dstFile);
//		System.out.println("压缩文件ok~~");
		//测试解压文件
		String zipFile = "D:\\计算方法\\图.zip";
		String dstFile = "D:\\计算方法\\图2.gif";
		unZipFile(zipFile, dstFile);
		System.out.println("解压文件ok~~~");
		/*
		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length);
		byte[] huffmanCodesBytes = huffmanZip(contentBytes);
		System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes) + "长度=" + huffmanCodesBytes.length);
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
		System.out.println("原来的字符串=" + new String(sourceBytes));
		*/
		/*
		List<Node> nodes = getNodes(contentBytes);
		System.out.println("nodes=" + nodes);
		//测试创建的赫夫曼树
		System.out.println("赫夫曼树");
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		System.out.println("前序遍历");
		huffmanTreeRoot.preOeder();
		//测试是否生成哈夫曼编码
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		System.out.println("生成的赫夫曼编码表" + huffmanCodes);
		//测试
		byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
		System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
		*/
	}
	//编写一个方法，完成对压缩文件的解压
	/**
	 * 
	 * @param zipFile 准备解压的文件
	 * @param dstFile 将文件解压到哪个路径
	 */
	public static void unZipFile(String zipFile, String dstFile) {
		//	定义文件输入流
		InputStream is = null;
		//定义一个对象输入流
		ObjectInputStream ois = null;
		//定义文件的输出流
		OutputStream os =null;
		try {
			//创建文件输入流
			is = new FileInputStream(zipFile);
			//创建一个和is关联的对象输入流
			ois = new ObjectInputStream(is);
			//读取byte数组,huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//读取赫夫曼编码表
			Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();
			//解码
			byte[] bytes = decode(huffmanCodes, huffmanBytes);
			//将bytes写入目标文件
			os = new FileOutputStream(dstFile);
			//写数据到文件中
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
	//编写一个方法，将一个	文件进行压缩
	/**
	 * 
	 * @param srcFile 传入的需要压缩的文件
	 * @param dstFile 压缩到哪个目录下
	 */
	public static void zipFile(String srcFile,String dstFile) {
		//创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//创建输入流，读取文件
		FileInputStream is = null;
		try {
			is = new FileInputStream(srcFile);
			//创建一个和原文件大小相同的byte[]
			byte[] b = new byte[is.available()];
			//读取文件
			is.read(b);
			//直接对源文件进行压缩
			byte[] huffmanBytes = huffmanZip(b);
			//创建一个输出流
			os = new FileOutputStream(dstFile);
			//创建一个和文件输出流相联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//把赫夫曼赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(huffmanBytes);
			//以对象流的方式写入赫夫曼编码；方便后面恢复源文件
			//一定要把赫夫曼编码写入压缩文件
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
	//对压缩数据的解码
	/**
	 * 
	 * @param huffmanCodes 赫夫曼编码表map
	 * @param huffmanBytes 赫夫曼得到的字节数组
	 * @return 原来的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		//得到hffmanBytes对应的二进制字符串
		StringBuilder stringBuilder = new StringBuilder();
		//将byte转成二进制字符串
		for(int i = 0; i< huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length-1);
			stringBuilder.append(byteToBitString(!flag, b));
		}
		System.out.println("赫夫曼字节数组对应的二进制字符串=" + stringBuilder.toString());
		//把字符串按照指定的赫夫曼编码进行解码
		//把赫夫曼编码进行调换
		Map<String, Byte> map = new HashMap<String, Byte>();
		for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		//创建一个集合；存放byte
		List<Byte> list = new ArrayList<>();
		for(int i = 0; i < stringBuilder.length(); ) {//i是一个索引，是扫描stringBuilder的
			int count = 1;//一个小的计算器
			boolean flag = true;
			Byte b = null;
			while(flag) {
				//递增取出key
				String key = stringBuilder.substring(i, i + count);//i不动，让count移动
				b = map.get(key);
				if(b == null) {//没有匹配到
					count ++;
				} else {//匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count;//i直接移动到count
		}
		//当for循环结束后，list就是存放了所有的字符
		//把list数组中的数据放入到byte[]并返回
		byte[] b = new byte[list.size()];
		for(int i = 0; i< b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}
	//完成数据解压
	//将huffmanCodesBytes先转成二进制字符串再转成对应的字符串
	/**
	 * 功能： 将一个byte转换为二进制字符串
	 * @param b 传入的byte
	 * @param flag 标志是否需要补高位，true 需要补,如果是最后一个字节，不需要补高位
	 * @return 对应二进制的字符串（补码返回）
	 */
	private static String byteToBitString(boolean flag, byte b) {
		// 使用一个变量保存b
		int temp = b;// 将b转成int
		// 如果为正数，需要补位
		if (flag) {
			temp |= 256;// 按位与
		}
		String str = Integer.toBinaryString(temp);// 返回的是二进制的补码
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}
	//封装，便于调用
	/**
	 * 
	 * @param bytes 原始数组
	 * @return 返回经过赫夫曼编码处理后的数组（压缩后的数组）
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		//
		List<Node> nodes = getNodes(bytes);
		//根据nodes创建赫夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		//生成哈夫曼编码	
		Map<Byte,String> huffmanCodes = getCodes(huffmanTreeRoot);
		//根据生成的赫夫曼编码压缩；得到压缩后的赫夫曼编码数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	//赫夫曼编码处理后的Byte[]
	/**
	 * 
	 * @param bytes  原始的字符串对应的byte[]
	 * @param huffmanCodes 生成赫夫曼编码
	 * @return 返回赫夫曼处理后的byte[]
	 */
	private static byte[] zip(byte[] bytes, Map<Byte,String> huffmanCodes) {
		//利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历byte
		for(byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		//System.out.println("测试stringBuilder=" + stringBuilder.toString());
		//将编码转成byte[]
		//统计返回的byte[] huffmanCodeBytes的长度
		int len;
		if(stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		//创建一个存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index= 0;//记录是第几个byte
		for(int i = 0; i < stringBuilder.length(); i += 8) {
			String strByte;
			if(i + 8 > stringBuilder.length()) {
				strByte = stringBuilder.substring(i);
			}else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			//将strByte转成byte，放入到huffmanCodeBytes
			huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
			index ++;
		}
		return huffmanCodeBytes;
	}
	//生成赫夫曼编码
	//将哈夫曼编码表放在map（Byte，String）
	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	//生成赫夫曼编码表时，需要拼接路径，定义一个StringBuilder存储某个叶子节点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	//重载getCodes，为了调用方便
	private static Map<Byte, String> getCodes(Node root){
		if(root == null) {
			return null;
		}
		//处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		//处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}
	/**
	 * 功能：	将传入的node节点的所有叶子节点的赫夫曼节点得到，并放入到huffmanCodes集合
	 * @param node 传入节点
	 * @param code 路经：左子节点 0 右子节点 1
	 * @param stringBuilder 拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//将code加入到stringBuilder2
		stringBuilder2.append(code);
		if(node != null) {//如果node == null 不处理
			//判断当前node是叶子节点，还是非叶子节点
			if(node.data == null) {//非叶子节点
				//递归处理
				//向左
				getCodes(node.left, "0", stringBuilder2);
				//向右
				getCodes(node.right, "1", stringBuilder2);
			}else {//是叶子节点
				//找到某个叶子节点的最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
	//前序遍历方法
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOeder();
		}else {
			System.out.println("赫夫曼树为空");
		}
	}
	/**
	 * 
	 * @param bytes  接收字节数组
	 * @return
	 */
	private static List<Node> getNodes(byte[] bytes){
		//创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		//遍历bytes，统计每个Byte出现的次数->map[key，value]
		Map<Byte,Integer> counts = new HashMap<>();
		for(byte b : bytes) {
			Integer count = counts.get(b);
			if(count == null) {
				counts.put(b, 1);
			}else {
				counts.put(b, count + 1);
			}
		}
		//把每个键值对转成一个Node对象，并加入到nodes集合
		//遍历map
		for(Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}
	//通过List生成赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while(nodes.size() > 1) {
			//排序，从小到大
			Collections.sort(nodes);
			//取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			//取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			//创建一颗新的二叉树，它的根节点没有data只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			//将处理的两颗二叉树从nodes删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//将新的二叉树加入
			nodes.add(parent);
		}
		//nodes最后的节点，就是哈夫曼树的根节点
		return  nodes.get(0);
	}

}
//创建Node，带权值
class Node implements Comparable<Node>{
	Byte data;//存放数据本身，Ascii码
	int weight;//权值，表示字符出现的次数
	Node left;
	Node right;
	public Node(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		//从小到大排
		return this.weight - o.weight;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + ", weight=" + weight + "]";
	}
	//前序遍历
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