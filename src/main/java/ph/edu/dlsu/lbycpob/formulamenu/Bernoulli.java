package ph.edu.dlsu.lbycpob.formulamenu;

public class Bernoulli implements IFormula{

    private final String[] parameterList = {
            "P1",
            "P2",
            "Density",
            "V1",
            "V2",
            "H1",
            "H2"
    };

    private double p1;
    private double p2;
    private double density;
    private double v1;
    private double v2;
    private double h1;
    private double h2;

    private final double g = 9.81;

    public Bernoulli() {
    }

    // MATH COMPUTATION METHODS

    public void computeP1() {
        p1 = p2
                + 0.5 * density * v2 * v2
                + density * g * h2
                - 0.5 * density * v1 * v1
                - density * g * h1;
    }

    public void computeP2() {
        p2 = p1
                + 0.5 * density * v1 * v1
                + density * g * h1
                - 0.5 * density * v2 * v2
                - density * g * h2;
    }

    public void computeDensity() {
        double denominator = 0.5 * (v2 * v2 - v1 * v1) + g * (h2 - h1);
        if (denominator != 0) {
            density = (p1 - p2) / denominator;
        } else {
            density = 0;
        }
    }

    public void computeV1() {
        double valueInsideSqrt = (2 * (p2 - p1) / density) + (v2 * v2) + (2 * g * (h2 - h1));
        v1 = Math.sqrt(Math.max(0, valueInsideSqrt));
    }

    public void computeV2() {
        double valueInsideSqrt = (2 * (p1 - p2) / density) + (v1 * v1) + (2 * g * (h1 - h2));
        v2 = Math.sqrt(Math.max(0, valueInsideSqrt));
    }

    public void computeH1() {
        h1 = ((p2 - p1) / (density * g)) + ((v2 * v2 - v1 * v1) / (2 * g)) + h2;
    }

    public void computeH2() {
        h2 = ((p1 - p2) / (density * g)) + ((v1 * v1 - v2 * v2) / (2 * g)) + h1;
    }

    @Override
    public double compute(String variable, String[] values) {
        return 0.0;
    }

    @Override
    public String[] getParameterList() {
        return new String[]{""};
    }

    @Override
    public String[] getInputParameters(String variable) {
        return new String[]{""};
    }
}
