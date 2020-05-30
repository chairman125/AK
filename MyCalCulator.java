package Calass.copy;

import java.util.Scanner;
import java.util.StringTokenizer;

public class MyCalCulator {
	public static void main(String[] args) {
		System.out.println("======= MyCalculator =======");
		
		while(true) {
			System.out.print("Infix로 수식을 입력하시오.");
			Scanner scan = new Scanner(System.in);
			System.out.print(">");
		
			String input = scan.nextLine();
			input = input.replaceAll("\\s","");
	
			System.out.print(">Postfix로 변환 : ");
		
			StringTokenizer TokenedString = new StringTokenizer(input, "+-*/()", true);
		
			LinkedList YeonSanJas = new LinkedList();
			LinkedList Expression = new LinkedList();
			LinkedList Infix = new LinkedList();
			LinkedList Postfix = new LinkedList();
			LinkedList PostExpression = new LinkedList();
			
			boolean errorcode = true;
			
			while(errorcode&&TokenedString.hasMoreTokens()) {	
				String MoreTokens = TokenedString.nextToken();
				switch(MoreTokens) {
					case "+" : { Infix.push("+"); } break;
					case "-" : { Infix.push("-"); } break;
					case "*" : { Infix.push("*"); } break;
					case "/" : { Infix.push("/"); } break;
					case "(" : { Infix.push("("); } break;
					case ")" : { Infix.push(")"); } break;
					default : {
						try {
							double temp = Double.parseDouble(MoreTokens);
							Infix.push(temp);
						}
						catch (NumberFormatException e){
							System.out.println("에 실패하였습니다. 숫자, 실수형태로만 입력해주세요..");
							errorcode = false;
							// int temp = Integer.parseInt(MoreTokens, 10);
							// Infix.push((double)temp);
						}
					} break;
				}
			}
			if(!errorcode) continue;
			insideout(Infix, Postfix);
			
			do {
				if(Postfix.peek()=="(") {
					Object second = Postfix.pop();
					YeonSanJas.push(second);
				}
				else if (Postfix.peek()!=")"&&Postfix.peek()!="+"&&Postfix.peek()!="-"&&Postfix.peek()!="*"&&Postfix.peek()!="/") {
					Object second = Postfix.pop();
					System.out.print(second + " ");
					Expression.push(second);
				}
				else if(Postfix.peek()!=")") {
					if(YeonSanJas.size()==0) {
						Object second = Postfix.pop();
						YeonSanJas.push(second);
					}
					else if(YeonSanJas.peek()=="("){
						Object second = Postfix.pop();
						YeonSanJas.push(second);
					}
					else if(weight(YeonSanJas.peek())<weight(Postfix.peek())) {
						Object second = Postfix.pop();
						YeonSanJas.push(second);
					}
					else {
						Object second = YeonSanJas.pop();
						System.out.print(second + " ");
						Expression.push(second);
					}
				}
				else {
					Postfix.pop();
					while(YeonSanJas.peek()!="("){
						Object second = YeonSanJas.pop();
						System.out.print(second + " ");
						Expression.push(second);
					}
					YeonSanJas.pop();
				}
			} while(Postfix.size()>0);
			
			while(YeonSanJas.size()>0) {
				Object second = YeonSanJas.pop();
				System.out.print(second + " ");
				Expression.push(second);
			}

			insideout(Expression, PostExpression);
			
			
			System.out.print ("\n계산을 시작할까요? (Y/N)\n>");
			String YesorNo = scan.nextLine();
			
			if(YesorNo.equals("Y")) {
				
				while(Infix.size()>0) {
					}

				double result = Calculation(PostExpression);
				System.out.print (">계산 값 : ");
				System.out.print(result);
				System.out.print ("\n계속하시겠습니까? (Y/N)\n>");
				YesorNo = scan.nextLine();
				
				if(YesorNo.equals("Y")) {
					continue;
				}
				else if ((YesorNo.equals("N"))) {
					System.out.println("\n사용해주셔서 감사합니다.\n프로그램을 종료합니다.\n\n=============================");
					System.exit(0);
				}
				else
					System.out.println("잘못된 입력입니다. 초기화면으로 돌아갑니다.");
				}
			else if (YesorNo.equals("N")) {
				System.out.print ("\n계속하시겠습니까? (Y/N)\n>");
				YesorNo = scan.nextLine();
				if(YesorNo.equals("Y"))
					continue;
				else if ((YesorNo.equals("N"))) {
					System.out.println("\n사용해주셔서 감사합니다.\n프로그램을 종료합니다.\n\n=============================");
					System.exit(0);
				}
				else
					System.out.println("잘못된 입력입니다. 초기화면으로 돌아갑니다.");
			}
			else
				System.out.println("잘못된 입력입니다. 초기화면으로 돌아갑니다.");
			scan.close();
		}	
	}

	
	static void insideout(LinkedList Infix, LinkedList Postfix) {
		while(Infix.size()>0) {
			Postfix.push(Infix.pop());
		}
	}

	static int weight(Object operator) {
		if(operator =="+"||operator == "-") return 1;
		else if (operator == "*"||operator == "/") return 2;
		else if (operator == "("||operator == ")") return 3;
		return 0;
	}
	
	static double Calculation(LinkedList PostExpression) {
		LinkedList stack = new LinkedList();
		while(PostExpression.size()>0) {
			if(PostExpression.peek()!="+"&&PostExpression.peek()!="-"&&PostExpression.peek()!="*"&&PostExpression.peek()!="/") {
				stack.push(PostExpression.pop());
			}
			else {
				Object operator = PostExpression.pop();
				if(operator == "+") {
					double b = (double)stack.pop();
					double a = (double)stack.pop();
					stack.push(a + b);
				}
				else if(operator == "-") {
					double b = (double)stack.pop();
					double a = (double)stack.pop();
					stack.push(a - b);
				}
				else if(operator == "*") {
					double b = (double)stack.pop();
					double a = (double)stack.pop();
					stack.push(a * b);
				}
				else {
					double b = (double)stack.pop();
					double a = (double)stack.pop();
					stack.push(a / b);
				}
			}	
		}
		return (double)stack.pop();
	}
}
