package com.capgemini.util;

import java.util.UUID;

public class GetUUID {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}
