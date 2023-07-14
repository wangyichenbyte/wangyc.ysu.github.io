package com.wang.utils;

import java.math.BigDecimal;

public class WebUtils {
	
	//����Ϊ�����࣬��װ���õĺ�����catch����ת���쳣�����ϳ���׳��
	
	
	/**
	 * StringתBigDecimal�����쳣����Ĭ��ֵ
	 * @param str  �ַ���
	 * @param defaultValue Ĭ��ֵ
	 * @return BigDecimalֵ
	 */
	public static BigDecimal bigdecimal(String str,BigDecimal defaultValue) {
		try {
			return new BigDecimal(str);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return defaultValue;
	}
	
	/**
     * �ַ���תint
     * @param string ��ת�����ַ���
     * @param defaultValue Ĭ��ֵ
     * @return int
     */
    public static int parseInt(String string,int defaultValue){
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
    

}
