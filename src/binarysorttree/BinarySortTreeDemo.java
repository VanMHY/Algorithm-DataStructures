package binarysorttree;

public class BinarySortTreeDemo {

	public static void main(String[] args) {
		int[] arr = { 7, 3, 10, 12, 5, 1, 9, 2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//循环的添加节点到二叉树
		for(int i = 0; i< arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}
		//中序遍历
		System.out.println("中序遍历二叉排序树");
		binarySortTree.infixOrder();
		//测试删除叶子节点
		binarySortTree.delNode(2);
		binarySortTree.delNode(5);
		binarySortTree.delNode(9);
		binarySortTree.delNode(12);
		binarySortTree.delNode(7);
		binarySortTree.delNode(3);
		binarySortTree.delNode(10);
		binarySortTree.delNode(1);
		System.out.println("删除节点后");
		binarySortTree.infixOrder();
		System.out.println("root=" + binarySortTree.getRoot());
	}

}
//创建二叉排序树
class BinarySortTree{
	private Node root;
	
	public Node getRoot() {
		return root;
	}
	//查找要删除的节点
	public Node search(int value) {
		if(root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	//查找父节点的方法
	public Node searchParent(int value) {
		if(root == null) {
			return null;
		} else {
			return root.searchParent(value);
		}
	}
	//编写一个方法
	//删除以node为根节点的二叉排序树的最小节点的值
	/**
	 * 
	 * @param node 传入的节点（当作二叉排序树的根节点）
	 * @return 返回以node为根节点的二叉排序树的最小节点的值
	 */
	public int delRightTreeMin(Node node) {
		Node target = node;
		//循环查找左子节点，就会找到最小值
		while(target.left != null) {
			target = target.left;
		}
		//这时就找到了最小的节点
		//删除最小节点
		delNode(target.value);
		return target.value;
	}
	//删除节点
	public void delNode(int value) {
		if(root == null) {
			return;
		}else {
			//先找到要删除的节点
			Node targetNode = search(value);
			//如果没有找到要删除的节点
			if(targetNode == null) {
				return;
			}
			//如果发现当前二叉排序树只有一个节点
			if(root.left == null && root.right == null) {
				root = null;
				return;
			}
			//去查找targetNode的父节点
			Node parent = searchParent(value);
			//如果删除的节点是叶子节点
			if(targetNode.left == null && targetNode.right == null) {
				//判断targetNode是父节点的左子节点，还是右子节点
				if(parent.left != null && parent.left.value == value) {//是左子节点
					parent.left = null;
				} else if(parent.right != null && parent.right.value == value) {//是右子节点
					parent.right = null;
				}
			}else if(targetNode.left != null && targetNode.right != null) {//删除有两颗子树的节点
				int minVal = delRightTreeMin(targetNode.right);
				targetNode.value = minVal;
			}else {//删除只有一颗子树的节点
				//如果要删除的节点有左子节点
				if (targetNode.left != null) {
					if (parent != null) {
						// 如果targetNode是parent的左子节点
						if (parent.left.value == value) {
							parent.left = targetNode.left;
						} else {// targetNode是parent的右子节点
							parent.right = targetNode.left;
						}
					} else {
						root = targetNode.left;
					}
				} else {// 如果要删除的节点有右子节点
					if (parent != null) {
						// 如果targetNode是parent的左子节点
						if (parent.left.value == value) {
							parent.left = targetNode.right;
						} else {// 如果targetNode是parent的右子节点
							parent.right = targetNode.right;
						}
					} else {
						root = targetNode.right;
					}
				}
			}
		}
	}
	//添加节点方法
	public void add(Node node) {
		if(root == null) {
			root = node;//如果root为空，root直接指向node	
		}else {
			root.add(node);
		}
	}
	//遍历
	public void infixOrder() {
		if(root != null) {
			root.infixOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}
}
//创建Node节点
class Node{
	int value;
	Node left;
	Node right;
	public Node(int value) {
		this.value = value;
	}
	//查找要删除的节点
	/**
	 * 
	 * @param value  希望删除的节点的值
	 * @return 找到返回该节点，否则返回null
	 */
	public Node search(int value) {
		if(value == this.value) {//找到就是删除的节点
			return this;
		}else if(value < this.value) {//如果查找的值小于当前节点，向左递归查找
			if(this.left == null) {
				//如果左子节点为null
				return null;
			}
			return this.left.search(value);
		} else {//如果查找的值不小于当前节点，向右递归查找
			if(this.right == null) {
				//如果右子节点为null
				return null;
			}
			return this.right.search(value);
		}
	}
	//查找要删除节点的父节点
	/**
	 * 
	 * @param value 要找的节点的值
	 * @return 返回要删除节点的父节点，如果没有找到就返回null
	 */
	public Node searchParent(int value) {
		//如果当前节点就是要删除节点的父节点就返回
		if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		}else {
			//如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
			if(value< this.value && this.left != null) {
				return this.left.searchParent(value);//向左递归查找
			}else if(value >= this.value && this.right != null) {
				return this.right.searchParent(value);//向右子树递归查找
			}else {
				return null;
			}
		}	
	}
	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	//添加节点
	//递归的形式添加，要满足二叉排序树的要求
	public void add(Node node) {
		if(node == null) {
			return;
		}
		//判断传入节点的值和当前子树节点的值的关系
		if(node.value < this.value) {
			//如果当前节点左子节点为空
			if(this.left==null) {
				this.left = node;
			} else {
				//递归向左子树添加
				this.left.add(node);
			}
		}else {//添加的节点的值大于当前节点的值
			if(this.right == null) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
	}
	//中序遍历
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
