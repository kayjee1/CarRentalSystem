package Core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    List<Car> cars;
    List<Rental> rentals;

    public CarRentalSystem(){
        this.cars = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }
    public void removeCar(Car car){
        cars.remove(car);
    }
    public List<Car> getAllCars(){
        return new ArrayList<>(cars);
    }
    public List<Car> filterBySeats(List<Car> input, int minSeats){
        List<Car> result = new ArrayList<>();
        for(Car car : input){
            if (car.getSeats() >= minSeats){
                result.add(car);
            }
        }
        return result;
    }
    public List<Car> filterByBrand(List<Car> input, String brand){
        List<Car> result = new ArrayList<>();
        if (brand == null || brand.isEmpty()){
            return input;
        }
        for(Car car : input){
            if(car.getBrand().equalsIgnoreCase(brand)){
                result.add(car);
            }
        }
        return result;
    }
    public List<Car> filterByAvailability(List<Car> input, LocalDateTime from, LocalDateTime to){
        List<Car> result = new ArrayList<>();
        for(Car car: input){
            if (car.isAvailableDuring(from, to)){
                result.add(car);
            }
        }
        return result;
    }
    public List<Car> filterByPriceRange(List<Car> input, int minPrice, int maxPrice){
        List<Car> result = new ArrayList<>();
        for(Car car : input){
            int price = car.getPrice();
            if (price >= minPrice && price <= maxPrice){
                result.add(car);
            }
        }
        return result;
    }
    public boolean rentCar(String carId, LocalDateTime from, LocalDateTime to){
        for(Car car : cars){
            if (car.getId().equals(carId)){
                if (car.isAvailableDuring(from, to)){
                    Rental rental = new Rental(carId, from, to);
                    car.getBookings().add(rental);
                    rentals.add(rental);
                    return true;
                }
                break;
            }
        }
        return false;
    }
}
