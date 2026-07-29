package ph.edu.dlsu.lbycpob.formulamenu;

public class ShannonCapacity implements IFormula {
    private final String[] parameterList = {"Capacity", "Bandwidth", "Signal Power", "Noise Power"};

    private double capacity;
    private double bandwidth;
    private double signalPower;
    private double noisePower;

    public ShannonCapacity() {
    }
}