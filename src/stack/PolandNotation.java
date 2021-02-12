package stack;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class PolandNotation {
	public static void main(String[] args) {
		//完成将一个中缀表达式转后缀表达式的功能
		//1+((2+3)×4)-5 =>转成1 2 3+4 ×+ 5 -
		//因为直接对str,进行操作，不方便，因此先将“1+((2+3)×4)-5”=》中缀的表达式对应的List
		//即"1+((2+3)×4)-5” =>ArrayList [1,+,(,(,2,+,3,),*,4,)-,5]
		//将得到的中缀表达式对应的List转换为后缀表达式对应的List
		String expression = "1+((2+3)*4)-5";
		List<String> infixExpressionList = toInfixExpressionList(expression);
		System.out.println("中缀表达式对应的List=" + 	infixExpressionList);
		List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
		System.out.println("后缀表达式对应的List=" + suffixExpreesionList);
		System.out.printf("expression=%d", calculate(suffixExpreesionList));
		/*
		//先定义一个逆波兰表达式
		//(3+4)×5-6->3 4 + 5 * 6 - 
		String suffixExpression = "30 4 + 5 * 6 -";
		//先将suffixExpression放到ArrayList中
		//然后将ArrayList传递给一个方法，遍历ArrayList配合栈 完成计算
		List<String> list = getListString(suffixExpression);
		System.out.println("rpnList=" + list);
		int res = calculate(list);
		System.out.println("计算的结果是=" + res);
		*/
	}
	//方法：将得到的中缀表达式对应的List转换为后缀表达式对应的List
	public static List<String> parseSuffixExpreesionList(List<String> ls){
		//定义两个栈
		Stack<String> s1 = new Stack<String>();//符号栈
		//因为中间结果栈，在整个转换过程中，没有pop操作，而且还需要逆序输出所有直接用List<String> s2
		//Stack<String> s2 = new Stack<String>();//中间结果栈
		List<String> s2 = new ArrayList<String>();//中间结果Lists2栈
		//遍历ls
		for(String item : ls) {
			//如果是一个数加入到s2
			if(item.matches("\\d")) {
				s2.add(item);
			}else if(item.equals("(")){
				s1.push(item);
			}else if(item.equals(")")){
				while(!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();//将（弹出s1，消除小括号
			}else {
				//当item的优先级小于等于s1栈顶运算符，
				while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
					s2.add(s1.pop());
				}
				//还需要将itme压入栈
				s1.push(item);
			}
		}
		//将s1中剩余的运算符依次加入到s2中
		while(s1.size() != 0) {
			s2.add(s1.pop());
		}
		return s2;//这里是存放在List中， 	按顺序输出就是对应的后缀表达式对应的List
	}
	//将中缀表达式转成相应的List
	public static List<String> toInfixExpressionList(String s){
		//定义一个List，存放中缀表达式对应的内容
		List<String> ls = new ArrayList<String>();
		int i = 0;//这是一个指针，用于遍历中缀表达式字符串
		String str;//对多位数的拼接
		char c;//每遍历到一个字符，就放入到c
		do {
			//如果c是一个非数字，需要加入到ls
			if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
				ls.add("" + c);
				i++;//后移
			}else {//如果是一个数，需要考虑多位数
				str = "";//置空
				while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
					str += c;//拼接
					i++;
				}
				ls.add(str);
			}
		} while (i < s.length());
		return ls;//返回
	}
	//将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
	public static List<String> getListString(String suffixExpression){
		//将suffixExpression分割
		String[] split = suffixExpression.split(" ");
		List<String> list = new ArrayList<String>();
		for(String ele : split) {
			list.add(ele);
		}
		return list;
	}
	//完成对逆波兰表达式的运算
	/*从左至右扫描,将3和4压入堆栈;
 	    遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈;
 	    将5入栈;
	    接下来是×运算符,因此弹出5和7，计算出7×5=35，将35入栈;
	    将6入栈;
	    最后是-运算符,计算出35-6的值,即29，由此得出最终结果
	 */
	public static int calculate(List<String> ls) {
		//创建一个栈一个即可
		Stack<String> stack = new Stack<String>();
		//遍历ls
		for(String item : ls) {
			//使用正则表达式取出数
			if(item.matches("\\d+")) {//匹配的是多位数
				//入栈
				stack.push(item);
			}else {
				//pop两个数，并运算，再入栈
				int num2 = Integer.parseInt(stack.pop());
				int num1 = Integer.parseInt(stack.pop());
				int res = 0;//存放结果
				if(item.equals("+")) {
					res = num1 + num2;
				}else if(item.equals("-")) {
					res = num1 - num2;
				}else if(item.equals("*")) {
					res = num1 * num2;
				}else if(item.equals("/")) {
					res = num1 / num2;
				}else {
					throw new RuntimeException("运算符有误");
				}
				//把res入栈
				stack.push("" + res);
			}
		}
		//最后留着stack中的就是结果
		return Integer.parseInt(stack.pop());
	}
}
//编写一个类Operation 可以返回一个运算符对应的优先级
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 1;
	private static int DIV = 1;
	//写一个方法，返回对应的优先级
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
			System.out.println("不存在该运算符");
			break;
		}
		return result;
	}
}