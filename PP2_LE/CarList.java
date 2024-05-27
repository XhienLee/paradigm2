import java.util.*;
public class CarList {
   private List<Car> cars;
   public CarList(){
      this.cars = new ArrayList<>();
      cars.add(new Car("Toyota", "Camry", 2021, 1234, 1500, false));
      cars.add(new Car("Honda", "Civic", 2020, 5678, 1400, true));
      cars.add(new Car("Ford", "Mustang", 2019, 9101, 2000, true));
      cars.add(new Car("Chevrolet", "Cruze", 2018, 2468, 1300, true));
      cars.add(new Car("BMW", "3 Series", 2022, 1357, 2500, true));
      cars.add(new Car("Mercedes-Benz", "C-Class", 2017, 7890, 1800, true));
      cars.add(new Car("Audi", "A4", 2016, 3579, 1700, true));
      cars.add(new Car("Nissan", "Altima", 2019, 8024, 1350, true));
      cars.add(new Car("Volkswagen", "Passat", 2020, 9023, 1600, true));
      cars.add(new Car("Hyundai", "Sonata", 2018, 4567, 1250, true));
      cars.add(new Car("Kia", "Optima", 2017, 6012, 1200, true));
      cars.add(new Car("Subaru", "Legacy", 2019, 7135, 1450, false));
      cars.add(new Car("Mazda", "6", 2021, 8903, 1550, true));
      cars.add(new Car("Lexus", "ES", 2020, 9521, 1900, true));
      cars.add(new Car("Infiniti", "Q50", 2018, 3456, 1700, true));
      cars.add(new Car("Acura", "TLX", 2017, 6789, 1600, true));
      cars.add(new Car("Volvo", "S60", 2022, 2143, 2300, true));
      cars.add(new Car("Jaguar", "XE", 2019, 8542, 2000, true));
      cars.add(new Car("Tesla", "Model 3", 2021, 3698, 3000, true));
      cars.add(new Car("Porsche", "Panamera", 2020, 4789, 2800, true));
      cars.add(new Car("Alfa Romeo", "Giulia", 2018, 9630, 2200, false));
      cars.add(new Car("Buick", "Regal", 2017, 1598, 1750, false));
      cars.add(new Car("Cadillac", "CT5", 2022, 2846, 2600, false));
      cars.add(new Car("Lincoln", "MKZ", 2019, 4875, 2100, true));
      cars.add(new Car("Chrysler", "300", 2020, 7283, 1700, true));
      cars.add(new Car("Toyota", "Corolla", 2022, 1928, 1600, true));
      cars.add(new Car("Honda", "Accord", 2021, 3642, 1500, true));
      cars.add(new Car("Ford", "Fusion", 2019, 4827, 1400, true));
      cars.add(new Car("Chevrolet", "Malibu", 2018, 5619, 1300, true));
      cars.add(new Car("BMW", "5 Series", 2022, 6473, 2500, false));
      cars.add(new Car("Mercedes-Benz", "E-Class", 2017, 7952, 1800, true));
      cars.add(new Car("Audi", "A6", 2016, 4235, 1700, true));
      cars.add(new Car("Nissan", "Maxima", 2019, 5743, 1350, true));
      cars.add(new Car("Volkswagen", "Jetta", 2020, 6872, 1600, true));
      cars.add(new Car("Hyundai", "Elantra", 2018, 7385, 1250, false));
      cars.add(new Car("Kia", "Forte", 2017, 8623, 1200, true));
      cars.add(new Car("Subaru", "Impreza", 2019, 9248, 1450, true));
   }

   public List<Car> getAllCars(){
      return cars;
   }

   public List<Car> getAvailableCars(){
      List<Car> availableCars = new ArrayList<>();
      for(Car car : cars){
         if(car.isAvailable()){
            availableCars.add(car);
         }
      }
      return availableCars;
   }

   public List<Car> getRentedCars(){
      List<Car> rentedCars = new ArrayList<>();
      for(Car car : cars){
         if(!car.isAvailable()){
            rentedCars.add(car);
         }
      }
      return rentedCars;
   }

   public Car getCarByRegNum(int regNum){
      for(Car car : cars){
         if(car.getRegNum()== regNum){
            return car;
         }
      }
      return null;
   }

   public List<Car> searchCars(String filter, String query){
      List<Car> result = new ArrayList<>();
      for(Car car : cars){
         if(filter.toLowerCase().equals("make") && car.getMake().equalsIgnoreCase(query)){
            result.add(car);
         }
         if(filter.toLowerCase().equals("registration number") && car.getRegNum() == Double.parseDouble(query)){
            result.add(car);
         }
         if(filter.toLowerCase().equals("model") && car.getModel().equalsIgnoreCase(query)){
            result.add(car);
         }
         if(filter.toLowerCase().equals("price") && car.getRentPrice() <= Double.parseDouble(query)){
            result.add(car);
         }
      }
      return result;
   }


   public boolean addCar(Car car) {
      for (Car existingCar : cars){
         if (existingCar.getRegNum() == car.getRegNum()){
            return false;
         }
      }
      cars.add(car);
      return true;
   }


   public boolean deleteCar(int regNum){
      Car car = getCarByRegNum(regNum);
      if(car != null){
         cars.remove(car);
         return true;
      }
      return false;
   }

   public boolean editCar(int regNum, String make, String model, double price, boolean isAvailable){
      Car car = getCarByRegNum(regNum);
      if(car != null){
         car.setMake(make);
         car.setMake(make);
         car.setModel(model);
         car.setRentPrice(price);
         car.setAvailable(isAvailable);
         return true;
      }
      return false;
   }
}
