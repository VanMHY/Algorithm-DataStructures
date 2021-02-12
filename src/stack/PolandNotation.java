package stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class PolandNotation {
	public static void main(String[] args) {
		//��ɽ�һ����׺���ʽת��׺���ʽ�Ĺ���
		//1+((2+3)��4)-5 =>ת��1 2 3+4 ��+ 5 -
		//��Ϊֱ�Ӷ�str,���в����������㣬����Ƚ���1+((2+3)��4)-5��=����׺�ı��ʽ��Ӧ��List
		//��"1+((2+3)��4)-5�� =>ArrayList [1,+,(,(,2,+,3,),*,4,)-,5]
		//���õ�����׺���ʽ��Ӧ��Listת��Ϊ��׺���ʽ��Ӧ��List
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("��׺���ʽ��Ӧ��List=" + 	infixExpressionList);
		List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
		System.out.println("��׺���ʽ��Ӧ��List=" + suffixExpreesionList);
		System.out.printf("expression=%d", calculate(suffixExpreesionList));
		/*
		//�ȶ���һ���沨�����ʽ
		//(3+4)��5-6->3 4 + 5 * 6 - 
		String suffixExpression = "30 4 + 5 * 6 -";
		//�Ƚ�suffixExpression�ŵ�ArrayList��
		//Ȼ��ArrayList���ݸ�һ������������ArrayList���ջ ��ɼ���
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);
		int res = calculate(list);
		System.out.println("����Ľ����=" + res);
		*/
	}
	//���������õ�����׺���ʽ��Ӧ��Listת��Ϊ��׺���ʽ��Ӧ��List
	public static List<String> parseSuffixExpreesionList(List<String> ls){
		//��������ջ
		Stack<String> s1 = new Stack<String>();//����ջ
		//��Ϊ�м���ջ��������ת�������У�û��pop���������һ���Ҫ�����������ֱ����List<String> s2
		//Stack<String> s2 = new Stack<String>();//�м���ջ
		List<String> s2 = new ArrayList<String>();//�м���Lists2ջ
		//����ls
		for(String item : ls) {
			//�����һ�������뵽s2
			if(item.matches("\\d")) {
				s2.add(item);
			}else if(item.equals("(")){
				s1.push(item);
			}else if(item.equals(")")){
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//��������s1������С����
			}else {
				//��item�����ȼ�С�ڵ���s1ջ���������
				while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}
				//����Ҫ��itmeѹ��ջ
				s1.push(item);
			}
		}
		//��s1��ʣ�����������μ��뵽s2��
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;//�����Ǵ����List�У� 	��˳��������Ƕ�Ӧ�ĺ�׺���ʽ��Ӧ��List
	}
	//����׺���ʽת����Ӧ��List
	public static List<String> toInfixExpressionList(String s){
		//����һ��List�������׺���ʽ��Ӧ������
		List<String> ls = new ArrayList<String>();
		int i = 0;//����һ��ָ�룬���ڱ�����׺���ʽ�ַ���
		String str;//�Զ�λ����ƴ��
		char c;//ÿ������һ���ַ����ͷ��뵽c
		do {
			//���c��һ�������֣���Ҫ���뵽ls
			if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
				ls.add("" + c);
				i++;//����
			}else {//�����һ��������Ҫ���Ƕ�λ��
				str = "";//�ÿ�
				while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c;//ƴ��
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;//����
	}
	//��һ���沨�����ʽ�����ν����ݺ���������뵽ArrayList��
	public static List<String> getListString(String suffixExpression){
		//��suffixExpression�ָ�
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele : split) {
			list.add(ele);
		}
		return list;
	}
	//��ɶ��沨�����ʽ������
	/*��������ɨ��,��3��4ѹ���ջ;
 	    ����+���������˵���4��3(4Ϊջ��Ԫ�أ�3Ϊ�ζ�Ԫ�أ��������3+4��ֵ����7���ٽ�7��ջ;
 	    ��5��ջ;
	    �������ǡ������,��˵���5��7�������7��5=35����35��ջ;
	    ��6��ջ;
	    �����-�����,�����35-6��ֵ,��29���ɴ˵ó����ս��
	 */
	public static int calculate(List<String> ls) {
		//����һ��ջһ������
		Stack<String> stack = new Stack<String>();
		//����ls
		for(String item : ls) {
			//ʹ��������ʽȡ����
			if(item.matches("\\d+")) {//ƥ����Ƕ�λ��
				//��ջ
				stack.push(item);
			}else {
				//pop�������������㣬����ջ
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;//��Ž��
				if(item.equals("+")) {
					res = num1 + num2;
				}else if(item.equals("-")) {
					res = num1 - num2;
				}else if(item.equals("*")) {
					res = num1 * num2;
				}else if(item.equals("/")) {
					res = num1 / num2;
				}else {
					throw new RuntimeException("���������");
				}
				//��res��ջ
				stack.push("" + res);
			}
		}
		//�������stack�еľ��ǽ��
		return Integer.parseInt(stack.pop());
	}
}
//��дһ����Operation ���Է���һ���������Ӧ�����ȼ�
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 1;
	private static int DIV = 1;
	//дһ�����������ض�Ӧ�����ȼ�
	public static int getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
		case "-":
			result = SUB;
		case "*":
			result = MUL;
		case "/":
			result = DIV;
			break;
		default:
			System.out.println("�����ڸ������");
			break;
		}
		return result;
	}
}