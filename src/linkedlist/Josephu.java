package linkedlist;

public class Josephu {

	public static void main(String[] args) {
		//���Կ���������������ͱ����Ƿ�ok
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);//����?��С��
		circleSingleLinkedList.showBoy();
		//����С����Ȧ
		circleSingleLinkedList.countBoy(1, 2, 5);
	}
	

}
//����һ�����εĵ�������
class CircleSingleLinkedList{
	//����һ��first�ڵ㣬��ǰû�б��
	private Boy first = new Boy(-1);
	//���С���ڵ㣬���ɻ�������
	public void addBoy(int nums) {
		//nums ��һ������У��
		if(nums < 1) {
			System.out.println("nums��ֵ����ȷ");
			return;
		}
		//ʹ��forѭ���������˻�������
		Boy curBoy = null;//����ָ�룬����������������
		for(int i = 1; i <= nums; i++) {
			//���ݱ��  ����С���ڵ�
			Boy boy = new Boy(i);
			//����ǵ�һ��С����
			if(i == 1) {
				first = boy;
				first.setNext(first);//���ɻ�
				curBoy = first;//��curBoyָ���һ��С��
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}	
	}
	//������ǰ��������
	public void showBoy() {
		//�ж������Ƿ�Ϊ��
		if(first == null) {
			System.out.println("û���κ�С��~");
			return;
		}
		//first���ܶ�����Ҫһ������ָ����������ɱ���
		Boy curBoy = first;
		while(true) {
			System.out.printf("С���ı��%d\n", curBoy.getNo());
			if(curBoy.getNext() == first) {//˵���������
				break;
			}
			curBoy = curBoy.getNext();//curBoy����
		}
	}
	//�����û���������С����Ȧ��˳��
	/**
	 * 
	 * @param startNo ��ʾ�ӵڼ���С����ʼ����
	 * @param countNum��ʾ������
	 * @param nums ��ʾ����ж���С����Ȧ
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		//�ȶ����ݽ���У��
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("����������������������");
			return;
		}
		//����һ������ָ�룬����С����Ȧ
		Boy helper = first;//�ʼָ�����һ���ڵ�
			while(true) {
				if(helper.getNext() == first) {//helper���ҵ����ڵ�
					break;
				}
				helper = helper.getNext();
			}
			//С������ǰ������first��helper�ƶ�k-1��
			for(int j = 0; j < startNo - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//��С������ʱ����first��helperͬʱ�ƶ� countNum- 1�Σ�Ȼ���Ȧ��һ��ѭ��������ֱ��Ȧ��ֻ��һ����
			while(true) {
				if(first == helper) {//˵��ֻ��һ����
					break;
				}
				//��first��helperͬʱ�ƶ�countNum-1�Σ�Ȼ���Ȧ
				for(int j = 0; j < countNum - 1; j++) {
					first = first.getNext();
					helper = helper.getNext();
				}
				//����firstָ��Ľڵ㣬����Ҫ��Ȧ��С��
				System.out.printf("С��%d��Ȧ\n", first.getNo());
				//���ǽ�firstָ���С����Ȧ
				first = first.getNext();
				helper.setNext(first);
			}
			System.out.printf("�������Ȧ�е�С�����%d\n", first.getNo());
			
	}
}
//����һ��Boy��
class Boy{
	private int no;
	private Boy next;
	public Boy(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Boy getNext() {
		return next;
	}
	public void setNext(Boy next) {
		this.next = next;
	}
}
