package barakocoffee._Others_NOT_USED;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FullSymbolTable {

    static String currentLine;
    static String[] arr;
    static String[] variable = null;

    public static void main(String[] args) {

        try {
            File file = new File("src\\main\\java\\barakocoffee\\test.txt");// file here
            FileReader fread = new FileReader(file);
            BufferedReader bf = new BufferedReader(fread);
            System.out.println(
                    "Datatype \t ID \t Initial Value \t Return Type \t type of Parameters \t no. of parameters");
            while ((currentLine = bf.readLine()) != null) {
                if (currentLine.contains("int") && !currentLine.contains("(") && !currentLine.contains(")")
                        && !currentLine.contains("'") && !currentLine.contains(".")) {
                    ReadVariables();

                } else if (currentLine.contains("char") && !currentLine.contains("(") && !currentLine.contains(")")) {

                    ReadVariables();

                } else if (currentLine.contains("(") && currentLine.contains(")")) {
                    ReadFunction();
                } else if (currentLine.contains("float") && !currentLine.contains("(") && !currentLine.contains(")")
                        && !currentLine.contains("'")) {

                    ReadVariables();

                } else if (currentLine.contains("double") && !currentLine.contains("(") && !currentLine.contains(")")
                        && !currentLine.contains("'")) {

                    ReadVariables();

                } else if (currentLine.contains("boolean") && !currentLine.contains("(") && !currentLine.contains(")")
                        && !currentLine.contains("'")) {
                    ReadVariables();
                } // add
                else if (currentLine.contains("String") && !currentLine.contains("(") && !currentLine.contains(")")
                        && !currentLine.contains("'")) {
                    ReadVariables();
                }
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void ReadVariables() {
        arr = currentLine.split(" ");
        System.out.print("\n" + arr[0] + "\t\t ");
        for (int l = 1; l < arr.length; l++) {
            arr[l] = arr[l].replaceAll(",", " ");
            variable = arr[l].split(" ");
            for (int i = 0; i < variable.length; i++) {
                if (variable[i].contains("=") == true) {
                    String[] s = variable[i].split("=");

                    if (arr[0].contains("boolean")) {
                        System.out.print("boolean" + "\t\t");
                    } // add
                    if (arr[0].contains("String")) {
                        System.out.print("String" + "\t\t");
                    }

                    for (int j = 0; j < s.length; j++) {
                        System.out.print(s[j].replace(';', ' '));
                        System.out.print("\t");
                    }
                    System.out.print("\n" + "\t\t ");
                } else {
                    System.out.print(variable[i].replace(';', ' '));
                    System.out.print("\t ");
                    System.out.print(0);
                    System.out.println();
                    System.out.print("\t\t ");

                }
            }
        }
    }

    static void ReadFunction() {
        arr = currentLine.split(" ", 100);
        System.out.print("\n" + "\t\t");
        System.out.print(arr[1] + "\t\t\t ");
        System.out.print(arr[0] + "\t\t ");
        int intcounter = 0;
        int floatcounter = 0;
        int charcounter = 0;
        int doublecounter = 0;
        int booleancounter = 0;// add
        int Stringcounter = 0;
        String[] fin = new String[arr.length];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i].replace("(", "");
            fin[i] = arr[i].replace(",", " ");
            if (fin[i].contains("int")) {
                intcounter++;
                System.out.print("int,");
                // System.out.print("yolo" + intcounter);
            }
            if (fin[i].contains("float")) {
                floatcounter++;
                System.out.print("float,");
            }
            if (fin[i].contains("char")) {
                charcounter++;
                System.out.print("char,");
                // System.out.println("yolo");
            }
            if (fin[i].contains("double")) {
                doublecounter++;
                System.out.print("double,");
            }
            if (fin[i].contains("boolean")) {
                booleancounter++;// add
                System.out.print("boolean,");
            }
            if (fin[i].contains("String")) {
                Stringcounter++;
                System.out.print("String,");
            }
        }
        System.out.print("\t\t\t");
        int total = intcounter + floatcounter + charcounter + doublecounter;
        System.out.print(total); // displays the total no. of parameters

    }
}