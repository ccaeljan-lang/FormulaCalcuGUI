package ph.edu.dlsu.lbycpob.formulamenu;

public interface IFormula {
    public double compute(String variable, String [] values);
    public String[] getParameterList();
    public String[] getInputParameters(String variable);
}
