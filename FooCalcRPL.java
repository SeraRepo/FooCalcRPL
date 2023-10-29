import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import UI.CalcUI;
import obj.PileRPL;

public class FooCalcRPL {
    private static boolean isStringInt(String s) {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }

    private static void printErrorMessage() {
        System.out.println("Wrong type or number of arguments. It should be -L for local mode or -R for remote mode.");
    }

    public static void main(String[] args) throws Exception {
        try{
            String pileHeight;
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            if (args.length == 1 ){
                if (args[0] ==  "-L") {
                    Boolean logged = true;
                    CalcUI calcUI = new CalcUI(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
                    System.out.print("Enter the height of the pile you want (default 5)\n> ");
                    pileHeight = input.readLine();

                    if (isStringInt(pileHeight) ==  false && pileHeight != "") {
                        System.out.println("You must enter a valid number for the pile height.");
                    }

                    if (isStringInt(pileHeight) == true) {
                        PileRPL pile = new PileRPL(Integer.parseInt(pileHeight));
                        System.out.println(pile);
                        calcUI.commandUI(pile, logged);
                    } else {
                        PileRPL pile = new PileRPL();
                        System.out.println(pile);
                        calcUI.commandUI(pile, logged);
                    }

                } else if (args[0] == "-R") {
                    try {
                    ServerSocket serverSocket = new ServerSocket(12345); // Port à utiliser
                    System.out.println("Server is running on port 12345...");

                    while (true) {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client connected.");

                        ObjectInputStream inputFromClient = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream outputToClient = new ObjectOutputStream(clientSocket.getOutputStream());

                        CalcUI calcUI = new CalcUI(new InputStreamReader(inputFromClient), new OutputStreamWriter(outputToClient));
                        Boolean logged = true;
                        PileRPL pile = new PileRPL();
                        calcUI.commandUI(pile, logged);

                        clientSocket.close();
                        System.out.println("Client disconnected.");
                    }
                } catch (IOException e) {
                    System.out.println("Error in server: " + e.getMessage());
                }
                } else {
                    printErrorMessage();
                }

            } else {
                printErrorMessage();
            }
            

        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
        }
    }
}  