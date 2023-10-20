package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import obj.PileRPL;

public class CalcUI {
    public void commandUI(PileRPL pile) throws IOException {
        String command;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("> ");
        command = input.readLine();
        while (command.length() > 0) {
            commandParser(command, pile);
            System.out.println(pile);
            System.out.print("> ");
            command = input.readLine();
        }
        input.close();
    }

    private static void commandParser(String cmd, PileRPL pile) {
        String[] cmdParsed;
        String type;
        cmdParsed = cmd.split(";");
        for (int i = 0; i < cmdParsed.length; i++) {
            type = regexChecker(cmdParsed[i]);
            switch (type) {
                case "sub":
                    // pile.sub();
                    System.out.println("Command sub received");
                    break;
                case "add":
                    // pile.add();
                    System.out.println("Command add received");
                    break;
                case "mult":
                    // pile.add();
                    System.out.println("Command mult received");
                    break;
                case "div":
                    // pile.add();
                    System.out.println("Command div received");
                    break;
                case "help":
                    // pile.add();
                    System.out.println("Command help received");
                    break;
                case "number":
                    // System.out.println("Number received: " + cmdParsed[i]);
                    pile.push(cmdParsed[i]);
                    break;
                default:
                    System.out.println("Command not found. Type \"help\" for help or \"quit\" to quit.");
            }
        }
    }

    private static String regexChecker(String command) {
        final Pattern patternAdd = Pattern.compile("add", Pattern.CASE_INSENSITIVE);
        final Matcher matcherAdd = patternAdd.matcher(command);
        final Pattern patternSub = Pattern.compile("sub", Pattern.CASE_INSENSITIVE);
        final Matcher matcherSub = patternSub.matcher(command);
        final Pattern patternMult = Pattern.compile("mult", Pattern.CASE_INSENSITIVE);
        final Matcher matcherMult = patternMult.matcher(command);
        final Pattern patternDiv = Pattern.compile("div", Pattern.CASE_INSENSITIVE);
        final Matcher matcherDiv = patternDiv.matcher(command);
        final Pattern patternHelp = Pattern.compile("help", Pattern.CASE_INSENSITIVE);
        final Matcher matcherHelp = patternHelp.matcher(command);
        final Matcher matcherNumber;
        boolean isNumberValid = false;
        if ((command.charAt(0) == '(') && (command.charAt(command.length() -1) == ')') ) {
            isNumberValid = checkIfVectorValid(command);
        } else {
            final Pattern patternNumber = Pattern.compile("^(?=[i.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)?(?![i.\\d]))?([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)?)?[i])?$", Pattern.CASE_INSENSITIVE);
            matcherNumber = patternNumber.matcher(command);
            isNumberValid = matcherNumber.matches();
        }
        

        if (isNumberValid) {
            return "number";
        } else if (matcherAdd.matches()) {
            return "add";
        } else if (matcherSub.matches()) {
            return "sub";
        } else if (matcherMult.matches()) {
            return "mult";
        } else if (matcherDiv.matches()) {
            return "div";
        } else if (matcherHelp.matches()) {
            return "help";
        } else {
            return "";
        }
    }

    private static boolean checkIfVectorValid(String vector) {
        String[] numbersStrings;
        vector = vector.replace("(", "");
        vector = vector.replace(")", "");
        numbersStrings = vector.split(",");

        for (int i = 0; i < numbersStrings.length; i++) {
            final Pattern patternNumber = Pattern.compile("^(?=[i.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)?(?![i.\\d]))?([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)?)?[i])?$", Pattern.CASE_INSENSITIVE);
            final Matcher matcherNumber = patternNumber.matcher(numbersStrings[i]);
            if (matcherNumber.matches() == false) {
                return false;
            }
        }
        return true;
    }
}
