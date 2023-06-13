package FlightPack;

public enum FlightModel {    //Enum, da sich die values nie ändern
    THIS_GUN_FOR_HIRE(101,"This Gun for Hire",
            "Sadistic killer-for-hire Raven (Alan Ladd) becomes enraged when his latest job is paid off in marked bills. Vowing to track down his double-crossing boss, nightclub executive Gates (Laird Cregar), Raven sits beside Gates' lovely new employee, Ellen (Veronica Lake), on a train out of town. Although Ellen is engaged to marry the police lieutenant (Robert Preston) who's hunting down Raven, she decides to try and set the misguided hit man straight as he hides from the cops and plots his revenge.",
            "src/main/resources/This_Gun_for_Hire_(1942)_poster.jpg",25.20),
    AVATAR_WAY_OF_WATER(102,"Avatar: Way of The Water",
            "Jake Sully and Ney'tiri have formed a family and are doing everything to stay together. However, they must leave their home and explore the regions of Pandora. When an ancient threat resurfaces, Jake must fight a difficult war against the humans.",
            "src/main/resources/Avatar2.jpg",50.25),
    ONCE_UPON_A_TIME_IN_THE_WEST(103,"Once Upon a time in the West",
            "There's a single piece of land around Flagstone with water on it, and rail baron Morton (Gabriele Ferzetti) aims to have it, knowing the new railroad will have to stop there. He sends his henchman Frank (Henry Fonda) to scare the land's owner, McBain (Frank Wolff), but Frank kills him instead and pins it on a known bandit, Cheyenne (Jason Robards). Meanwhile, a mysterious gunslinger with a score to settle (Charles Bronson) and McBain's new wife, Jill (Claudia Cardinale), arrive in town.",
            "src/main/resources/OnceUponATImeInTheWest.jpg",15.50);

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
            case 101 -> FlightModel.THIS_GUN_FOR_HIRE;
            case 102 -> FlightModel.AVATAR_WAY_OF_WATER;
            default -> FlightModel.ONCE_UPON_A_TIME_IN_THE_WEST;
        };
    }


    public double getPrice() {
        return price;
    }
}
