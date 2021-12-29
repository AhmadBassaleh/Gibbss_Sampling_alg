import gurobi.*;
import java.util.Random;
import com.sun.source.tree.IfTree;
import gurobi.*;
import javax.sound.midi.Soundbank;
import java.util.Random;

public class IE402SolveExact {
   private IE402Paramaters paramaters;
    GRBEnv env ;
    GRBModel model;
    GRBVar[][] Y;
    GRBVar[][] X;
    public IE402SolveExact(IE402Paramaters parameters) throws GRBException {
            long ti= System.currentTimeMillis();

                this.paramaters=parameters;
            //Creating model and environment
                this.env = new GRBEnv("Senior_project");
                this.model= new GRBModel(env);
        CreateVariables();
        CreateConstraints();
        CreateObjectiveFunction();
        this.model.optimize();
        PrintValues();
        long tf= System.currentTimeMillis();
        System.out.println(tf-ti+" time");
    }


        public  void CreateVariables() throws GRBException {
            //Creating Yvr variables
            Y = new GRBVar[this.paramaters.Vn][this.paramaters.Rn];
            for (int v = 0; v < this.paramaters.Vn; v++) {
                for (int r = 0; r < this.paramaters.Rn; r++) {
                    Y[v][r] = model.addVar(0, 1, 0, GRB.BINARY, "Y" + v + r);
                }
            }
            // Creating Xsv variables
            X = new GRBVar[3][2];
            for (int s = 0; s < this.paramaters.Sn; s++) {
                for (int v = 0; v < this.paramaters.Vn; v++) {
                    X[s][v] = model.addVar(0, 1, 0, GRB.BINARY, "X" + s + v);
                }
            }
        }
    public void CreateObjectiveFunction() throws GRBException {
        // Creating objective function
        GRBLinExpr objective = new GRBLinExpr();
        for (int v = 0; v < this.paramaters.Vn; v++) {
            for (int r = 0; r < this.paramaters.Rn; r++) {
                objective.addTerm(this.paramaters.c[v][r], Y[v][r]);
            }
        }
        model.setObjective(objective, GRB.MINIMIZE);
    }
    public void CreateConstraints() throws GRBException {

//Weight constraints(2)
        for (int v = 0; v < this.paramaters.Vn; v++) {
            GRBLinExpr LHS = new GRBLinExpr();
            for (int s = 0; s < this.paramaters.Sn; s++) {
                LHS.addTerm(this.paramaters.w[s], X[s][v]);
            }
            model.addConstr(LHS, GRB.LESS_EQUAL, this.paramaters.W[v], "Const" + v);
        }
//Pallets constraints(3)
        for (int v = 0; v < this.paramaters.Vn; v++) {
            GRBLinExpr LHS1 = new GRBLinExpr();
            for (int s = 0; s < this.paramaters.Sn; s++) {
                LHS1.addTerm(this.paramaters.p[s], X[s][v]);
            }
            model.addConstr(LHS1, GRB.LESS_EQUAL, this.paramaters.P[v], "Const" + v);
        }

        //Each route used by one vehicle constraint(4)
        for (int r = 0; r < this.paramaters.Rn; r++) {
            GRBLinExpr LHS2 = new GRBLinExpr();
            for (int v = 0; v < this.paramaters.Vn; v++) {
                LHS2.addTerm(1, Y[v][r]);
            }
            model.addConstr(LHS2, GRB.LESS_EQUAL, 1, "Const" + r);
        }

        //Routes access (5)
        for (int v = 0; v < this.paramaters.Vn; v++) {
            for (int r = 0; r < this.paramaters.Rn; r++) {
                GRBLinExpr LHS3 = new GRBLinExpr();
                LHS3.addTerm(1, Y[v][r]);
                model.addConstr(LHS3, GRB.LESS_EQUAL, this.paramaters.k[v][r], "Constr" + r);
            }
        }

        //Shipment assignment constraint (6)
        for (int s = 0; s < this.paramaters.Sn; s++) {
            GRBLinExpr LHS4 = new GRBLinExpr();
            for (int v = 0; v < this.paramaters.Vn; v++) {
                LHS4.addTerm(1, X[s][v]);
            }
            model.addConstr(LHS4, GRB.EQUAL, 1, "ConstR" + s);
        }

        //Assigning vehicles to routes constraint (7)
        for (int v = 0; v < this.paramaters.Vn; v++) {
            for (int s = 0; s < this.paramaters.Sn; s++) {
                GRBLinExpr LHS5 = new GRBLinExpr();
                for (int r = 0; r < this.paramaters.Rn; r++) {
                    LHS5.addTerm(1, Y[v][r]);
                }
                model.addConstr(LHS5, GRB.GREATER_EQUAL, X[s][v], "Constraint" + v + s);
            }
        }
//Each route can be seized at most once (8)
        for (int v = 0; v < this.paramaters.Vn; v++) {
            GRBLinExpr LHS6 = new GRBLinExpr();
            for (int r = 0; r < this.paramaters.Rn; r++) {
                LHS6.addTerm(1, Y[v][r]);
            }
            model.addConstr(LHS6, GRB.LESS_EQUAL, 1, "Constraint" + v);
        }

//Connecting shipment, with vehicle, with route, with district (9)
        for (int s = 0; s < this.paramaters.Sn; s++) {
            for (int r = 0; r < this.paramaters.Rn; r++) {
                for (int v = 0; v < this.paramaters.Vn; v++) {
                    GRBLinExpr LHS7 = new GRBLinExpr();
                    GRBLinExpr LHSC = new GRBLinExpr();
                    LHS7.addTerm(1, X[s][v]);
                    LHS7.addTerm(1, Y[v][r]);
                    for (int d = 0; d < 4; d++) {
                       int Pars= (this.paramaters.a[s][d]*this.paramaters.q[r][d])+1;
                        LHSC.addConstant(Pars);
                    }
                    model.addConstr(LHS7, GRB.LESS_EQUAL,LHSC, "Constraint" + v + r);
                }
            }
        }


        //Time window constraint(10)

        for (int s = 0; s < this.paramaters.Sn; s++) {
            for (int d = 0; d < this.paramaters.Dn; d++) {
                GRBLinExpr LHS8 = new GRBLinExpr();
                for (int v = 0; v < this.paramaters.Vn; v++) {
                    int constant = this.paramaters.a[s][d] * this.paramaters.l[v][d];
                    LHS8.addTerm(constant, X[s][v]);
                }
                model.addConstr(LHS8, GRB.LESS_EQUAL, this.paramaters.t[s], "Constraints" + s + d);
            }
        }
    }
    private void PrintValues() throws GRBException {

        model.write("test.Model.lp");
                model.optimize();
                int status =model.get(GRB.IntAttr.Status);
                if (status== GRB.SUBOPTIMAL || status== GRB.OPTIMAL){
                    double objectivevalue= model.get(GRB.DoubleAttr.ObjVal);
                    System.out.println("Objective function is "+objectivevalue);
                    for (int s = 0; s <this.paramaters.Sn ; s++) {
                        for (int v = 0; v <this.paramaters.Vn ; v++) {
                            Double Xs =X[s][v].get(GRB.DoubleAttr.X);
                            System.out.println("X "+(s+1)+" "+(v+1)+" is "+Xs);
                        }
                    }
                    for (int v = 0; v <this.paramaters.Vn ; v++) {
                        for (int r = 0; r <this.paramaters.Rn ; r++) {
                            Double Ys =Y[v][r].get(GRB.DoubleAttr.X);
                            System.out.println("Y "+(v+1)+" "+(r+1)+" is "+Ys);

                        }
                    }
                }else{
                    System.out.println("infeasable");
                }
            }
        }
