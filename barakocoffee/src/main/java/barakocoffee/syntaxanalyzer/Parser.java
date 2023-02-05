package barakocoffee.syntaxanalyzer;

import java.util.HashMap;

import barakocoffee.SymbolTable;

public class Parser extends Productions {
    public boolean isValidSyntax(SymbolTable symbolTable) {
        if(isBarakoCoffeeProduction(symbolTable, 0) == symbolTable.getSymbolTable().size()) {
            if (!errorMessage.equals("")) {
                System.out.println(errorMessage);
                return false;
            }
            return true;
        } else {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(symbolTable.getSymbolTable().size() - 1).getLineNumber() + "\n"
                                + "Reached End of File while Parsing";
            if (!errorMessage.equals("")) {
                System.out.println(errorMessage);
                return false;
            }
        }
        
        return false;
    }

    public SymbolTable translate(SymbolTable symbolTable) {
        HashMap<String, String> tagalogKeywords = new HashMap<>();

        tagalogKeywords.put("ENT_KEYWORD", "INT_KEYWORD");
        tagalogKeywords.put("KAR_KEYWORD", "CHAR_KEYWORD");
        tagalogKeywords.put("EKS_KEYWORD", "EXP_KEYWORD");
        tagalogKeywords.put("KUNDI_KEYWORD", "ELSE_KEYWORD");
        tagalogKeywords.put("KUNG_KEYWORD", "IF_KEYWORD");
        tagalogKeywords.put("KUWERDAS_KEYWORD", "STRING_KEYWORD");
        tagalogKeywords.put("LOBO_KEYWORD", "FLOAT_KEYWORD");
        tagalogKeywords.put("PROTEKTADO_KEYWORD", "PROTECTED_KEYWORD");
        tagalogKeywords.put("PRIBADO_KEYWORD", "PRIVATE_KEYWORD");
        tagalogKeywords.put("PINAL_KEYWORD", "FINAL_KEYWORD");
        tagalogKeywords.put("PUBLIKO_KEYWORD", "PUBLIC_KEYWORD");
        tagalogKeywords.put("PUTOL_KEYWORD", "BREAK_KEYWORD");
        tagalogKeywords.put("KLASE_KEYWORD", "CLASS_KEYWORD");
        tagalogKeywords.put("PINAHABA_KEYWORD", "EXTENDS_KEYWORD");
        tagalogKeywords.put("ITO_KEYWORD", "THIS_KEYWORD");
        tagalogKeywords.put("PASOK_KEYWORD", "SCAN_KEYWORD");
        tagalogKeywords.put("LABAS_KEYWORD", "PRINT_KEYWORD");
        tagalogKeywords.put("TULONG_KEYWORD", "HELP_KEYWORD");
        tagalogKeywords.put("IBALIK_KEYWORD", "RETURN_KEYWORD");
        tagalogKeywords.put("ESTATIKA_KEYWORD", "STATIC_KEYWORD");
        tagalogKeywords.put("PRINSIPAL_RESERVED_WORD", "MAIN_RESERVED_WORD");
        tagalogKeywords.put("TULOY_RESERVED_WORD", "CONTINUE_RESERVED_WORD");
        tagalogKeywords.put("TAMA_BOOLEAN_LITERAL", "TAMA_BOOLEAN_LITERAL");
        tagalogKeywords.put("MALI_BOOLEAN_LITERAL", "MALI_BOOLEAN_LITERAL");
        tagalogKeywords.put("ABSTRAK_KEYWORD", "ABSTRACT_KEYWORD");
        
        for (int i = 0; i < symbolTable.getSymbolTable().size(); i++) {
            if (tagalogKeywords.containsKey(symbolTable.getSymbolTable().get(i).getType())) {
                symbolTable.getSymbolTable().get(i).setType(tagalogKeywords.get(symbolTable.getSymbolTable().get(i).getType()));
            }
        }

        return symbolTable;
    }
}