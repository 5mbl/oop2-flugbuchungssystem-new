package FlightPack;

public enum FlightModel {    //Enum, da sich die values nie ändern
    HWR_AIRLINES(1001,"HWR Airline",
            "HWR Airways: Your dedicated airline for HWR students. We specialize in providing convenient and reliable transportation, connecting you to your academic destination with ease and comfort. Fly with HWR Airways for a seamless student travel experience.",
            "src/main/resources/HWR-Airlines-Logo.png",125.20),
    LUFTHANSA(1002,"Lufthansa",
            "Lufthansa: A premium German airline, synonymous with exceptional service, cutting-edge technology, and a global network of destinations. Experience the epitome of luxury and reliability in air travel with Lufthansa.",
            "src/main/resources/lufthansa-logo.png",150.25),
    RYANAIR(1003,"RyanAir",
            "Ryanair: Europe's leading low-cost airline, offering affordable fares, extensive network, and no-frills service. Fly with us for unbeatable value and convenient travel across the continent",
            "src/main/resources/ryanair-logo.png",80.50);

    //Hier können Filme hinzugefügt werden

    private final int ID;
    private final String name;
    private final String description;
    private final String imagePath;
    private final double price;

    FlightModel(int ID, String name, String description, String imagePath, double price) { //assigned all die Values zu den Enums
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
        this.price = price;
    }

    //Getter Methoden um auf die Values zuzugreifen

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getImagePath() {
        return imagePath;
    }

    //Methode um mit der ID einen Enum zu kriegen

    public static FlightModel getEnum(int ID) {
        return switch (ID) {
            case 101 -> FlightModel.HWR_AIRLINES;
            case 102 -> FlightModel.LUFTHANSA;
            default -> FlightModel.RYANAIR;
        };
    }


    public double getPrice() {
        return price;
    }
}

/*
public enum FlightModel {
    AIRLINE_1(1001,"NAME ...", "BESCHREIBUNG ...", int PREIS),
    AIRLINE_2(1002,"NAME ...", "BESCHREIBUNG ...", int PREIS),
    AIRLINE_3(1003,"NAME ...", "BESCHREIBUNG ...", int PREIS),
 */
