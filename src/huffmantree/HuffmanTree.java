package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = {13, 7, 8,3,29,6,1};
		Node root = createHuffmanTree(arr);
		//测试
		preOrder(root);
	}
	//编写一个前序遍历的方法
	public static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("是空树，不能遍历~~");
		}
	}
	//创建赫夫曼树方法
	/**
	 * 
	 * @param arr 需要创建成赫夫曼树的数组
	 * @return 创建好后的root节点
	 */
	public static Node createHuffmanTree(int[] arr) {
		//遍历arr数组
		//将arr数组的每个元素构成一个Node
		//将Node放入到ArrayList中
		List<Node> nodes = new ArrayList<Node>();
		for(int value : arr) {
			nodes.add(new Node(value));
		}
		//循环处理
		while(nodes.size() > 1) {
			// 排序，从小到大
			Collections.sort(nodes);
			System.out.println("nodes=" + nodes);
			// 取出根节点权值最小的两颗二叉树
			// 取出权值最小的节点
			Node leftNode = nodes.get(0);
			// 取出第二小的
			Node rightNode = nodes.get(1);
			// 构建一个新的二叉树
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			// 从ArrayList中删除 处理过的二叉树
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// 将parent加入ArrayList
			nodes.add(parent);
		}
		//返回哈夫曼树的root节点
		return nodes.get(0);
	}	
}
//创建节点类
//为了让Node对象支持排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
	int value;//节点权值
	Node left;//指向左子节点
	Node right;//指向右子节点
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
	public Node(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
	@Override
	public int compareTo(Node o) {
		//从小到大排序（大到小加-）
		return this.value - o.value;
	}
	
}
