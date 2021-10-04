import java.util.Stack;

/******************************************************************************
* This class is used for evaluating postfix expressions.
* 
* @version
*   April 2018
*   
* @author Ryan Andrews
******************************************************************************/

public class PostfixEvaluator {

/******************************************************************************
*Evaluates the string to see if there are any issues
*
*@param String postfix - postfix is the postfix equation to be evaluated
*
*@return - A string with the answer to the evaluated postfix equation
******************************************************************************/
	public static String evaluate(String postfix) {
		String error = "";
		if(postfix.isEmpty() || postfix == "#") {
			return "No input";
		}
		
		if(postfix != null && postfix.trim().isEmpty()){ 
			return "No input"; 
		}
		
		for(int i=0; i < postfix.length(); i++) {
			
			if(postfix.length() > 3 && i < postfix.length() - 1 && i >= 2 && Character.isDigit(postfix.charAt(i))) {
				if(postfix.charAt(i+1) == '.' && postfix.charAt(i-1) == '.') {
					error = "not all input used";
				}
			}
			
			else if(postfix.length() > 3 && i < postfix.length() - 1 && i >= 2 && postfix.charAt(i) == '.') {
					if(postfix.charAt(i+1) == '.' || postfix.charAt(i-1) == '.') {
						error = "not all input used";
					}
			}
			else if(postfix.charAt(0) == '.') {
				return "Error, invalid input";
			}
			else if(!Character.isDigit(postfix.charAt(i))) {
				if(postfix.charAt(i) != '.' && postfix.charAt(i) != '+' && postfix.charAt(i) != '-' && postfix.charAt(i) != '*' && postfix.charAt(i) != '/' && !Character.isWhitespace(postfix.charAt(i)) && postfix.charAt(i) != '(' && postfix.charAt(i) != ')') {
					error = "not all input used";
				}
				else if(postfix.charAt(i) == '(' || postfix.charAt(i) == ')') {
					return (Character.toString(postfix.charAt(i)) + "has no meaning here"); 
				}
			}
			else if(Character.isDigit(postfix.charAt(i)) && postfix.length() == 1) {
				return (Character.toString(postfix.charAt(i)));
			}
		}
		return trueEval(postfix, error);
	}
	
/******************************************************************************
*Runs more tests and calculates the answer to the postfix equation
*
*@param String post - post is the postfix equation to be evaluated
*		String error - error contains the error message determined in the evaluate method
*
*@return - A string with the answer to the evaluated postfix equation
******************************************************************************/
	
	private static String trueEval(String post, String error) {
		Stack<Double> stack = new Stack<>(); 
		Double num1, num2;
		TokenScanner fix = new TokenScanner(post);
		Token loop;
		int tokenNum = 0;
		
		while(fix.hasNextToken()) {
			tokenNum++;
			loop = fix.nextToken();
			if(loop.isNumber() && fix.hasNextToken() == true) {
				stack.push(loop.numberValue());
				}
			else if(loop.isNumber() && !fix.hasNextToken() && stack.size() > 1) {
				return "error";
			}
			else if(loop.isNumber() && !fix.hasNextToken() && stack.size() == 1) {
				stack.push(loop.numberValue());
				return ("values remain on stack");
			}
			else if(loop.isNumber() && !fix.hasNextToken() && stack.size() == 0 && tokenNum > 1) {
				stack.push(loop.numberValue());
				return Double.toString(stack.pop()) + " " + error;
			}
			else if(loop.isNumber() && !fix.hasNextToken() && stack.size() == 0 && tokenNum == 1 && error == "") {
				stack.push(loop.numberValue());
				return Double.toString(stack.pop());
			}
			else if(loop.isNumber() && !fix.hasNextToken() && stack.size() == 0 && tokenNum == 1 && error != "") {
				stack.push(loop.numberValue());
				return Double.toString(stack.pop()) + " " + error;
			}
			else if(loop.isOperator() == true && stack.size() == 0){
				return("nderflow");
				}
			else if(loop.isOperator() == true && stack.size() == 1){
				return("nderflow");
				}
			else if(loop.isOperator() == true && stack.size() > 2 && !fix.hasNextToken()){
				error = "values remain on stack";
			}	
			else if (loop.isOperator() && stack.size() > 1 && fix.hasNextToken() == false){
				num1 = stack.pop();
				num2 = stack.pop();
				
				switch(loop.operatorCharValue()) {
				
				case '+': stack.push(num2 + num1);
				break;
				
				case '-': stack.push(num2 - num1);
				break;
				
				case '/': stack.push(num2 / num1);
				break;
				
				case '*': stack.push(num2 * num1);
				break;
				
					}
				}
			
			else if(loop.isOperator() && stack.size() > 1 && fix.hasNextToken() == true) {

				num1 = stack.pop();
				num2 = stack.pop();
		
				switch(loop.operatorCharValue()) {
				
				case '+': stack.push(num2 + num1);
				break;
				
				case '-': stack.push(num2 - num1);
				break;
				
				case '/': stack.push(num2 / num1);
				break;
				
				case '*': stack.push(num2 * num1);
				break;
				
				}
					
			}
			
		}
		if(error != "") {
		return (Double.toString(stack.pop()) + " " + error);
		}
		return (Double.toString(stack.pop()));
	}
}