import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    	String opcaoStr;
    	
    	Scanner scan = new Scanner(System.in);
        InfixToSufix toSufix = new InfixToSufix();
        Arvore ar = new Arvore();
        
        System.out.println("Iniciando Programa - Cálculo de expressões aritméticas com árvore binária.");
        
        String expressaoPosfixa = "";
        
        while (true) {
            System.out.print("\n------------------------ Menu ------------------------\n"
                + "[1] Entrada da expressão aritmética na notação infixa.\n"
                + "[2] Criação da árvore binária de expressão aritmética.\n"
                + "[3] Exibição da árvore binária de expressão aritmética.\n"
                + "[4] Cálculo da expressão (realizando o percurso da árvore).\n"
                + "[5] Encerramento do programa.\n"
                + "\nDigite a opção:\n> ");

            opcaoStr = scan.nextLine();
            
            int OpcaoMenu;
            try {
                OpcaoMenu = Integer.parseInt(opcaoStr);
            } catch (NumberFormatException e) {
                System.out.print("\nERRO: Opção invalida. \nPressione ENTER para continuar...");
                scan.nextLine();
                continue;
            }
                        
            switch(OpcaoMenu) {
                case 1:
                    System.out.print("\nDigite a expressão matemática:\n> ");
                    String expressao = scan.nextLine();
                    if(expressao.isEmpty()) {
                    	System.out.print("\nERRO: Expressão inexistente!\nPressione ENTER para continuar...");
                        scan.nextLine();
                    	break;
                    }
                    
                    expressaoPosfixa = toSufix.convert(expressao);
                    
                    if (!expressaoPosfixa.equals("null")) {
                        System.out.println("\nExpressão lida com sucesso!\nPós-fixa = " + expressaoPosfixa);
                    }
                    
                    System.out.print("Pressione ENTER para continuar...");
                    scan.nextLine();
                    break;

                case 2:
                    if (!expressaoPosfixa.isEmpty() && !expressaoPosfixa.equals("null")) {
                        ar.construirArvore(expressaoPosfixa);
                        System.out.println("\nÁrvore binária construída com sucesso.");
                    } else {
                        System.out.println("\nExpressão inexistente. Primeiro insira uma expressão válida (Item [1]).");
                    }
                    System.out.print("Pressione ENTER para continuar...");
                    scan.nextLine();
                    break;

                case 3:
                    if (ar.raiz  != null) {
                		System.out.println("Arvore:");
                    	ar.exibirArvore();
                    	System.out.print("\nPre Ordem:  ");
                    	ar.preOrdem();
                    	System.out.print("Em Ordem:  ");
                    	ar.emOrdem();
                    	System.out.print("Pos Ordem:  ");
                    	ar.posOrdem();
                    } else {
                        System.out.println("\nArvore inexistente. Antes é necessário a criação da arvore (Item [2]).");
                    }
                    System.out.print("Pressione ENTER para continuar...");
                    scan.nextLine();
                    break;

                case 4:
                    if (ar.raiz != null) {
                        float resultado = ar.calcularExpressao();
                        System.out.println("\nResultado da expressão = " + resultado);
                    } else {
                    	System.out.println("\nArvore inexistente. Antes é necessário a criação da arvore (Item [2]).");
                    }
                    System.out.print("Pressione ENTER para continuar...");
                    scan.nextLine();
                    break;

                case 5:
                    scan.close();
                    System.out.print("\nEncerrando o programa...");
                    return;
                
                default:
                	System.out.print("\nERRO: Opção invalida. \nPressione ENTER para continuar...");
                	scan.nextLine();
            }
        }
    }
}