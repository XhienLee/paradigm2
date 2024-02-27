public class TemperatureMain{
   public static void main(String[] args){
      String line = "__________________________________________________";
     
      TemperatureMethod tempMethod = new TemperatureMethod();
      
      tempMethod.display(); // display all data contain in temperature array ( located in TemperatureMethod )
      System.out.println(line);
      
      tempMethod.displayTemp(4,7); // display specify temperature, parameter ( int week, int day ). Week min 1, max 4. Day min 1, max 7. 
      System.out.println(line);
   
   
      tempMethod.displayAveTemp(4); // display the average of that week, parameter ( int week ). Week min 1, max 4.
      System.out.println(line);
   
   
      tempMethod.displayTempWholeWeek(4); // display all in specified week, parameter ( int week). Week min 1, max 4.
      System.out.println(line);
   
   
      tempMethod.displayCountTempLower(10.5); // display all temperature lower than the specified amount, parameter ( double lessThan ).
      System.out.println(line);
   
   
      tempMethod.displayhighestTemp(); // display the highest temperature and what wek and day it is located
      System.out.println(line);
   
   
      tempMethod.displayLowestTemp(); // display the highest temperature and what wek and day it is located
      System.out.println(line);
   
   }
}