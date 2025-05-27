package Core;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Car {
    private String id;
    private String brand;
    private int price;
    private int seats;
    private boolean isAvailable;
    List<Rental> bookings;

    public Car(String id, String brand, int price, int seats, boolean isAvailable){
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.seats = seats;
        this.isAvailable = isAvailable;
        this.bookings = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }
    public String getBrand(){
        return this.brand;
    }
    public int getPrice(){
        return this.price;
    }
    public int getSeats(){
        return this.seats;
    }
    public boolean isAvailable(){
        return this.isAvailable;
    }
    public List<Rental> getBookings(){
        return this.bookings;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public void setAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    public boolean isAvailableDuring(LocalDateTime from, LocalDateTime to){
        for(Rental rental : bookings){
            if (rental.overlapWith(from, to)){
                return false;
            }
        }
        return true;
    }
    public String toString() {
        return "Car ID: " + id + ", Brand: " + brand + ", Seats: " + seats +
                ", Price for a day: " + price + "֏" + ", Available: " + isAvailable;
    }
}

