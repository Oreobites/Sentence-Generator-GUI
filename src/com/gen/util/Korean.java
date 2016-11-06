package com.gen.util;
import java.lang.Math;

public class Korean {
	String noun[] = {"사과", "우유", "치즈", "치킨", "강아지", "고양이", "호랑이", "선생님", "학생", "나무"};
	String verb[] = {"먹는다", "만든다", "싫어한다", "핥는다", "원한다", "때린다", "사랑한다", "지켜본다", "가르친다", "마신다"};
	
	public String getNoun(int c) {
		int i = (int)(Math.random() * 10);
		if (c == 0)	return this.noun[i];
		else {
			//첫 문자만 대문자로
			String newString = this.noun[i].substring(0, 1).toUpperCase();
			newString += this.noun[i].substring(1);
			return newString;
		}
	}
	
	public String getVerb() {
		int i = (int)(Math.random() * 10);
		return this.verb[i];
	}
	
	public boolean isBatchimExist(String a) {
		//받침 유무 판단
		char last = a.charAt(a.length()-1);
		int i = (last - 0xAC00) % 28;
		if (i > 0) return true;
		else return false;
	}
	
	public String generateSentence() {
		//주어 출력
		String sentence = "";
		
		String newnoun = getNoun(1);
		sentence += newnoun;
		if (isBatchimExist(newnoun)) sentence += "이 ";
		else sentence += "가 ";
		
		//목적어 출력
		newnoun = getNoun(1);
		sentence += newnoun;
		if (isBatchimExist(newnoun)) sentence += "을 ";
		else sentence += "를 ";
		
		//동사 출력
		sentence += (getVerb() + ".");
		 
		return sentence;
	}
}
