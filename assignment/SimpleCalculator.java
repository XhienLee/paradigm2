import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import java.util.*;
public class NumberGuessing extends Application{
   private int randomNumber, heart, attempt;
   @Override
   public void start(Stage stage){
      Random rand = new Random(); 
      int max = 100; // default max, 0 to 99
      randomNumber = rand.nextInt(max);
      heart = 5; // default heart
      attempt = 0;
      // user inputed number (guess)
      TextField userInputNumber = new TextField();
      userInputNumber.setMaxWidth(250);
       
      
      // submit button (verify guess)
      Button submit = new Button("Submit");
      
      Text attemptText = new Text(20, 20, "Attempt: "+attempt);
      attemptText.setFont(Font.font("Arial", 20));
   
      // message for correct quess, wrong, less than, greater than, new number generated.
      Text msg = new Text(10, 20, "Guess the number");
      msg.setFont(Font.font("Arial", 15));
      
      // message fore heart/left
      Text msgHeart = new Text(10, 20, "You have "+heart+" heart/s left");
      msgHeart.setFont(Font.font("Arial", 20));
      
      // show the max number of rand (max = 100 ) 0 to 100
      Text text = new Text(10, 20, "Max random number : "+max); 
      text.setFont(Font.font("Arial", 15));
      
      // if the submit button is click, it will execute this line of code
      
      submit.setOnAction( 
         e -> {
            if(heart > 0){
               int guess = Integer.parseInt(userInputNumber.getText());
               if(guess > randomNumber){
                  msg.setText("Number to high");
                  msg.setFill(Color.RED);
                  heart -= 1;
                  msgHeart.setText("You have " + heart + " heart/s left");
                  attempt++;
                  
               }
               else if(guess < randomNumber){
                  msg.setText("Number to low");
                  msg.setFill(Color.RED);
                  heart -= 1;
                  msgHeart.setText("You have " + heart + " heart/s left");
                  attempt++;
               }
               else{
                  msg.setText("Congratulation for guessing the correct number");
                  msg.setFill(Color.GREEN);
                  randomNumber = rand.nextInt(max);
                  heart = 5;
                  msgHeart.setText("New number generated\nYou have " + heart + " heart/s left");
                  attempt++;
               }
            }
            else{
               randomNumber = rand.nextInt(max);
               heart = 5;
               msgHeart.setText("GAME OVER!!!\nNew Random number is generated\nHeart Left "+heart);
            } 
            attemptText.setText("Attempt: "+attempt);
         });
     
      
      VBox root = new VBox();
      root.setSpacing(10);
      root.setAlignment(Pos.CENTER);
   
      root.getChildren().addAll(attemptText, msgHeart, text, userInputNumber, submit, msg);
   
      Scene scene = new Scene(root, 400, 400);
      stage.setScene(scene);
      stage.setTitle("Number Guesing by Segn Lee B. Buslon");
      stage.show();
   }
   
   
   public static void main(String[] args){
      launch(args);
   }
  
}
