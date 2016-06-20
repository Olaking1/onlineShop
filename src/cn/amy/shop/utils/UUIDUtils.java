package cn.amy.shop.utils;

import java.util.UUID;

/*
 * 生成随机字符串的类
 */
public class UUIDUtils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
