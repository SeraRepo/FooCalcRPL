package obj;

public class Number {
    protected double interger_part;
    protected double imaginary_part;

    public Number(String number) {
        double[] parsedNum = stringToNum(number);

        this.interger_part = parsedNum[0];
        this.imaginary_part = parsedNum[1];
    }

    private static double[] stringToNum(String num) {
        double real = 0;
        boolean realSign = true;
        double complexe = 0;
        boolean complexeSign = true;
        double[] number = new double[2];

        if (num.charAt(0) == '-') {
            realSign = false;
        }
        if (num.substring(1).contains("-")) {
            complexeSign =false;
        }

        String[] split = num.split("[+-]");
        if (split[0].contains("i"))
            complexe = Double.parseDouble((realSign ? "+" : "-") + split[0].substring(0,split[0].length() - 1));
        else
            real = Double.parseDouble((realSign ? "+" : "-") + split[0]);
        if (split.length > 1) {
            if (split[1].contains("i"))
                complexe = Double.parseDouble((complexeSign ? "+" : "-") + split[1].substring(0,split[1].length() - 1));
            else
                real = Double.parseDouble((complexeSign ? "+" : "-") + split[1]);
}
        
        number[0] =  real;
        number[1] = complexe;
        return number;
    }

    public String toString() {
        if (interger_part != 0 && imaginary_part != 0) {
            if (imaginary_part > 0) {
                return Double.toString(interger_part) + "+" + Double.toString(imaginary_part) + "i";
            } else {
                return Double.toString(interger_part) + Double.toString(imaginary_part) + "i";
            }
            
        } else if (interger_part == 0 && imaginary_part == 0) {
            return "0";
        } else if (interger_part == 0){
            return Double.toString(imaginary_part) + "i";
        } else {
            return Double.toString(interger_part);
        } 
    }
}
