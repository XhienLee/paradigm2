public class Payment{
   private double money;
   private double amountToPay;
   public Payment(double money, double amountToPay){
      this.money = money;
      this.amountToPay = amountToPay;
   }
   public boolean enoughMoney(){
      if(this.money >= this.amountToPay){
         return true;
      }
      else{
         return false;
      }
   }
   public double getAmountToPay(){
      return this.amountToPay;
   }
   public void setAmountToPay(double amountToPay){
      this.amountToPay = amountToPay;
   }
   public double moneyLeft(){
      return this.money - this.amountToPay;
   }
   
}