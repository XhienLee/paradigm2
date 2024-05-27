public class Renter{
   private String name;
   private String address;
   private int driverLicense;
   public Renter(String name, String address, int driverLicense){
      this.name = name;
      this.address = address;
      this.driverLicense = driverLicense;
   }
   public String getName(){
      return this.name;
   }
   public void setName(String name){
      this.name = name;
   }
   public String getAddress(){
      return this.address;
   }
   public void setAddress(String address){
      this.address = address;
   }
   public int getDriverLicense(){
      return this.driverLicense;
   }
   public void setDriverLicense(int driverLicense){
      this.driverLicense = driverLicense;
   }
}