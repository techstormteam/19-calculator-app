package com.ioptime.calculatorapp;

import java.text.DecimalFormat;

import android.util.Log;
import android.widget.TextView;

public class CalculatorBrain {
	// 3 + 6 = 9
	// 3 & 6 are called the operand.
	// The + is called the operator.
	// 9 is the result of the operation.

	private double mOperand;
	public static double mWaitingOperand;
	public static String mWaitingOperator;
	private double mCalculatorMemory;
	public String smallTextviewText;

	// operator types
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";

	public static final String CLEAR = "C";
	public static final String CLEARMEMORY = "MC";
	public static final String ADDTOMEMORY = "M+";
	public static final String SUBTRACTFROMMEMORY = "M-";
	public static final String RECALLMEMORY = "MR";
	public static final String SQUAREROOT = "√";
	public static final String SQUARED = "^";
	public static final String INVERT = "1/x";
	public static final String SINE = "sin";
	public static final String COSINE = "cos";
	public static final String TANGENT = "tan";
	public static final String MODULO = "%";

	public CalculatorBrain() {
		mOperand = 0;
		mWaitingOperand = 0;
		mWaitingOperator = "";
		mCalculatorMemory = 0;
	}

	public void setOperand(double operand) {
		mOperand = operand;
	}

	public double getResult() {
		return mOperand;
	}

	public void setMemory(double calculatorMemory) {
		mCalculatorMemory = calculatorMemory;
	}

	public double getMemory() {
		return mCalculatorMemory;
	}

	public String toString() {
		return Double.toString(mOperand);
	}

	public double performOperation(String operator) {
		if (operator.equals(CLEAR)) {
			mOperand = 0;
			mWaitingOperator = "";
			mWaitingOperand = 0;
		} else if (operator.equals(CLEARMEMORY)) {
			mCalculatorMemory = 0;
		} else if (operator.equals(ADDTOMEMORY)) {
			mCalculatorMemory = mCalculatorMemory + mOperand;
		} else if (operator.equals(SUBTRACTFROMMEMORY)) {
			mCalculatorMemory = mCalculatorMemory - mOperand;
		} else if (operator.equals(RECALLMEMORY)) {
			mOperand = mCalculatorMemory;
		} else if (operator.equals(SQUAREROOT)) {
			mOperand = Math.sqrt(mOperand);
		} else if (operator.equals(INVERT)) {
			if (mOperand != 0) {
				mOperand = 1 / mOperand;
			}
		}
		// else if (operator.equals(TOGGLESIGN)) {
		// mOperand = -mOperand;
		// }
		else if (operator.equals(SINE)) {
			mOperand = Math.sin(Math.toRadians(mOperand));
		} else if (operator.equals(COSINE)) {
			mOperand = Math.cos(Math.toRadians(mOperand));
		} else if (operator.equals(TANGENT)) {
			mOperand = Math.tan(Math.toRadians(mOperand));
		} else if (operator.equals(MODULO)) {
			mOperand = (mOperand / 100);
		} else {
			performWaitingOperation();
			mWaitingOperator = operator;
			mWaitingOperand = mOperand;
			Log.d("calcapp: waiting operand", mWaitingOperand + "");
		}
		return mOperand;
	}

	protected void performWaitingOperation() {
		if (mWaitingOperator.equals("=")) {
			mWaitingOperator = "";
		}
		if (mWaitingOperator != "") {
			Log.d("calcapp: setting small text", mWaitingOperand
					+ mWaitingOperator + mOperand);
			if (MainActivity.isFirstCalc) {
				smallTextviewText = MainActivity.df.format(mWaitingOperand)
						+ mWaitingOperator + MainActivity.df.format(mOperand);
				// MainActivity.mTextViewSmall.setText(MainActivity.df
				// .format(mWaitingOperand)
				// + mWaitingOperator
				// + MainActivity.df.format(mOperand));
				MainActivity.isFirstCalc = false;

				if (smallTextviewText.length() < 27) {
					MainActivity.mTextViewSmall.setText(smallTextviewText);
				} else {
					MainActivity.mTextViewSmall.setText(smallTextviewText
							.substring(smallTextviewText.length() - 27));
				}
			} else {
				smallTextviewText = smallTextviewText + mWaitingOperator
						+ MainActivity.df.format(mOperand);
				if (smallTextviewText.length() < 27) {
					MainActivity.mTextViewSmall.setText(smallTextviewText);
				} else {
					MainActivity.mTextViewSmall.setText(smallTextviewText
							.substring(smallTextviewText.length() - 27));
				}
				// MainActivity.mTextViewSmall.append(mWaitingOperator
				// + MainActivity.df.format(mOperand));
			}
		}
		if (mWaitingOperator.equals(ADD)) {
			mOperand = mWaitingOperand + mOperand;

		}

		else if (mWaitingOperator.equals(SUBTRACT)) {
			mOperand = mWaitingOperand - mOperand;
		}

		else if (mWaitingOperator.equals(MULTIPLY)) {
			mOperand = mWaitingOperand * mOperand;
		}

		else if (mWaitingOperator.equals(DIVIDE)) {
			if (mOperand != 0) {
				mOperand = mWaitingOperand / mOperand;
			}
		}

		// else if (mWaitingOperator.equals(MODULO)) {
		// // (obtainedScore * 100/ totalScore)
		// mOperand = (mWaitingOperand * 100 / mOperand);
		//
		// }

		else if (mWaitingOperator.equals(SQUARED)) {
			mOperand = Math.pow(mWaitingOperand, mOperand);
		}

		if (mWaitingOperator != "") {
			MainActivity.mCalculatorDisplay.setText("");
			if (MainActivity.df.format(mOperand).equals("0.0")) {
				MainActivity.mCalculatorDisplay.setText("0");
			} else {
				MainActivity.mCalculatorDisplay.setText(MainActivity.df
						.format(mOperand));
			}
		}
	}
}