package com.project.utils;

public abstract class StringUtil {
    public static String removeTag(String str) {
        return (!str.isBlank()) ? str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "") : "";
    }

    public static String subStringSpaceBar(String str) {
        return (!str.isBlank()) ? str.substring(str.indexOf(" ")) : "";
    }

    public static String dosiChange(String str) {
        String[] oldStr = {"서울특별시","부산광역시","인천광역시","대구광역시","광주광역시","경기도","강원도","충청북도","충청남도","전라북도","전라남도","경상북도","경상남도"};
        String[] newStr = {"서울","부산","인천","대구","광주","경기","강원","충북","충남","전북","전남","경북","경남"};
        for (int i = 0; i < newStr.length; i++) {
            if(str.equals(oldStr[i])) return newStr[i];
        }
        return str;
    }
}
