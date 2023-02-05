package barakocoffee.lexicalanalyzer;

import java.util.HashMap;

public class TokenHashMap {
    private HashMap<String, String> lexemes = new HashMap<>();

    TokenHashMap() {
        // keywords
        lexemes.put("doble", "LOBO_KEYWORD");
        lexemes.put("double", "FLOAT_KEYWORD");
        lexemes.put("boolean", "BOOLEAN_KEYWORD");
        lexemes.put("default", "DEFAULT_KEYWORD");
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
        lexemes.put("static", "STATIC_KEYWORD");
        lexemes.put("estatika", "ESTATIKA_KEYWORD");
        lexemes.put("abstract", "ABSTRACT_KEYWORD");
        lexemes.put("abstrak", "ABSTRAK_KEYWORD");
        lexemes.put("interface", "INTERFACE_KEYWORD");
        lexemes.put("do", "DO_KEYWORD");
        lexemes.put("while", "WHILE_KEYWORD");
        lexemes.put("switch", "SWITCH_KEYWORD");
        lexemes.put("case", "CASE_KEYWORD");
        lexemes.put("implements", "IMPLEMENTS_KEYWORD");

        // reserved words
        lexemes.put("prinsipal", "PRINSIPAL_RESERVED_WORD");
        lexemes.put("main", "MAIN_RESERVED_WORD");
        lexemes.put("tuloy", "TULOY_RESERVED_WORD");
        lexemes.put("continue", "CONTINUE_RESERVED_WORD");

        // noise words
        lexemes.put("entero", "ENT_KEYWORD");
        lexemes.put("integer", "INT_KEYWORD");
        lexemes.put("karakter", "KAR_KEYWORD");
        lexemes.put("character", "CHAR_KEYWORD");
        lexemes.put("eksponente", "EKS_KEYWORD");
        lexemes.put("exponent", "EXP_KEYWORD");
        lexemes.put("kundiman", "KUNDI_KEYWORD");
        lexemes.put("labasf", "LABAS_KEYWORD");
        lexemes.put("printf", "PRINT_KEYWORD");
        lexemes.put("scanf", "SCAN_KEYWORD");
        lexemes.put("pasokf", "PASOK_KEYWORD");

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
        lexemes.put(":", "COLON_DELIMITER");
        lexemes.put(".", "DOT_DELIMITER");

        // literals
        lexemes.put("true", "BOOLEAN_LITERAL");
        lexemes.put("false", "BOOLEAN_LITERAL");
        lexemes.put("tama", "BOOLEAN_LITERAL");
        lexemes.put("mali", "BOOLEAN_LITERAL");
        lexemes.put("null", "NULL_LITERAL");
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
