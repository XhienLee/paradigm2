public class Login{
   private String username;
   private String password;
   private boolean logined;
   public Login(String username, String password){
      this.username = username;
      this.password = password;
      
      if(this.username.equals("admin") && this.password.equals("123")){
         this.logined = true;
      }
      else{
         this.logined = false;
      }
   }
   
   public boolean isAuthenticated(){
      if(this.logined){
         return true;
      }
      else{
         return false;
      }
   }
   
   public boolean getLogined(){
      return this.logined;
   }
   
   public void setLogined(boolean logined){
      this.logined = logined;
   }
   
   public String getUsername(){
      return username;
   }
   
   public void setUsername(String username){
      this.username = username;
   }
   
   public String getPassword(){
      return this.password;
   }
   
   public void setPassword(String password){
      this.password = password;
   }
   
   public void logout(){
      this.logined = false;
   }
}