import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Proj1_final {
	public static void main(String[] args) {
		
		UserInterface ui = new UserInterface();
		
		JFrame window = new JFrame("Prefix Expression Calculator");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(ui);
		window.getRootPane().setDefaultButton(ui.defaultButton());
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		
	}
}

@SuppressWarnings("serial")
class UserInterface extends JPanel {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 200;
	public static final int PADDING = 10;
	
	public static final String FONT_NAME = "Calibri";
	
	private JPanel top_panel;
	
	private JLabel lbl_prompt;
	private JTextField txt_input;
	private JButton btn_go;
	
	private JPanel bottom_panel;
	
	private JLabel lbl_output;
	
	public UserInterface() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		
		top_panel = new JPanel();
		
			// components in top panel
			lbl_prompt = new JLabel("Enter a prefix expression:");
			lbl_prompt.setFont(new Font(FONT_NAME, Font.PLAIN, 20));
			top_panel.add(lbl_prompt);
			
			txt_input = new JTextField();
			txt_input.setPreferredSize(new Dimension(200, 30));
			txt_input.setFont(new Font(FONT_NAME, Font.PLAIN, 20));
			txt_input.setMargin(new Insets(0, 6, 0, 6));
			top_panel.add(txt_input);
			
			btn_go = new JButton("Go");
			btn_go.setFont(new Font(FONT_NAME, Font.BOLD, 20));
			btn_go.setPreferredSize(new Dimension(80, 30));
			top_panel.add(btn_go);
			
		bottom_panel = new JPanel();
		
			// components in bottom panel
			lbl_output = new JLabel(" ");
			lbl_output.setFont(new Font(FONT_NAME, Font.PLAIN, 28));
			bottom_panel.add(lbl_output);
		
		// add panels to the user interface
		add(top_panel);
		add(bottom_panel);
		
		// Gets prefix expression from text field and returns the answer if valid prefix expression.  Otherwise, returns an error message.
		btn_go.addActionListener(e -> {
			
			PrefixExpression expr = new PrefixExpression(txt_input.getText());
			lbl_output.setText(
					expr.valid() ? "The answer is " + expr.compute() + "." : expr.getErrorMessage()
				);
			
		});
	}
	
	public JButton defaultButton() {
		return btn_go;
	}
}

class PrefixExpression {
	
	private final String valid_operators = "*+";
	
	private String input;
	private boolean valid;
	private String error;
	
	private Stack<String> operators = new Stack<>();
	private Stack<Integer> operands = new Stack<>();

	public PrefixExpression() { }
	
	public PrefixExpression(String input) {
		this.input = input.trim();
		valid = isValid();
	}
	
	/**
	 * Tests if the input is a valid prefix expression.  If the input is not a valid prefix expression, String error will be updated.
	 * 
	 * @return True if valid.  Otherwise, false.
	 */
	private boolean isValid(){
		
		if(input.isEmpty()) {	// input cannot be empty
			error = "Input cannot be empty.";
			return false;
		}
		
		String[] input_arr = input.split("\\s+");
		
		int operator_count = 0;
		int operand_count = 0;
		
		for(String s : input_arr) {
			if(s.matches("\\d*\\.{1}\\d*") ||				// test for decimal values
					s.matches("\\-{1}\\d*\\.?\\d*")) {		// test for negative values
				error = "Elements must be positive integers."; 
				return false;
			}
			
			if(s.matches("[" + valid_operators + "]\\d+")) {
				error = "Elements must be separated by a space.";
				return false;
			}
			
			if(!isPositiveInteger(s) && !isValidOperator(s)) {	// invalid character
				error = s + " not recognized.";
				return false;
			}
			
			if(isPositiveInteger(s)) operand_count++;
			if(isValidOperator(s)) operator_count++;
		}
		
		if(input_arr.length > 1 && isPositiveInteger(input_arr[0]) ||	// false if input begins with a number and contains more than 1 element
				!isPositiveInteger(input_arr[input_arr.length-1]) ||		// false if last element is not a number
				operator_count != operand_count - 1) {		// input must have exactly 1 more integer values than operators
			
			error = "Invalid prefix expression.";
			return false;
			
		}
		
		error = "";
		return true;
	}
	
	public String getErrorMessage() {
		return error;
	}
	
	/**
	 * Returns the result of a given prefix expression.
	 * 
	 * @return The string representation of the result of a prefix expression
	 * @throws NumberFormatException
	 */
	public String compute() throws NumberFormatException {
		if(!valid) return null;
		
		int consecutive_operands = 0;
		for(String token : input.split("\\s+")) {
			
			if(isPositiveInteger(token)) {
				
				consecutive_operands++;
				operands.push(Integer.parseInt(token));
				
				if(consecutive_operands >= 2) {
					operands.push(calculate(operators.pop(), operands.pop(), operands.pop()));
				}
			} else if(isValidOperator(token)) {
				
				consecutive_operands = 0;
				operators.push(token);
				
			}
			
		}
		
		while(!operators.isEmpty() && operands.size() > 1) {
			operands.push(calculate(operators.pop(), operands.pop(), operands.pop()));
		}
		
		return String.valueOf(operands.pop());
	}
	
	/**
	 * Calculates the result given an operator and two integers.
	 * 
	 * @param operator
	 * @param n2
	 * @param n1
	 * @return The calculated result.
	 */
	private int calculate(String operator, int n2, int n1) {
		int result = 0;
		
		if(operator.equals("+")) result = n1 + n2;
		if(operator.equals("*")) result = n1 * n2;
		
		return result;
	}
	
	public void set(String input) {
		this.input = input;
		valid = isValid();
	}
	
	public boolean valid() {
		return valid;
	}
	
	/**
	 * Tests whether the given string is a number.
	 * 
	 * @param s
	 * @return True if string is a number.  Otherwise, false.
	 */
	private boolean isPositiveInteger(String s) {
		return s.matches("\\d+");
	}
	
	/**
	 * Tests whether the given string is a valid operator.
	 * 
	 * @param s
	 * @return True if string is a valid operator.  Otherwise, false.
	 */
	private boolean isValidOperator(String s) {
		return s.matches("[" + valid_operators + "]");
	}
}
