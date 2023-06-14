package FlightPack;

public enum Destination {
    BERLIN("Berlin","src/main/resources/BerlinerFlughafenDateTimeSheet.csv",2.5),HAMBURG("Hamburg","src/main/resources/HamburgFlughafenDateTimeSheet.csv",1.4),DRESDEN("Dresden","src/main/resources/DresdenFlughafenDateTimeSheet.csv",1.1);

    private final String Name;
    private final String DataSheetPath;
    private final double PaymentFactor;

    Destination(String Name, String DataSheetPath, double PaymentFactor) {
        this.Name = Name;
        this.DataSheetPath = DataSheetPath;
        this.PaymentFactor = PaymentFactor;
    }

    public String getName() {
        return Name;
    }

    public String getDataSheetPath() {
        return DataSheetPath;
    }

    public double getPaymentFactor() {
        return PaymentFactor;
    }
}
