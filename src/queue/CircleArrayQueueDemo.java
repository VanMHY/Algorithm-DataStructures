package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

	public static void main(String[] args) {
		//����
		System.out.println("��������ģ�⻷�ζ���~~~");
		//����һ�����ζ���
		CilcleArray queue = new CilcleArray(4);//�����Ч����n - 1
		char key = ' ';// �����û�����
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		// ���һ���˵�
		while (loop) {
			System.out.println("s(show):��ʾ����");
			System.out.println("e(exit):�˳�����");
			System.out.println("a(add):������ݵ�����");
			System.out.println("g(get):�Ӷ���ȡ������");
			System.out.println("h(head):�鿴����ͷ����");
			key = scanner.next().charAt(0);// ����һ���ַ�
			switch (key) {
			case 's':
				queue.showQueue();
				break;
			case 'a':
				System.out.println("���һ����");
				int value = scanner.nextInt();
				queue.addQueue(value);
				break;
			case 'g':// ȡ������
				try {
					int res = queue.getQueue();
					System.out.printf("�����������%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'h':// �鿴����ͷ������
				try {
					int res = queue.headQueue();
					System.out.printf("����ͷ������%d\n", res);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage());
				}
				break;
			case 'e':// �˳�����
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


class CilcleArray{
	private int maxSize;//��ʾ�����������
	//front�����ĺ�����һ������: front��ָ����еĵ�һ��Ԫ�أ�Ҳ����˵arr[front]
	//front�ĳ�ʼֵ=0
	private int front;
	//rear�����ĺ�����һ������:rearָ����е����һ��Ԫ�صĺ�һ��λ�á���Ϊϣ���ճ�һ���ռ���ΪԼ��
	//rear�ĳ�ʼֵ= 0
	private int rear;//����β
	private int[] arr;//���������ڴ�����ݣ�ģ�����
	public CilcleArray(int arrMaxSize){
		maxSize = arrMaxSize;
		arr = new int[maxSize];
	}
	//�ж������Ƿ���
	public boolean isFull(){
		return (rear + 1) % maxSize == front;
	}
	//�ж϶����Ƿ�Ϊ��
	public boolean isEmpty() {
		return rear == front;
	}
	//������ݵ�����
	public void addQueue(int n) {
			//�ж������Ƿ���
		if(isFull()) {
			System.out.println("�����������ܼ�������~");
			return;
		}
		//ֱ�ӽ����ݼ���
		arr[rear] = n;
		//��rear���ƣ�������뿼��ȡģ
		rear = (rear + 1) % maxSize;
	}
	//��ȡ�������ݣ�������
	public int getQueue() {
		//�ж϶����Ƿ��
		if(isEmpty()) {
			//ͨ�����쳣����
			throw new RuntimeException("���пգ�����ȡ����");
		}
		//������Ҫ������front��ָ����еĵ�һ��Ԫ��
		//�Ȱ�front��Ӧ��ֵ���浽һ����ʱ������
		//��front����,����ȡģ
		//����ʱ����ı�������
		int value = arr[front];
		front = (front + 1) % maxSize;
		return value;
	}
	//��ʾ���е���������
	public void showQueue() {
		// ����
		if (isEmpty()) {
			System.out.println("���пյģ�û������~");
			return;
		}
		//��front��ʼ����������size��Ԫ��
		for (int i = front; i < front + size(); i++) {
			System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
		}
	}
	//�����ǰ������Ч���ݵĸ���
	public int size() {
		return (rear + maxSize - front) % maxSize;
	}
	//��ʾ���е�ͷ���ݣ�����ȡ������
	public int headQueue() {
		// �ж�
		if (isEmpty()) {
			// System.out.println("���пյģ�û������~");
			throw new RuntimeException("���пյģ�û������~");
		}
		return arr[front];

	}
}
