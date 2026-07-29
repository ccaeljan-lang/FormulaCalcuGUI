package ph.edu.dlsu.lbycpob.formulamenu;

public class ShannonCapacity implements IFormula {
    private final String[] parameterList = {"Capacity", "Bandwidth", "Signal Power", "Noise Power"};

    private double capacity;
    private double bandwidth;
    private double signalPower;
    private double noisePower;

    public ShannonCapacity() {
    }

    public double getCapacity() { return capacity; }
    public void setCapacity(double capacity) { this.capacity = capacity; }

    public double getBandwidth() { return bandwidth; }
    public void setBandwidth(double bandwidth) { this.bandwidth = bandwidth; }

    public double getSignalPower() { return signalPower; }
    public void setSignalPower(double signalPower) { this.signalPower = signalPower; }

    public double getNoisePower() { return noisePower; }
    public void setNoisePower(double noisePower) { this.noisePower = noisePower; }

    public void computeCapacity() {
        capacity = bandwidth * (Math.log(1 + (signalPower / noisePower)) / Math.log(2));
    }

    public void computeBandwidth() {
        bandwidth = capacity / (Math.log(1 + (signalPower / noisePower)) / Math.log(2));
    }

    public void computeSignalPower() {
        signalPower = noisePower * (Math.pow(2, capacity / bandwidth) - 1);
    }

    public void computeNoisePower() {
        noisePower = signalPower / (Math.pow(2, capacity / bandwidth) - 1);
    }

    @Override
    public double compute(String variable, String[] values) {
        if (variable == null) return 0;

        if (variable.equalsIgnoreCase("Capacity")) {
            bandwidth = Double.parseDouble(values[0]);
            signalPower = Double.parseDouble(values[1]);
            noisePower = Double.parseDouble(values[2]);
            computeCapacity();
            return capacity;
        } else if (variable.equalsIgnoreCase("Bandwidth")) {
            capacity = Double.parseDouble(values[0]);
            signalPower = Double.parseDouble(values[1]);
            noisePower = Double.parseDouble(values[2]);
            computeBandwidth();
            return bandwidth;
        } else if (variable.equalsIgnoreCase("Signal Power")) {
            capacity = Double.parseDouble(values[0]);
            bandwidth = Double.parseDouble(values[1]);
            noisePower = Double.parseDouble(values[2]);
            computeSignalPower();
            return signalPower;
        } else if (variable.equalsIgnoreCase("Noise Power")) {
            capacity = Double.parseDouble(values[0]);
            bandwidth = Double.parseDouble(values[1]);
            signalPower = Double.parseDouble(values[2]);
            computeNoisePower();
            return noisePower;
        }
        return 0;
    }

    @Override
    public String[] getParameterList() {
        return parameterList;
    }
}