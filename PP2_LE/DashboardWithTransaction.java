import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;
public class DashboardWithTransaction extends Application{ 
   private boolean logined = false;
   private ArrayList<javafx.scene.Node> oldScene;
   private String paymentMethod = "default";
   private GridPane root = new GridPane();
   private Login login;
   private int carSelectedRegNum = 0000;
   Runnable loadCarList = null;
   private CarList carList = new CarList();
   private TransactionList transactionList = new TransactionList(); 
   @Override
   public void start(Stage stage){
      // Main Grid (root)
      GridPane root = new GridPane();
      root.setVgap(8);
      root.setHgap(10);
      root.setPadding(new Insets(10, 10, 10, 10));
      root.setStyle("-fx-background-color: #f0f0f0;");
      root.setAlignment(Pos.CENTER);
      
      // Login Box
      VBox loginBox = new VBox(10); 
      loginBox.setPadding(new Insets(20));
      loginBox.setAlignment(Pos.CENTER);
      loginBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;");
      loginBox.setPrefSize(250, 200); 
      
      // Login
      TextField user = new TextField();
      user.setPromptText("Enter Username");
      user.setFont(Font.font("Roboto", 15));
      
      PasswordField pass = new PasswordField();
      pass.setPromptText("Enter password");
      pass.setFont(Font.font("Roboto", 15));   
      
      Button loginButton = new Button("Login");
      loginButton.setFont(Font.font("Roboto", 15));
      // design using setStyle (using css)
      loginButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;"); 
      loginBox.getChildren().addAll(user, pass, loginButton); // add all of this to the loginBox (VBox)
      root.add(loginBox, 0, 0);
    
      // Dashboards Info (details)
      // Main Menu
      Label titleLabel = new Label("Car Rental System");
      titleLabel.setFont(Font.font("Roboto", 25)); // set the font    
      GridPane.setConstraints(titleLabel, 0, 0); // postion the label within the grid
      GridPane.setHalignment(titleLabel, HPos.CENTER); // Center the label
      
      // All Car (rented  adn available)
      Button carListButton = new Button("Car List");
      carListButton.setFont(Font.font("Roboto", 15));     
      GridPane.setConstraints(carListButton, 0, 1); 
      carListButton.setPrefWidth(200); // adjust width of the button
      carListButton.setPrefHeight(50); // adjust height of the button
      carListButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
      // Avialble Car Only
      Button availableCarButton = new Button("Available Car");
      availableCarButton.setFont(Font.font("Roboto", 15));
      GridPane.setConstraints(availableCarButton, 0, 2); 
      availableCarButton.setPrefWidth(200);
      availableCarButton.setPrefHeight(50);
      availableCarButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
   
      // Rented Car Only
      Button rentedCarButton = new Button("Rented Cars");
      rentedCarButton.setFont(Font.font("Roboto", 15));
      GridPane.setConstraints(rentedCarButton, 0, 3); 
      rentedCarButton.setPrefWidth(200);
      rentedCarButton.setPrefHeight(50);
      rentedCarButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
      // Manage Car (edit, add or remove)
      Button manageCarButton = new Button("Manage Cars");
      manageCarButton.setFont(Font.font("Roboto", 15));
      GridPane.setConstraints(manageCarButton, 0, 4); 
      manageCarButton.setPrefWidth(200);
      manageCarButton.setPrefHeight(50);
      manageCarButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
      Button transactionCarButton = new Button("Transactions");
      transactionCarButton.setFont(Font.font("Roboto", 15));
      GridPane.setConstraints(transactionCarButton, 0, 4); 
      transactionCarButton.setPrefWidth(200);
      transactionCarButton.setPrefHeight(50);
      transactionCarButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
   
      
      // Logout button
      Button logout = new Button("Logout");
      logout.setFont(Font.font("Roboto", 15));
      HBox logoutBox = new HBox(logout);
      logoutBox.setAlignment(Pos.BOTTOM_LEFT);
      logout.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
      // some error have appear and the log out button can't be display in the right position (I use this method to fix it)
      GridPane.setConstraints(logout, 0, 15); 
      logout.setPrefWidth(200);
      logout.setPrefHeight(50);
      
      // Rent Button (after clicking, redirect to renter fill up)
      Button rentButton = new Button("Rent the Car");
      rentButton.setFont(Font.font("Roboto", 15));
      GridPane.setConstraints(rentButton, 0, 2); 
      GridPane.setHalignment(rentButton, HPos.CENTER); 
      rentButton.setPrefWidth(150);
      rentButton.setPrefHeight(50);
      rentButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
   
      // Back to main menu image 
      ImageView mainMenuImage = new ImageView(new Image(getClass().getResourceAsStream("/resources/menu-svgrepo-com.png"))); // get the file(.png)
      mainMenuImage.setFitWidth(20); // adjust image width
      mainMenuImage.setFitHeight(20); // adjust image height
            
     // Search functions
      TextField searchField = new TextField();
      searchField.setFont(Font.font("Roboto", 15));
      searchField.setPrefWidth(200);
      searchField.setPrefHeight(30);
   
      Button searchButton = new Button("Search");
      searchButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      searchButton.setFont(Font.font("Roboto", 13)); 
      
      ComboBox<String> filterComboBox = new ComboBox<>(); // ComboBox, used for the filter
      filterComboBox.getItems().addAll("Make", "Registration Number", "Model", "Price"); // filters that can be used
      filterComboBox.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-font-family: 'Roboto'; -fx-font-size: 13px; -fx-cell-text-fill: white; -fx-fill: white;"); 
      filterComboBox.setValue("Make"); // default filter
      filterComboBox.setPrefWidth(80); // Adjusted the width for better visibility
      
      HBox searchBox = new HBox(5); // HBox
      searchBox.setAlignment(Pos.CENTER);
      searchBox.setPadding(new Insets(10));
      searchBox.getChildren().addAll(searchField, filterComboBox, searchButton); // Add all of this to the searchBox (HBox)
      GridPane.setConstraints(searchBox, 0, 2);
      
      Button backButton = new Button("Back"); // function: back to previos scence (note: not perfect)
      GridPane.setConstraints(backButton, 0, 4);
      backButton.setPrefWidth(70);
      backButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      backButton.setFont(Font.font("Roboto", 13));
      
      Button mainMenuButton = new Button("", mainMenuImage); // back to dashboard, this is where the image is use (image Main Menu)
      GridPane.setConstraints(mainMenuButton, 0, 4);
      mainMenuButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-fill: white;"); 
      mainMenuButton.setFont(Font.font("Roboto", 15)); 
   
      GridPane allCarGrid = new GridPane(); // GridPane for all cars (rented, available)
      ScrollPane allCarPane = new ScrollPane(allCarGrid); // So that you can scroll
      allCarPane.setPrefViewportWidth(400);
      allCarPane.setPrefViewportHeight(500);
      GridPane.setConstraints(allCarPane, 0, 3);
      
      GridPane availableCarGrid = new GridPane(); // GridPane for available car
      ScrollPane availableCarPane = new ScrollPane(availableCarGrid);
      availableCarPane.setPrefViewportWidth(400);
      availableCarPane.setPrefViewportHeight(500);
      GridPane.setConstraints(availableCarPane, 0, 3);
      
      GridPane rentedCarGrid = new GridPane(); // GridPane for Rented Car
      ScrollPane rentedCarPane = new ScrollPane(rentedCarGrid);
      rentedCarPane.setPrefViewportWidth(400);
      rentedCarPane.setPrefViewportHeight(500);
      GridPane.setConstraints(rentedCarPane, 0, 3);
      
      GridPane manageCarGrid = new GridPane(); // GridPane for Rented Car
      ScrollPane manageCarPane = new ScrollPane(manageCarGrid);
      manageCarPane.setPrefViewportWidth(400);
      manageCarPane.setPrefViewportHeight(500);
      GridPane.setConstraints(manageCarPane, 0, 3);
      
      GridPane transactionCarGrid = new GridPane(); // GridPane for Rented Car
      ScrollPane transactionCarPane = new ScrollPane(transactionCarGrid);
      transactionCarPane.setPrefViewportWidth(400);
      transactionCarPane.setPrefViewportHeight(500);
      GridPane.setConstraints(transactionCarPane, 0, 3);
      GridPane.setConstraints(transactionCarButton, 0, 5);
      
      GridPane searchResultGrid = new GridPane(); // GridPane For search Car (regNum, make, model, price) (all price less than or equal to the inputed price is listed)
      ScrollPane searchResultPane = new ScrollPane(searchResultGrid);
      searchResultPane.setPrefViewportWidth(400);
      searchResultPane.setPrefViewportHeight(500);
      GridPane.setConstraints(searchResultPane, 0, 3);
      
      
      // renter info (if they click the rent the car button)
      VBox renterBox = new VBox(10);  // VBox with 10px spacing
      renterBox.setPadding(new Insets(20));
      renterBox.setAlignment(Pos.CENTER);
      renterBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;"); // css style design
      renterBox.setPrefSize(250, 300); 
      
      // Renter Form (name, address, driverLicence, paymentMethod (cash, card)
      TextField name = new TextField();
      TextField address = new TextField();
      TextField driverLicense = new TextField();
      name.setPromptText("Enter Full Name");
      name.setFont(Font.font("Roboto", 15));
      address.setPromptText("Enter Address");
      address.setFont(Font.font("Roboto", 15));
      driverLicense.setPromptText("Enter Driver License");
      driverLicense.setFont(Font.font("Roboto", 15));
      
      
      Button cashButton = new Button("Cash");
      cashButton.setFont(Font.font("Roboto", 15)); 
      cashButton.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
      
      Button cardButton = new Button("Card");
      cardButton.setFont(Font.font("Roboto", 15)); 
      cardButton.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
      
      Button submitForm = new Button("Submit");
      submitForm.setFont(Font.font("Roboto", 15)); 
      submitForm.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
   
      HBox paymentButtons = new HBox(10);
      paymentButtons.setAlignment(Pos.CENTER);
      paymentButtons.getChildren().addAll(cashButton, cardButton);
    
      TextField cardId = new TextField();
      TextField cardPin = new TextField();
      TextField cash = new TextField();
      
      Text rented = new Text("Rented few days ago");
      rented.setFont(Font.font("Roboto", 20));
      GridPane.setConstraints(rented, 0, 1);
   
   
      // Display All Cars, record cars (rented, available)
      Button editButton = new Button("Edit Car Info");
      editButton.setFont(Font.font("Roboto", 15)); 
      editButton.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
   
      Button deleteButton = new Button("Delete Car");
      deleteButton.setFont(Font.font("Roboto", 15)); 
      deleteButton.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
   
      Button addButton = new Button("Add Car");
      addButton.setFont(Font.font("Roboto", 15)); 
      addButton.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
   
      Button refreshButton = new Button("Refresh");
      refreshButton.setFont(Font.font("Roboto", 15)); 
      refreshButton.setPrefWidth(200);
      refreshButton.setPrefHeight(50);
      refreshButton.setStyle("-fx-background-color: slateblue; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white;");
      
   
      GridPane.setConstraints(refreshButton, 0, 6);
      
      HBox manageCarBox = new HBox();
      manageCarBox.setSpacing(1);
        
      // adding a car
      TextField addRegNumField = new TextField();
      TextField addMakeField = new TextField();
      TextField addModelField = new TextField();
      TextField addPriceField = new TextField();
      TextField addYearMadeField = new TextField();
      addRegNumField.setFont(Font.font("Roboto", 15));
      addMakeField.setFont(Font.font("Roboto", 15));
      addModelField.setFont(Font.font("Roboto", 15));
      addPriceField.setFont(Font.font("Roboto", 15));
      addYearMadeField.setFont(Font.font("Roboto", 15));
   
   
     // editing a car
      TextField editRegNumField = new TextField();
      TextField editMakeField = new TextField();
      TextField editModelField = new TextField();
      TextField editPriceField = new TextField();
      TextField editYearMadeField = new TextField();
      CheckBox editAvailabilityCheckbox = new CheckBox("Available");
      editRegNumField.setFont(Font.font("Roboto", 15));
      editMakeField.setFont(Font.font("Roboto", 15));
      editModelField.setFont(Font.font("Roboto", 15));
      editPriceField.setFont(Font.font("Roboto", 15));
      editAvailabilityCheckbox.setFont(Font.font("Roboto", 15));
      
      HBox addBox = new HBox(addButton);
      addBox.setAlignment(Pos.BOTTOM_RIGHT); 
      
      Text toPay = new Text();
      toPay.setFont(Font.font("Roboto", 20));
      // loginButton clicked
      loginButton.setOnAction(
         e -> {
            if(user.getText().isEmpty()){
               showMessageDialog(null, "Empty username"); 
            }
            else if(pass.getText().isEmpty()){
               showMessageDialog(null, "Empty password");
            }
            else{
               login = new Login(user.getText(), pass.getText()); 
               logined = login.isAuthenticated(); // verify if user password and username if correct
               if(logined == true){
                  showMessageDialog(null, "Login Success");
                  root.getChildren().removeAll(user, pass, loginBox);
                  root.getChildren().addAll(titleLabel, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout); 
               }
               else{
                  showMessageDialog(null, "Username or Password Incorrect");
               }
            }
            user.setText("");
            pass.setText("");
         });
      // Logout user
      logout.setOnAction(
         e -> {
            int response = JOptionPane.showConfirmDialog(null, "Confirm Logout", null, JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
               login.logout();
               root.getChildren().clear();
               root.getChildren().addAll(loginBox);
            }
         });
      mainMenuButton.setOnAction(
         e -> {
            root.getChildren().clear();
            titleLabel.setText("Car Rental System");
            root.getChildren().addAll(titleLabel, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout);  //return to dashboard
         });
                             
      carListButton.setOnAction( // button is clicked
         e -> {
            titleLabel.setText("All Cars"); // set the titleLable to new text(All Cars)
            root.getChildren().clear();
            root.getChildren().addAll(titleLabel, searchBox, allCarPane, mainMenuButton); 
            oldScene = new ArrayList<>(root.getChildren());
         });
   
      availableCarButton.setOnAction(
         e -> {
            titleLabel.setText("Available Car");
            root.getChildren().clear();
            root.getChildren().addAll(titleLabel, searchBox, availableCarPane, mainMenuButton);
            oldScene = new ArrayList<>(root.getChildren());
         });
         
      rentedCarButton.setOnAction(
         e -> {
            titleLabel.setText("Rented Car");
            root.getChildren().clear();
            root.getChildren().addAll(titleLabel, searchBox, rentedCarPane, mainMenuButton);
            oldScene = new ArrayList<>(root.getChildren());
         });
         
      manageCarButton.setOnAction(
         e -> {
            titleLabel.setText("Manage Car");
            root.getChildren().clear();
            GridPane.setConstraints(addBox, 0, 0);
            addBox.setAlignment(Pos.BOTTOM_RIGHT);
            root.getChildren().addAll(titleLabel, searchBox, manageCarPane, mainMenuButton, addBox); // Add both main menu button and add button to the scene graph
            oldScene = new ArrayList<>(root.getChildren());
         });
      transactionCarButton.setOnAction(
         e -> {
            titleLabel.setText("Transaction");
            root.getChildren().clear();
            GridPane.setConstraints(addBox, 0, 0);
            addBox.setAlignment(Pos.BOTTOM_RIGHT);
            root.getChildren().addAll(titleLabel, transactionCarPane, mainMenuButton); // Add both main menu button and add button to the scene graph
            oldScene = new ArrayList<>(root.getChildren());
         });
      Text title = new Text("Selected Car");
      title.setFont(Font.font("Roboto", 25)); // set the font    
      GridPane.setConstraints(title, 0, 0); // postion the label within the grid
      GridPane.setHalignment(title, HPos.CENTER); // Center the label
      
      loadCarList = 
         () -> {
         // Clear the current car list UI
            allCarGrid.getChildren().clear();
            manageCarGrid.getChildren().clear();
            availableCarGrid.getChildren().clear();
            rentedCarGrid.getChildren().clear();
            transactionCarGrid.getChildren().clear();
            int row = 0;
            for(Car car : carList.getAllCars()){ // CarList is already called
            
               Button carButton = new Button(car.carInfo()); // make a button that have the car Info (make - model, price per day
               carButton.setFont(Font.font("Roboto", 15));
               carButton.setPrefWidth(364);
               carButton.setPrefHeight(60);
               carButton.setId("allCarButton_" + car.getRegNum()); // set the Id of button so that we can avoid conflict in the functions
               allCarGrid.add(carButton, 0, row++);
            
               Button manageCar = new Button(car.carInfo());
               manageCar.setFont(Font.font("Roboto", 15));
               manageCar.setPrefWidth(364);
               manageCar.setPrefHeight(60);
               manageCar.setId("manageCar_" + car.getRegNum());
               manageCarGrid.add(manageCar, 0, row++);
            
               if(car.isAvailable()){
                  Button availableCar = new Button(car.carInfo());
                  availableCar.setFont(Font.font("Roboto", 15));
                  availableCar.setPrefWidth(364);
                  availableCar.setPrefHeight(60);
                  availableCar.setId("availableCar_" + car.getRegNum());
                  availableCarGrid.add(availableCar, 0, row++);
                  availableCar.setOnAction( 
                     a -> {
                        root.getChildren().clear();
                        String buttonId = ((Button) a.getSource()).getId();
                        int regNum = Integer.parseInt(buttonId.split("_")[1]);
                        Car carFound = carList.getCarByRegNum(regNum);
                        Label carDetail = new Label(carFound.carInfo());
                        carDetail.setFont(Font.font("Roboto", 20));
                        GridPane.setConstraints(carDetail, 0, 2);
                        GridPane.setConstraints(rentButton, 0, 3);
                        if(carFound.isAvailable()){
                           root.getChildren().addAll(title, carDetail, rentButton, mainMenuButton);
                           carSelectedRegNum = regNum;
                        }
                        else{
                           root.getChildren().addAll(title, carDetail, rented, backButton);
                        }
                     });
               }
               else{
                  Button rentedCar = new Button(car.carInfo());
                  rentedCar.setFont(Font.font("Roboto", 15));
                  rentedCar.setPrefWidth(364);
                  rentedCar.setPrefHeight(60);
                  rentedCar.setId("rentedCar_" + car.getRegNum());
                  rentedCarGrid.add(rentedCar, 0, row++);
                  rentedCar.setOnAction( 
                     a -> {
                        root.getChildren().clear();
                        String buttonId = ((Button) a.getSource()).getId();
                        int regNum = Integer.parseInt(buttonId.split("_")[1]);
                        Car carFound = carList.getCarByRegNum(regNum);
                        Label carDetail = new Label(carFound.carInfo());
                        carDetail.setFont(Font.font("Roboto", 20));
                        GridPane.setConstraints(carDetail, 0, 2);
                        GridPane.setConstraints(rentButton, 0, 3);
                        root.getChildren().addAll(title, carDetail, rented, backButton);
                     }); 
               }
               manageCar.setOnAction( 
                  a -> {
                     manageCarBox.getChildren().clear();
                     root.getChildren().clear();
                     String buttonId = ((Button) a.getSource()).getId();
                     int regNum = Integer.parseInt(buttonId.split("_")[1]);
                     Car carFound = carList.getCarByRegNum(regNum);
                     Label carDetail = new Label(carFound.carInfo());
                     carDetail.setFont(Font.font("Roboto", 20));
                     GridPane.setConstraints(carDetail, 0, 2);
                     GridPane.setConstraints(rentButton, 0, 3);
                     GridPane.setConstraints(manageCarBox, 0, 4);
                     manageCarBox.getChildren().addAll(editButton, deleteButton);
                     GridPane.setConstraints(backButton, 0, 5);
                     root.getChildren().addAll(title, carDetail, manageCarBox, rented, backButton);
                  
                     deleteButton.setOnAction(
                        b -> {
                           try { 
                              if(carList.deleteCar(regNum)){
                                 showMessageDialog(null, "Car deleted successfully!", "Success", INFORMATION_MESSAGE);
                                 root.getChildren().clear();
                                 loadCarList.run();
                                 root.getChildren().addAll(title, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout);
                              }
                              else {
                                 showMessageDialog(null, "Car not found.", "Error", ERROR_MESSAGE);
                              }
                           }
                           catch(NumberFormatException ex) {
                              showMessageDialog(null, "Please enter a valid registration number.", "Error", ERROR_MESSAGE);
                           }
                        });
                  
                     editButton.setOnAction(
                        b -> {
                           try{
                           
                              VBox editBox = new VBox(10);
                              editBox.setPadding(new Insets(20));
                              editBox.setAlignment(Pos.CENTER);
                              editBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;"); // css style design
                              editBox.setPrefSize(250, 300); 
                           
                              Car editCar = carList.getCarByRegNum(regNum);
                           
                              editRegNumField.setText(String.valueOf(editCar.getRegNum())); 
                              editRegNumField.setPromptText("Registration Number");
                              editRegNumField.setEditable(false);
                           
                              editMakeField.setText(editCar.getMake());                
                              editMakeField.setPromptText("Make");
                           
                              editModelField.setText(editCar.getModel());
                              editModelField.setPromptText("Model");
                           
                              editYearMadeField.setText(String.valueOf(editCar.getYearMade()));
                              editYearMadeField.setPromptText("Year Made");
                           
                              editPriceField.setText(String.valueOf(editCar.getRentPrice()));
                              editPriceField.setPromptText("Price per Day");
                           
                              CheckBox availabilityCheckBox = new CheckBox("Available");
                              availabilityCheckBox.setSelected(car.isAvailable());
                           
                              Button submitEdit = new Button("Save");
                              submitEdit.setPrefWidth(100); // adjust width of the button
                              submitEdit.setPrefHeight(50); // adjust height of the button
                              submitEdit.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
                              root.getChildren().clear();
                              editBox.getChildren().addAll(editRegNumField, editMakeField, editModelField, editYearMadeField, editPriceField, availabilityCheckBox, submitEdit, backButton);
                              root.getChildren().add(editBox);
                           
                              submitEdit.setOnAction(
                                 c -> {                    
                                    String make = editMakeField.getText();
                                    String model = editModelField.getText();
                                    double price = Double.parseDouble(editPriceField.getText());
                                    boolean isAvailable = editAvailabilityCheckbox.isSelected();
                                    if(carList.editCar(regNum, make, model, price, isAvailable)) {
                                       showMessageDialog(null, "Car edited successfully!", "Success", INFORMATION_MESSAGE);
                                       editRegNumField.clear();
                                       editMakeField.clear();
                                       editModelField.clear();
                                       editPriceField.clear();
                                       root.getChildren().clear();
                                       loadCarList.run();
                                       root.getChildren().addAll(title, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout);                 
                                    }
                                    else{
                                       showMessageDialog(null, "Car not found.", "Error", ERROR_MESSAGE);
                                    }
                                 });
                           }
                           catch(NumberFormatException ex) {
                              showMessageDialog(null, "Please enter valid inputs.", "Error", ERROR_MESSAGE);
                           }
                        
                        });
                  });
               addButton.setOnAction(
                  a -> {
                     oldScene = new ArrayList<>(root.getChildren());  
                     root.getChildren().clear();
                     VBox addCarBox = new VBox(10);
                     addCarBox.setPadding(new Insets(20));
                     addCarBox.setAlignment(Pos.CENTER);
                     addCarBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 10; -fx-background-radius: 10;"); // css style design
                     addCarBox.setPrefSize(250, 300); 
                        
                     addRegNumField.setPromptText("Registration Number");                                            
                     addMakeField.setPromptText("Make");    
                     addModelField.setPromptText("Model");
                     addYearMadeField.setPromptText("Year Made");  
                     addPriceField.setPromptText("Price per Day");
                     CheckBox addAvailabilityCheckbox = new CheckBox("Available");
                     addAvailabilityCheckbox.setSelected(car.isAvailable());
                           
                     Button submitAdd = new Button("Submit");
                     submitAdd.setStyle("-fx-background-color: slateblue; -fx-border-color: grey; -fx-border-radius: 10; -fx-background-radius: 10; -fx-text-fill: white;");
                     submitAdd.setPrefWidth(100); // adjust width of the button
                     submitAdd.setPrefHeight(50); // adjust height of the button
                     addCarBox.getChildren().addAll(addRegNumField, addMakeField, addModelField, addYearMadeField, addPriceField, addAvailabilityCheckbox, submitAdd, backButton);
                     root.getChildren().add(addCarBox);
                           
                     submitAdd.setOnAction(
                           b -> {
                              try{
                                 int regNum = Integer.parseInt(addRegNumField.getText());
                                 String make = addMakeField.getText();
                                 String model = addModelField.getText();
                                 int yearMade = Integer.parseInt(addYearMadeField.getText());
                                 double price = Double.parseDouble(addPriceField.getText());
                                 boolean isAvailable = addAvailabilityCheckbox.isSelected(); 
                                 Car newCar = new Car(make, model, yearMade, regNum, price, isAvailable);
                                 if(carList.addCar(newCar)){
                                    showMessageDialog(null, "Car added successfully!", "Success", INFORMATION_MESSAGE);
                                    addRegNumField.clear();
                                    addMakeField.clear();
                                    addModelField.clear();
                                    addYearMadeField.clear();
                                    addPriceField.clear();
                                    root.getChildren().clear();
                                    loadCarList.run();
                                    root.getChildren().addAll(title, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout);
                                 }
                                 else{
                                    showMessageDialog(null, "Car not added, registration number existed.", "Error", ERROR_MESSAGE);
                                 }
                              }
                              catch(NumberFormatException ex) {
                                 showMessageDialog(null, "Please enter valid inputs.", "Error", ERROR_MESSAGE);
                              }
                           });
                  });
            
            
               carButton.setOnAction( 
                  a -> {
                     root.getChildren().clear();
                     String buttonId = ((Button) a.getSource()).getId();
                     int regNum = Integer.parseInt(buttonId.split("_")[1]);
                     Car carFound = carList.getCarByRegNum(regNum);
                     Label carDetail = new Label(carFound.carInfo());
                     carDetail.setFont(Font.font("Roboto", 20));
                     GridPane.setConstraints(carDetail, 0, 2);
                     GridPane.setConstraints(rentButton, 0, 3);
                     if(carFound.isAvailable()){
                        root.getChildren().addAll(title, carDetail, rentButton, mainMenuButton);
                        carSelectedRegNum = regNum;
                     }
                     else{
                        root.getChildren().addAll(title, carDetail, rented, backButton);
                     }
                  });    
            }
            
            
            // fo transaction
            int rows = 0;
            for(Transaction tran : transactionList.getAllCars()){ // CarList is already called
            
               Button tranButton = new Button(tran.transactionInfo()); // make a button that have the car Info (make - model, price per day
               tranButton.setFont(Font.font("Roboto", 15));
               tranButton.setPrefWidth(364);
               tranButton.setPrefHeight(60);
               tranButton.setId("transactionButton_" + tran.getRegNum()); // set the Id of button so that we can avoid conflict in the functions
               transactionCarGrid.add(tranButton, 0, rows++);
               tranButton.setOnAction( 
                  a -> {
                     root.getChildren().clear();
                     String buttonId = ((Button) a.getSource()).getId();
                     int regNum = Integer.parseInt(buttonId.split("_")[1]);
                     Transaction tranFound = transactionList.getCarByRegNum(regNum);
                     Label carDetail = new Label(tranFound.moreTransactionInfo());
                     carDetail.setFont(Font.font("Roboto", 20));
                     GridPane.setConstraints(carDetail, 0, 2);
                     GridPane.setConstraints(rentButton, 0, 3);
                     title.setText("Transaction Info");
                     root.getChildren().addAll(title, carDetail, backButton);
                    
                  });    
            }
         };
         
      loadCarList.run();
             
      // Search Functions
      searchButton.setOnAction(
         e -> {
           // save the current scence so that it can be recover using the backButton
            oldScene = new ArrayList<>(root.getChildren()); // original scene
            searchResultGrid.getChildren().clear(); // clear the scence (it is saved alread)
            String selectedFilter = filterComboBox .getValue(); // the filter choosed
            List<Car> searchResults = new ArrayList<>(); // ArrayList for the found Car
            root.getChildren().remove(searchResultGrid);
            Car regNumSearchResult = null; // for the regNum filter
            try{
               searchResults = carList.searchCars(selectedFilter, searchField.getText());
               if(!searchResults.isEmpty()){ // if searchResult is not empty, display it
                  int rows = 0;
                  for (Car car : searchResults){
                     Button searchResultButton = new Button(car.carInfo());
                     searchResultButton.setFont(Font.font("Roboto", 15));
                     searchResultButton.setPrefWidth(364);
                     searchResultButton.setPrefHeight(60);
                     searchResultButton.setId("searchResultButton_" + car.getRegNum());
                     searchResultGrid.add(searchResultButton, 0, rows++);
                     titleLabel.setText("Search Result");
                  
                     searchResultButton.setOnAction( // the result of the search (if the button is clicked it will display info) and a rent button
                        a -> {
                           ArrayList<javafx.scene.Node> oldScene2 = new ArrayList<>(root.getChildren()); // second scene
                           title.setText("Selected Car");
                           root.getChildren().clear();
                           String buttonId = ((Button) a.getSource()).getId();
                        // split the text (ex. searchResultButton_1234, by using split("_"), it will make an array from the left and right part of the _
                        // [0] is the searchResultButton
                        // [1] regNum (1234)
                        // if there is multiple "_" ex. search_button_1234 ([0] => search, [1] => button. [2] => 1234)
                           int regNum = Integer.parseInt(buttonId.split("_")[1]);
                           Car carFound = carList.getCarByRegNum(regNum);
                           Label carDetail = new Label(carFound.carInfo());
                           carDetail.setFont(Font.font("Roboto", 20)); 
                           GridPane.setConstraints(carDetail, 0, 2);
                           GridPane.setConstraints(rentButton, 0, 3);
                           if(carFound.isAvailable()){
                              carSelectedRegNum = regNum;
                              root.getChildren().addAll(title, carDetail, rentButton, mainMenuButton); // if not rented
                           }
                           else{
                              root.getChildren().addAll(title, carDetail, rented, mainMenuButton); // if rented, no rent button will show up
                           }
                        });
                  }
               }
               else{ // if searchResult is empty return this result
                  titleLabel.setText("No Result Found");
               }
               root.getChildren().clear(); // clear the scence
               searchField.clear();
               root.getChildren().addAll(titleLabel, searchResultPane, backButton); // then add this
            }
            catch (Exception ex){
               searchField.clear();
               showMessageDialog(null, "Please enter number only.", "Error", ERROR_MESSAGE);
            }
         });
      
     
      rentButton.setOnAction( // rent button (fill up form)
         e -> {
            oldScene = new ArrayList<>(root.getChildren());
            renterBox.getChildren().clear();
            root.getChildren().clear();
            toPay.setText(String.valueOf("To Pay "+carList.getCarByRegNum(carSelectedRegNum).getRentPrice()));;
            renterBox.getChildren().addAll(toPay, name, address, driverLicense, paymentButtons, submitForm);
            root.getChildren().addAll(renterBox, backButton);
         });
         
      cardButton.setOnAction(// if payment method is card
         e -> { 
         // add this to the form
            cardId.setPromptText("Enter Card Id");
            cardId.setFont(Font.font("Roboto", 15));
            cardPin.setPromptText("Enter Card Pin");
            cardPin.setFont(Font.font("Roboto", 15));
            renterBox.getChildren().clear(); // clear the renterBox so that It can be updated in the following
            renterBox.getChildren().addAll(toPay, name, address, driverLicense, cardId, cardPin, submitForm);
         });
      cashButton.setOnAction(// if payment method is cash
         e -> { 
            cash.setPromptText("Enter Cash (php)");  // add this to the form  
            cash.setFont(Font.font("Roboto", 15));
            renterBox.getChildren().clear();
            renterBox.getChildren().addAll(toPay, name, address, driverLicense, cash, submitForm);
         });
         
      submitForm.setOnAction( // submit form (all info is filled up)
         e -> {
            try{
               boolean userRented = false;
               double rentPrice = carList.getCarByRegNum(carSelectedRegNum).getRentPrice();
               int userLincense = Integer.parseInt(driverLicense.getText());
               
               ArrayList<Car> selectedCar = new ArrayList<>();
               if(!name.getText().isEmpty() && !address.getText().isEmpty() &&  !driverLicense.getText().isEmpty()){ // if one is empty it willl give an message
                  if(!cardId.getText().isEmpty() && !cardPin.getText().isEmpty()){ // emtpy (cardId or cardPin) or the PaymentMethod is Cash
                     Card card = new Card(rentPrice, Integer.parseInt(cardId.getText()), Integer.parseInt(cardPin.getText()));
                     selectedCar.add(carList.getCarByRegNum(carSelectedRegNum));
                     if(card.enoughMoney()){
                        showMessageDialog(null, "Rent Success, Changes "+ card.moneyLeft());
                        if(transactionList.addTransaction(name.getText(), address.getText(), userLincense, rentPrice, card.moneyLeft(), card.moneyLeft())){
                           userRented = true;
                        }
                        else{
                           showMessageDialog(null, "Error"); 
                        }
                     }
                     else{
                        showMessageDialog(null, "Oops, not enought balance");
                        cash.clear();
                     }   
                  }
                  else if(!cash.getText().isEmpty()){// emtpy (cash) no data inputed in the field(return a message)
                  
                     Payment coldHardCash = new Payment(Double.parseDouble(cash.getText()), rentPrice);
                     selectedCar.add(carList.getCarByRegNum(carSelectedRegNum));
                     if(coldHardCash.enoughMoney()){
                        
                        if(transactionList.addTransaction(name.getText(), address.getText(), userLincense, rentPrice, Double.parseDouble(cash.getText()), coldHardCash.moneyLeft())){
                           showMessageDialog(null, "Rent Success, Change "+ coldHardCash.moneyLeft());
                           userRented = true;
                        }
                        else{
                           showMessageDialog(null, "Error"); 
                        }
                     }
                     else{
                        showMessageDialog(null, "Oops, not enought balance");
                        cash.clear();
                     }
                  }
                  else{
                     showMessageDialog(null, "Fill up the form completely"); // the message
                  }  
               }
               else{
                  showMessageDialog(null, "Fill up the form completely"); // the message
               } 
               if(userRented){
                  name.clear();
                  cash.clear();
                  cardId.clear();
                  cardPin.clear();
                  address.clear();
                  driverLicense.clear(); 
                  for(Car car : selectedCar){
                     car.setAvailable(false);
                     root.getChildren().clear();
                     root.getChildren().addAll(titleLabel, carListButton, availableCarButton, rentedCarButton, manageCarButton, transactionCarButton, refreshButton, logout);
                  }
                  loadCarList.run();// refresh carList || call the carList again
               }
            }
            catch(NumberFormatException ex) {
               showMessageDialog(null, "Please enter valid inputs.", "Error", ERROR_MESSAGE);
            }
         });
         
      refreshButton.setOnAction(
         e -> {
            loadCarList.run();
            showMessageDialog(null, "Car List Refreshed", "Success", INFORMATION_MESSAGE);
         });
      backButton.setOnAction( 
         e ->{
            root.getChildren().clear();
            root.getChildren().addAll(oldScene);
         });
   
      Scene scene = new Scene(root, 400, 500);
      stage.setTitle("LE2: Rent A Car");
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args){
      launch(args);
   }
  
}