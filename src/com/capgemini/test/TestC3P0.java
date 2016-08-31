package com.capgemini.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestC3P0 {

	public static void main(String[] args) {
		//得到随机不重复的数
		Random random = new Random();
		int n = 10;
		List<Integer> list = new ArrayList<Integer>();
		int num = 0;
		boolean[] b = new boolean[n];
		for (int i = 0; i < 4; i++) {
			do{
				num = random.nextInt(n);
			}while(b[num]);
			b[num] = true;
			list.add(num);
		}
		for (Integer integer : list) {
			System.out.print(integer);
		}
	}
}
