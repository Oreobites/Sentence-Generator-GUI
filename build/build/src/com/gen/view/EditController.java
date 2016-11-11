package com.gen.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.gen.util.English;
import java.util.ArrayList;
import java.util.Arrays;

public class EditController {

	@FXML Button ok;
	@FXML ChoiceBox<String> treatAs = new ChoiceBox<String>();
	ObservableList<String> treatAsData = FXCollections.observableArrayList("Noun", "Verb", "Adverb");
	
	@FXML TextField newWord;
	@FXML ListView<String> nounListView;
	@FXML ListView<String> verbListView;
	@FXML ListView<String> adverbListView;
	
	ObservableList<String> nounArrayList = FXCollections.observableArrayList();
	ObservableList<String> verbArrayList = FXCollections.observableArrayList();
	ObservableList<String> adverbArrayList = FXCollections.observableArrayList();
	
	@FXML private void initialize() {
		treatAs.setItems(treatAsData);
		
		updateList();
		adverbArrayList.set(0, "(no adverb)");

		nounListView.setItems(nounArrayList);
		verbListView.setItems(verbArrayList);
		adverbListView.setItems(adverbArrayList);
	}
	
	@FXML private void updateList() {
		nounArrayList.setAll(English.nounArrayList);
		verbArrayList.setAll(English.verbArrayList);
		adverbArrayList.setAll(English.adverbArrayList);
		System.out.println("단어 리스트 업데이트 완료");
	}
	
	@FXML private void handleOk() {
		String word = newWord.getText();
		String treat = treatAs.getSelectionModel().getSelectedItem();
		switch (treat) {
		case "Noun":
			English.nounArrayList.add(word);
			break;
		case "Verb":
			English.verbArrayList.add(word);
			break;
		case "Adverb":
			English.adverbArrayList.add(word);
			break;
		}
		
		updateList();
	}
}
