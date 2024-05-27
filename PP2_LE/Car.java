import java.util.*;
public class Car {
   private String make;
   private String model;
   private int yearMade;
   private int regNum;
   private double rentPrice;
   private boolean available;
   public Car(String make, String model, int yearMade, int regNum, double rentPrice, boolean available) {
      this.make = make;
      this.model = model;
      this.yearMade = yearMade;
      this.regNum = regNum;
      this.rentPrice = rentPrice;
      this.available = available;
   }

 
   public String carInfo(){
      return make+" - "+model+", Rent/day : "+rentPrice; 
   }
   public String allCarInfo(){
      return make+" - "+model+" Rent/day : "+rentPrice; 
   }
   public boolean isAvailable(){
      return this.available;
   }
   public void setAvailable(boolean available) {
      this.available = available;
   }

   public String getMake() {
      return make;
   }

   public void setMake(String make) {
      this.make = make;
   }

   public String getModel() {
      return model;
   }

   public void setModel(String model) {
      this.model = model;
   }

   public int getYearMade() {
      return yearMade;
   }

   public void setYearMade(int yearMade) {
      this.yearMade = yearMade;
   }

   public int getRegNum() {
      return regNum;
   }

   public void setRegNum(int regNum) {
      this.regNum = regNum;
   }

   public double getRentPrice() {
      return rentPrice;
   }

   public void setRentPrice(double rentPrice) {
      this.rentPrice = rentPrice;
   }
}
