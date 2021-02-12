package huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

	public static void main(String[] args) {
		int arr[] = {13, 7, 8,3,29,6,1};
		Node root = createHuffmanTree(arr);
		//����
		preOrder(root);
	}
	//��дһ��ǰ������ķ���
	public static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("�ǿ��������ܱ���~~");
		}
	}
	//�����շ���������
	/**
	 * 
	 * @param arr ��Ҫ�����ɺշ�����������
	 * @return �����ú��root�ڵ�
	 */
	public static Node createHuffmanTree(int[] arr) {
		//����arr����
		//��arr�����ÿ��Ԫ�ع���һ��Node
		//��Node���뵽ArrayList��
		List<Node> nodes = new ArrayList<Node>();
		for(int value : arr) {
			nodes.add(new Node(value));
		}
		//ѭ������
		while(nodes.size() > 1) {
			// ���򣬴�С����
			Collections.sort(nodes);
			System.out.println("nodes=" + nodes);
			// ȡ�����ڵ�Ȩֵ��С�����Ŷ�����
			// ȡ��Ȩֵ��С�Ľڵ�
			Node leftNode = nodes.get(0);
			// ȡ���ڶ�С��
			Node rightNode = nodes.get(1);
			// ����һ���µĶ�����
			Node parent = new Node(leftNode.value + rightNode.value);
			parent.left = leftNode;
			parent.right = rightNode;
			// ��ArrayList��ɾ�� ������Ķ�����
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			// ��parent����ArrayList
			nodes.add(parent);
		}
		//���ع���������root�ڵ�
		return nodes.get(0);
	}	
}
//�����ڵ���
//Ϊ����Node����֧������Collections��������
//��Nodeʵ��Comparable�ӿ�
class Node implements Comparable<Node>{
	int value;//�ڵ�Ȩֵ
	Node left;//ָ�����ӽڵ�
	Node right;//ָ�����ӽڵ�
	//ǰ�����
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
		//��С�������򣨴�С��-��
		return this.value - o.value;
	}
	
}
