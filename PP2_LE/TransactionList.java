import java.util.*;
public class TransactionList{
   private ArrayList<Transaction> transaction = new ArrayList<>();
   public TransactionList(){
      this.transaction = new ArrayList<>();
   
   }

   public ArrayList<Transaction> getAllCars(){
      return transaction;
   }
   public Transaction getCarByRegNum(int regNum){
      for(Transaction car : transaction){
         if(car.getRegNum()== regNum){
            return car;
         }
      }
      return null;
   }   

   public boolean addTransaction(String name, String address, int driverLicense, double rentPrice, double payed, double changes){
      transaction.add(new Transaction(name, address, driverLicense, rentPrice, payed, changes));
      return true;
   }
}
