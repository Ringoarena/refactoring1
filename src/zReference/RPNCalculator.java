package zReference;

public class RPNCalculator {


	public static void main(String[] args) {
		SimpleWindow gui = new SimpleWindow("Calculator");
		DoubleStack stack = new DoubleStack();

		while (true){
			if (stack.depth()==0) {
				gui.clear();
				gui.addString("[empty]\n");
				gui.addString("Commands: q=quit c=clear + - * / number");
			} else {
				gui.clear();
				gui.addString(stack.toString());
			}
			String input = gui.getString().trim();
			if (input.equals("")) {
				input = " ";
			}
			char command = input.charAt(0);
			if (Character.isDigit(command)){
				double value = Double.parseDouble(input);
				stack.push(value);
			} else if(command == '+') {
				double v1 = stack.pop();
				double v2 = stack.pop();
				stack.push(v1+v2);
			} else if(command == '-') {
				double v1 = stack.pop();
				double v2 = stack.pop();
				stack.push(v2-v1);
			} else if(command == '*') {
				double v1 = stack.pop();
				double v2 = stack.pop();
				stack.push(v1*v2);
			} else if(command == '/') {
				double v1 = stack.pop();
				double v2 = stack.pop();
				stack.push(v2/v1);	
			} else if(command == 'c') {
				stack.clear();
				gui.clear();
			} else if(command == 'q') {
				stack.clear();
				gui.exit();
			}else {
				gui.addString("Illegal command\n");
			}		
		}
	}
}
