
public class Card {
	//set values of a card
	boolean isShown = false;
	boolean selected = false;
	String front = "";
	String back = "cardback.jpg";
	
	//create a card
	public Card(int num){
		isShown = false;
		selected = false;
		front = imageShown(num);
		
//		setOnMouseClicked(e->{
//			flip();
//		});
	}
	
	//method to show what side of the card and select/deselect it
	public String showCard(){
		if(isShown){
			selectCard();
			return front;
		}
		else{
			deselectCard();
			return back;
		}
	}
	
	//method to select card
	public void selectCard(){
		selected = true;
	}
	
	//method to deselect card
	public void deselectCard(){
		selected = false;
	}
	
	//method to return if card is selected or not
	public boolean isSelected(){
		return selected;
	}
	
	//method to flip card
	public void flip(){
		if(isShown){
			isShown = false;
		}
		else{
			isShown = true;
		}
	}
	
	//method to return what image is shown according to what value is entered
	public String imageShown(int num){
		//takes a number and returns the image file corresponding to that number
		String image = "";
		if(num == 0 || num == 10){
			image = "star.jpg";
		}
		else if (num == 1 || num == 11){
			image = "mercury.jpg";
		}
		else if (num == 2 || num == 12) {
			image = "earth.jpg";
		}
		else if (num == 3 || num == 13) {
			image = "saturn.jpg";
		}
		else if (num == 4 || num == 14) {
			image = "venus.jpg";
		}
		else if (num == 5 || num == 15) {
			image = "neptune.jpg";
		}
		else if (num == 6 || num == 16) {
			image = "uranus.jpg";
		}
		else if (num == 7 || num == 17) {
			image = "mars.jpg";
		}
		else if (num == 8 || num == 18) {
			image = "pluto.jpg";
		}
		else if (num == 9 || num == 19) {
			image = "jupiter.jpg";
		}
		return image;
	}
}
