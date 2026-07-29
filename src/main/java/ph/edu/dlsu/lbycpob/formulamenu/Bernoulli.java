package ph.edu.dlsu.lbycpob.formulamenu;

public class Bernoulli implements IFormula{
    public Bernoulli() {
    }

    private final String[] parameterList = {
            "P1",
            "P2",
            "Density",
            "V1",
            "V2",
            "H1",
            "H2"
    };

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
