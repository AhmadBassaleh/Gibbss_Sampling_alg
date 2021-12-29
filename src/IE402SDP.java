import gurobi.GRBException;

public class IE402SDP {
    public static void main(String [] args) throws GRBException {

        IE402Paramaters parameters = new IE402Paramaters();
        IE402SolveExact Solver = new IE402SolveExact(parameters);

    }
}
