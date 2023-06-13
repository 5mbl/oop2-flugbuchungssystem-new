package FlightPack;

public enum Location {
    BERLIN("Berlin","src/main/resources/BerlinDateTimeSheet.csv",2.5),HAMBURG("Hamburg","src/main/resources/HamburgDateTimeSheet.csv",1.4),DRESDEN("Dresden","src/main/resources/DresdenDateTimeSheet.csv",1.1);

    private final String Name;
    private final String DataSheetPath;
    private final double PaymentFactor;

    Location(String Name,String DataSheetPath,double PaymentFactor) {
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
