public class IE402Paramaters {
    public  int Vn;
    public  int Sn;
    public  int Rn;
    public  int Dn;
    public   int [] w ;   //Weight matrix of the shipments
    public int [] p ;   //Pallet requirement matrix for shipments
    public  int [] W ;   // Weight restriction matrix for vehicles
    public  int [] P ;   // Pallets restriction matrix for vehicles
    public  int [][] k ; // Matrix representing if a vehicle can take a route or not
    public int [][] q ; // Matrix representing if a route passes through a district or not
    public int [][] a ; // Matrix representing if a shipment must be sent to a certain district or not
    public int [][] l ; //Matrix representing lead time for a vehicle to a district
    public  int [] t ;   // Matrix representing the maximum delivery date for each shipment
    public int [][] c ; // Matrix representing the cost of sending a vehicle to a route

    public IE402Paramaters(){
        Createw();
        Createp();
        CreateW();
        CreateP();
        Createk();
        Createq();
        Createa();
        Createl();
        Createt();
        Createc();
        CreateVn();
        CreateSn();
        CreateRn();
        CreateDn();

    }
    private void CreateVn() {
        int Vn  = 2;
        this.Vn = Vn ;
    }
    private void CreateSn() {
        int Sn  = 3;
        this.Sn = Sn ;
    }
    private void CreateRn() {
        int Rn  = 3;
        this.Rn = Rn ;
    }
    private void CreateDn() {
        int Dn  = 4;
        this.Dn = Dn ;
    }

    private void Createw() {
        int [] w = {12,24,15};
        this.w =w ;
    }

    private void CreateW(){
        int [] W = {50,25};
         this.W=W;
    }
    private void Createp() {
        int [] p = {2,4,3};
        this.p =p ;
    }

    private void CreateP(){
        int [] P = {100,120};
        this.P=P;
    }
    private void Createk(){
        int [][] k = {
                     {1,0,1},
                     {0,1,1}
                              };
                     this.k=k;
                                 }
    private void Createq(){
        int [][] q = {  {1,0,0,0},
                        {1,0,1,0},
                        {0,1,1,1}
        };
        this.q=q;
    }
    private void Createa(){
        int [][] a = {{1, 0, 0, 0},
                      {0, 0, 0, 1},
                      {0, 1, 0, 0},
        };
        this.a=a;
    }
    private void Createl(){
        int [][] l = { {5, 2, 3, 4},
                       {2, 4, 1, 7},
        };
        this.l=l;
    }
    private void Createt(){
        int [] t = {5,4,4};
        this.t=t;
    }
    private void Createc(){
        int [][] c = {{785, 1200, 783},
                      {874, 2000, 3468},

        };
        this.c=c;
    }

}
