import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainMenu extends Application {
	

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		DoYouRemember doYou = new DoYouRemember();

		
		/*
		 * Creating the buttons for the menu
		 * -A Modes Button named modes that will
		 * take the user to the modes menu screen
		 * 
		 * 
		 */
		
//	//modes Button
//		Button modes = new Button("Modes");
//		modes.setStyle("-fx-background-color: #424242");
//		modes.setTextFill(Color.GOLDENROD);
//		modes.setPrefSize(200, 100);
//		modes.setOnAction(e -> {
//			try {
//				mode.start(primaryStage);
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		});
		
		//funBtn
		Button funBtn = new Button("Start Game");
		funBtn.setPrefSize(200, 100);
		funBtn.setStyle("-fx-background-color: #424242");
		funBtn.setTextFill(Color.GOLDENROD);
		funBtn.setOnAction(e->{
			try {
				doYou.start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		

		//VBox for holding the all the menu buttons
		HBox menuBox = new HBox();
		menuBox.getChildren().addAll(funBtn);
		menuBox.setAlignment(Pos.CENTER);
		menuBox.setSpacing(20);
		
		//Border pane to hold all the menu buttons as well as the back to title screen button
		BorderPane menu = new BorderPane();
		menu.setStyle("-fx-background-color: #2196F3");
		menu.setStyle("-fx-background-image: url(gameBG.jpg)");
		menu.setCenter(menuBox);

		/*
		 *-A Label name version holding the version of the app
		 *
		 *-A Scene named scene containing the menu BorderPane
		 */
	//version Label
		Label version = new Label("Version 1.0");
		menu.setBottom(version);
		BorderPane.setAlignment(version, Pos.BOTTOM_RIGHT);
	//scene Scene
		Scene scene = new Scene(menu, 750,450);
		
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Menu");
		primaryStage.show();
		
	}
}