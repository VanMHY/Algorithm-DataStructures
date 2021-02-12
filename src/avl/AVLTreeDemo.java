package avl;

public class AVLTreeDemo {

	public static void main(String[] args) {
		//int[] arr = { 4, 3, 6, 5, 7, 8 };
		//int[] arr = { 10, 12, 8, 9, 7, 6 };
		int[] arr = { 10, 11, 7, 6, 8, 9 };
		// ����һ��AVLTree����
		AVLTree avlTree = new AVLTree();
		// ���ӽڵ�
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new Node(arr[i]));
		}
		// ����
		System.out.println("�������");
		avlTree.infixOrder();
		System.out.println("ƽ�⴦����~~");
		System.out.println("���ĸ߶�="+avlTree.getRoot().height());
		System.out.println("�����������߶�="+avlTree.getRoot().leftHeight());
		System.out.println("�����������߶�="+avlTree.getRoot().rightHeight());
		System.out.println("��ǰ���ڵ�Ϊ="+avlTree.getRoot());
	}
}

//����AVL��
	class AVLTree {
		private Node root;

		public Node getRoot() {
			return root;
		}

		// ����Ҫɾ���Ľڵ�
		public Node search(int value) {
			if (root == null) {
				return null;
			} else {
				return root.search(value);
			}
		}

		// ���Ҹ��ڵ�ķ���
		public Node searchParent(int value) {
			if (root == null) {
				return null;
			} else {
				return root.searchParent(value);
			}
		}

		// ��дһ������
		// ɾ����nodeΪ���ڵ�Ķ�������������С�ڵ��ֵ
		/**
		 * 
		 * @param node ����Ľڵ㣨���������������ĸ��ڵ㣩
		 * @return ������nodeΪ���ڵ�Ķ�������������С�ڵ��ֵ
		 */
		public int delRightTreeMin(Node node) {
			Node target = node;
			// ѭ���������ӽڵ㣬�ͻ��ҵ���Сֵ
			while (target.left != null) {
				target = target.left;
			}
			// ��ʱ���ҵ�����С�Ľڵ�
			// ɾ����С�ڵ�
			delNode(target.value);
			return target.value;
		}

		// ɾ���ڵ�
		public void delNode(int value) {
			if (root == null) {
				return;
			} else {
				// ���ҵ�Ҫɾ���Ľڵ�
				Node targetNode = search(value);
				// ���û���ҵ�Ҫɾ���Ľڵ�
				if (targetNode == null) {
					return;
				}
				// ������ֵ�ǰ����������ֻ��һ���ڵ�
				if (root.left == null && root.right == null) {
					root = null;
					return;
				}
				// ȥ����targetNode�ĸ��ڵ�
				Node parent = searchParent(value);
				// ���ɾ���Ľڵ���Ҷ�ӽڵ�
				if (targetNode.left == null && targetNode.right == null) {
					// �ж�targetNode�Ǹ��ڵ�����ӽڵ㣬�������ӽڵ�
					if (parent.left != null && parent.left.value == value) {// �����ӽڵ�
						parent.left = null;
					} else if (parent.right != null && parent.right.value == value) {// �����ӽڵ�
						parent.right = null;
					}
				} else if (targetNode.left != null && targetNode.right != null) {// ɾ�������������Ľڵ�
					int minVal = delRightTreeMin(targetNode.right);
					targetNode.value = minVal;
				} else {// ɾ��ֻ��һ�������Ľڵ�
						// ���Ҫɾ���Ľڵ������ӽڵ�
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

		// ���ӽڵ㷽��
		public void add(Node node) {
			if (root == null) {
				root = node;// ���rootΪ�գ�rootֱ��ָ��node
			} else {
				root.add(node);
			}
		}

		// ����
		public void infixOrder() {
			if (root != null) {
				root.infixOrder();
			} else {
				System.out.println("������Ϊ�գ����ܱ���");
			}
		}
	}

//����node�ڵ�
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	// �������ĸ߶�
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}

	// �����������߶�
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}

	// �����Ե�ǰ�ڵ�Ϊ���ڵ�ĸ߶�
	public int height() {
		return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
	}
	//����ת�ķ���
	private void leftRotate() {
		//�����µĽڵ�,�Ե�ǰ���ڵ��ֵ
		Node newNode = new Node(value);
		//���µĽڵ�����������óɵ�ǰ�ڵ��������
		newNode.left = left;
		//���µĽڵ�����������óɵ�ǰ�ڵ����������������
		newNode.right = right.left;
		//�ѵ�ǰ�ڵ��ֵ�滻�����ӽڵ��ֵ
		value = right.value;
		//�ѵ�ǰ�ڵ�������������ó���������������
		right = right.right;
		//�ѵ�ǰ�ڵ�������������ӽڵ㣩���ó��µĽڵ�
		left = newNode;
	}
	//����ת�ķ���
	private void rightRotate() {
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}
	// ����Ҫɾ���Ľڵ�
	/**
	 * 
	 * @param value ϣ��ɾ���Ľڵ��ֵ
	 * @return �ҵ����ظýڵ㣬���򷵻�null
	 */
	public Node search(int value) {
		if (value == this.value) {// �ҵ�����ɾ���Ľڵ�
			return this;
		} else if (value < this.value) {// ������ҵ�ֵС�ڵ�ǰ�ڵ㣬����ݹ����
			if (this.left == null) {
				// ������ӽڵ�Ϊnull
				return null;
			}
			return this.left.search(value);
		} else {// ������ҵ�ֵ��С�ڵ�ǰ�ڵ㣬���ҵݹ����
			if (this.right == null) {
				// ������ӽڵ�Ϊnull
				return null;
			}
			return this.right.search(value);
		}
	}

	// ����Ҫɾ���ڵ�ĸ��ڵ�
	/**
	 * 
	 * @param value Ҫ�ҵĽڵ��ֵ
	 * @return ����Ҫɾ���ڵ�ĸ��ڵ㣬���û���ҵ��ͷ���null
	 */
	public Node searchParent(int value) {
		// �����ǰ�ڵ����Ҫɾ���ڵ�ĸ��ڵ�ͷ���
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
			return this;
		} else {
			// ������ҵ�ֵС�ڵ�ǰ�ڵ��ֵ�����ҵ�ǰ�ڵ�����ӽڵ㲻Ϊ��
			if (value < this.value && this.left != null) {
				return this.left.searchParent(value);// ����ݹ����
			} else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value);// ���������ݹ����
			} else {
				return null;
			}
		}
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	// ���ӽڵ�
	// �ݹ����ʽ���ӣ�Ҫ���������������Ҫ��
	public void add(Node node) {
		if (node == null) {
			return;
		}
		// �жϴ���ڵ��ֵ�͵�ǰ�����ڵ��ֵ�Ĺ�ϵ
		if (node.value < this.value) {
			// �����ǰ�ڵ����ӽڵ�Ϊ��
			if (this.left == null) {
				this.left = node;
			} else {
				// �ݹ�������������
				this.left.add(node);
			}
		} else {// ���ӵĽڵ��ֵ���ڵ�ǰ�ڵ��ֵ
			if (this.right == null) {
				this.right = node;
			} else {
				this.right.add(node);
			}
		}
		//��������һ���ڵ����������������ĸ߶� -�������߶ȣ�>1,����ת
		if(rightHeight() - leftHeight() > 1) {
			//����������������������߶ȴ��������������ĸ߶�
			if(right != null && right.leftHeight() > right.rightHeight()) {
				right.rightRotate();
				leftRotate();//����ת
			}else {
				//ֱ�ӽ�������ת
			    leftRotate();
			}
			return;//����Ҫ
		}
		//��������һ���ڵ����������������ĸ߶� -�������߶ȣ�>1,����ת
		if(leftHeight() - rightHeight() > 1) {
			//����������������������߶ȴ��������������ĸ߶�
			if(left != null && left.rightHeight() > left.leftHeight()) {
				//�ȶԵ�ǰ�ڵ����ڵ㣨��������->����ת
				left.leftRotate();
				//�ٶԵ�ǰ�ڵ��������ת
				rightRotate();
			}else {
				//ֱ������ת����
				rightRotate();
			}
		}
		
	}

	// �������
	public void infixOrder() {
		if (left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (right != null) {
			this.right.infixOrder();
		}

	}
}