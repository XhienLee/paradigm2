import java.util.Scanner;
public class TemperatureMethod{
   public double temperature[][] = {
      {12.5, 12.2, 14.0, 13.0, 13.2, 13.0, 12.2},
      {15.2, 11.9, 11.8, 13.2, 12.7, 14.5, 12.4},
      {9.75, 11.8, 11.9, 13.5, 12.7, 14.7, 13.0},
      {14.0, 13.2, 11.8, 14.0, 14.3, 11.0, 11.9}
   };
   
   public void display(){
      System.out.print("   ");
      for(int i = 0; i < temperature[0].length; i++){
         System.out.print("   Day "+(i+1));
      }
      System.out.print("\n");
      for(int i = 0; i < temperature.length; i++){
         System.out.print("Week "+(i+1)+" :  ");
         for(int x = 0; x < temperature[i].length; x++){
            System.out.print(temperature[i][x]+"    ");
         }
         System.out.println();
      }  
   }
   
   public void displayTemp(int week, int day){
      System.out.println("Week "+week+" Day "+day+" : "+temperature[week-1][day-1]);      
   }
    
     
   public void displayAveTemp(int week){
      double sum = 0.0;
      for(int i = 0; i < temperature[week-1].length; i++){
         sum += temperature[week-1][i]; 
      }
      System.out.println("Week "+week+" Average : "+(sum/7));   
   }
   
   public void displayTempWholeWeek(int week){
      System.out.print("       ");
      for(int i = 0; i < temperature[0].length; i++){
         System.out.print("   Day "+(i+1));
      }
      System.out.print("\n");
      System.out.print("Week "+(week+1)+" :  ");
      for(int x = 0; x < temperature[week-1].length; x++){
         System.out.print(temperature[week-1][x]+"    ");
      }
      System.out.println();
   }
   
   public void displayCountTempLower(double lowerThan){
      int lowerCount = 0;
      for(int i = 0; i < temperature.length; i++){
         for(int x = 0; x < temperature[i].length; x++){
            if(temperature[i][x] < lowerThan){
               lowerCount += 1;
            }       
         }
      }  
   }
   
   public void displayhighestTemp(){
      double currentHigh = 0.0;
      int week = 0, day = 0;
      for(int i = 0; i < temperature.length; i++){
         for(int x = 0; x < temperature[i].length; x++){
            if(currentHigh < temperature[i][x]){
               currentHigh = temperature[i][x];
               week = i;
               day = x;
            }
         }
      }
      System.out.println("The highest temperature is in Week "+(week+1)+" Day "+(day+1)+" : "+currentHigh);
   }
   
   public void displayLowestTemp(){
      double currentLow = 999;
      int week = 0, day = 0;
      for(int i = 0; i < temperature.length; i++){
         for(int x = 0; x < temperature[i].length; x++){
            if(currentLow > temperature[i][x]){
               currentLow = temperature[i][x];
               week = i;
               day = x;
            }  
         }
      }
      System.out.println("The lowest temperature is in Week "+(week+1)+" Day "+(day+1)+" : "+currentLow);
   }
}