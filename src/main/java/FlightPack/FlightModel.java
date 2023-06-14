package FlightPack;

public enum FlightModel {    //Enum, da sich die values nie ändern
    HWR_AIRLINES(1001,"HWR Airline",
            "Sadistic killer-for-hire Raven (Alan Ladd) becomes enraged when his latest job is paid off in marked bills. Vowing to track down his double-crossing boss, nightclub executive Gates (Laird Cregar), Raven sits beside Gates' lovely new employee, Ellen (Veronica Lake), on a train out of town. Although Ellen is engaged to marry the police lieutenant (Robert Preston) who's hunting down Raven, she decides to try and set the misguided hit man straight as he hides from the cops and plots his revenge.",
            "src/main/resources/HWR-Airlines-Logo.png",25.20),
    LUFTHANSA(1002,"Lufthansa",
            "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora. When an ancient threat resurfaces, Jake must fight a difficult war against the humans.",
            "src/main/resources/lufthansa-logo.png",50.25),
    RYANAIR(1003,"RyanAir",
            "There's a single piece of land around Flagstone with water on it, and rail baron Morton (Gabriele Ferzetti) aims to have it, knowing the new railroad will have to stop there. He sends his henchman Frank (Henry Fonda) to scare the land's owner, McBain (Frank Wolff), but Frank kills him instead and pins it on a known bandit, Cheyenne (Jason Robards). Meanwhile, a mysterious gunslinger with a score to settle (Charles Bronson) and McBain's new wife, Jill (Claudia Cardinale), arrive in town.",
            "src/main/resources/ryanair-logo.png",12.50);

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
