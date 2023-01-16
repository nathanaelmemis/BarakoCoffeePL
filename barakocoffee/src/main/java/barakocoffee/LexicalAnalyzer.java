package barakocoffee;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LexicalAnalyzer {
    public void scan(String file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file));
        String lineOfCode;

        while (scanner.hasNextLine()); {
            lineOfCode = scanner.nextLine();
        } 
    }

    boolean checkIfAlphabet(char x) {
        if ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }
}