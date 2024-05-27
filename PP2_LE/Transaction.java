import java.util.*;
public class Transaction{
   private String name;
   private String address;
   private int driverLicense;
   private double rentPrice;
   private double payed;
   private double changes;
   private String make;
   private String model;
   private int yearMade;
   private int regNum;
   private boolean available;
   private ArrayList<Car> carRented = new ArrayList<>();
   public Transaction(String name, String address, int driverLicense, double rentPrice, double payed, double changes) {
      this.name = name;
      this.address = address;
      this.driverLicense = driverLicense;
      this.rentPrice = rentPrice;
      this.payed = payed;
      this.changes = changes;
   }

 
   public String transactionInfo(){
      return name;
   }
   public String moreTransactionInfo(){
      return "Name : "+name+"\nAddress : "+address+"\nPayed : "+rentPrice; 
   }
   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return this.address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public ArrayList<Car> getRentedCar(){
      return this.carRented;
   }

   public double getRentPrice() {
      return rentPrice;
   }

   public void setRentPrice(double rentPrice) {
      this.rentPrice = rentPrice;
   }
   
   public double getPayed() {
      return this.payed;
   }

   public void setPayed(double payed) {
      this.payed = payed;
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
}
