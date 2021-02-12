package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		// ����һ�ö�����
		BinaryTree binaryTree = new BinaryTree();
		// �����ڵ�
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode node2 = new HeroNode(2, "����");
		HeroNode node3 = new HeroNode(3, "¬����");
		HeroNode node4 = new HeroNode(4, "�ֳ�");
		HeroNode node5 = new HeroNode(5, "��ʤ");
		// ���ֶ�����
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		// ����
//		System.out.println("ǰ�����");
//		binaryTree.preOrder();
//		System.out.println("�������");
//		binaryTree.infixOrder();
		System.out.println("�������");
		binaryTree.postOrder();
		//ǰ���������
//		System.out.println("ǰ��������ҷ�ʽ~~");
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("�ҵ��ˣ���ϢΪno= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("û���ҵ�no = %d ��Ӣ��", 5);
//		}
//		System.out.println("����������ҷ�ʽ~~");
//		 HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("�ҵ��ˣ���ϢΪno= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("û���ҵ�no = %d ��Ӣ��", 5);
//		}
//		System.out.println("����������ҷ�ʽ~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("�ҵ��ˣ���ϢΪno= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("û���ҵ�no = %d ��Ӣ��", 5);
//		}
		//����ɾ���ڵ����
//		System.out.println("ɾ��ǰ��ǰ�����");
//		binaryTree.preOrder();
//		//binaryTree.delNode(5);
//		binaryTree.delNode(5);
//		System.out.println("ɾ����ǰ�����");
//		binaryTree.preOrder();
//		
	}

}

//����һ��������
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//ɾ���ڵ�
	public void delNode(int no) {
		if(root !=null) {
			//���ֻ��һ��root�������ж��ǲ���Ҫɾ���Ľڵ�
			if(root.getNo() == no) {
				root =null;
			}else {
				//�ݹ�ɾ��
				root.delNode(no);
			}
		}else {
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
	//ǰ���������
	public HeroNode preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}
	//�������
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		} else {
			return null;
		}
	}
	//�������
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return root.postOrdreSearch(no);
		} else {
			return null;
		}
	}
	
}

//���� HeroNode���
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// Ĭ��null
	private HeroNode right;// Ĭ��null

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
	//�ݹ�ɾ���ڵ�
	//���ɾ���Ľڵ���Ҷ�ӽڵ㣬��ɾ���ýڵ�
	//���ɾ���Ľڵ��Ƿ�Ҷ�ӽڵ㣬��ɾ��������.
	public void delNode(int no) {
		//�ж����ӽڵ�
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		//�ж����ӽڵ�
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//�������ݹ�ɾ��
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
	//ǰ���������
	public HeroNode preOrderSearch(int no) {
		System.out.println("����ǰ��������ҵĴ���~~");
		//�Ƚϵ�ǰ�ڵ�
		if(this.no == no) {
			return this;
		}
		//��ߵݹ����
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//�ұߵݹ����
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	//�����������
	public HeroNode infixOrderSearch(int no) {
		//����ݹ�
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//�ٱȽ�
		System.out.println("��������������ҵĴ���~~");
		if(this.no == no) {
			return this;
		}
		//�ҵݹ�
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	//�����������
	public HeroNode postOrdreSearch(int no) {
		//��ݹ�
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.postOrdreSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//�ҵݹ�
		if(this.left !=null) {
			resNode = this.right.postOrdreSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//�Ƚ�
		System.out.println("�������������ҵĴ���~~");
		if(this.no == no) {
			return this;
		}
		//δ�ҵ�����resNode
		return resNode;
	}
}
