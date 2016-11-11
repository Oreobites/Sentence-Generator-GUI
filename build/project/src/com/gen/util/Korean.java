package com.gen.util;
import java.lang.Math;

public class Korean {
	String noun[] = {"���", "����", "ġ��", "ġŲ", "������", "�����", "ȣ����", "������", "�л�", "����"};
	String verb[] = {"�Դ´�", "�����", "�Ⱦ��Ѵ�", "�Ӵ´�", "���Ѵ�", "������", "����Ѵ�", "���Ѻ���", "����ģ��", "���Ŵ�"};
	
	public String getNoun(int c) {
		int i = (int)(Math.random() * 10);
		if (c == 0)	return this.noun[i];
		else {
			//ù ���ڸ� �빮�ڷ�
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
		//��ħ ���� �Ǵ�
		char last = a.charAt(a.length()-1);
		int i = (last - 0xAC00) % 28;
		if (i > 0) return true;
		else return false;
	}
	
	public String generateSentence() {
		//�־� ���
		String sentence = "";
		
		String newnoun = getNoun(1);
		sentence += newnoun;
		if (isBatchimExist(newnoun)) sentence += "�� ";
		else sentence += "�� ";
		
		//������ ���
		newnoun = getNoun(1);
		sentence += newnoun;
		if (isBatchimExist(newnoun)) sentence += "�� ";
		else sentence += "�� ";
		
		//���� ���
		sentence += (getVerb() + ".");
		 
		return sentence;
	}
}
