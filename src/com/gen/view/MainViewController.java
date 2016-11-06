package com.gen.view;

import java.io.IOException;

import com.gen.MainApp;
import com.gen.model.Sentence;
import com.gen.util.English;
import com.gen.util.Korean;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainViewController {
	
	private MainApp mainApp;
	
	@FXML TableView<Sentence> tableView;
	@FXML TableColumn<Sentence, Number> tableNumber;
	@FXML TableColumn<Sentence, String> tableSentence;
	
	@FXML Button randomGenerate;
	@FXML Button customGenerate;
	@FXML Button countUp;
	@FXML Button countDown;
	@FXML Button clear;
	
	@FXML TextField count;
	@FXML TextField word;
	
	final ToggleGroup language = new ToggleGroup();
	@FXML RadioButton radioButtonKorean;
	@FXML RadioButton radioButtonEnglish;
	
	@FXML Label labelCount;
	@FXML Label labelCustomGenerate;
	@FXML Label labelRandomGenerate;
	@FXML Label labelSelectLanguage;
	@FXML Label labelTitle;
	@FXML Label labelWord;
	
	boolean isEnglishSelected = true;

	English englishDict = new English();
	Korean koreanDict = new Korean();
	
	@FXML private void initialize() {
		count.setText("5");
		
		//���̺� �� ����
		tableNumber.setCellValueFactory(cellData -> cellData.getValue().index());
		tableSentence.setCellValueFactory(cellData -> cellData.getValue().sentence());
		
		//ToggleGroup ����
		radioButtonKorean.setToggleGroup(language);
		radioButtonEnglish.setToggleGroup(language);
		radioButtonEnglish.setSelected(true);
		
		//���Ϸκ��� ������ ��������
		try {
			englishDict.loadDataFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML private void handleRandomGenerate() {
		int currentCount;
		try {
			currentCount = Integer.parseInt(count.getText());
		} catch (Exception e) {
			System.out.println("count�� �߸� ������. 5�� ���� ������.");
			currentCount = 5;
			count.setText(Integer.toString(currentCount));
		}
		if (isEnglishSelected) {
			for (int i = 0; i < currentCount; i++) {
				String sentence = englishDict.generateRandom();
				Sentence obj = new Sentence(sentence);
				mainApp.getSentenceData().add(obj);
				System.out.println(i + "��° ���� ���� ���� �Ϸ�");
			}
		} else {
			for (int i = 0; i < currentCount; i++) {
				String sentence = koreanDict.generateSentence();
				Sentence obj = new Sentence(sentence);
				mainApp.getSentenceData().add(obj);
				System.out.println(i + "��° �ѱ� ���� ���� �Ϸ�");
			}
		}
	}
	
	@FXML private void handleCustomGenerate() {
		englishDict.generateSentence();
	}
	
	@FXML private void handleCountUp() {
		int currentCount = Integer.parseInt(count.getText());
		count.setText(Integer.toString(currentCount + 1));
	}
	
	@FXML private void handleCountDown() {
		int currentCount = Integer.parseInt(count.getText());
		count.setText(Integer.toString(currentCount - 1));
	}
	
	@FXML private void handleRadioButtonKorean() {
		labelTitle.setText("���� ������");
		labelSelectLanguage.setText("��� ����");
		labelRandomGenerate.setText("�ڵ� ����");
		labelCustomGenerate.setText("���� ����");
		labelCount.setText("���� : ");
		labelWord.setText("�ܾ� : ");
		randomGenerate.setText("����");
		customGenerate.setText("����");
		tableSentence.setText("����");
		clear.setText("�ʱ�ȭ");
		isEnglishSelected = false;
	}
	
	@FXML private void handleRadioButtonEnglish() {
		labelTitle.setText("Sentence Generator");
		labelSelectLanguage.setText("Language Select");
		labelRandomGenerate.setText("Random Generate");
		labelCustomGenerate.setText("Custom Generate");
		labelCount.setText("Count : ");
		labelWord.setText("Word : ");
		randomGenerate.setText("Generate");
		customGenerate.setText("Generate");
		tableSentence.setText("Sentence");
		clear.setText("Clear");
		isEnglishSelected = true;
	}
	
	@FXML private void handleClear() {
		mainApp.getSentenceData().clear();
		Sentence.count = 1;
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		//Add observable list data to the table
		tableView.setItems(mainApp.getSentenceData());
	}
	
	public static int randomRange(int n1, int n2) {
	    return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}
	
	public static void main(String[] args) {
		
	}

}
