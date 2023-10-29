package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import obj.PileRPL;

public class CalcUI {
    private BufferedReader inputReader;
    private PrintWriter outputWriter;

    public CalcUI(Reader input, Writer output) {
        this.inputReader = new BufferedReader(input);
        this.outputWriter = new PrintWriter(output);
    }

    public void commandUI(PileRPL pile, boolean logged) throws IOException {
        outputWriter.print("> ");
        String command;
        command = inputReader.readLine();
        while (command != null && command.length() > 0) {
            if (logged) {
                try {
                    printToFile(command);
                } catch (IOException e) {
                    outputWriter.println(e);
                }
            }
            commandParser(command, pile);
            outputWriter.println(pile);
            outputWriter.flush();
            outputWriter.print("> ");
            command = inputReader.readLine();
        }
    }

    private void replayLog(PileRPL pile, boolean b, BufferedReader lecteur) throws IOException {
        String command;
        command = lecteur.readLine();
        while (command != null && command.length() > 0) {
            commandParser(command, pile);
            outputWriter.println(pile);
            outputWriter.flush();
            outputWriter.print("> ");
            command = inputReader.readLine();
        }
    }

    private static void printToFile(String command) throws IOException {
        PrintWriter logCommand;
        logCommand = new PrintWriter(new BufferedWriter(new FileWriter("log.txt")));
        logCommand.close();
    }

    private void commandParser(String cmd, PileRPL pile) throws IOException {
        String[] cmdParsed;
        String type;
        cmdParsed = cmd.split(";");
        for (int i = 0; i < cmdParsed.length; i++) {
            type = regexChecker(cmdParsed[i]);
            switch (type) {
                case "sub":
                    pile.sub();
                    // outputWriter.println("Command sub received");
                    break;
                case "add":
                    pile.add();
                    // outputWriter.println("Command add received");
                    break;
                case "mult":
                    pile.mult();
                    // outputWriter.println("Command mult received");
                    break;
                case "div":
                    pile.div();
                    // outputWriter.println("Command div received");
                    break;
                case "replay":
                    BufferedReader lecteur = null;
                    try {
                        lecteur = new BufferedReader( new FileReader("log.txt" ) );
                        replayLog(pile, false, lecteur);
                    } catch( FileNotFoundException exc ) {
                        outputWriter.println( "No log file present." );
                    }
                case "help":
                    // pile.add();
                    outputWriter.println("Command help received");
                    break;
                case "number":
                    pile.push(cmdParsed[i]);
                    // outputWriter.println("Number received: " + cmdParsed[i]);
                    break;
                default:
                    outputWriter.println("Command not found. Type \"help\" for help or \"quit\" to quit.");
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
        final Pattern patternReplay = Pattern.compile("replay", Pattern.CASE_INSENSITIVE);
        final Matcher matcherReplay = patternReplay.matcher(command);
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
        } else if (matcherReplay.matches()) {
            return "replay";
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
