package barakocoffee.fileHandling.src;

//import java.io.File;
//import java.io.FileNotFoundException;   
import java.io.*;
import java.util.Scanner;

public class FileHandling {

    public static void OpenFile(String myFile) throws FileNotFoundException {
        File myObj = new File(myFile);
        try {
            Scanner myReader = new Scanner(myObj);
            System.out.println("-------------------------- FILE CONTENT --------------------------------\n");
            System.out.println("------------------------------------------------------------------------\n");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                System.out.println(" \n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found\n");
        }
    }

    public static void CreateFile(String newFile) {
        try {
            File myobj = new File(newFile);
            if (myobj.createNewFile()) {
                System.out.println("File Created: " + myobj.getName());
            } else {
                System.out.println("File Already exists!");
            }
        } catch (IOException e) {
            System.out.println("An error occuured");
        }
    }

    public static void WriteFile(String FileName, String code) {
        try {
            FileWriter myobj = new FileWriter(FileName);
            myobj.write(code);
            myobj.close();

            System.out.println("Encoded Successfully!");

        } catch (IOException e) {
            System.out.println("An error occuured");
        }

    }

    // public static void DeleteFile(String FileName)
    // {
    //
    // File myobj = new File(FileName);
    // if(myobj.delete()){
    // System.out.println("File Deleted Successfully!");
    // }
    // else {
    // System.out.println("Failed to Delete the File!");
    // }
    //
    // }
    public static void validateExtension(String FileName) {
        // get the file extension
        String fileExt = FileName.substring(FileName.lastIndexOf(".") + 1);
        // if the file extension is .txt or .doc, return true
        if (fileExt.equals("bc") || fileExt.equals("BC")) {
            System.out.println("Valid File type");
        } else {
            System.out.println("Invalid File type");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String Ans;

        do {
            System.out.println("\n______BARAKO COFFEE_______ \n");
            System.out.println(" [C] CREATE AND WRITE CODE ");
            System.out.println(" [R] READ ");
            System.out.println(" [E] EXIT ");
            System.out.print(" Enter your Choice: ");
            String Choice = sc.nextLine();
            switch (Choice) {

                case "C":
                case "c": {

                    System.out.print(" \nCreate New File: ");
                    String newFile = sc.next();
                    // Validate the file extension of the file name
                    if (!newFile.endsWith(".bc")) {
                        System.out.println("\nError: Invalid file extension. File must be a .bc extension.\n");
                    } else {
                        CreateFile(newFile);
                        System.out.print(" \nEnter Code: ");
                        sc.nextLine();
                        String code = sc.nextLine();
                        WriteFile(newFile, code);
                        System.exit(0);
                    }

                }
                    // System.out.print("Choose file to delete: ");
                    // String delFile = sc.next();
                    // DeleteFile(delFile);

                    break;
                case "R":
                case "r": {
                    Scanner cs = new Scanner(System.in);
                    System.out.print(" \nChoose File to Read: ");
                    String myFile = cs.next();
                    if (!myFile.endsWith(".bc")) {
                        System.out.println("\nError: Invalid file extension. File must be a .bc extension.\n");
                        System.exit(0);
                    } else {
                        OpenFile(myFile);
                        System.out.println(" \n");
                        System.exit(0);
                    }
                }
                    break;
                case "E": {
                    System.out.println(" \nTHANK YOU FOR USING BARAKO COFFEE\n ");
                    System.exit(0);
                }
                default:
                    System.out.println("\n WARNING: INVALID INPUT \n");
            }
            System.out.print("\n");
            Ans = sc.nextLine();
        } while (!Ans.equals("N"));
    }
}