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
public class SimpleCalculator extends Application{
   private double result = 0, result1 = 0;
   @Override
   public void start(Stage stage){
   
      TextField num1 = new TextField();
      TextField num2 = new TextField();
   
      num1.setMaxWidth(150);
      num1.setFont(Font.font("Arial", 20));
      
      num2.setMaxWidth(150);
      num2.setFont(Font.font("Arial", 20));
      // answer field ( can't be edit )
      Label answerField = new Label("Result: ");
      answerField.setMaxWidth(250);
      answerField.setFont(Font.font("Arial", 20));
   
      
      Button addButton = new Button("+");
      Button subButton = new Button("-");
      Button mulButton = new Button("*");
      Button divButton = new Button("/");
      Button clrButton = new Button("clear");
      
      addButton.setPrefSize(50, 50); 
      subButton.setPrefSize(50, 50);
      mulButton.setPrefSize(50, 50);
      divButton.setPrefSize(50, 50);
      clrButton.setPrefSize(100, 50);
   
      Text resultText = new Text("");
      resultText.setFont(Font.font("Arial", 20));
      addButton.setOnAction( 
         e -> {
            result = Double.parseDouble(num1.getText())+Double.parseDouble(num2.getText());
            answerField.setText("Result: "+Double.toString(result));
            num1.setText(Double.toString(result));
            num2.setText(""); // clear num2 textfield
         });
      subButton.setOnAction( 
         e -> {
            result = Double.parseDouble(num1.getText())-Double.parseDouble(num2.getText());
            answerField.setText("Result: "+Double.toString(result));
            num1.setText(Double.toString(result));
            num2.setText(""); // clear num2 textfield
         });
      mulButton.setOnAction( 
         e -> {
            result = Double.parseDouble(num1.getText())*Double.parseDouble(num2.getText());
            answerField.setText("Result: "+Double.toString(result));
            num1.setText(Double.toString(result));
            num2.setText(""); // clear num2 textfield
         });
      divButton.setOnAction( 
         e -> {
            if(Double.parseDouble(num2.getText()) == 0){
               answerField.setText("Cannot divide zero");
               num2.setText("");
            }
            else{
               result = Double.parseDouble(num1.getText())/Double.parseDouble(num2.getText());
               answerField.setText("Result: "+Double.toString(result));
               num1.setText(Double.toString(result));
               num2.setText(""); // clear num2 textfield
            }
         });
      clrButton.setOnAction( 
         e -> {
            num1.setText(""); // clear num1 textfield
            num2.setText(""); // clear num2 textfield
            answerField.setText("");
         });
          
      
      VBox root = new VBox();
      root.setSpacing(10);
      root.setAlignment(Pos.CENTER);
   
      root.getChildren().addAll(num1, num2, answerField, addButton, subButton, mulButton, divButton, clrButton);
   
      Scene scene = new Scene(root, 400, 400);
      stage.setScene(scene);
      stage.setTitle("Simple Calculator by Segn Lee B. Buslon");
      stage.show();
   
   }
   
   
   public static void main(String[] args){
      launch(args);
   }
}