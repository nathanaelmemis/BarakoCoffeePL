package barakocoffee.lexicalanalyzer;

import java.util.HashMap;

public class TokenHashMap {
    private HashMap<String, String> lexemes = new HashMap<>();

    TokenHashMap() {
        // keywords
        lexemes.put("boolean", "BOOLEAN_KEYWORD");
        lexemes.put("defecto", "DEFECTO_KEYWORD");
        lexemes.put("default", "DEFAULT_KEYWORD");
        lexemes.put("doble", "DOBLE_KEYWORD");
        lexemes.put("double", "DOUBLE_KEYWORD");
        lexemes.put("ent", "ENT_KEYWORD");
        lexemes.put("int", "INT_KEYWORD");
        lexemes.put("kar", "KAR_KEYWORD");
        lexemes.put("char", "CHAR_KEYWORD");
        lexemes.put("eks", "EKS_KEYWORD");
        lexemes.put("exp", "EXP_KEYWORD");
        lexemes.put("kundi", "KUNDI_KEYWORD");
        lexemes.put("else", "ELSE_KEYWORD");
        lexemes.put("kung", "KUNG_KEYWORD");
        lexemes.put("if", "IF_KEYWORD");
        lexemes.put("kuwerdas", "KUWERDAS_KEYWORD");
        lexemes.put("string", "STRING_KEYWORD");
        lexemes.put("lobo", "LOBO_KEYWORD");
        lexemes.put("float", "FLOAT_KEYWORD");
        lexemes.put("for", "FOR_KEYWORD");
        lexemes.put("protektado", "PROTEKTADO_KEYWORD");
        lexemes.put("protected", "PROTECTED_KEYWORD");
        lexemes.put("pribado", "PRIBADO_KEYWORD");
        lexemes.put("private", "PRIVATE_KEYWORD");
        lexemes.put("pinal", "PINAL_KEYWORD");
        lexemes.put("final", "FINAL_KEYWORD");
        lexemes.put("publiko", "PUBLIKO_KEYWORD");
        lexemes.put("public", "PUBLIC_KEYWORD");
        lexemes.put("putol", "PUTOL_KEYWORD");
        lexemes.put("break", "BREAK_KEYWORD");
        lexemes.put("klase", "KLASE_KEYWORD");
        lexemes.put("class", "CLASS_KEYWORD");
        lexemes.put("pinahaba", "PINAHABA_KEYWORD");
        lexemes.put("extends", "EXTENDS_KEYWORD");
        lexemes.put("ito", "ITO_KEYWORD");
        lexemes.put("this", "THIS_KEYWORD");
        lexemes.put("pasok", "PASOK_KEYWORD");
        lexemes.put("scan", "SCAN_KEYWORD");
        lexemes.put("labas", "LABAS_KEYWORD");
        lexemes.put("print", "PRINT_KEYWORD");
        lexemes.put("tulong", "TULONG_KEYWORD");
        lexemes.put("help", "HELP_KEYWORD");
        lexemes.put("struct", "STRUCT_KEYWORD");
        lexemes.put("void", "VOID_KEYWORD");
        lexemes.put("return", "RETURN_KEYWORD");
        lexemes.put("ibalik", "IBALIK_KEYWORD");

        // reserved words
        lexemes.put("prinsipal", "PRINSIPAL_RESERVED_WORD");
        lexemes.put("main", "MAIN_RESERVED_WORD");
        lexemes.put("tuloy", "TULOY_RESERVED_WORD");
        lexemes.put("continue", "CONTINUE_RESERVED_WORD");

        // operators
        lexemes.put("+", "ADDITION_OPERATOR");
        lexemes.put("-", "SUBTRACTION_OPERATOR");
        lexemes.put("*", "MULTIPLICATION_OPERATOR");
        lexemes.put("/", "DIVISION_OPERATOR");
        lexemes.put("%", "MODULO_OPERATOR");
        lexemes.put("~", "INTEGER_DIVISION_OPERATOR");
        lexemes.put("^", "EXPONENT_OPERATOR");
        lexemes.put("=", "ASSIGNMENT_OPERATOR");
        lexemes.put("++", "INCREMENT_OPERATOR");
        lexemes.put("--", "DECREMENT_OPERATOR");
        lexemes.put("+=", "ADDITION_ASSIGNMENT_OPERATOR");
        lexemes.put("-=", "SUBTRACTION_ASSIGNMENT_OPERATOR");
        lexemes.put("*=", "MULTIPLICATION_ASSIGNMENT_OPERATOR");
        lexemes.put("/=", "DIVISION_ASSIGNMENT_OPERATOR");
        lexemes.put("%=", "MODULO_ASSIGNMENT_OPERATOR");
        lexemes.put("~=", "INTEGER_DIVISION_ASSIGNMENT_OPERATOR");
        lexemes.put("^=", "EXPONENT_ASSIGNMENT_OPERATOR");
        lexemes.put("!", "NOT_OPERATOR");
        lexemes.put("==", "EQUAL_OPERATOR");
        lexemes.put("!=", "NOT_EQUAL_OPERATOR");
        lexemes.put(">", "GREATER_OPERATOR");
        lexemes.put("<", "LESS_OPERATOR");
        lexemes.put(">=", "GREATER_EQUAL_OPERATOR");
        lexemes.put("<=", "LESS_EQUAL_OPERATOR");
        lexemes.put("||", "OR_OPERATOR");
        lexemes.put("&&", "AND_OPERATOR");

        // delimiters
        lexemes.put("{", "OPEN_CURLY_DELIMITER");
        lexemes.put("}", "CLOSE_CURLY_DELIMITER");
        lexemes.put("[", "OPEN_SQUARE_DELIMITER");
        lexemes.put("]", "CLOSE_SQUARE_DELIMITER");
        lexemes.put("(", "OPEN_PARENTHESIS_DELIMITER");
        lexemes.put(")", "CLOSE_PARENTHESIS_DELIMITER");
        lexemes.put(",", "COMMA_DELIMITER");
        lexemes.put(";", "SEMICOLON_DELIMITER");
        lexemes.put(".", "DOT_DELIMITER");

        // boolean literals
        lexemes.put("true", "TRUE_BOOLEAN_LITERAL");
        lexemes.put("false", "FALSE_BOOLEAN_LITERAL");
        lexemes.put("tama", "TAMA_BOOLEAN_LITERAL");
        lexemes.put("mali", "MALI_BOOLEAN_LITERAL");
    }

    public Boolean isToken(String lexeme) {
        if (!lexemes.containsKey(lexeme)) {
            return false;
        }

        return true;
    }

    public String getType(String lexeme) {
        return lexemes.get(lexeme);
    }
}
