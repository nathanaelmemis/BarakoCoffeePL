package barakocoffee.syntaxanalyzer;

import barakocoffee.SymbolTable;

public class Productions {
    SymbolTable symbolTable;

    // terminals
    final String LITERAL = "(INTEGER_LITERAL|FLOAT_LITERAL|STRING_LITERAL|CHAR__LITERAL|BOOLEAN_LITERAL|NULL_LITERAL)";
    final String DATA_TYPE = "(INT_KEYWORD|FLOAT_KEYWORD|DOUBLE_KEYWORD|CHAR_KEYWORD|STRING_KEYWORD|BOOLEAN_KEYWORD)";
    final String RETURN_TYPE = "(VOID_KEYWORD|" + DATA_TYPE + ")";
    final String OBJECT_TYPE = "(" + DATA_TYPE + "|IDENTIFIER|CONSTANT)";
    final String ARGUMENT_TYPE = "(" + LITERAL + "|IDENTIFIER|CONSTANT)";
    final String ACCESS_MODIFIER = "(PUBLIC_KEYWORD|PRIVATE_KEYWORD|PROTECTED_KEYWORD)";
    final String CREMENT_OPERATOR = "(INCREMENT_OPERATOR|DECREMENT_OPERATOR)";
    final String BINARY_OPERATOR = "(ADDITION_OPERATOR|SUBTRACTION_OPERATOR|MULTIPLICATION_OPERATOR|DIVISION_OPERATOR|EXPONENT_OPERATOR|MODULO_OPERATOR|INTEGER_DIVISION_OPERATOR)";
    final String LOGICAL_OPERATOR = "(OR_OPERATOR|AND_OPERATOR)";
    final String RELATIONAL_OPERATOR = "(EQUAL_OPERATOR|NOT_EQUAL_OPERATOR|GREATER_OPERATOR|LESS_OPERATOR|GREATER_EQUAL_OPERATOR|LESS_EQUAL_OPERATOR)";
    final String OPERATOR = "(" + BINARY_OPERATOR + "|" + LOGICAL_OPERATOR + "|" + RELATIONAL_OPERATOR + ")";
    final String ASSIGNMENT_OPERATOR = "(ASSIGNMENT_OPERATOR|ADDITION_ASSIGNMENT_OPERATOR|SUBTRACTION_ASSIGNMENT_OPERATOR|MULTIPLICATION_ASSIGNMENT_OPERATOR|DIVISION_ASSIGNMENT_OPERATOR|MODULO_ASSIGNMENT_OPERATOR|INTEGER_DIVISION_ASSIGNMENT_OPERATOR|EXPONENT_ASSIGNMENT_OPERATOR)";
    final String OPEN_CLOSE_SQUARE_DELIMITER = "(OPEN_SQUARE_DELIMITER CLOSE_SQUARE_DELIMITER)";

    // parameters
    final String DECLARATION_PARAMETERS = "(OPEN_PARENTHESIS_DELIMITER ?(" + OBJECT_TYPE + " IDENTIFIER COMMA_DELIMITER ?)* ?(" + OBJECT_TYPE + " IDENTIFIER)? ?CLOSE_PARENTHESIS_DELIMITER)";
    final String OBJECT_CALL_PARAMETERS = "(OPEN_PARENTHESIS_DELIMITER ?(" + ARGUMENT_TYPE + " ?((" + OPEN_CLOSE_SQUARE_DELIMITER + ") ?)* ?)* (COMMA_DELIMITER ?(" + ARGUMENT_TYPE + " ?((" + OPEN_CLOSE_SQUARE_DELIMITER + ") ?)* ?)* ?)* ?CLOSE_PARENTHESIS_DELIMITER)";

    // declaration/object instatiation
    final String DECLARATION = "((FINAL_KEYWORD)? ?(STATIC_KEYWORD)? ?" + OBJECT_TYPE + " ?((OPEN_SQUARE_DELIMITER CLOSE_SQUARE_DELIMITER) ?)* ?(IDENTIFIER)?)";

    // assignment
    final String ASSIGNMENT_LEFT = "(IDENTIFIER " + ASSIGNMENT_OPERATOR + ")";

    // initialization
    final String INITIALIZATION_LEFT = "((FINAL_KEYWORD)? ?(STATIC_KEYWORD)? ?" + DATA_TYPE + " ?((OPEN_SQUARE_DELIMITER CLOSE_SQUARE_DELIMITER) ?)* ?(CONSTANT|IDENTIFIER) ASSIGNMENT_OPERATOR)";

    // method call
    final String OBJECT_CALL = "(((IDENTIFIER|CONSTANT) DOT_DELIMITER ?)* ?(IDENTIFIER|CONSTANT)?)" ;
    
    // expression
    final String NOT_OPERAND = "(NOT_OPERATOR *IDENTIFIER)";
    final String CREMENT_ARITHMETIC_OPERAND = "(" + CREMENT_OPERATOR + "? ?IDENTIFIER ?" + CREMENT_OPERATOR + "?)";
    final String OPERAND = "((INTEGER_LITERAL|FLOAT_LITERAL)|" + CREMENT_ARITHMETIC_OPERAND + ")";
    final String EXPRESSION = "( *(" + OPERAND + "|" + NOT_OPERAND + ") *(( *" + OPERATOR + " *(" + OPERAND + "|" + NOT_OPERAND + ")) *)*)";

    // classes
    final String INNER_CLASS_HEADER = "(FINAL_KEYWORD)? ?" + ACCESS_MODIFIER + "? ?(STATIC_KEYWORD)? ?CLASS_KEYWORD (CONSTANT|IDENTIFIER)";
    final String CLASS_METHOD_HEADER_LEFT = "(FINAL_KEYWORD)? ?" + ACCESS_MODIFIER + "? ?(STATIC_KEYWORD)? ?" + RETURN_TYPE + "? ?(CONSTANT|IDENTIFIER)";
    final String CLASS_HEADER = "(FINAL_KEYWORD)? ?(PUBLIC_KEYWORD)? ?CLASS_KEYWORD (CONSTANT|IDENTIFIER)";
    final String ABSTRACT_CLASS_HEADER = "(PUBLIC_KEYWORD)? ?ABSTRACT_KEYWORD CLASS_KEYWORD IDENTIFIER";
    final String INTERFACE_HEADER = "(PUBLIC_KEYWORD)? ?(ABSTRACT_KEYWORD)? ?INTERFACE_KEYWORD IDENTIFIER";

    public String getTokens(int startIndex, int endIndex) {
        String tokens = "";

        for (; startIndex < endIndex; startIndex++) {
            tokens += symbolTable.getSymbolTable().get(startIndex).getType() + " ";
        }

        if (tokens.equals("")) {
            return tokens;
        }

        return tokens.substring(0, tokens.length() - 1);
    }

    public int isBarakoCoffeeProduction(SymbolTable symbolTable, int index) throws SyntaxException {
        this.symbolTable = symbolTable;

        // classes/abstract classes/interfaces
        for (int endIndex = 0; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[{};]")) {
                    break;
                }
                endIndex++;
            }

            String tokens = getTokens(index, endIndex);

            if (tokens.matches(CLASS_HEADER)) {
                // class body
                index = isClassBodyProduction(endIndex);
                continue;
            }

            if (tokens.matches(ABSTRACT_CLASS_HEADER)) {
                // abstract class body
                // index = isAbstractClassBodyProduction(endIndex);
                continue;
            }

            if (tokens.matches(INTERFACE_HEADER)) {
                // interface body
                // index = isInterfaceBodyProduction(endIndex);
            } else {
                throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber());
            }
        }

        return index;
    }

    public int isClassBodyProduction(int index) throws SyntaxException {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Class Body");
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER)")) {
                    if (symbolTable.getSymbolTable().get(endIndex).getLexeme().equals("}")) {
                        return endIndex;
                    } else if (symbolTable.getSymbolTable().get(endIndex).getType().matches(ASSIGNMENT_OPERATOR)) {
                        endIndex++;
                    }

                    break;
                }
                endIndex++;
            }

            String tokens = getTokens(index, endIndex);

            if (tokens.matches(CLASS_METHOD_HEADER_LEFT)) {
                // method body
                index = isDeclarationParameterProduction(endIndex);
                index = isMethodBodyProduction(index);
                continue;
            } else if (tokens.matches(ASSIGNMENT_LEFT)) {
                // right-hand side
                index = isExpressionProduction(endIndex);
                continue;
            }

            index = isStatementProduction(index, endIndex, tokens);
            // object instatiation
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                index = isDeclarationParameterProduction(endIndex);
                if (symbolTable.getSymbolTable().get(index).getType().matches("(IDENTIFIER|CONSTANT)")) {
                    index++;
                }
            }
        }

        return index;
    }

    public int isMethodBodyProduction(int index) throws SyntaxException {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Class Body");
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER)")) {
                    if (symbolTable.getSymbolTable().get(endIndex).getLexeme().equals("}")) {
                        return endIndex;
                    } else if (symbolTable.getSymbolTable().get(endIndex).getType().matches(ASSIGNMENT_OPERATOR)) {
                        endIndex++;
                    }

                    break;
                }
                endIndex++;
            }

            String tokens = getTokens(index, endIndex);

            if (tokens.matches(OBJECT_CALL)) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                continue;
            } 

            index = isStatementProduction(index, endIndex, tokens);
            endIndex = ++index;
            // object instatiation
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                index = isDeclarationParameterProduction(endIndex);
                if (symbolTable.getSymbolTable().get(index + 1).getType().matches("(INDENTIFIER|CONSTANT)")) {
                    index++;
                }
            }
        }

        return index;
    }

    public int isStatementProduction(int index, int endIndex, String tokens) throws SyntaxException {
        if (tokens.matches(INNER_CLASS_HEADER)) {
            // class body
            return isClassBodyProduction(endIndex);
        }

        if (tokens.matches(DECLARATION)) {
            return endIndex;
        }

        if (tokens.matches(INITIALIZATION_LEFT)) {
            // right-hand side
            return isExpressionProduction(endIndex);
        } else {
            throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber());
        }
    }

    public int isExpressionProduction(int index) throws SyntaxException {
        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[;]")) {
                    break;
                }
                endIndex++;
            }

            String tokens = getTokens(index, endIndex);

            int parenthesis = 0;
            for (int i = 0; i <= tokens.length() - "OPEN_PARENTHESIS_DELIMITER".length(); i++) {
                if (tokens.substring(i, i + "OPEN_PARENTHESIS_DELIMITER".length()).equals("OPEN_PARENTHESIS_DELIMITER")) {
                    parenthesis++;
                }
            }
            for (int i = 0; i <= tokens.length() - "CLOSE_PARENTHESIS_DELIMITER".length(); i++) {
                if (tokens.substring(i, i + "CLOSE_PARENTHESIS_DELIMITER".length()).equals("CLOSE_PARENTHESIS_DELIMITER")) {
                    parenthesis--;
                }
            }
            if (parenthesis < 0) {
                throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Imbalanced Parenthesis");
            }

            // remove parenthesis
            tokens = tokens.replaceAll("OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER", "");

            if (tokens.matches(EXPRESSION)) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[);]")) {
                    if (index == endIndex) {
                        throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                    + "Expected Expression");
                    }
                    if (symbolTable.getSymbolTable().get(endIndex + 1).getLexeme().equals(";")) {
                        return endIndex + 1;
                    } else {
                        return endIndex;
                    }
                    
                } else if (symbolTable.getSymbolTable().get(endIndex - 1).getLexeme().equals("(")) {
                    index = isExpressionProduction(endIndex);
                    if (symbolTable.getSymbolTable().get(endIndex).getLexeme().equals(")")) {
                        endIndex--;
                    }
                }
            } else {
                throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Invalid Expression");
            }
        }

        return index;
    }

    public int isDeclarationParameterProduction(int index) throws SyntaxException {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[)]")) {
                endIndex++;
                break;
            }
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter");
        }

        String tokens = getTokens(index, endIndex);

        if (tokens.matches(DECLARATION_PARAMETERS)) {
            return endIndex;
        } else {
            throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Parameters");
        }
    }

    public int isObjectCallParameterProduction(int index) throws SyntaxException {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[)]")) {
                endIndex++;
                break;
            }
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter");
        }

        String tokens = getTokens(index, endIndex);

        if (tokens.matches(OBJECT_CALL_PARAMETERS)) {
            return endIndex;
        } else {
            throw new SyntaxException("Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Parameters");
        }
    }

}
