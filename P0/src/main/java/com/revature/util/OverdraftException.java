package com.revature.util;

public class OverdraftException extends Exception{
	public OverdraftException() {
		System.out.println("Insufficient funds, try again next time");
	}
}
