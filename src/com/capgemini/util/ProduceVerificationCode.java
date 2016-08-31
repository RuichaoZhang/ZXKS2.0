package com.capgemini.util;

import java.util.Random;

/**
 * 生成验证码的工具类
 * @author chao538
 *
 */
public class ProduceVerificationCode {
	
	/**
	 * 生成验证码
	 * @return
	 */
	public static String getVerificationCode(){
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		int n = 10;
		int num = 0;
		boolean[] b = new boolean[n];
		for (int i = 0; i < 4; i++) {
			do{
				num = random.nextInt(n);
			}while(b[num]);
			b[num] = true;
			buffer.append(num);
		}
		System.out.println(buffer);
		return buffer.toString();
	}
}
