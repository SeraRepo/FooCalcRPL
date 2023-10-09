package obj;

public class ObjEmp {
    protected int interger_part;
    protected int imaginary_part;

    public ObjEmp(int integer, int imaginary) {
        this.interger_part = integer;
        this.imaginary_part = imaginary;
    }

    public String toString() {
        return Integer.toString(interger_part) + "+" +Integer.toString(imaginary_part) + "i";
    }
}
