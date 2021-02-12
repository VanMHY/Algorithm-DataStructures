package linkedlist;

public class Josephu {

	public static void main(String[] args) {
		//测试看看构建环形链表和遍历是否ok
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);//加入?个小孩
		circleSingleLinkedList.showBoy();
		//测试小孩出圈
		circleSingleLinkedList.countBoy(1, 2, 5);
	}
	

}
//创建一个环形的单向链表
class CircleSingleLinkedList{
	//创建一个first节点，当前没有编号
	private Boy first = new Boy(-1);
	//添加小孩节点，构成环形链表
	public void addBoy(int nums) {
		//nums 做一个数据校验
		if(nums < 1) {
			System.out.println("nums的值不正确");
			return;
		}
		//使用for循环来创建了环形链表
		Boy curBoy = null;//辅助指针，帮助构建环形链表
		for(int i = 1; i <= nums; i++) {
			//根据编号  创建小孩节点
			Boy boy = new Boy(i);
			//如果是第一个小孩，
			if(i == 1) {
				first = boy;
				first.setNext(first);//构成环
				curBoy = first;//让curBoy指向第一个小孩
			}else {
				curBoy.setNext(boy);
				boy.setNext(first);
				curBoy = boy;
			}
		}	
	}
	//遍历当前环形链表
	public void showBoy() {
		//判断链表是否为空
		if(first == null) {
			System.out.println("没有任何小孩~");
			return;
		}
		//first不能动，需要一个辅助指针来帮助完成遍历
		Boy curBoy = first;
		while(true) {
			System.out.printf("小孩的编号%d\n", curBoy.getNo());
			if(curBoy.getNext() == first) {//说明遍历完毕
				break;
			}
			curBoy = curBoy.getNext();//curBoy后移
		}
	}
	//根据用户输入计算出小孩出圈的顺序
	/**
	 * 
	 * @param startNo 表示从第几个小孩开始数数
	 * @param countNum表示数几下
	 * @param nums 表示最初有多少小孩在圈
	 */
	public void countBoy(int startNo, int countNum, int nums) {
		//先对数据进行校验
		if(first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误，请重新输入");
			return;
		}
		//创建一个辅助指针，帮助小孩出圈
		Boy helper = first;//最开始指向最后一个节点
			while(true) {
				if(helper.getNext() == first) {//helper已找到最后节点
					break;
				}
				helper = helper.getNext();
			}
			//小孩报数前，先让first和helper移动k-1次
			for(int j = 0; j < startNo - 1; j++) {
				first = first.getNext();
				helper = helper.getNext();
			}
			//当小孩报数时，让first和helper同时移动 countNum- 1次，然后出圈，一个循环操作，直到圈中只有一个人
			while(true) {
				if(first == helper) {//说明只有一个人
					break;
				}
				//让first和helper同时移动countNum-1次，然后出圈
				for(int j = 0; j < countNum - 1; j++) {
					first = first.getNext();
					helper = helper.getNext();
				}
				//这是first指向的节点，就是要出圈的小孩
				System.out.printf("小孩%d出圈\n", first.getNo());
				//这是将first指向的小孩出圈
				first = first.getNext();
				helper.setNext(first);
			}
			System.out.printf("最后留着圈中的小孩编号%d\n", first.getNo());
			
	}
}
//创建一个Boy类
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
