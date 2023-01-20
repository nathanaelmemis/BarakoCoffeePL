package barakocoffee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import barakocoffee.LexicalAnalyzer.LexicalAnalyzer;
import barakocoffee.LexicalAnalyzer.SymbolTable;

public class BarakoCoffee {
    public static void main(String[] args) throws IOException {
        BarakoCoffee barakoCoffee = new BarakoCoffee();
        
        if (args.length == 0) {
            barakoCoffee.commandLineCompile(new String[]{"samplecode.bc", "test.txt"});
            return;
        }

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while(!input.matches("e|E")) {
            barakoCoffee.printMenu();
            input = scanner.nextLine();
            switch (input) {
                case "c":
                case "C":
                    barakoCoffee.compile(scanner);
                    break;
                case "r":
                case "R":
                    barakoCoffee.read(scanner);
                    break;
                case "e":
                case "E":
                    System.out.println("\n THANK YOU FOR USING BARAKO COFFEE");
                    scanner.close();
                    break;
                default:
                    System.out.println("\n WARNING: INVALID INPUT \n");
            }
            System.out.print("\n");
        }
    }

    private void printMenu() {
        System.out.println("\n _______BARAKO COFFEE_______ \n");
        System.out.println(" [C] COMPILE ");
        System.out.println(" [R] READ ");
        System.out.println(" [E] EXIT \n");
        System.out.print(" Enter your Choice: ");
    }

    enum FileState {
        EXIST,
        NOT_EXIST,
        NOT_GIVEN
    }

    enum FileType {
        INPUT,
        OUTPUT
    }

    private boolean checkFile (String file, FileType fileType, FileState fileState) {
        switch(fileState) {
            case EXIST:
                if (new File(file).exists()) {
                    System.out.println(" Error: The " + fileType.toString().toLowerCase() + " file already exist!");
                    return true;
                }
                break;
            case NOT_EXIST:
                if (!new File(file).exists()) {
                    System.out.println(" Error: The " + fileType.toString().toLowerCase() + " file doesn't exist!");
                    return true;
                }
                break;
            case NOT_GIVEN:
                if (file.isEmpty()) {
                    System.out.println(" Error: The " + fileType.toString().toLowerCase() + " file name is missing!");
                    return true;
                }
                break;
            default:
                return false;
        }

        return false;
    }

    private void commandLineCompile(String[] args) throws IOException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();;
        SymbolTable symbolTable;
        
        if (checkFile(args[0], FileType.INPUT, FileState.NOT_EXIST)) {
            return;
        }
        if (!args[0].endsWith(".bc")) {
            System.out.println("\nError: Invalid file extension. File must have a .bc extension.\n");
            return;
        }
        if (checkFile(args[1], FileType.OUTPUT, FileState.NOT_GIVEN)) {
            return;
        }
        if (checkFile(args[1], FileType.OUTPUT, FileState.EXIST)) {
            return;
        }

        symbolTable = lexicalAnalyzer.scan(args[0]);
        symbolTable.printLexemes(args[1], false);
        symbolTable.printSymbolTable(args[1], true);

        System.out.println("Compilation successful!");
    }

    private void compile(Scanner scanner) throws IOException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();;
        SymbolTable symbolTable;

        System.out.print("\n Enter BarakoCoffee code file name: ");
        String input = scanner.nextLine();
        if (checkFile(input, FileType.INPUT, FileState.NOT_EXIST)) {
            return;
        }
        if (!input.endsWith(".bc")) {
            System.out.print(" Error: Invalid file extension. File must have a .bc extension.");
            return;
        }
        System.out.print("\n Enter output file name: ");
        String output = scanner.nextLine();
        if (checkFile(output, FileType.OUTPUT, FileState.EXIST)) {
            return;
        }

        symbolTable = lexicalAnalyzer.scan(input);
        symbolTable.printLexemes(output, false);
        symbolTable.printSymbolTable(output, true);

        System.out.print("\n Compilation successful!");
    }

    private void read(Scanner scanner) throws FileNotFoundException {
        System.out.print("\n Enter BarakoCoffee code file name: ");
        String input = scanner.nextLine();
        if (checkFile(input, FileType.INPUT, FileState.NOT_EXIST)) {
            return;
        }
        if (!input.endsWith(".bc")) {
            System.out.print(" Error: Invalid file extension. File must have a .bc extension.");
            return;
        }

        Scanner filScanner = new Scanner(new File(input));
        System.out.println("\n -------------------------- FILE CONTENT --------------------------------");
        for (int i = 1; filScanner.hasNextLine(); i++) {
            String data = filScanner.nextLine();
            System.out.println(" " + i + ": " + data);
        }
        System.out.print(" ------------------------------------------------------------------------");
    }
}