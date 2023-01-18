package barakocoffee.LexicalAnalyzer.Tokenizer;

import java.util.Scanner;

public class Keywords_NOT_FINAL {

	private String[] keywords = {"bago", "new", "boolean", "defecto", "default", "doble", "double",
								"ent", "int", "kar", "char", "eks", "exp", "kar", "char", "kundi",
								"else", "kung", "if", "kuwerdas", "string", "lobo", "float", "para",
								"for", "protektado", "protected", "pribado", "private", "pinal", "final",
								"publiko", "public", "putol", "break", "klase", "class", "pinahaba",
								"extends", "ito", "this", "pasok", "scan", "labas", "print", "tulong",
								"help", "arrayParser"};

	private String[] reservedWords = { "mali", "false", "prinsipal", "main", "tuloy", "continue", "tama", "true" };

	public static void main(String[] args) {

		Keywords_NOT_FINAL kw = new Keywords_NOT_FINAL();
		Scanner a = new Scanner(System.in);
		String input;
		do {
			System.out.println("\nEnter an input (Type 'end' if you want to exit): ");
			input = a.nextLine();
			String[] inputTokens = input.split("\\s+|(?<=;)");
			for (String inputToken : inputTokens) {
				boolean isKeyword = false;
				for (String keyword : kw.keywords) {
					if (inputToken.equals(keyword)) {
						System.out.println("\nKeyword: " + inputToken);
						isKeyword = true;
						break;
					}
				}
				if (!isKeyword) {
					for (String reservedWord : kw.reservedWords) {
						if (inputToken.equals(reservedWord)) {
							System.out.println("Reserved Word: " + inputToken);
							isKeyword = true;
							break;
						}
					}
				}
				if (!isKeyword) {
					// Check if the token starts with a letter and contains only letters, digits,
					// and underscores
					if (inputToken.matches("^[A-Za-z][A-Za-z0-9_]*$")) {
						System.out.println("Identifier: " + inputToken);
					} else {
						// Check if the token is an operator
						if (inputToken.matches("^[+-/*%=<>!&|^]+$")) {
							System.out.println("Operator: " + inputToken);
						} else {
							System.out.println("token: " + inputToken);
						}
					}
				}
			}

		} while (!input.equals("end"));
		System.out.println("\nPROGRAM END.");
		a.close();
	}
}
