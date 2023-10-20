package obj;

public class ObjEmp {
    protected Number[] numbers;
    protected int dims;

    public ObjEmp(String nums) {
        String[] parsedNums = parseNums(nums);
        this.dims = parsedNums.length;
        this.numbers = new Number[parsedNums.length];

        for (int i = 0; i < parsedNums.length; i++) {
            Number number = new Number(parsedNums[i]);
            numbers[i] = number;
        }
    }

    private static String[] parseNums(String nums) {
        String[] numbersStrings;
        nums = nums.replace("(", "");
        nums = nums.replace(")", "");
        numbersStrings = nums.split(",");
        return numbersStrings;
    }

    public String toString() {
        String obj = "(";

        for (int i = 0; i < numbers.length; i++) {
            obj += numbers[i].toString();
            obj += ",";
        }
        obj += ")";

        return obj;
    }
}
