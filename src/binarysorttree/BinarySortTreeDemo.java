package binarysorttree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//ѭ������ӽڵ㵽������
		for(int i = 0; i< arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		//�������
		System.out.println("�����������������");
		binarySortTree.infixOrder();
		//����ɾ��Ҷ�ӽڵ�
		binarySortTree.delNode(2);
		binarySortTree.delNode(5);
		binarySortTree.delNode(9);
		binarySortTree.delNode(12);
		binarySortTree.delNode(7);
		binarySortTree.delNode(3);
		binarySortTree.delNode(10);
		binarySortTree.delNode(1);
		System.out.println("ɾ���ڵ��");
		binarySortTree.infixOrder();
		System.out.println("root=" + binarySortTree.getRoot());
	}

}
//��������������
class BinarySortTree{
	private Node root;
	
	public Node getRoot() {
		return root;
	}
	//����Ҫɾ���Ľڵ�
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	//���Ҹ��ڵ�ķ���
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}
	//��дһ������
	//ɾ����nodeΪ���ڵ�Ķ�������������С�ڵ��ֵ
	/**
	 * 
	 * @param node ����Ľڵ㣨���������������ĸ��ڵ㣩
	 * @return ������nodeΪ���ڵ�Ķ�������������С�ڵ��ֵ
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		//ѭ���������ӽڵ㣬�ͻ��ҵ���Сֵ
		while(target.left != null) {
			target = target.left;
		}
		//��ʱ���ҵ�����С�Ľڵ�
		//ɾ����С�ڵ�
		delNode(target.value);
		return target.value;
	}
	//ɾ���ڵ�
	public void delNode(int value) {
		if(root == null) {
			return;
		}else {
			//���ҵ�Ҫɾ���Ľڵ�
			Node targetNode = search(value);
			//���û���ҵ�Ҫɾ���Ľڵ�
			if(targetNode == null) {
				return;
			}
			//������ֵ�ǰ����������ֻ��һ���ڵ�
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}
			//ȥ����targetNode�ĸ��ڵ�
			Node parent = searchParent(value);
			//���ɾ���Ľڵ���Ҷ�ӽڵ�
			if(targetNode.left == null && targetNode.right == null) {
				//�ж�targetNode�Ǹ��ڵ�����ӽڵ㣬�������ӽڵ�
				if(parent.left != null && parent.left.value == value) {//�����ӽڵ�
					parent.left = null;
				} else if(parent.right != null && parent.right.value == value) {//�����ӽڵ�
					parent.right = null;
				}
			}else if(targetNode.left != null && targetNode.right != null) {//ɾ�������������Ľڵ�
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
			}else {//ɾ��ֻ��һ�������Ľڵ�
				//���Ҫɾ���Ľڵ������ӽڵ�
				if (targetNode.left != null) {
					if (parent != null) {
						// ���targetNode��parent�����ӽڵ�
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else {// targetNode��parent�����ӽڵ�
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else {// ���Ҫɾ���Ľڵ������ӽڵ�
					if (parent != null) {
						// ���targetNode��parent�����ӽڵ�
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else {// ���targetNode��parent�����ӽڵ�
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}
			}
		}
	}
	//��ӽڵ㷽��
	public void add(Node node) {
		if(root == null) {
			root = node;//���rootΪ�գ�rootֱ��ָ��node	
		}else {
			root.add(node);
		}
	}
	//����
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		} else {
			System.out.println("������Ϊ�գ����ܱ���");
		}
	}
}
//����Node�ڵ�
class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	//����Ҫɾ���Ľڵ�
	/**
	 * 
	 * @param value  ϣ��ɾ���Ľڵ��ֵ
	 * @return �ҵ����ظýڵ㣬���򷵻�null
	 */
	public Node search(int value) {
		if(value == this.value) {//�ҵ�����ɾ���Ľڵ�
			return this;
		}else if(value < this.value) {//������ҵ�ֵС�ڵ�ǰ�ڵ㣬����ݹ����
			if(this.left == null) {
				//������ӽڵ�Ϊnull
				return null;
			}
			return this.left.search(value);
		} else {//������ҵ�ֵ��С�ڵ�ǰ�ڵ㣬���ҵݹ����
			if(this.right == null) {
				//������ӽڵ�Ϊnull
				return null;
			}
			return this.right.search(value);
		}
	}
	//����Ҫɾ���ڵ�ĸ��ڵ�
	/**
	 * 
	 * @param value Ҫ�ҵĽڵ��ֵ
	 * @return ����Ҫɾ���ڵ�ĸ��ڵ㣬���û���ҵ��ͷ���null
	 */
	public Node searchParent(int value) {
		//�����ǰ�ڵ����Ҫɾ���ڵ�ĸ��ڵ�ͷ���
		if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		}else {
			//������ҵ�ֵС�ڵ�ǰ�ڵ��ֵ�����ҵ�ǰ�ڵ�����ӽڵ㲻Ϊ��
			if(value< this.value && this.left != null) {
				return this.left.searchParent(value);//����ݹ����
			}else if(value >= this.value && this.right != null) {
				return this.right.searchParent(value);//���������ݹ����
			}else {
				return null;
			}
		}	
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	//��ӽڵ�
	//�ݹ����ʽ��ӣ�Ҫ���������������Ҫ��
	public void add(Node node) {
		if(node == null) {
			return;
		}
		//�жϴ���ڵ��ֵ�͵�ǰ�����ڵ��ֵ�Ĺ�ϵ
		if(node.value < this.value) {
			//�����ǰ�ڵ����ӽڵ�Ϊ��
			if(this.left==null) {
				this.left = node;
			} else {
				//�ݹ������������
				this.left.add(node);
			}
		}else {//��ӵĽڵ��ֵ���ڵ�ǰ�ڵ��ֵ
			if(this.right == null) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
	}
	//�������
	public void infixOrder() {
		if(left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if(right != null) {
			this.right.infixOrder();
		}
		
	}
}
