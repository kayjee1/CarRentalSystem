package Core;

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
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
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

                        System.out.println("Enter start date and time (format: 2025-06-01T10:00): ");
                        LocalDateTime rentFrom = LocalDateTime.parse(scanner.nextLine());
                        System.out.println("Enter end date and time (format: 2025-07-01T10:00): ");
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

                    List<Car> matchingCars1 = system.getAllCars().stream()
                            .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                            .collect(Collectors.toList());

                    if (matchingCars1.isEmpty()) {
                        System.out.println("No cars found for this brand.");
                        break;
                    }
                    System.out.println("Available " + brand + " cars:");
                    for (Car car : matchingCars1) {
                        System.out.println("ID: " + car.getId() +
                                " | Price per day: " + car.getPrice() + "֏" +
                                " | Seats: " + car.getSeats());
                    }
                    System.out.print("Do you want to rent this car? (yes/no): ");
                    String rentChoice1 = scanner.nextLine();

                    if (rentChoice1.equalsIgnoreCase("yes")) {
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

                        System.out.println("Enter start date and time (format: 2025-06-01T10:00): ");
                        LocalDateTime rentFrom = LocalDateTime.parse(scanner.nextLine());
                        System.out.println("Enter end date and time (format: 2025-07-01T10:00): ");
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
                case 3:
                    System.out.print("Minimum seats: ");
                    int seats = scanner.nextInt();
                    List<Car> matchingCars2 = system.getAllCars().stream()
                            .filter(car -> car.getSeats() == seats)
                            .collect(Collectors.toList());
                    if (matchingCars2.isEmpty()) {
                        System.out.println("No cars found for this seats number.");
                        break;
                    }
                    System.out.println("Available cars with " + seats + " seats: ");
                    for (Car car : matchingCars2) {
                        System.out.println("ID: " + car.getId() + " | Brand: " + car.getBrand() +
                                " | Price per day: " + car.getPrice() + "֏" +
                                " | Seats: " + car.getSeats());
                    }
                    System.out.print("Do you want to rent this car? (yes/no): ");
                    String rentChoice2 = scanner.nextLine();

                    if (rentChoice2.equalsIgnoreCase("yes")) {
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

                        System.out.println("Enter start date and time (format: 2025-06-01T10:00): ");
                        LocalDateTime rentFrom = LocalDateTime.parse(scanner.nextLine());
                        System.out.println("Enter end date and time (format: 2025-07-01T10:00): ");
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
                case 4:
                    System.out.print("Min price: ");
                    int min = scanner.nextInt();
                    System.out.print("max Price: ");
                    int max = scanner.nextInt();
                    scanner.nextLine();
                    List<Car> priceFiltered = system.filterByPriceRange(system.getAllCars(), min, max);
                    if (priceFiltered.isEmpty()) {
                        System.out.println("No cars found in this price range.");
                        break;
                    }
                    System.out.println("Cars available between " + min + "֏ and " + max + "֏:");
                    for (Car car : priceFiltered) {
                        System.out.println("ID: " + car.getId() +
                                " | Brand: " + car.getBrand() +
                                " | Price per day: " + car.getPrice() + "֏" +
                                " | Seats: " + car.getSeats());
                    }
                    System.out.print("Do you want to rent this car? (yes/no): ");
                    String rentChoice3 = scanner.nextLine();

                    if (rentChoice3.equalsIgnoreCase("yes")) {
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

                        System.out.println("Enter start date and time (format: 2025-06-01T10:00): ");
                        LocalDateTime rentFrom = LocalDateTime.parse(scanner.nextLine());
                        System.out.println("Enter end date and time (format: 2025-07-01T10:00): ");
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
                case 5:
                    System.out.print("Start date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime start = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("End date and time (YYYY-MM-DD HH:mm): ");
                    LocalDateTime end = LocalDateTime.parse(scanner.nextLine());
                    List<Car> availableInRange = system.filterByAvailability(system.getAllCars(), start, end);

                    if (availableInRange.isEmpty()) {
                        System.out.println("No cars are available in that date/time range.");
                        break;
                    }
                    System.out.println("Available cars from " + start + " to " + end + ":");
                    for (Car car : availableInRange) {
                        System.out.println("ID: " + car.getId() +
                                " | Brand: " + car.getBrand() +
                                " | Price per day: " + car.getPrice() + "֏" +
                                " | Seats: " + car.getSeats());
                    }
                    System.out.print("Do you want to rent one of these cars? (yes/no): ");
                    String rentChoice4 = scanner.nextLine();

                    if (rentChoice4.equalsIgnoreCase("yes")) {
                        System.out.print("Enter Car ID: ");
                        String carId = scanner.nextLine();

                        Car selectedCar = availableInRange.stream()
                                .filter(car -> car.getId().equalsIgnoreCase(carId))
                                .findFirst()
                                .orElse(null);

                        if (selectedCar == null) {
                            System.out.println("Invalid Car ID.");
                            break;
                        }

                        long days = java.time.Duration.between(start, end).toDays();
                        if (days <= 0) {
                            System.out.println("Invalid rental period.");
                            break;
                        }

                        int totalPrice = (int) (days * selectedCar.getPrice());
                        System.out.println("Total rental price: " + totalPrice + "֏");

                        System.out.print("Confirm rental? (yes/no): ");
                        String confirm = scanner.nextLine();

                        if (confirm.equalsIgnoreCase("yes")) {
                            if (system.rentCar(carId, start, end)) {
                                System.out.println("Car rented successfully!");
                            } else {
                                System.out.println("Car is not available anymore for that time.");
                            }
                        } else {
                            System.out.println("Rental cancelled. Returning to main menu...");
                        }
                    } else {
                        System.out.println("Returning to main menu...");
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
