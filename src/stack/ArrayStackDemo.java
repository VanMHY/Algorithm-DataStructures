package stack;

import java.util.Scanner;

public class ArrayStackDemo {

	public static void main(String[] args) {
		//����ArrayStack
		//�ȴ���һ��ArrayStack����-����ʾһ��ջ
		ArrayStack stack = new ArrayStack(4);
		String key = "";
		boolean loop = true;//�����Ƿ��˳��˵�
		Scanner scanner = new Scanner(System.in);
		while(loop) {
			System.out.println("show:��ʾջ");
			System.out.println("exit:�˳�����");
			System.out.println("push:������ݵ�ջ");
			System.out.println("pop:��ջȡ������");
			System.out.println("���������ѡ��");
			key = scanner.next();//����һ���ַ�
			switch (key) {
			case "show":
				stack.list();
				break;
			case "push":
				System.out.println("������һ����");
				int value = scanner.nextInt();
				stack.push(value);
				break;
			case "pop":
				try {
					int res = stack.pop();
					System.out.printf("��ջ������Ϊ%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case "exit":
				scanner.close();
				loop = false;
				break;

			default:
				break;
			}
		}
		System.out.println("�����˳�");
	}

}
//����һ���࣬��ʾջ
class ArrayStack{
	private int maxSize;//ջ�Ĵ�С
	private int[] stack;//���飬����ģ��ջ�����ݾͷ��ڸ�������
	private int top = -1;//top��ʾջ������ʼ��Ϊ-1
	//������
	public  ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	//�ж�ջ��
	public boolean isFull() {
		return top == maxSize - 1;
	}
	//�ж�ջ��
	public boolean isEmpty() {
		return top == -1;
	}
	//��ջ
	public void push(int value) {
		//���ж�ջ��
		if(isFull()) {
			System.out.println("ջ��");
			return;
		}
		top++;
		stack[top] = value;
	}
	//��ջ,��ջ��Ԫ�ط���
	public int pop() {
		//�ж�ջ�Ƿ��
		if(isEmpty()) {
			//�׳��쳣
			throw new RuntimeException("ջ�գ�û������~~");
		}
		int value = stack[top];
		top--;
		return value;
	}
	//��ʾջ�����[����ջ],����ʱ����Ҫ��ջ����ʼ��ʾ����
	public void list() {
		if(isEmpty()) {
			System.out.println("ջ�գ�û������~~");
			return;
		}
		for(int i = top; i >= 0; i--) {
			System.out.printf("stack[%d]=%d\n", i,stack[i]);
		}
	}
}
