//import java.io.File;
//import java.io.FileNotFoundException;   
import java.io.*;
import java.util.Scanner;

public class FileHandling {

    public static void OpenFile(String myFile) throws FileNotFoundException
    {
        File myObj = new File(myFile);
        try{
            Scanner myReader = new Scanner(myObj);
            System.out.println("======= File COntent ====");
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
    }
    
    public static void CreateFile(String newFile)
    {
        try{
            File myobj = new File(newFile);
            if (myobj.createNewFile()){
                System.out.println("File Created: "+myobj.getName());
            }
            else {
                System.out.println("File Already exists!");
            }
        }catch(IOException e)
        {
            System.out.println("An error occuured");
        }
    }
    
     public static void WriteFile(String FileName, String code)
     {
         try{
             FileWriter myobj = new FileWriter(FileName);
                 myobj.write(code);
                 myobj.close();
             
             System.out.println("Encoded Successfully!");
             
         }catch(IOException e)
        {
            System.out.println("An error occuured");
        }
         
     }
     
//      public static void DeleteFile(String FileName)
//     {
//         
//             File myobj = new File(FileName);
//             if(myobj.delete()){
//                 System.out.println("File Deleted Successfully!");
//             }
//             else {
//                 System.out.println("Failed to Delete the File!");
//             }
//             
//         }
    public static void validateExtension(String FileName) {
        // get the file extension
        String fileExt = FileName.substring(FileName.lastIndexOf(".") + 1);
        // if the file extension is .txt or .doc, return true
        if (fileExt.equals("bc") || fileExt.equals("BC")) {
            System.out.println("Valid File type");
        }else{
            System.out.println("Invalid File type");
        }
    }     
     
    
    
    public static void main(String[] args) throws FileNotFoundException {
          Scanner sc = new Scanner(System.in);
          String Ans;
          
           do
            { 
                System.out.println(" BARAKO COFFEE ");
                System.out.println(" [C] CREATE AND WRITE CODE ");
                System.out.println(" [R] READ ");
                System.out.println(" [E] EXIT ");      
                System.out.print(" Enter your Choice: ");
                String Choice = sc.nextLine();
                switch(Choice)
                {

                case "C" :
                case "c" :{

                  System.out.print("create New File: ");
                  String newFile = sc.next();
                  // Validate the file extension of the file name
                        if(!newFile.endsWith(".bc")) {
                                System.out.println("Error: Invalid file extension. File must be a .bc extension.");
                        }
                        else{
                            CreateFile(newFile);
                            System.out.print("Enter Code: ");
                            sc.nextLine();
                            String code = sc.nextLine();
                            WriteFile(newFile, code);
                            System.exit(0);
                        }

                  
                }
        //          System.out.print("Choose file to delete: ");
        //          String delFile = sc.next();
        //          DeleteFile(delFile);

                break;
                case "R" :
                case "r" :{
                  Scanner cs = new Scanner(System.in);
                  System.out.print("Choose File to Read: ");
                  String myFile = cs.next();
                        if(!myFile.endsWith(".bc")) {
                              System.out.println("Error: Invalid file extension. File must be a .bc extension.");
                              System.exit(0);
                        }else{
                              OpenFile(myFile);
                              System.exit(0);
                        }
                }
                 break;
                 case "E":{
                       System.out.println(" GOODBYE AND THANK YOU FOR USING BARAKO COFFEE ");
                       System.exit(0);
                    }
                default:
                        System.out.println(" INVALID INPUT ");
                }
            System.out.print("\n");
            Ans = sc.nextLine();
        }while ( !Ans.equals("N"));      
    }
}