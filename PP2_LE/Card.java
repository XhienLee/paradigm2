public class Card extends Payment{
   private double money;
   private double amountToPay;
   private int cardId;
   private int cardPin;
   public Card(double amountToPay, int cardId, int cardPin){
      super(99999, amountToPay);
      this.money = 99999; // card balance (unlimited)
      this.amountToPay = amountToPay;
      this.cardId = cardId;
      this.cardPin = cardPin;
   }
   public int getCardId(){
      return this.cardId;
   }
   public void setCardId(int cardId){
      this.cardId = cardId;
   }
   public int getCardPin(){
      return this.cardPin;
   }
   public void getCardPin(int cardPin){
      this.cardPin = cardPin;
   }
}