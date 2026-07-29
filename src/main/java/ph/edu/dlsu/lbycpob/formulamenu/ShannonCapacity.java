package ph.edu.dlsu.lbycpob.formulamenu;

public class ShannonCapacity implements IFormula{
    public ShannonCapacity() {

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
