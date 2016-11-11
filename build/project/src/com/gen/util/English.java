package com.gen.util;
import java.io.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Scanner;

public class English {
	//��ųʸ�
	public static String[] noun = new String[100];
	public static String[] verb = new String[100];
	boolean[] isNounActive = new boolean[100];
	boolean[] isNounFood = new boolean[100];
	
	public static ArrayList<String> nounArrayList = new ArrayList<String>();
	public static ArrayList<String> verbArrayList = new ArrayList<String>();
	public static ArrayList<String> adverbArrayList = new ArrayList<String>();

	public static int nounCnt = -1;
	public static int verbCnt = -1;
	public static int adverbCnt = -1;
	
	//���� ���� ������� �̿��� ����.
	public static String[] auxverb = {"", "", "should", "can", "will"};
	public static String[] adverb = new String[100];
	public String[] adverbDefaults = {"", "rapidly", "slowly", "beautifully", "a lot", "happily", "well"};
	public static String[] connective = {"And", "But", "Also,", "However,", "Even", "So,", "By the way,", ""};

	Scanner scan = new Scanner(System.in);
	
	public void saveDataToFile() throws IOException {
		//���Ϸκ��� �д� �� : noun �迭 ��
		//���Ϸ� ����ϴ� �� : isFood �迭 ��, isActive �迭 ��
		FileInputStream dictIn = new FileInputStream("E:\\JavaSpace\\Dict_noun.txt");
		FileWriter isFoodOut = new FileWriter("E:\\JavaSpace\\Dict_isFood.txt", false);
		FileWriter isActiveOut = new FileWriter("E:\\JavaSpace\\Dict_isActive.txt", false);
		
		boolean stopFlag = false;
		isFoodOut.write("");
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = dictIn.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			System.out.println("Is '" + tmp + "' food? ");
			int t = scan.nextInt();
			if (t==1) isFoodOut.append("1");
			else isFoodOut.append("0");
			
			System.out.println("Is '" + tmp + "' active? ");
			t = scan.nextInt();
			if (t==1) isActiveOut.append("1");
			else isActiveOut.append("0");
			
			if (stopFlag) break;
		}

		System.out.println("�Է� �Ϸ�");
		dictIn.close();
		isFoodOut.close();
		isActiveOut.close();
	}
	
	public void loadDataFromFile() throws IOException {
		for (int i = 0 ; i < 7; i++) {
			adverb[i] = adverbDefaults[i];
			adverbArrayList.add(adverbDefaults[i]);
			adverbCnt++;
		}
		
		//���Ϸκ��� �д� �� : noun �迭 ��, isFood �迭, Verb �迭 ��
		FileInputStream fileInputNoun = new FileInputStream("E:\\JavaSpace\\Dict_noun.txt");
		FileInputStream fileInputIsFood = new FileInputStream("E:\\JavaSpace\\Dict_isFood.txt");
		FileInputStream fileInputVerb = new FileInputStream("E:\\JavaSpace\\Dict_verb.txt");
		
		boolean stopFlag = false;
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = fileInputNoun.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			noun[i] = tmp;	
			nounArrayList.add(tmp);
			nounCnt++;
			if (stopFlag) break;
		}
		
		for (int i = 0; i < 100; i++) {
			int c = fileInputIsFood.read();
			
			if (c != -1) {
				switch (c) {
				case '1': isNounFood[i] = true; break;
				default: isNounFood[i] = false; break;
				}
			} else break;
		}
		
		stopFlag = false;
		
		for (int i = 0; i < 100; i++) {
			String tmp = "";
			
			while (true) {
				int c = fileInputVerb.read();
				char ch = (char)c;
				if (ch != ',' && ch != '!') tmp += ch;
				else {
					if (ch == '!') stopFlag = true;
					break;
				}
			}
			
			verb[i] = tmp;
			verbArrayList.add(tmp);
			verbCnt++;
			if (stopFlag) break;
		}
		
		fileInputNoun.close();
		fileInputIsFood.close();
		fileInputVerb.close();
	}
	
	public void printAllNouns() throws IOException {
		//noun �迭�� isFood �迭 �� ���
		for (int i = 0; i < 100; i++) {
			System.out.println(noun[i] + " = " + isNounFood[i]);
		}
	}
	
	public static int randomRange(int n1, int n2) {
		//���� �� ��ȯ
	    return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public String firstLetterToUpperCase(String s) {
		//���ڿ� ù ���ڸ� �빮�ڷ�
		String newString = s.substring(0, 1).toUpperCase();
		newString += s.substring(1).toLowerCase();
		return newString;
	}
	
	public String firstLetterToLowerCase(String s) {
		//���ڿ� ù ���ڸ� �ҹ��ڷ�
		String newString = s.substring(0, 1).toLowerCase();
		newString += s.substring(1);
		return newString;
	}
	
	public String getNoun(boolean isCapital, boolean isActive, boolean isPersonFine) {
		//���
		int i = 0;
		
		if (isActive) {
			if (isPersonFine) i = randomRange(4, 8);
			else i = randomRange(4, 6);
		} else {
			if (isPersonFine) i = randomRange(0, 9);
			else i = randomRange(0, 6);
		}
		
		String newNoun = this.noun[i];
		if (isCapital) return firstLetterToUpperCase(newNoun);
		else return newNoun;
	}
	
	public String getNoun() {
		int i = randomRange(0, nounArrayList.size() - 1);
		String newNoun = nounArrayList.get(i);
		return newNoun;
	}
	
	public String getSubject() {
		//�־�
		int i = randomRange(4, nounArrayList.size() - 1);
		String newSubject = nounArrayList.get(i);
		return newSubject;
	}
	
	public String getVerb(int c) {
		int i;
		if (isNounFood[c]) i = randomRange(0, verbArrayList.size() - 1);
		else i = randomRange(0, verbArrayList.size() - 1);
		String newVerb = verbArrayList.get(i);
		return newVerb;
	}
	
	public String getVerb() {
		//����
		int i = randomRange(0, verbArrayList.size() - 1);
		String newVerb = verbArrayList.get(i);
		return newVerb;
	}
	
	public String getAuxverb() {
		//������
		return auxverb[randomRange(0, auxverb.length-1)];
	}
	
	public String getAdverb() {
		//�λ�
		int i = randomRange(0, adverbArrayList.size() - 1);
		String newAdverb = adverbArrayList.get(i);
		return newAdverb;
	}
	
	public String addArticleToNoun(String original) {
		//����
		String newString = "";

		switch (randomRange(0, 1)) {
		case 0:
			if (original.charAt(0) == 'a')
				newString += "An "; // ��������
			else
				newString += "A ";
			break;
		case 1:
			newString += "The "; // ������
			break;
		}
		
		newString += firstLetterToLowerCase(original);
		return newString;
	}
	
	public void generateSentence(boolean isQuestion, boolean isSubjectCapital) {
		//���� ����
		String newSubject = getNoun(true, true, true);
		newSubject = addArticleToNoun(newSubject);
		if (!isSubjectCapital) newSubject = firstLetterToLowerCase(newSubject);
		
		String newObject = getNoun(false, false, false);
		newObject = addArticleToNoun(newObject);
		newObject = firstLetterToLowerCase(newObject);
		
		int newObjectIndex = 0;
		
		for (int i=0; i<10; i++) {
			if (newObject.equals(noun[i])) {
				newObjectIndex = i;
				break;
			}
		}
		
		String newAuxVerb = getAuxverb();
		String newVerb = getVerb(newObjectIndex);
		String newAdverb = getAdverb();
		
		if (isQuestion) {
			if (newAuxVerb == "") System.out.print("Does ");
			else System.out.print(firstLetterToUpperCase(newAuxVerb) + " ");
			System.out.print(firstLetterToLowerCase(newSubject) + " " + newVerb + " " + newObject + "?");
		}
		else {
			if (isSubjectCapital) System.out.print(newSubject);
			else System.out.print(firstLetterToLowerCase(newSubject));
			System.out.print(" " + newAuxVerb);
			
			if (newAuxVerb.equals("")) {
				System.out.print(newVerb);
				if (newVerb.substring(newVerb.length()-2).equals("ch")) System.out.print("es ");
				else System.out.print("s ");
			} else System.out.print(" " + newVerb + " ");
			
			if (newVerb.equals("want")) {
				newObject = getNoun(false, false, false);
				newObjectIndex = 0;
				
				for (int i=0; i<10; i++) {
					if (newObject.equals(noun[i])) {
						newObjectIndex = i;
						break;
					}
				}
				
				newVerb = getVerb(newObjectIndex);
				System.out.print("to " + newVerb + " ");
			} 
			
			System.out.print(newObject);
			if (newAdverb.equals("")) System.out.print(".");
			else System.out.print(" " + newAdverb + ".");
		}
	}
	
	public String generateSentence() {
		String sentence = "";
		
		//���� ����
		String newSubject = getSubject();
		newSubject = addArticleToNoun(newSubject);
		
		String newObject = getNoun();
		newObject = addArticleToNoun(newObject);
		newObject = firstLetterToLowerCase(newObject);
		
		String newAuxVerb = getAuxverb();
		String newVerb = getVerb();
		String newAdverb = getAdverb();
		
		sentence += (newSubject + " " + newAuxVerb);
		
		if (newAuxVerb.equals("")) {
			sentence += newVerb;
			if (newVerb.substring(newVerb.length() - 2).equals("ch"))
				sentence += "es ";
			else
				sentence += "s ";
		} else {
			sentence += (" " + newVerb + " ");
		}

		if (newVerb.equals("want")) {
			newObject = getNoun();
			newVerb = getVerb();
			sentence += ("to " + newVerb + " ");
		}

		sentence += newObject;
		
		if (!newAdverb.equals("")) {
			sentence += (" " + newAdverb);
		}

		sentence += ".";
		sentence = firstLetterToUpperCase(sentence);
		System.out.println(sentence);
		return sentence;
	}

	public String generateQuestion() {
		String sentence = "";

		// ���� ����
		String newSubject = getSubject();
		newSubject = addArticleToNoun(newSubject);

		String newObject = getNoun();
		newObject = addArticleToNoun(newObject);
		newObject = firstLetterToLowerCase(newObject);

		String newAuxVerb = getAuxverb();
		String newVerb = getVerb();
		String newAdverb = getAdverb();

		if (newAuxVerb.equals("")) sentence += "Does";
		else sentence += firstLetterToUpperCase(newAuxVerb);
		
		sentence += (" " + firstLetterToLowerCase(newSubject) + " " + newVerb);
	
		if (newVerb.equals("want")) {
			newVerb = getVerb();
			sentence += (" to " + newVerb);
		}
		
		sentence += (" " + newObject);

		if (!newAdverb.equals("")) {
			sentence += (" " + newAdverb);
		}

		sentence += "?";
		sentence = firstLetterToUpperCase(sentence);
		System.out.println(sentence);
		return sentence;
	}

	public String generateSentence(String word, String treatAs) {
		String sentence = "";

		String newSubject = getSubject();
		newSubject = addArticleToNoun(newSubject);
		
		String newObject = getNoun();
		newObject = addArticleToNoun(newObject);
		newObject = firstLetterToLowerCase(newObject);
		
		String newAuxVerb = getAuxverb();
		String newVerb = getVerb();
		String newAdverb = getAdverb();
		
		switch (treatAs) {
		case "Subject":
			newSubject = word;
			break;
		case "Verb":
			newVerb = word;
			break;
		case "Object":
			newObject = word;
			break;
		case "Adverb":
			newAdverb = word;
			break;
		}
		
		//���� ����
		sentence += (newSubject + " " + newAuxVerb);
		
		if (newAuxVerb.equals("")) {
			sentence += newVerb;
			if (newVerb.substring(newVerb.length() - 2).equals("ch"))
				sentence += "es ";
			else
				sentence += "s ";
		} else {
			sentence += (" " + newVerb + " ");
		}

		if (newVerb.equals("want")) {
			newObject = getNoun();
			newVerb = getVerb();
			sentence += ("to " + newVerb + " ");
		}

		sentence += newObject;
		
		if (!newAdverb.equals("")) {
			sentence += (" " + newAdverb);
		}

		sentence += ".";
		System.out.println(sentence);
		return sentence;
	}

	public void getConnective() {
		// ���ӻ�
		System.out.println(" " + connective[randomRange(0, 7)] + " ");
	}

	public void generateConnectedSentence(){
		//���ӻ�� ����� ���� ����
		generateSentence(false, true);
		getConnective();
		generateSentence(false, false);
	}
	
	public void generateParagraph() {
		//���� ����
		for (int i=0; i<randomRange(1,5); i++) {
			if (i==0) {
				generateSentence(false, true);
				getConnective();
				generateSentence(false, false);
			} else {
				getConnective();
				generateSentence(false, false);
			}
		}
	}

	public String generateRandom() {
		String str = "";
		switch (randomRange(0,1)) {
		case 0:
			str = generateSentence();
			break;
		case 1:
			str = generateQuestion();
			break;
		}
		return str;
	}
	
}
