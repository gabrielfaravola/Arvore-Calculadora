import java.util.Stack;

public class InfixToSufix {
    Stack<Character> stack = new Stack<>();

    public String convert(String equacao) {
        
    	int i = 0;
        char[] caracteresValidos = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '-','−','–','*','∗', '/', '(', ')', '.'};
        char[] operadores = {'+', '-','−','–','*','∗', '/', '(', ')', '.'};
        char[] operandos = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char aux;
        
        StringBuilder numero = new StringBuilder();  // Para capturar números inteiros e decimais
        StringBuilder Posfixa = new StringBuilder();  // Usaremos StringBuilder para garantir a impressão
        
        equacao = equacao.replaceAll(" ", "");  // Remove espaços

        // Convertendo para char e armazenando em um array
        char[] Infixa = equacao.toCharArray();
       
        // Verificação de caracteres inválidos
        for (i = 0; i < Infixa.length; i++) {
            boolean encontrado = false;
            for (char c : caracteresValidos) {
                if (Infixa[i] == c) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                System.out.println("\nERRO: Expressão invalida. Operador ou operando '" + Infixa[i] + "' não reconhecido.");
                return "null";
            }
        }
        
        // Verifica se tem operadores
        boolean temOperador = false;
        for (char c : Infixa) {
            for (char op : operadores) {
                if (c == op) {
                    temOperador = true;
                    break;
                }
            }
            if (temOperador) break;
        }
        if (!temOperador) {
            System.out.println("\nERRO: Nenhum operador foi encontrado.");
            return "null";
        }
        
        // Verifica se tem operandos
        boolean temOperando = false;
        for (char c : Infixa) {
            for (char op : operandos) {
                if (c == op) {
                	temOperando = true;
                    break;
                }
            }
            if (temOperando) break;
        }
        if (!temOperando) {
            System.out.println("\nERRO: Nenhum operando foi encontrado.");
            return "null";
        }

        // Verifica validade dos parenteses
        if(Infixa[0] == ')') {
        	System.out.println("\nERRO: Parenteses ')' invalido.");
        	return "null";
        }
        int ParentesesE = 0;
        int ParentesesD = 0;
        
        for(i=0; i<Infixa.length; i++) {
        	if(Infixa[i] == '(') {
        		ParentesesE++;
        	}
        	if(Infixa[i] == ')') {
        		ParentesesD++;
        	}
        }
        
        if(ParentesesE > ParentesesD) {
        	System.out.println("\nERRO: Parenteses '(' não foi fechado.");
        	return "null";
        } else if(ParentesesE < ParentesesD) {
        	System.out.println("\nERRO: Parenteses ')' não foi aberto.");
        	return "null";
        } 
        
        // Interpreta números decimais
        for (i = 0; i < Infixa.length; i++) {
            // Se o caractere for um número ou ponto (parte de número decimal)
            if (Character.isDigit(Infixa[i]) || Infixa[i] == '.') {
                numero.append(Infixa[i]); // Constrói números inteiros e decimais
            } else {
                // Quando um operador ou parêntese for encontrado, adiciona o número anterior
                if (numero.length() > 0) {
                    // Adiciona o número à notação pós-fixa com espaço
                    Posfixa.append(numero.toString()).append(" "); // Agora usando StringBuilder
                    numero.setLength(0);  // Reseta o acumulador de número
                }

                // Lógica para operadores e parênteses
                switch (Infixa[i]) {
                    case '(':
                        stack.push(Infixa[i]);
                        break;

                    case ')':
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            aux = stack.pop();
                            Posfixa.append(aux).append(" ");
                        }
                        stack.pop();
                        break;

                    case '+': case '-': case '−': case '–':
                        while (!stack.isEmpty() && stack.peek() != '(') {
                            aux = stack.pop();
                            Posfixa.append(aux).append(" ");
                        }
                        stack.push(Infixa[i]);
                        break;

                    case '*': case '∗': case '/':
                        while (!stack.isEmpty() && (aux = stack.peek()) != '(' && aux != '+' && aux != '-' && aux != '–' && aux != '−') {
                            Posfixa.append(aux).append(" "); // Adiciona o operador com espaço
                            stack.pop();
                        }
                        stack.push(Infixa[i]);
                        break;

                    default:
                        Posfixa.append(Infixa[i]).append(" ");  // Adiciona diretamente se for operando simples
                }
            }
        }

        // Se sobrar algum número no final da expressão, adiciona o número final + espaço
        if (numero.length() > 0) {
            Posfixa.append(numero.toString()).append(" ");
        }

        // Coloca os operadores restantes da pilha na notação pós-fixa + espaço
        while (!stack.isEmpty()) {
            aux = stack.pop();
            Posfixa.append(aux).append(" ");
        }

        // Construção da árvore
        Arvore arvore = new Arvore();
        String verifica = arvore.construirArvore(Posfixa.toString());
        if(verifica != "null") {
        	return Posfixa.toString().trim();  // Retorna a expressão pós-fixa
        }
        System.out.println("\nERRO: Operador invalido.");
        return "null";  // Retorna a expressão pós-fixa
    }
}
