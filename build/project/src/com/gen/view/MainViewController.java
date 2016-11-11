package com.gen.view;

import java.io.IOException;

import com.gen.MainApp;
import com.gen.model.Sentence;
import com.gen.util.English;
import com.gen.util.Korean;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	@FXML Button edit;
	
	@FXML TextField count;
	@FXML TextField word;
	
	final ToggleGroup language = new ToggleGroup();
	@FXML RadioButton radioButtonKorean;
	@FXML RadioButton radioButtonEnglish;
	
	@FXML ChoiceBox<String> treatAs = new ChoiceBox<String>();
	ObservableList<String> treatAsData = FXCollections.observableArrayList("Subject", "Verb", "Object", "Adverb");
	
	boolean isEnglishSelected = true;
	
	English englishDict = new English();
	Korean koreanDict = new Korean();
	
	@FXML private void initialize() {
		count.setText("5");
		
		//테이블 값 설정
		tableNumber.setCellValueFactory(cellData -> cellData.getValue().index());
		tableSentence.setCellValueFactory(cellData -> cellData.getValue().sentence());
		
		//ToggleGroup 설정
		radioButtonKorean.setToggleGroup(language);
		radioButtonEnglish.setToggleGroup(language);
		radioButtonEnglish.setSelected(true);
		
		//ChoiceBox 설정
		treatAs.setItems(treatAsData);
		
		//파일로부터 데이터 가져오기
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
			System.out.println("count가 잘못 설정됨. 5로 임의 설정함.");
			currentCount = 5;
			count.setText(Integer.toString(currentCount));
		}
		if (isEnglishSelected) {
			for (int i = 0; i < currentCount; i++) {
				String sentence = englishDict.generateRandom();
				Sentence obj = new Sentence(sentence);
				mainApp.getSentenceData().add(obj);
				System.out.println(i + "번째 영어 문장 생성 완료");
			}
		} else {
			for (int i = 0; i < currentCount; i++) {
				String sentence = koreanDict.generateSentence();
				Sentence obj = new Sentence(sentence);
				mainApp.getSentenceData().add(obj);
				System.out.println(i + "번째 한글 문장 생성 완료");
			}
		}
	}
	
	@FXML private void handleCustomGenerate() {
		String userWord = word.getText(); 
		String userTreatAs = treatAs.getSelectionModel().getSelectedItem();
		
		System.out.println(userTreatAs);
		String sentence = englishDict.generateSentence(userWord, userTreatAs);
		
		Sentence obj = new Sentence(sentence);
		mainApp.getSentenceData().add(obj);
		System.out.println("수동 문장 생성 완료");
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
		isEnglishSelected = false;
	}
	
	@FXML private void handleRadioButtonEnglish() {
		isEnglishSelected = true;
	}
	
	@FXML private void handleClear() {
		mainApp.getSentenceData().clear();
		Sentence.count = 1;
	}
	
	@FXML private void handleEdit() {
		mainApp.showEdit();
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tableView.setItems(mainApp.getSentenceData());
	}
	
	public static int randomRange(int n1, int n2) {
	    return (int)(Math.random() * (n2 - n1 + 1)) + n1;
	}

	public static void main(String[] args) {
		
	}

}
