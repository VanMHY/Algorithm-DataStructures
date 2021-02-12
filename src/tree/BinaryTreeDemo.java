package tree;

public class BinaryTreeDemo {

	public static void main(String[] args) {
		// 创建一棵二叉树
		BinaryTree binaryTree = new BinaryTree();
		// 创建节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "关胜");
		// 先手动创建
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		binaryTree.setRoot(root);
		// 测试
//		System.out.println("前序遍历");
//		binaryTree.preOrder();
//		System.out.println("中序遍历");
//		binaryTree.infixOrder();
		System.out.println("后序遍历");
		binaryTree.postOrder();
		//前序遍历查找
//		System.out.println("前序遍历查找方式~~");
//		HeroNode resNode = binaryTree.preOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为no= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到no = %d 的英雄", 5);
//		}
//		System.out.println("中序遍历查找方式~~");
//		 HeroNode resNode = binaryTree.infixOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为no= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到no = %d 的英雄", 5);
//		}
//		System.out.println("后序遍历查找方式~~");
//		HeroNode resNode = binaryTree.postOrderSearch(5);
//		if (resNode != null) {
//			System.out.printf("找到了，信息为no= %d name =%s", resNode.getNo(), resNode.getName());
//		} else {
//			System.out.printf("没有找到no = %d 的英雄", 5);
//		}
		//测试删除节点代码
//		System.out.println("删除前，前序遍历");
//		binaryTree.preOrder();
//		//binaryTree.delNode(5);
//		binaryTree.delNode(5);
//		System.out.println("删除后，前序遍历");
//		binaryTree.preOrder();
//		
	}

}

//定义一个二叉树
class BinaryTree {
	private HeroNode root;

	public void setRoot(HeroNode root) {
		this.root = root;
	}
	//删除节点
	public void delNode(int no) {
		if(root !=null) {
			//如果只有一个root，立即判断是不是要删除的节点
			if(root.getNo() == no) {
				root =null;
			}else {
				//递归删除
				root.delNode(no);
			}
		}else {
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
	//前序遍历查找
	public HeroNode preOrderSearch(int no) {
		if (root != null) {
			return root.preOrderSearch(no);
		} else {
			return null;
		}
	}
	//中序遍历
	public HeroNode infixOrderSearch(int no) {
		if (root != null) {
			return root.infixOrderSearch(no);
		} else {
			return null;
		}
	}
	//后序遍历
	public HeroNode postOrderSearch(int no) {
		if (root != null) {
			return root.postOrdreSearch(no);
		} else {
			return null;
		}
	}
	
}

//创建 HeroNode结点
class HeroNode {
	private int no;
	private String name;
	private HeroNode left;// 默认null
	private HeroNode right;// 默认null

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
	//递归删除节点
	//如果删除的节点是叶子节点，则删除该节点
	//如果删除的节点是非叶子节点，则删除该子树.
	public void delNode(int no) {
		//判断左子节点
		if(this.left != null && this.left.no == no) {
			this.left = null;
			return;
		}
		//判断右子节点
		if (this.right != null && this.right.no == no) {
			this.right = null;
			return;
		}
		//左子树递归删除
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
	//前序遍历查找
	public HeroNode preOrderSearch(int no) {
		System.out.println("进入前序遍历查找的次数~~");
		//比较当前节点
		if(this.no == no) {
			return this;
		}
		//左边递归查找
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.preOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//右边递归查找
		if(this.right != null) {
			resNode = this.right.preOrderSearch(no);
		}
		return resNode;
	}
	//中序遍历查找
	public HeroNode infixOrderSearch(int no) {
		//先左递归
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//再比较
		System.out.println("进入中序遍历查找的次数~~");
		if(this.no == no) {
			return this;
		}
		//右递归
		if(this.right != null) {
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}
	//后序遍历查找
	public HeroNode postOrdreSearch(int no) {
		//左递归
		HeroNode resNode = null;
		if(this.left !=null) {
			resNode = this.left.postOrdreSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//右递归
		if(this.left !=null) {
			resNode = this.right.postOrdreSearch(no);
		}
		if(resNode != null) {
			return resNode;
		}
		//比较
		System.out.println("进入后序遍历查找的次数~~");
		if(this.no == no) {
			return this;
		}
		//未找到返回resNode
		return resNode;
	}
}
