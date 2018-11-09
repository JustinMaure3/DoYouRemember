import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TitleScreen extends Application{

	public static void main(String[] args) {
		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		File song = new File("Music/bgMusic.mp3");
		Media media = new Media(song.toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setCycleCount(Timeline.INDEFINITE);
		player.play();


		MainMenu menu = new MainMenu();
		
		/**
		 * Title "Do You Remember?"
		 */
		
		Text text1 = new Text("Do You Remember?");
		text1.setFont(Font.font("Times New Roman", FontWeight.BOLD,
				FontPosture.REGULAR, 70));
		text1.setStyle("-fx-fill: #BDBDBD");
		text1.setStrokeWidth(5);
		text1.setStroke(Color.BLACK);
		
		HBox box = new HBox();
		box.getChildren().addAll(text1);
		box.setAlignment(Pos.CENTER);
		
		/*
		 * titleScreen BorderPane
		 * use mouseClicked event to change scenes when user clicks
		 * the screen
		 */
		
		BorderPane titlePane = new BorderPane();
		titlePane.setStyle("-fx-background-color: #0277BD");
		titlePane.setOnMouseClicked(e -> {
			try {
				menu.start(primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		titlePane.setCenter(box);
		titlePane.setStyle("-fx-background-image: url(gameBG.jpg)");
		
		FadeTransition fade = new FadeTransition(Duration.millis(500),box);
		fade.setFromValue(1);
		fade.setToValue(.6);
		fade.setCycleCount(Timeline.INDEFINITE);
		fade.setAutoReverse(true);
		fade.play();
	
	//version Label
//		Label version = new Label("Version 1.0");
//		titlePane.setBottom(version);
//		BorderPane.setAlignment(version, Pos.BOTTOM_RIGHT);
		
		Scene scene = new Scene(titlePane, 750,450);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Do you remember?");
		primaryStage.show();
		
	}
}