import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Arvore {
    public No raiz;

    public void excluirArvore() {
        this.raiz = null;
    }

    public void inserirOperando(float valor) {
        No novoNo = new NoOperando(valor);
        if (this.raiz == null) {
            raiz = novoNo;
        } else {
            System.out.println("Erro: Operandos não podem ser inseridos diretamente na árvore.");
        }
    }

    public void inserirOperador(char operador, No esquerda, No direita) {
        No novoNo = new NoOperador(operador);
        novoNo.esquerda = esquerda;
        novoNo.direita = direita;
        this.raiz = novoNo;
    }

    public String construirArvore(String expressaoSufixa) {
        Stack<No> pilha = new Stack<>();
        String[] elementos = expressaoSufixa.split(" ");

        for (String elemento : elementos) {
            if (ehOperador(elemento)) {
                NoOperador operadorNo = new NoOperador(elemento.charAt(0));
                operadorNo.direita = pilha.pop();
                if(pilha.size() == 0) {
                	return "null";
                }
                operadorNo.esquerda = pilha.pop();
                pilha.push(operadorNo);
            } else {
                float valor = Float.parseFloat(elemento);
                pilha.push(new NoOperando(valor));
            }
        }

        if (!pilha.isEmpty()) {
            raiz = pilha.pop();
        }
        return "";
    }

    private boolean ehOperador(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    public void exibirArvore() {
        exibirArvore(this.raiz, 0);
    }

    private void exibirArvore(No no, int nivel) {
        if (no != null) {
            exibirArvore(no.direita, nivel + 1);
            for (int r = 1; r <= nivel; r++) {
                System.out.print("-");
            }
            if (no instanceof NoOperando) {
                System.out.println("<  "+((NoOperando) no).visitar());
            } else if (no instanceof NoOperador) {
                System.out.println("<  "+((NoOperador) no).operador);
            }
            exibirArvore(no.esquerda, nivel + 1);
        }
    }

    public void emOrdem() {
        emOrdem(raiz);
        System.out.println();
    }

    private void emOrdem(No no) {
        if (no != null) {
            emOrdem(no.esquerda);
            if (no instanceof NoOperando) {
                System.out.print(((NoOperando) no).visitar() + " ");
            } else if (no instanceof NoOperador) {
                System.out.print(((NoOperador) no).operador + " ");
            }
            emOrdem(no.direita);
        }
    }

    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(No no) {
        if (no != null) {
            if (no instanceof NoOperando) {
                System.out.print(((NoOperando) no).visitar() + " ");
            } else if (no instanceof NoOperador) {
                System.out.print(((NoOperador) no).operador + " ");
            }
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    public void posOrdem() {
        posOrdem(raiz);
        System.out.println();
    }

    private void posOrdem(No no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            if (no instanceof NoOperando) {
                System.out.print(((NoOperando) no).visitar() + " ");
            } else if (no instanceof NoOperador) {
                System.out.print(((NoOperador) no).operador + " ");
            }
        }
    }

    public void emLargura() {
        if (this.raiz == null) {
            System.out.println("Árvore não existe.");
            return;
        }

        Queue<No> q = new LinkedList<>();
        q.add(raiz);

        while (!q.isEmpty()) {
            No atual = q.poll();
            if (atual instanceof NoOperando) {
                System.out.print(((NoOperando) atual).visitar() + " ");
            } else if (atual instanceof NoOperador) {
                System.out.print(((NoOperador) atual).operador + " ");
            }

            if (atual.esquerda != null) {
                q.add(atual.esquerda);
            }
            if (atual.direita != null) {
                q.add(atual.direita);
            }
        }
        System.out.println();
    }

    public int contarNos() {
        return contarNos(raiz);
    }

    private int contarNos(No raiz) {
        if (raiz != null) {
            return 1 + contarNos(raiz.esquerda) + contarNos(raiz.direita);
        } else {
            return 0;
        }
    }

    public float calcularExpressao() {
        if (raiz == null) {
            System.out.println("Árvore vazia.");
            return Float.NaN;
        }
        return raiz.visitar();
    }
}
