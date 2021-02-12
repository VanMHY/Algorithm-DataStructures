package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
		// ����
		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "dim");
		// ����������
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		// ������
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();
		// ����10�Žڵ�
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("10�Žڵ��ǰ���ڵ� = " + leftNode);
		System.out.println("10�Žڵ�ĺ�̽ڵ� = " + rightNode);
		System.out.println("ʹ���������ķ�ʽ����������������");
		threadedBinaryTree.threadedList();
	}

}

//����ThreadedBinaryTreeʵ�����������ܵĶ�����
class ThreadedBinaryTree {
	private HeroNode root;
	// Ϊ��ʵ������������Ҫһ��Ҫ����ǰ�ڵ��ǰ���ڵ��ָ��
	// �ڵݹ����������ʱ��pre���Ǳ���ǰһ�����
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// ����threadedNodes����
	public void threadedNodes() {
		this.threadedNodes(root);
	}

	// �����������������ķ���
	public void threadedList() {
		// ����һ����������ʱ�洢��ǰ�����Ľڵ㡣��root��ʼ
		HeroNode node = root;
		while (node != null) {
			// ѭ���ҵ�leftType == 1�Ľڵ㣬�������ű����仯
			// ��ΪleftType == 1�ǽڵ�����������Ч�ڵ�
			while (node.getLeftType() == 0) {
				node = node.getLeft();
			}
			// ��ӡ�ڵ�
			System.out.println(node);
			// �����ǰ�ڵ����ָ��ָ����Ǻ�̽ڵ㣬��һֱ���
			while (node.getRightType() == 1) {
				// ��ȡ��̽ڵ�
				node = node.getRight();
				System.out.println(node);
			}
			// �滻�����Ľڵ�
			node = node.getRight();
		}
	}

	// ��д�Զ��������������������ķ���
	/**
	 * 
	 * @param node ���ǵ�ǰ��Ҫ�������Ľڵ�
	 */
	public void threadedNodes(HeroNode node) {
		// ���node==null������������
		if (node == null) {
			return;
		}
		// ��������������
		threadedNodes(node.getLeft());
		// ��������ǰ�ڵ�
		// ����ǰ�ڵ��ǰ���ڵ�
		if (node.getLeft() == null) {
			// �õ�ǰ�ڵ����ָ��ָ��ǰ���ڵ�
			node.setLeft(pre);
			// �޸ĵ�ǰ�ڵ��ָ������;ָ��ǰ���ڵ�
			node.setLeftType(1);
		}
		// �����̽ڵ�
		if (pre != null && pre.getRight() == null) {
			// ��ǰ���ڵ����ָ��ָ��ǰ�ڵ�
			pre.setRight(node);
			// �޸�ǰ���ڵ����ָ������
			pre.setRightType(1);
		}
		// ÿ����һ���ڵ���õ�ǰ�ڵ�����һ���ڵ��ǰ���ڵ�
		pre = node;
		// ���������ӽڵ�
		threadedNodes(node.getRight());
	}

	// ɾ���ڵ�
	public void delNode(int no) {
		if (root != null) {
			// ���ֻ��һ��root�������ж��ǲ���Ҫɾ���Ľڵ�
			if (root.getNo() == no) {
				root = null;
			} else {
				// �ݹ�ɾ��
				root.delNode(no);
			}
		} else {
			System.out.println("����������ɾ��");
		}
	}

	// ǰ�����
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("��ǰ������Ϊ�գ��޷�����");
		}
	}

	// �������
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("��ǰ������Ϊ�գ��޷�����");
		}
	}

	// �������
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("��ǰ������Ϊ�գ��޷�����");
		}
	}

	// ǰ���������
	public HeroNode preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	// �������
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		} else {
			return null;
		}
	}

	// �������
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return root.postOrdreSearch(no);
		} else {
			return null;
		}
	}

}

//����HeroNode�ڵ�
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// Ĭ��null
	private HeroNode right;// Ĭ��null
	// ���leftType==0��ʾ���ӽڵ㣻���Ϊ1��ʾǰ���ڵ�
	// ���rightType==0��ʾ���ӽڵ㣻���Ϊ1��ʾ��̽ڵ�
	private int leftType;
	private int rightType;

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode [no=" + no + ", name=" + name + "]";
	}

	// �ݹ�ɾ���ڵ�
	// ���ɾ���Ľڵ���Ҷ�ӽڵ㣬��ɾ���ýڵ�
	// ���ɾ���Ľڵ��Ƿ�Ҷ�ӽڵ㣬��ɾ��������.
	public void delNode(int no) {
		// �ж����ӽڵ�
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		// �ж����ӽڵ�
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		// �������ݹ�ɾ��
		if (this.left != null) {
			this.left.delNode(no);
		}
		// �������ݹ�ɾ��
		if (this.right != null) {
			this.right.delNode(no);
		}
	}

	// ��дǰ���������
	public void preOrder() {
		System.out.println(this);// ��������ڵ�
		// �ݹ�������ǰ�����
		if (this.left != null) {
			this.left.preOrder();
		}
		// �ݹ�������ǰ�����
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// �������
	public void infixOrder() {
		// �ݹ����������������
		if (this.left != null) {
			this.left.infixOrder();
		}
		// ������ڵ�
		System.out.println(this);
		// �ݹ����������������
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// �������
	public void postOrder() {
		if (this.left != null) {
			this.left.postOrder();
		}
		if (this.right != null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}

	/**
	 * 
	 * @param no ���ҵı��
	 * @return ����ҵ����أ�û���ҵ�����null
	 */
	// ǰ���������
	public HeroNode preOrderSearch(int no) {
		System.out.println("����ǰ��������ҵĴ���~~");
		// �Ƚϵ�ǰ�ڵ�
		if (this.no == no) {
			return this;
		}
		// ��ߵݹ����
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// �ұߵݹ����
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}

	// �����������
	public HeroNode infixOrderSearch(int no) {
		// ����ݹ�
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// �ٱȽ�
		System.out.println("��������������ҵĴ���~~");
		if (this.no == no) {
			return this;
		}
		// �ҵݹ�
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}

	// �����������
	public HeroNode postOrdreSearch(int no) {
		// ��ݹ�
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrdreSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// �ҵݹ�
		if (this.left != null) {
			resNode = this.right.postOrdreSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// �Ƚ�
		System.out.println("�������������ҵĴ���~~");
		if (this.no == no) {
			return this;
		}
		// δ�ҵ�����resNode
		return resNode;
	}
}
