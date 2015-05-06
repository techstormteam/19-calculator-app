package com.ioptime.calculatorapp;

public class CurrencyFlags {
	private String currency;
	private int flag;
	private boolean tick;

	public CurrencyFlags(String currency, int flag, boolean tick) {
		this.currency = currency;
		this.flag = flag;
		this.tick = tick;

	}

	public String getCurrency() {
		return this.currency;
	}

	public int getFlag() {
		return this.flag;
	}

	public void setTick(boolean tick) {
		this.tick = tick;

	}

	public boolean getTick() {
		return this.tick;
	}
}
