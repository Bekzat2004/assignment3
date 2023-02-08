import java.util.Scanner;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Automobile> automobiles = List.of(
                new Automobile(false, 1998, true),
                new Automobile(true, 2003, false)
        );
        List<Motortransport> motortransports = List.of(new Motortransport("12 km/h", 10000, 2));
        TransmissionBoxFilter transmission = new TransmissionBoxFilter();
        ReleaseYearFilter releaseYear = new ReleaseYearFilter();
        automobiles = transmission.filter(automobiles);
        System.out.println(automobiles);
        menu(input, automobiles, motortransports);
    }

    public static void menu(Scanner scanner, List<Automobile> automobiles, List<Motortransport> motortransports) {
        boolean isOpen = true;
        while (isOpen) {
            System.out.println("Choose an option by the number below:");
            System.out.println("1. Buy a car");
            System.out.println("2. Publish new car");
            System.out.println("3. Exit");
            switch (scanner.nextInt()) {
                case 1 -> buyCar(automobiles);
                case 2 -> publishCar(automobiles, motortransports);
                case 3 -> isOpen = false;
                default -> System.out.println("Pick a number between 1 and 3");
            }
        }
    }

    private static void buyCar(List<Automobile> automobiles) {
        Scanner scanner = new Scanner(System.in);
        TransmissionBoxFilter filterTransmission = new TransmissionBoxFilter();
        ConditionFilter filterCondition = new ConditionFilter();
        ReleaseYearFilter filterYear = new ReleaseYearFilter();
        PriceRangeFilter filterPrice = new PriceRangeFilter();
        VolumeOfEngineFilter filterVolume = new VolumeOfEngineFilter();
        ColorFilter filterColor = new ColorFilter();

        System.out.println("You should follow instructions to buy a car.");
        System.out.println("What type of transport do you want to buy?\n1 - Automobile\n2 - Mototransport");
        switch(scanner.nextInt()) {
            case 1:
                inputCondition(scanner, filterCondition);
                inputTransmission(scanner, filterTransmission);
                inputReleaseYearRange(scanner, filterYear);
                inputPriceRange(scanner, filterPrice);
                inputVolume(scanner, filterVolume);
                inputColor(scanner, filterColor);
                System.out.println(automobiles);
                for (Automobile automobile : automobiles){
                    System.out.println(automobile.toString());
                }
        }
    }

    private static void publishCar(List<Automobile> automobiles, List<Motortransport> motortransports) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You should follow instructions to publish a car.");
        System.out.println("What type of transport do you want to publish?\n1 - Automobile\n2 - Mototransport");
        switch(scanner.nextInt()) {
            case 1:
                System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
                boolean isNew = scanner.nextBoolean();
                System.out.println("Choose the transmission:\nTrue - mechanic\nFalse - automatic");
                boolean transmissionType = scanner.nextBoolean();
                System.out.println("Enter a year.");
                int year = scanner.nextInt();
                System.out.println("Enter a price.");
                int price = scanner.nextInt();
                System.out.println("Enter a color.");
                String color = scanner.nextLine();
                System.out.println("Enter a volume of engine.");
                double volume = scanner.nextDouble();
                Automobile automobile = new Automobile(color, price, volume);
                automobiles.add(automobile);

        }
    }

    private static void inputReleaseYearRange(Scanner scanner, ReleaseYearFilter filter) {
        System.out.println("Enter the range of release.");
        System.out.print("From year: ");
        filter.setFromYear(scanner.nextInt());
        System.out.print("To year: ");
        filter.setToYear(scanner.nextInt());
    }

    public static void inputTransmission(Scanner scanner, TransmissionBoxFilter filter) {
        System.out.println("Choose the transmission:\nTrue - mechanic\nFalse - automatic");
        filter.setAutomatic(scanner.nextBoolean());
    }

    public static void inputCondition(Scanner scanner, ConditionFilter filter) {
        System.out.println("Choose the condition of the car:\nTrue - new\nFalse - used");
        filter.setCondition(scanner.nextBoolean());
    }

    public static void inputColor(Scanner scanner, ColorFilter filter) {
        System.out.print("Write a color of car by lower case letters.");
        filter.setColor(scanner.nextLine());
    }

    public static void inputPriceRange(Scanner scanner, PriceRangeFilter filter) {
        System.out.println("Enter the range of price.");
        System.out.println("From minimum amount:");
        filter.setFrom(scanner.nextInt());
        System.out.println("To maximum amount:");
        filter.setTo(scanner.nextInt());
    }

    public static void inputVolume(Scanner scanner, VolumeOfEngineFilter filter) {
        System.out.println("Enter the volume of engine.");
        filter.setVolumeOfEngine(scanner.nextDouble());
    }
}

//топливо(электрокар инклудед) как отдельный класс, и буу\нью тоже как этаж