package Core;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Ui {
    public static void main(String[] args) {
        CarRentalSystem system = new CarRentalSystem();
        Scanner scanner = new Scanner(System.in);

        system.addCar(new Car("A1","BMW",1500,5,true));
        system.addCar(new Car("A2", "Porsche", 2000, 2, true));
        system.addCar(new Car("A3", "Ford", 1200, 7, true));

        List<Car> filtered = system.getAllCars();
        while(true){
            System.out.println("\n--- Car Rental Menu ---");
            System.out.println("1. Show all cars");
            System.out.println("2. Filter by brand");
            System.out.println("3. Filter by seats");
            System.out.println("4. Filter by price");
            System.out.println("5. Filter by availability");
            System.out.println("6. Rent a car");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            //only case 1 works however I want
            switch (choice){
                case 1:
                    Set<String> brandsOffered = system.getAllCars().stream()
                            .map(Car::getBrand)
                            .collect(Collectors.toSet());

                    System.out.println("Car Brands Available for Rent:");
                    for (String brandName : brandsOffered) {
                        System.out.println("- " + brandName);
                    }
                    System.out.print("Enter the brand you'd like to view: ");
                    String selectedBrand = scanner.nextLine();

                    List<Car> matchingCars = system.getAllCars().stream()
                            .filter(car -> car.getBrand().equalsIgnoreCase(selectedBrand))
                            .collect(Collectors.toList());

                    if (matchingCars.isEmpty()) {
                        System.out.println("No cars found for this brand.");
                        break;
                    }
                    System.out.println("Available " + selectedBrand + " cars:");
                    for (Car car : matchingCars) {
                        System.out.println("ID: " + car.getId() +
                                " | Price per day: " + car.getPrice() + "֏" +
                                " | Seats: " + car.getSeats());
                    }
                    System.out.print("Do you want to rent one of these cars? (yes/no): ");
                    String rentChoice = scanner.nextLine();

                    if (rentChoice.equalsIgnoreCase("yes")) {
                        System.out.print("Enter Car ID: ");
                        String carId = scanner.nextLine();

                        Car selectedCar = system.getAllCars().stream()
                                .filter(car -> car.getId().equalsIgnoreCase(carId))
                                .findFirst()
                                .orElse(null);

                        if (selectedCar == null) {
                            System.out.println("Invalid Car ID.");
                            break;
                        }

                        System.out.println("Enter start date and time (format: yyyy-MM-ddTHH:mm): ");
                        LocalDateTime rentFrom = LocalDateTime.parse(scanner.nextLine());
                        System.out.println("Enter end date and time (format: yyyy-MM-ddTHH:mm): ");
                        LocalDateTime rentTo = LocalDateTime.parse(scanner.nextLine());

                        long days = java.time.Duration.between(rentFrom, rentTo).toDays();
                        if (days <= 0) {
                            System.out.println("Invalid rental period.");
                            break;
                        }

                        int totalPrice = (int) (days * selectedCar.getPrice());
                        System.out.println("Total rental price: " + totalPrice + "֏");

                        System.out.print("Confirm rental? (yes/no): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            if (system.rentCar(carId, rentFrom, rentTo)) {
                                System.out.println("Car rented successfully!");
                            } else {
                                System.out.println("Car is not available for that time.");
                            }
                        } else {
                            System.out.println("Rental cancelled. Returning to main menu...");
                        }
                    } else {
                        System.out.println("Returning to main menu...");
                    }
                    break;
                case 2:
                    System.out.print("Enter brand: ");
                    String brand = scanner.nextLine();
                    filtered = system.filterByBrand(filtered,brand);
                    filtered.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Minimum seats: ");
                    int seats = scanner.nextInt();
                    filtered = system.filterBySeats(filtered,seats);
                    filtered.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Min price: ");
                    int min = scanner.nextInt();
                    System.out.print("max Price: ");
                    int max = scanner.nextInt();
                    filtered = system.filterByPriceRange(filtered, min, max);
                    filtered.forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Start date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("End date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime end = LocalDateTime.parse(scanner.nextLine());
                    filtered = system.filterByAvailability(filtered, start, end);
                    filtered.forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Enter Car ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Start date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime from = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("End date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime to = LocalDateTime.parse(scanner.nextLine());
                    if (system.rentCar(id, from, to)){
                        System.out.println("Car rented successfully!");
                    }else{
                        System.out.println("Car not available.");
                    }
                    break;
                case 0:
                    System.out.println("Bye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
