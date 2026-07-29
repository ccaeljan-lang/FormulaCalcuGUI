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

    // --- IFormula Interface Methods ---

    @Override
    public double compute(String variable, String[] values) {
        if (variable == null || values == null || values.length < 6) {
            return 0;
        }

        try {
            if (variable.equalsIgnoreCase("P1")) {
                p2 = Double.parseDouble(values[0]);
                density = Double.parseDouble(values[1]);
                v1 = Double.parseDouble(values[2]);
                v2 = Double.parseDouble(values[3]);
                h1 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeP1();
                return p1;

            } else if (variable.equalsIgnoreCase("P2")) {
                p1 = Double.parseDouble(values[0]);
                density = Double.parseDouble(values[1]);
                v1 = Double.parseDouble(values[2]);
                v2 = Double.parseDouble(values[3]);
                h1 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeP2();
                return p2;

            } else if (variable.equalsIgnoreCase("Density")) {
                p1 = Double.parseDouble(values[0]);
                p2 = Double.parseDouble(values[1]);
                v1 = Double.parseDouble(values[2]);
                v2 = Double.parseDouble(values[3]);
                h1 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeDensity();
                return density;

            } else if (variable.equalsIgnoreCase("V1")) {
                p1 = Double.parseDouble(values[0]);
                p2 = Double.parseDouble(values[1]);
                density = Double.parseDouble(values[2]);
                v2 = Double.parseDouble(values[3]);
                h1 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeV1();
                return v1;

            } else if (variable.equalsIgnoreCase("V2")) {
                p1 = Double.parseDouble(values[0]);
                p2 = Double.parseDouble(values[1]);
                density = Double.parseDouble(values[2]);
                v1 = Double.parseDouble(values[3]);
                h1 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeV2();
                return v2;

            } else if (variable.equalsIgnoreCase("H1")) {
                p1 = Double.parseDouble(values[0]);
                p2 = Double.parseDouble(values[1]);
                density = Double.parseDouble(values[2]);
                v1 = Double.parseDouble(values[3]);
                v2 = Double.parseDouble(values[4]);
                h2 = Double.parseDouble(values[5]);
                computeH1();
                return h1;

            } else if (variable.equalsIgnoreCase("H2")) {
                p1 = Double.parseDouble(values[0]);
                p2 = Double.parseDouble(values[1]);
                density = Double.parseDouble(values[2]);
                v1 = Double.parseDouble(values[3]);
                v2 = Double.parseDouble(values[4]);
                h1 = Double.parseDouble(values[5]);
                computeH2();
                return h2;
            }
        } catch (NumberFormatException e) {
            return 0.0;
        }

        return 0.0;
    }

    @Override
    public String[] getParameterList() {
        return parameterList;
    }

    @Override
    public String[] getInputParameters(String variable) {
        if (variable == null) return new String[0];

        String[] inputs = new String[6];
        int index = 0;

        for (String param : parameterList) {
            if (!param.equalsIgnoreCase(variable)) {
                if (index < 6) {
                    inputs[index] = param;
                    index++;
                }
            }
        }
        return inputs;
    }
}
