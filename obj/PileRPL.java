package obj;

public class PileRPL {
    final static int NB_MAX_OBJ = 5;
    protected ObjEmp[] pile;
    protected int nbMaxObj;
    protected int nbStack;

    public PileRPL() {
        this.nbMaxObj = NB_MAX_OBJ;
        this.pile = new ObjEmp[nbMaxObj];
        nbStack = 0;
    }

    public PileRPL(int nb) {
        this.nbMaxObj = nb;
        this.pile = new ObjEmp[nbMaxObj];
        nbStack = 0;
    }

    public void push(ObjEmp obj) {
        if( nbStack < NB_MAX_OBJ) {
            pile[nbStack] = obj;
            nbStack++;
        }
    }

    public void add() {
        if (nbStack > 1) {
            // pile[nbStack - 2].imaginary_part = pile[nbStack - 2].imaginary_part + pile[nbStack - 1].imaginary_part;
            // pile[nbStack - 2].interger_part = pile[nbStack - 2].interger_part + pile[nbStack - 1].interger_part;
            // pile[nbStack - 1] = null;
            nbStack--;
        } else {
            System.out.println("Not enough elements in stack.");
        }
    }

    public void sub() {
         if (nbStack > 1) {
            // pile[nbStack - 2].imaginary_part = pile[nbStack - 2].imaginary_part - pile[nbStack - 1].imaginary_part;
            // pile[nbStack - 2].interger_part = pile[nbStack - 2].interger_part - pile[nbStack - 1].interger_part;
            // pile[nbStack - 1] = null;
            nbStack--;
        } else {
            System.out.println("Not enough elements in stack.");
        }
    }

    public String toString() {
        String representationString = "--Stack Begin--\n";
        if (this.pile != null) {
            for (int i = pile.length -1; i >= 0; i--) {
                if (pile[i] == null) {
                    representationString += i + ":\n" ;
                } else {
                    representationString += i + ":" + pile[i].toString() + "\n";
                }
            }
        }
        
        representationString += "--Stack End--";
        return representationString;
    }
}
