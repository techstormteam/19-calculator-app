/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ioptime.calculatorapp;

/**
 *
 * @author Haroon
 */
import java.io.*;

import android.util.Log;

class Stack {

	private int[] a;
	private int top, m;

	public Stack(int max) {
		m = max;
		a = new int[m];
		top = -1;
	}

	public void push(int key) {
		a[++top] = key;
	}

	public int pop() {
		return (a[top--]);
	}
}

class Evaluation {

	public int calculate(String s) {
		int n, r = 0;
		n = s.length();
		String number = "";
		Stack a = new Stack(n);
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (ch >= '0' && ch <= '9' || ch == '$') {
				if (ch == '$') {
					System.out.println("number is--" + number);
					a.push(Integer.parseInt(number));
					number = "";
				} else {
					number = number + ch;
				}

			} else {
				int x = a.pop();
				int y = a.pop();
				Log.d("character is", ch + "");
				switch (ch) {
				case '+':
					r = x + y;
					break;
				case '-':
					r = y - x;
					break;
				case '*':
					r = y * x;
					break;
				case '/':
					r = y / x;
					break;
				default:
					r = 0;
				}
				a.push(r);
			}
		}
		r = a.pop();
		return (r);
	}

	// public int calculate(String s)
	// {
	// int n,r=0;
	// n=s.length();
	// Stack a=new Stack(n);
	// for(int i=0;i<n;i++)
	// {
	// char ch=s.charAt(i);
	// if(ch>='0'&&ch<='9')
	// a.push((int)(ch-'0'));
	// else
	// {
	// int x=a.pop();
	// int y=a.pop();
	// switch(ch)
	// {
	// case '+':r=x+y;
	// break;
	// case '-':r=y-x;
	// break;
	// case '*':r=x*y;
	// break;
	// case '/':r=y/x;
	// break;
	// default:r=0;
	// }
	// a.push(r);
	// }
	// }
	// r=a.pop();
	// return(r);
	// }
}

class PostfixEvaluation {

	public PostfixEvaluation(String input) {
		Evaluation e = new Evaluation();
		System.out.println("Result:- " + e.calculate(input));
		PerformOperation po = new PerformOperation();
		po.setPostValue(e.calculate(input) + "");
	}

	public static String getString() throws IOException {
		DataInputStream inp = new DataInputStream(System.in);
		String s = inp.readLine();
		return s;
	}
}
