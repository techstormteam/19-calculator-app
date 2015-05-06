/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioptime.calculatorapp;

import java.util.Stack;

import android.util.Log;

/**
 * 
 * @author Haroon
 */
public class PostfixEval extends Stack<Character> {

	/**
	 * @return postfixString value
	 */
	// public PostfixEval(int x) {
	//
	// super();
	// }
	public void InToPost(String infixString) {
		Log.d("infix string ", infixString);

		String postfixString = " ";
		String number = "";
		for (int index = 0; index < infixString.length(); ++index) {
			char chValue = infixString.charAt(index);
			if (chValue >= '0' && chValue <= '9') {
				number = number + chValue;
			}
			if (chValue == '(') {
				push('(');

			} else if (chValue == ')') {
				Character oper = peek();
				while (!(oper.equals('(')) && !(isEmpty())) {
					postfixString += oper.charValue();
					pop();
					oper = peek();
				}
				pop();
			} else if (chValue == '+' || chValue == '-' || chValue == '*'
					|| chValue == '/') {
				// Stack is empty
				if (isEmpty()) {
					push(chValue);
					// current Stack is not empty
				} else {
					Character oper = peek();
					while (!(isEmpty() || oper.equals(new Character('(')) || oper
							.equals(new Character(')')))) {
						pop();
						postfixString += oper.charValue();
					}
					push(chValue);
				}
			} else if (chValue == '*' || chValue == '/') {
				if (isEmpty()) {
					push(chValue);

				} else {
					Character oper = peek();
					while (!oper.equals(new Character('+'))
							&& !oper.equals(new Character('-')) && !isEmpty()) {
						pop();
						postfixString += oper.charValue();
					}
					push(chValue);
				}
			} else {
				postfixString += chValue;
			}
		}
		while (!isEmpty()) {
			Character oper = peek();
			if (!oper.equals(new Character('('))) {
				pop();
				postfixString += oper.charValue();
			}
		}
		Log.d("postfix string", postfixString);
		postfixString = postfixString.replaceAll("\\s+", "");
		PostfixEvaluation p = new PostfixEvaluation(postfixString);

	}

	public void InfixToPostfixConvert(String infixBuffer)

	{

		int priority = 0;

		String postfixBuffer = "";

		Stack<Character> s1 = new Stack<Character>();

		for (int i = 0; i < infixBuffer.length(); i++)

		{

			char ch = infixBuffer.charAt(i);

			if (ch == '+' || ch == '-' || ch == '*' || ch == '/')

			{

				// check the precedence

				if (s1.size() <= 0)

					s1.push(ch);

				else

				{

					Character chTop = (Character) s1.peek();

					if (chTop == '*' || chTop == '/')

						priority = 1;

					else

						priority = 0;

					if (priority == 1)

					{

						if (ch == '+' || ch == '-')

						{

							postfixBuffer += s1.pop();

							i--;

						}

						else

						{ // Same

							postfixBuffer += s1.pop();

							i--;

						}

					}

					else

					{

						if (ch == '+' || ch == '-')

						{

							postfixBuffer += s1.pop();

							s1.push(ch);

						}

						else

							s1.push(ch);

					}

				}

			}

			else

			{

				postfixBuffer += ch;

			}

		}

		int len = s1.size();

		for (int j = 0; j < len; j++)

			postfixBuffer += s1.pop();

		// return postfixBuffer;

		postfixBuffer = postfixBuffer.replaceAll("\\s+", "");
		postfixBuffer = postfixBuffer.replace("(", "");
		postfixBuffer = postfixBuffer.replace(")", "");
		Log.d("postfix string", postfixBuffer);
		PostfixEvaluation p = new PostfixEvaluation(postfixBuffer);

	}

	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
			return true;
		return false;
	}

	/**
	 * Checks if c2 has same or higher precedence than c1
	 * 
	 * @param c1
	 *            first operator
	 * @param c2
	 *            second operator
	 * @return true if c2 has same or higher precedence
	 */
	private boolean checkPrecedence(char c1, char c2) {
		if ((c2 == '+' || c2 == '-') && (c1 == '+' || c1 == '-'))
			return true;
		else if ((c2 == '*' || c2 == '/')
				&& (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
			return true;
		else if ((c2 == '^')
				&& (c1 == '+' || c1 == '-' || c1 == '*' || c1 == '/'))
			return true;
		else
			return false;
	}

	/**
	 * Converts infix expression to postfix
	 * 
	 * @param infix
	 *            infix expression to be converted
	 * @return postfix expression
	 */
	public void convert(String infix) {
		System.out.printf("%-8s%-10s%-15s\n", "Input", "Stack", "Postfix");
		String postfix = ""; // equivalent postfix is empty initially
		Stack<Character> s = new Stack<Character>(); // stack to hold symbols
		s.push('#'); // symbol to denote end of stack

		System.out
				.printf("%-8s%-10s%-15s\n", "", format(s.toString()), postfix);

		for (int i = 0; i < infix.length(); i++) {
			char inputSymbol = infix.charAt(i); // symbol to be processed
			if (isOperator(inputSymbol)) { // if a operator
				// repeatedly pops if stack top has same or higher precedence
				while (checkPrecedence(inputSymbol, s.peek()))
					postfix += s.pop();
				s.push(inputSymbol);
			} else if (inputSymbol == '(')
				s.push(inputSymbol); // push if left parenthesis
			else if (inputSymbol == ')') {
				// repeatedly pops if right parenthesis until left parenthesis
				// is found
				while (s.peek() != '(')
					postfix += s.pop();
				s.pop();
			} else
				postfix += inputSymbol;
			System.out.printf("%-8s%-10s%-15s\n", "" + inputSymbol,
					format(s.toString()), postfix);
		}

		// pops all elements of stack left
		while (s.peek() != '#') {
			postfix += s.pop();
			System.out.printf("%-8s%-10s%-15s\n", "", format(s.toString()),
					postfix);

		}

		PostfixEvaluation p = new PostfixEvaluation(postfix);
	}

	/**
	 * Formats the input stack string
	 * 
	 * @param s
	 *            It is a stack converted to string
	 * @return formatted input
	 */
	private String format(String s) {
		s = s.replaceAll(",", ""); // removes all , in stack string
		s = s.replaceAll(" ", ""); // removes all spaces in stack string
		s = s.substring(1, s.length() - 1); // removes [] from stack string

		return s;
	}

}