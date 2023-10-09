package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import obj.ObjEmp;
import obj.PileRPL;

public class CalcUI {
    public void commandParser(PileRPL pile) throws IOException {
        String command;
        String commandType;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        command = input.readLine();
        while (command.length() > 0) {
            commandType = regexChecker(command);
            switch (commandType) {
                case "sub":
                    pile.sub();
                    // System.out.println("Command sub received");
                    break;
                case "add":
                    pile.add();
                    // System.out.println("Command add received");
                    break;
                case "complexe":
                    pushComplexe(command, pile);
                    break;
                case "int":
                    pushInt(command);
                    break;
                case "double":
                    pushDouble(command);
                    break;
                default:
                    System.out.println("Command not found.");

            }
            System.out.println(pile);
            System.out.print("> ");
            command = input.readLine();
        }
        input.close();
    }

    private String regexChecker(String command) {
        final Pattern patternComplexe = Pattern.compile("[0-9]+,[0-9]*", Pattern.CASE_INSENSITIVE);
        final Matcher matcherComplexe = patternComplexe.matcher(command);
        final Pattern patternAdd = Pattern.compile("add", Pattern.CASE_INSENSITIVE);
        final Matcher matcherAdd = patternAdd.matcher(command);
        final Pattern patternSub = Pattern.compile("sub", Pattern.CASE_INSENSITIVE);
        final Matcher matchersub = patternSub.matcher(command);
        final Pattern patternInt = Pattern.compile("-?[0-9]+", Pattern.CASE_INSENSITIVE);
        final Matcher matcherInt = patternInt.matcher(command);
        final Pattern patternFloat = Pattern.compile("[0-9]+\\.[0-9]*", Pattern.CASE_INSENSITIVE);
        final Matcher matcherFloat = patternFloat.matcher(command);

        if (matcherComplexe.matches()) {
            return "complexe";
        } else if (matcherAdd.matches()) {
            return "add";
        } else if (matchersub.matches()) {
            return "sub";
        } else if (matcherInt.matches()) {
            return "int";
        } else if (matcherFloat.matches()) {
            return "double";
        } else {
            return "";
        }

    }

    private void pushComplexe(String command, PileRPL pile) {
        int interger_part = 0;
        int imaginary_part = 0;
        String[] values = command.split(",");
        interger_part = Integer.parseInt(values[0]);
        imaginary_part = Integer.parseInt(values[1]);

        ObjEmp obj = new ObjEmp(interger_part, imaginary_part);
        pile.push(obj);
        // System.out.println("Command push complexe received: " + interger_part + " + " + imaginary_part + "i");

    }

    private void pushInt(String command) {
        StringTokenizer st;
        int integer = 0;
        st = new StringTokenizer(command);
        while (st.hasMoreTokens()) {
            integer += Integer.parseInt( st.nextToken() );
        }
        // pile.push(integer);
        System.out.println("Command push integer received: " + integer);
    }

    private void pushDouble(String command) {
        StringTokenizer st;
        double number = 0;
        st = new StringTokenizer(command);
        while (st.hasMoreTokens()) {
            number += Double.parseDouble( st.nextToken() );
        }
        // pile.push(null);
        System.out.println("Command push double received: " + number);
    }
    
}
