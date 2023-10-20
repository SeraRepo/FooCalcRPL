import UI.CalcUI;
import obj.PileRPL;

public class FooCalcRPL {
    public static boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        try{
            CalcUI calcUI = new CalcUI();
            if (args.length == 0 ){
                PileRPL pile = new PileRPL();
                System.out.println(pile);
                calcUI.commandUI(pile);
            } else if (isStringInt(args[0]) == true || args.length > 1) {
                PileRPL pile = new PileRPL(Integer.parseInt(args[0]));
                System.out.println(pile);
                calcUI.commandUI(pile);
            } else {
                System.out.println("Wrong type or number of arguments. It should be a single positive integer.");
            }
            

        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
        }
    }
}  