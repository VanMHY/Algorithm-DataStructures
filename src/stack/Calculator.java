package stack;

public class Calculator {

	public static void main(String[] args) {
		//��ɱ��ʽ������
		String expression = "70+2*6-2";
		//��������ջ����ջ�ͷ���ջ
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		//������Ҫ����ر���
		int index = 0;//����ɨ��
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch =' ';//��ÿ��ɨ��õ���char���浽ch
		String keepNum = "";//����ƴ�Ӷ�λ��
		//��ʼwhileѭ����ɨ��expression
		while(true) {
			//���εõ�expression��ÿ���ַ�
			ch = expression.substring(index,index + 1).charAt(0);
			//�ж�ch��������
			if(operStack.isOper(ch)) {//����������
				//�жϵ�ǰջ�Ƿ�Ϊ��
				if(!operStack.isEmpty()) {
					//�������ջ�в��������ͽ��бȽ�,�����ǰ�Ĳ����������ȼ�С�ڻ��ߵ���ջ�еĲ�����,����Ҫ����ջ��pop��������,
					//�ڴӷ���ջ��pop��һ�����ţ��������㣬���õ����������ջ��Ȼ�󽫵�ǰ�Ĳ����������ջ
					if(operStack.priority(ch) <= operStack.priority(operStack.peek())) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						//�������������ջ
						numStack.push(res);
						//�ѷ��������ջ
						operStack.push(ch);
					}else {//�����ǰ�Ĳ����������ȼ�����ջ�еĲ���������ֱ�������ջ
						operStack.push(ch);
					}
				}else {
					//���Ϊ��ֱ�������ջ
					operStack.push(ch);
				}
			}else {//�����������ֱ������ջ
				//�������λ��ʱ�����ܷ�����һ������������ջ,��Ϊ�������Ƕ�λ��
				//�ڴ���������Ҫ��expression�ı��ʽ��index ���ٿ�һλ,��������ͽ���ɨ�裬
				//����Ƿ��Ų���ջ�����Ҫ����һ�������ַ���,����ƴ��
				//numStack.push(ch - 48);
				//�����λ��
				keepNum += ch;
				//���ch�Ѿ������һλ����ֱ����ջ
				if(index == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
				}else {
					
					//�ж���һ���ַ����Ƿ�Ϊ���֣���������֣�����ɨ�裬����������������ջ
					//ֻ�ǿ���һλ�������Һ���
					if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
						//�����һλ�������������ջkeepNum = ��1�� ����"123"
						numStack.push(Integer.parseInt(keepNum));
						//*******keepNum���******
						keepNum = "";
					}
				}
			}
			//��index + 1�����ж��Ƿ�ɨ�赽expression���
			index++;
			if(index >= expression.length()) {
				break;
			}
		}
		//�����ʽɨ����ϣ���˳��Ĵ���ջ�ͷ���¥��pop����Ӧ�����ͷ��ţ������С�
		while(true) {
			//�������ջΪ�գ�����㵽���������ջ��ֻ��һ�����֡������
			if(operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);//��ջ
		}
		//����ջ��������pop���������ǽ��
		//System.out.printf("���ʽ%s=%d",expression,numStack.pop());
		int res2 = numStack.pop();
		System.out.printf("���ʽ%s=%d",expression,res2);
	}

}
//�ȴ���һ��ջ,��Ҫ��չһ�¹���
class ArrayStack2{
	private int maxSize;//ջ�Ĵ�С
	private int[] stack;//���飬����ģ��ջ�����ݾͷ��ڸ�������
	private int top = -1;//top��ʾջ������ʼ��Ϊ-1
	//������
	public  ArrayStack2(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	//����һ�����������Է��ص�ǰջ����ֵ��������������pop
	public int peek() {
		return stack[top];
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
	//������������������ȼ������ȼ��ǿ���ȷ���ģ�ʹ�����ֱ�ʾ��Խ�����ȼ�Խ��
	public int priority(int oper) {
		if(oper == '*' || oper == '/') {
			return 1;
		}else if(oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;//����ֻ����+��-,*,/
		}
	}
	//�ж��ǲ���һ�������
	public boolean isOper(char val) {
		return val == '+' ||val == '-' || val == '*' ||val == '/';
	}
	//���㷽��
	public int cal(int num1, int num2,int oper) {
		int res = 0;//���ڴ�ż�����
		switch (oper) {
		case '+':
			res = num2 + num1;
			break;
		case '-':
			res = num2 - num1;//ע��˳��
			break;
		case '*':
			res = num2 * num1;
			break;
		case '/':
			res = num2 / num1;//ע��˳��
			break;

		default:
			break;
		}
		return res;
	}
}

