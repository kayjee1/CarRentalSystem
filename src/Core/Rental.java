package Core;

import java.time.LocalDateTime;

public class Rental {
    private String carId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Rental(String carId, LocalDateTime startDate, LocalDateTime endDate){
        this.carId = carId;
        this.startDate = startDate;;
        this.endDate = endDate;
    }

    public String getCarId(){
        return this.carId;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }

    public boolean overlapWith(LocalDateTime from, LocalDateTime to){
        return !(endDate.isBefore(from) || startDate.isAfter(to));
    }

    public String toString(){
        return "Rental for car with ID: " + carId + " from " + startDate + " to " + endDate;
    }
}
