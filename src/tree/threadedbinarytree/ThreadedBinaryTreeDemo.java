package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {

	public static void main(String[] args) {
		// 测试
		HeroNode root = new HeroNode(1, "tom");
		HeroNode node2 = new HeroNode(3, "jack");
		HeroNode node3 = new HeroNode(6, "smith");
		HeroNode node4 = new HeroNode(8, "mary");
		HeroNode node5 = new HeroNode(10, "king");
		HeroNode node6 = new HeroNode(14, "dim");
		// 二叉树创建
		root.setLeft(node2);
		root.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		// 线索化
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes();
		// 测试10号节点
		HeroNode leftNode = node5.getLeft();
		HeroNode rightNode = node5.getRight();
		System.out.println("10号节点的前驱节点 = " + leftNode);
		System.out.println("10号节点的后继节点 = " + rightNode);
		System.out.println("使用线索化的方式遍历线索化二叉树");
		threadedBinaryTree.threadedList();
	}

}

//定义ThreadedBinaryTree实现线索化功能的二叉树
class ThreadedBinaryTree {
	private HeroNode root;
	// 为了实现线索化，需要一个要给当前节点的前驱节点的指针
	// 在递归进行线索化时，pre总是保留前一个结点
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	// 重载threadedNodes方法
	public void threadedNodes() {
		this.threadedNodes(root);
	}

	// 遍历线索化二叉树的方法
	public void threadedList() {
		// 定义一个变量，临时存储当前遍历的节点。从root开始
		HeroNode node = root;
		while (node != null) {
			// 循环找到leftType == 1的节点，后面随着遍历变化
			// 因为leftType == 1是节点线索化后有效节点
			while (node.getLeftType() == 0) {
				node = node.getLeft();
			}
			// 打印节点
			System.out.println(node);
			// 如果当前节点的右指针指向的是后继节点，就一直输出
			while (node.getRightType() == 1) {
				// 获取后继节点
				node = node.getRight();
				System.out.println(node);
			}
			// 替换遍历的节点
			node = node.getRight();
		}
	}

	// 编写对二叉树进行中序线索化的方法
	/**
	 * 
	 * @param node 就是当前需要线索化的节点
	 */
	public void threadedNodes(HeroNode node) {
		// 如果node==null，不能线索化
		if (node == null) {
			return;
		}
		// 先线索化左子树
		threadedNodes(node.getLeft());
		// 线索化当前节点
		// 处理当前节点的前驱节点
		if (node.getLeft() == null) {
			// 让当前节点的左指针指向前驱节点
			node.setLeft(pre);
			// 修改当前节点的指针类型;指向前驱节点
			node.setLeftType(1);
		}
		// 处理后继节点
		if (pre != null && pre.getRight() == null) {
			// 让前驱节点的右指针指向当前节点
			pre.setRight(node);
			// 修改前驱节点的右指针类型
			pre.setRightType(1);
		}
		// 每处理一个节点后让当前节点是下一个节点的前驱节点
		pre = node;
		// 线索化右子节点
		threadedNodes(node.getRight());
	}

	// 删除节点
	public void delNode(int no) {
		if (root != null) {
			// 如果只有一个root，立即判断是不是要删除的节点
			if (root.getNo() == no) {
				root = null;
			} else {
				// 递归删除
				root.delNode(no);
			}
		} else {
			System.out.println("空树，不能删除");
		}
	}

	// 前序遍历
	public void preOrder() {
		if (this.root != null) {
			this.root.preOrder();
		} else {
			System.out.println("当前二叉树为空，无法遍历");
		}
	}

	// 中序遍历
	public void infixOrder() {
		if (this.root != null) {
			this.root.infixOrder();
		} else {
			System.out.println("当前二叉树为空，无法遍历");
		}
	}

	// 后序遍历
	public void postOrder() {
		if (this.root != null) {
			this.root.postOrder();
		} else {
			System.out.println("当前二叉树为空，无法遍历");
		}
	}

	// 前序遍历查找
	public HeroNode preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}

	// 中序遍历
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		} else {
			return null;
		}
	}

	// 后序遍历
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return root.postOrdreSearch(no);
		} else {
			return null;
		}
	}

}

//创建HeroNode节点
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// 默认null
	private HeroNode right;// 默认null
	// 如果leftType==0表示左子节点；如果为1表示前驱节点
	// 如果rightType==0表示右子节点；如果为1表示后继节点
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

	// 递归删除节点
	// 如果删除的节点是叶子节点，则删除该节点
	// 如果删除的节点是非叶子节点，则删除该子树.
	public void delNode(int no) {
		// 判断左子节点
		if (this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		// 判断右子节点
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		// 左子树递归删除
		if (this.left != null) {
			this.left.delNode(no);
		}
		// 右子树递归删除
		if (this.right != null) {
			this.right.delNode(no);
		}
	}

	// 编写前序遍历方法
	public void preOrder() {
		System.out.println(this);// 先输出父节点
		// 递归左子树前序遍历
		if (this.left != null) {
			this.left.preOrder();
		}
		// 递归右子树前序遍历
		if (this.right != null) {
			this.right.preOrder();
		}
	}

	// 中序遍历
	public void infixOrder() {
		// 递归向左子树中序遍历
		if (this.left != null) {
			this.left.infixOrder();
		}
		// 输出父节点
		System.out.println(this);
		// 递归向右子树中序遍历
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	// 后序遍历
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
	 * @param no 查找的编号
	 * @return 如果找到返回，没有找到返回null
	 */
	// 前序遍历查找
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历查找的次数~~");
		// 比较当前节点
		if (this.no == no) {
			return this;
		}
		// 左边递归查找
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.preOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 右边递归查找
		if (this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}

	// 中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		// 先左递归
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 再比较
		System.out.println("进入中序遍历查找的次数~~");
		if (this.no == no) {
			return this;
		}
		// 右递归
		if (this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}

	// 后序遍历查找
	public HeroNode postOrdreSearch(int no) {
		// 左递归
		HeroNode resNode = null;
		if (this.left != null) {
			resNode = this.left.postOrdreSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 右递归
		if (this.left != null) {
			resNode = this.right.postOrdreSearch(no);
		}
		if (resNode != null) {
			return resNode;
		}
		// 比较
		System.out.println("进入后序遍历查找的次数~~");
		if (this.no == no) {
			return this;
		}
		// 未找到返回resNode
		return resNode;
	}
}
