package com.gen;

import java.io.IOException;
import com.gen.model.Sentence;
import com.gen.view.MainViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private ObservableList<Sentence> sentenceData = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sentence Generator");
		
		initRootLayout();
		showMainView();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMainView() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainView.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();
			
			MainViewController controller = loader.getController();
			controller.setMainApp(this);
			
			rootLayout.setCenter(mainView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Sentence> getSentenceData() {
		return sentenceData;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
