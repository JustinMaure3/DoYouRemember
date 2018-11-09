import java.util.ArrayList;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Group: Hello World
 * Members: Justin, Jack and Tomas
 * Date Last Modified: April 17th 2017
 *
 */
public class DoYouRemember extends Application {
	public ArrayList<Integer> cardlist = new ArrayList<>();
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainMenu menu = new MainMenu();
		
		//Declaring Variables
		int numCards = 20;
		int numCardsH = 5;
		int numCardsV = 4;
		ArrayList<ImageView> selectedCardImgs = new ArrayList<>();
		ArrayList<Card> matchedCards = new ArrayList<>();
		ArrayList<Card> selectedCards = new ArrayList<>();
		
		
				
		//Create borderpane and add another stackpane in the center with an image and gridpane
		//(stack/gridpane(center) is where the cards will be shown)
		//(borderpane(outer) is the background with scores and buttons)
		BorderPane bpane = new BorderPane();
		StackPane spane = new StackPane();
		GridPane gpane = new GridPane();
				
		//set stackpane with gridpane and the background image
		BackgroundImage background = new BackgroundImage(new Image("space.jpg", 600, 400, false, true), 
																BackgroundRepeat.NO_REPEAT,
																BackgroundRepeat.NO_REPEAT,
																BackgroundPosition.CENTER,
																BackgroundSize.DEFAULT);
		spane.setBackground(new Background(background));
		spane.getChildren().addAll(gpane);
		spane.setAlignment(gpane, Pos.CENTER);
		gpane.setMaxHeight(400);
		gpane.setMaxWidth(600);
				
		//set outer borderpane center as the stackpane
		bpane.setCenter(spane);
		bpane.setPadding(new Insets(5));
				
				
		//BorderPane outer(Overall layout)
		bpane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
				
			//create borderpane to store top values
			BorderPane tbpane = new BorderPane();
			bpane.setTop(tbpane);
				
			//Exit button
			Button xButton = new Button(); 
			xButton.setText("Exit");
			xButton.setOnMouseClicked(e->{
				try {
					menu.start(primaryStage);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			//Winning message
			Text win = new Text("You Win");
			win.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 100));
			win.setStrokeWidth(5);
			win.setFill(Color.RED);
			
			FadeTransition fade = new FadeTransition(Duration.millis(1000), win);
			fade.setFromValue(1);
			fade.setToValue(.4);
			fade.setAutoReverse(true);
			fade.setCycleCount(Timeline.INDEFINITE);
			fade.play();
			
			StrokeTransition stroke = new StrokeTransition(Duration.millis(500), win);
			stroke.setFromValue(Color.web("#FFEA00"));
			stroke.setToValue(Color.web("#FFA000"));
			stroke.setAutoReverse(true);
			stroke.setCycleCount(Timeline.INDEFINITE);
			stroke.play();
				
			//Card Matched Score
			Text ScoreCards = new Text("Matched Cards: " + 0);
			ScoreCards.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 25));
			ScoreCards.setFill(Color.WHITESMOKE);
			ScoreCards.setTextAlignment(TextAlignment.CENTER);
			bpane.setRight(ScoreCards);
				
			//Time Limit
			Text time = new Text("Time: 00");
			time.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
			time.setFill(Color.WHITESMOKE);
			time.setTextAlignment(TextAlignment.CENTER);
				
				
			//Title
			Text name = new Text("Do You Remember?");
			name.setFill(Color.AQUAMARINE);
			name.setStroke(Color.BLACK);
			name.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40));
			name.setTextAlignment(TextAlignment.CENTER);
				
			//load borderpane(top) and set alignments
			tbpane.setLeft(xButton);
			tbpane.setCenter(name);
			tbpane.setRight(time);
				
				
				
			//GridPane(cards and main game activities)
				
				for(int i = 0; i < numCardsH; i++){
					for(int j = 0; j < numCardsV; j++){
						//create card and an image view to hold cards picture
						Card card = new Card(getNum());
						ImageView cardimg = new ImageView(card.showCard());
						//make card a on click listener
						cardimg.setOnMouseClicked(e->{
							//if the card isnt already selected (face up) flip the card and display it
							if(!card.isSelected()){
								System.out.println("Card has been selected");
								card.flip();
								cardimg.setImage(new Image(card.showCard()));
								//add to the selected arrays (one for card and one for img View)
								selectedCards.add(card);
								selectedCardImgs.add(cardimg);
								//if the selected cards array has two cards in it
								if(selectedCards.size() == 2){
									//if the two cards match
									if(Match(selectedCards.get(0), selectedCards.get(1))){
										System.out.println("Two cards Match");
										//add both cards to matched cards array
										matchedCards.add(selectedCards.get(0));
										matchedCards.add(selectedCards.get(1));
										//clear cards from selected arrays so that we can use them again
										selectedCards.clear();
										selectedCardImgs.clear();
										//update the score
										ScoreCards.setText("Matched Cards: " + (matchedCards.size()/2));
										//if all cards are matched display win message
										if(matchedCards.size() == 20){
											spane.getChildren().addAll(win);
										}
									}
									//if the two cards don't match
									else{
										System.out.println("Two cards DONT Match");	
										//flip both cards and display their back images
										selectedCards.get(0).flip();
										selectedCardImgs.get(0).setImage(new Image(selectedCards.get(0).showCard()));
										selectedCards.get(1).flip();
										selectedCardImgs.get(1).setImage(new Image(selectedCards.get(1).showCard()));
										//clear cards from selected arrays so that we can use them again
										selectedCards.clear();
										selectedCardImgs.clear();
									}
								}
							}
						});
					//set card img sizing
					cardimg.setFitHeight(90);
					cardimg.setFitWidth(65);
					//add to the gridpane with padding
					gpane.add(cardimg, i, j);
					gpane.setMargin(cardimg, new Insets(5, 10, 0 ,10));
					}
				}
				
				//Setting the stage
				Scene scene = new Scene(bpane, 750, 450);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Do You Remember?");
				primaryStage.setResizable(false);
				primaryStage.show();
				
			}
	
	
	public boolean Match(Card cardA, Card cardB){
		boolean match = false;
		if(cardA.showCard() == cardB.showCard()){
			match = true;
		}
		return match;
	}
	
	public int getNum(){
		int num = 0;
		Random machine = new Random();
		do{
			num = machine.nextInt(20);
		}while(cardlist.contains(num));
		cardlist.add(num);
		return num;
	}
		
}
	
