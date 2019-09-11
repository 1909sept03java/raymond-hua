package com.revature.junit;

public class Methods {
	
	public int add(int... nums) {
		int sum = 0;
		for(int n : nums) {
			sum +=n;
		}
		return sum;
	}
	
	public boolean anagram(String str1, String str2) {
		return false;
	}
}
