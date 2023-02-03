package barakocoffee.syntaxanalyzer;

import barakocoffee.SymbolTable;

public class Productions {
    SymbolTable symbolTable;
    String errorMessage = "";

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
    final String SIGN_OPERATOR = "(ADDITION_OPERATOR|SUBTRACTION_OPERATOR)";

    // parameters
    final String DECLARATION_PARAMETERS = "(OPEN_PARENTHESIS_DELIMITER ?(" + OBJECT_TYPE + " IDENTIFIER COMMA_DELIMITER ?)* ?(" + OBJECT_TYPE + " IDENTIFIER)? ?CLOSE_PARENTHESIS_DELIMITER)";
    final String OBJECT_CALL_PARAMETERS = "(OPEN_PARENTHESIS_DELIMITER ?(" + ARGUMENT_TYPE + " ?((" + OPEN_CLOSE_SQUARE_DELIMITER + ") ?)* ?)* (COMMA_DELIMITER ?(" + ARGUMENT_TYPE + " ?((" + OPEN_CLOSE_SQUARE_DELIMITER + ") ?)* ?)* ?)* ?CLOSE_PARENTHESIS_DELIMITER)";

    // declaration/object instatiation
    final String DECLARATION = "((FINAL_KEYWORD)? ?(STATIC_KEYWORD)? ?" + OBJECT_TYPE + " ?((OPEN_SQUARE_DELIMITER CLOSE_SQUARE_DELIMITER) ?)* ?(IDENTIFIER)?)";

    // assignment
    final String ASSIGNMENT_LEFT = "(IDENTIFIER " + ASSIGNMENT_OPERATOR + ")";
    final String CREMENTATION = "((" + CREMENT_OPERATOR + " IDENTIFIER)|(IDENTIFIER " + CREMENT_OPERATOR + "))";

    // initialization
    final String INITIALIZATION_LEFT = "((FINAL_KEYWORD)? ?(STATIC_KEYWORD)? ?" + DATA_TYPE + " ?((OPEN_SQUARE_DELIMITER CLOSE_SQUARE_DELIMITER) ?)* ?(CONSTANT|IDENTIFIER) ASSIGNMENT_OPERATOR)";

    // method call
    final String OBJECT_CALL = "((IDENTIFIER|CONSTANT) ?(DOT_DELIMITER (IDENTIFIER|CONSTANT))*)" ;

    // iterative statements
    final String FOR_HEADER_LEFT = "(FOR_KEYWORD)";
    final String WHILE_HEADER_LEFT = "(WHILE_KEYWORD)";
    final String DO_HEADER_LEFT = "(DO_KEYWORD)";
    final String WHILE_DO_WHILE_HEADER_LEFT = "(WHILE_KEYWORD|DO_KEYWORD)";
    final String LOOP_KEYWORDS = "(BREAK_KEYWORD|CONTINUE_RESERVED_WORD)";
    final String SWITCH = "(" + LOOP_KEYWORDS + "|CASE_KEYWORD .*|DEFAULT_KEYWORD)";

    // relational statements
    final String IF_HEADER_LEFT = "(IF_KEYWORD)";
    final String ELSE_IF_ELSE_HEADER_LEFT = "(ELSE_KEYWORD IF_KEYWORD|ELSE_KEYWORD)";
    final String IF_ELSE_IF_ELSE_HEADER_LEFT = "(" + IF_HEADER_LEFT + "|" + ELSE_IF_ELSE_HEADER_LEFT + ")";
    final String SWITCH_HEADER_LEFT = "(SWITCH_KEYWORD)";
    final String SWITCH_RELATED = "(" + SWITCH_HEADER_LEFT + "|CASE_KEYWORD .*|DEFAULT_KEYWORD .*)";
    
    // expression
    final String NOT_OPERAND = "(NOT_OPERATOR *IDENTIFIER)";
    final String CREMENT_ARITHMETIC_OPERAND = "(" + CREMENT_OPERATOR + "? ?IDENTIFIER ?" + CREMENT_OPERATOR + "?)";
    final String SIGNED_OPERAND = "(" + SIGN_OPERATOR + " (IDENTIFIER|INTEGER_LITERAL|FLOAT_LITERAL))";
    final String OPERAND = "((INTEGER_LITERAL|FLOAT_LITERAL|STRING_LITERAL|BOOLEAN_LITERAL|CHARACTER_LITERAL|SCAN_KEYWORD)|" + CREMENT_ARITHMETIC_OPERAND + ")";
    final String EXPRESSION = "( *(" + OPERAND + "|" + NOT_OPERAND + "|" + SIGNED_OPERAND + ") *(( *" + OPERATOR + " *(" + OPERAND + "|" + NOT_OPERAND + "|" + SIGNED_OPERAND + ")) *)*)";

    // classes
    final String CLASS_HEADER = "(FINAL_KEYWORD)? ?(PUBLIC_KEYWORD)? ?CLASS_KEYWORD (CONSTANT|IDENTIFIER)";
    final String INNER_CLASS_HEADER = "(FINAL_KEYWORD)? ?" + ACCESS_MODIFIER + "? ?(STATIC_KEYWORD)? ?CLASS_KEYWORD (CONSTANT|IDENTIFIER)";
    final String ABSTRACT_CLASS_HEADER = "(PUBLIC_KEYWORD)? ?ABSTRACT_KEYWORD CLASS_KEYWORD IDENTIFIER";
    final String INNER_ABSTRACT_CLASS_HEADER = "(PUBLIC_KEYWORD)? ?(STATIC_KEYWORD)? ?ABSTRACT_KEYWORD CLASS_KEYWORD IDENTIFIER";
    final String INTERFACE_HEADER = "(PUBLIC_KEYWORD)? ?(ABSTRACT_KEYWORD)? ?INTERFACE_KEYWORD IDENTIFIER";
    final String INNER_INTERFACE_HEADER = "(PUBLIC_KEYWORD)? ?(ABSTRACT_KEYWORD)? ?(STATIC_KEYWORD)? ?INTERFACE_KEYWORD (CONSTANT|IDENTIFIER)";
    final String STRUCT_HEADER = "(STRUCT_KEYWORD IDENTIFIER)";
    final String CLASS_METHOD_HEADER_LEFT = "(FINAL_KEYWORD)? ?" + ACCESS_MODIFIER + "? ?(STATIC_KEYWORD)? ?" + RETURN_TYPE + "? ?(CONSTANT|IDENTIFIER)";

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

    public int isBarakoCoffeeProduction(SymbolTable symbolTable, int index)  {
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

            if (tokens.matches(STRUCT_HEADER)) {
                // struct body
                index = isStructBodyProduction(endIndex);
            } else if (tokens.matches(CLASS_HEADER)) {
                // class body
                index = isClassBodyProduction(endIndex);
            } else if (tokens.matches(ABSTRACT_CLASS_HEADER)) {
                // abstract class body
                index = isAbstractClassBodyProduction(endIndex);
            } else if (tokens.matches(INTERFACE_HEADER)) {
                // interface body
                index = isInterfaceBodyProduction(endIndex);
            } else {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber();
            }
        }

        return index;
    }

    public int isClassBodyProduction(int index)  {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Class Body";
                return index;
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER|COLON_DELIMITER)")) {
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

            // object instantiation
            if (tokens.matches("(IDENTIFIER|CONSTANT)")) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                if (symbolTable.getSymbolTable().get(index).getType().matches("(IDENTIFIER|CONSTANT)")) {
                    index++;
                }
                continue;
            } else if (tokens.matches(CLASS_METHOD_HEADER_LEFT)) {
                // method body
                index = isDeclarationParameterProduction(endIndex);
                index = isMethodBodyProduction(index, 1);
                continue;
            } else if (tokens.matches(INNER_INTERFACE_HEADER)) {
                // interface body
                index = isInterfaceBodyProduction(endIndex);
                continue;
            } else if (tokens.matches(INNER_ABSTRACT_CLASS_HEADER)) {
                // interface body
                index = isAbstractClassBodyProduction(endIndex);
                continue;
            }

            index = isStatementProduction(index, endIndex, tokens);
        }

        return index;
    }

    public int isInterfaceBodyProduction(int index)  {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Interface Body";
                return index;
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER|COLON_DELIMITER)")) {
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

            // object instantiation
            if (tokens.matches("(IDENTIFIER|CONSTANT)")) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                if (symbolTable.getSymbolTable().get(index).getType().matches("(IDENTIFIER|CONSTANT)")) {
                    index++;
                }
                continue;
            } else if (tokens.matches(CLASS_METHOD_HEADER_LEFT)) {
                // method body
                index = isDeclarationParameterProduction(endIndex);
                if (!symbolTable.getSymbolTable().get(index).getType().matches("(SEMICOLON_DELIMITER)")) {
                    errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                + "Abstract Methods Do Not Specify A Body";
                }
                continue;
            } else if (tokens.matches(INNER_INTERFACE_HEADER)) {
                // interface body
                index = isInterfaceBodyProduction(endIndex);
                continue;
            } else if (tokens.matches(INNER_ABSTRACT_CLASS_HEADER)) {
                // interface body
                index = isAbstractClassBodyProduction(endIndex);
                continue;
            }

            index = isStatementProduction(index, endIndex, tokens);
        }

        return index;
    }

    public int isAbstractClassBodyProduction(int index)  {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Abstract Class Body";
                return index;
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER|COLON_DELIMITER)")) {
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

            // object instantiation
            if (tokens.matches("(IDENTIFIER|CONSTANT)")) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                if (symbolTable.getSymbolTable().get(index).getType().matches("(IDENTIFIER|CONSTANT)")) {
                    index++;
                }
                continue;
            } else if (tokens.matches(CLASS_METHOD_HEADER_LEFT)) {
                // method body
                index = isDeclarationParameterProduction(endIndex);
                if (symbolTable.getSymbolTable().get(index).getType().matches("(SEMICOLON_DELIMITER)")) {
                    continue;
                }
                index = isMethodBodyProduction(index, 1);
                continue;
            } else if (tokens.matches(INNER_INTERFACE_HEADER)) {
                // interface body
                index = isInterfaceBodyProduction(endIndex);
                continue;
            } else if (tokens.matches(INNER_ABSTRACT_CLASS_HEADER)) {
                // interface body
                index = isAbstractClassBodyProduction(endIndex);
                continue;
            }

            index = isStatementProduction(index, endIndex, tokens);
        }

        return index;
    }

    public int isStructBodyProduction(int index)  {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Struct Body";
                return index;
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER|COLON_DELIMITER)")) {
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
    
            if (tokens.matches(DECLARATION)) {
                index = endIndex;
                continue;
            } else if (tokens.matches(INITIALIZATION_LEFT)) {
                // right-hand side
                index =  isExpressionProduction(endIndex);
                continue;
            } else if (tokens.matches(OBJECT_CALL)) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                continue;
            } else {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Abstract Methods Do Not Specify A Body";
            }
        }

        return index;
    }

    public int isMethodBodyProduction(int index, int loop)  {
        if (index < symbolTable.getSymbolTable().size()) {
            if (!symbolTable.getSymbolTable().get(index).getLexeme().matches("[{]")) {
                errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                            + "Expected Method Body";
                return index;
            }
        }
        index++;

        for (int endIndex = index; index < symbolTable.getSymbolTable().size(); index++, endIndex = index) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getType().matches("("+ ASSIGNMENT_OPERATOR + "|OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER|OPEN_CURLY_DELIMITER|CLOSE_CURLY_DELIMITER|SEMICOLON_DELIMITER|COLON_DELIMITER)")) {
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

            if (tokens.matches(CREMENTATION)) {
                index = endIndex;
                continue;
            } else if (tokens.matches(SWITCH)) {
                if (loop == 1) {
                    errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                + "Loop Tokens Used In Non-Loop Statement";
                } else if (loop == 2) {
                    if (tokens.matches(LOOP_KEYWORDS)) {
                        continue;
                    } else {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                    + "Switch Statement Tokens Used In Non-Switch Statement";
                    }
                } else {
                    if (tokens.matches("(CASE_KEYWORD .*)")) {
                        index = isExpressionProduction(index + 1);
                    } else if (tokens.matches("(DEFAULT_KEYWORD)")) {
                    }
                }
                continue;
            } else if (tokens.matches(FOR_HEADER_LEFT)) {
                index = isForParameterProduction(endIndex);
                endIndex = ++index;
                index = isMethodBodyProduction(endIndex, 2);
                continue;
            } else if (tokens.matches(SWITCH_RELATED)) {
                index = isExpressionProduction(endIndex);
                endIndex = index;
                index = isMethodBodyProduction(endIndex, 3); 
                continue;
            } else if (tokens.matches(WHILE_DO_WHILE_HEADER_LEFT)) {
                if (tokens.matches(WHILE_HEADER_LEFT)) {
                    index = isExpressionProduction(endIndex);
                    endIndex = index;
                    index = isMethodBodyProduction(endIndex, 2);
                } else {
                    index = isMethodBodyProduction(endIndex, 2);
                    if (index + 1 >= symbolTable.getSymbolTable().size()) {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                    + "Expected While Statement";
                        continue;
                    }
                    if (symbolTable.getSymbolTable().get(index + 1).getType().equals("WHILE_KEYWORD")) {
                        index = isExpressionProduction(index + 2);
                    } else {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                    + "Expected While Statement";
                    }
                }
                continue;
            } else if (tokens.matches(IF_HEADER_LEFT)) {
                index = isExpressionProduction(endIndex);
                endIndex = index;
                index = isMethodBodyProduction(endIndex, 1);
                endIndex = ++index;
                index = isIfStructureProcedure(endIndex);
                continue;
            } else if (tokens.matches(OBJECT_CALL)) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[(]")) {
                    index = isObjectCallParameterProduction(endIndex);
                }
                if (tokens.matches("(IDENTIFIER|CONSTANT)")
                        && symbolTable.getSymbolTable().get(index).getType().matches("(IDENTIFIER|CONSTANT)")) {
                    index++;
                }
                continue;
            } else if (tokens.matches(ASSIGNMENT_LEFT)) {
                // right-hand side
                index = isExpressionProduction(endIndex);
                continue;
            } else if (tokens.matches("(PRINT_KEYWORD)")) {
                // right-hand side
                index = isExpressionProduction(endIndex);
                continue;
            }

            index = isStatementProduction(index, endIndex, tokens);
        }

        return index;
    }

    public int isStatementProduction(int index, int endIndex, String tokens)  {
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
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber();
        }
        return endIndex;
    }

    public int isExpressionProduction(int index)  {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[:;{]")) {
                break;
            }
            endIndex++;
        }

        String tokens = getTokens(index, endIndex);

        // check if parentheses are balanced
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
        if (parenthesis != 0) {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                    + "Imbalanced Parenthesis";
        }

        // remove parenthesis
        tokens = tokens.replaceAll("OPEN_PARENTHESIS_DELIMITER|CLOSE_PARENTHESIS_DELIMITER", "");

        if (tokens.matches(EXPRESSION)) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[);]")) {
                if (index == endIndex) {
                    errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                                + "Expected Expression";
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
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Expression";
        }
        return endIndex;
    }

    public int isDeclarationParameterProduction(int index)  {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[)]")) {
                endIndex++;
                break;
            }
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter";
            return endIndex;
        }

        String tokens = getTokens(index, endIndex);

        if (tokens.matches(DECLARATION_PARAMETERS)) {
            return endIndex;
        } else {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Parameters";
        }
        return endIndex;
    }

    public int isObjectCallParameterProduction(int index)  {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[)]")) {
                endIndex++;
                break;
            }
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter";
            return endIndex;
        }

        String tokens = getTokens(index, endIndex);

        if (tokens.matches(OBJECT_CALL_PARAMETERS)) {
            return endIndex;
        } else {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Parameters";
        }
        return endIndex;
    }

    public int isForParameterProduction(int index)  {
        int count = 1;
        int endIndex = ++index;
        endIndex++;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[)]")) {
                return endIndex;
            }
            String tokens = getTokens(index, endIndex);
            if (tokens.matches("(" + INITIALIZATION_LEFT + "|SEMICOLON_DELIMITER)")) {
                if (count == 1) {
                    if (tokens.matches(INITIALIZATION_LEFT)) {
                        index = isExpressionProduction(endIndex);
                        endIndex = index;
                    } else {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid First Parameter";
                    }
                } else if (count == 2) {
                    if (tokens.matches("(SEMICOLON_DELIMITER)")) {
                        index = isExpressionProduction(endIndex);
                        endIndex = ++index;
                    } else {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Second Parameter";
                    }
                } else if (count == 3) {
                    if (tokens.matches(INITIALIZATION_LEFT)) {
                        index = isExpressionProduction(endIndex);
                        endIndex = index;
                    } else {
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Third Parameter";
                    } 
                } else {
                    errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                    + "Invalid Parameter";
                } 
            } else {
                switch (count) {
                    case 1:
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid First Parameter";
                        break;
                    case 2:
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Second Parameter";
                        break;
                    case 3:
                        errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Invalid Third Parameter";
                        break;
                }
            }
            count++;
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter";
        }
        return endIndex;
    }

    public int isIfStructureProcedure(int index)  {
        int endIndex = index;
        while (endIndex < symbolTable.getSymbolTable().size()) {
            while (endIndex < symbolTable.getSymbolTable().size()) {
                if (symbolTable.getSymbolTable().get(endIndex).getLexeme().matches("[({]")) {
                    break;
                }
                endIndex++;
            }
            
            String tokens = getTokens(index, endIndex);
            if (!tokens.matches(ELSE_IF_ELSE_HEADER_LEFT)) {
                return index - 1;
            } else {
                if (tokens.matches("(ELSE_KEYWORD IF_KEYWORD)")) {
                    index = isExpressionProduction(endIndex);
                    endIndex = index;
                    index = isMethodBodyProduction(endIndex, 1);
                    endIndex = index++;
                } else if (tokens.matches("(ELSE_KEYWORD)")) {
                    index = isMethodBodyProduction(endIndex, 1);
                    return index;
                }
            }
            endIndex++;
        }
        if (endIndex == symbolTable.getSymbolTable().size()) {
            errorMessage += "\n\n" + "Error: Syntax Error on Line " + symbolTable.getSymbolTable().get(index).getLineNumber() + "\n"
                                        + "Unclosed Parameter";
        }
        return endIndex;
    }
}
