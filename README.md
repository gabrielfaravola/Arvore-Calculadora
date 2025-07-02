# Arvore-Calculadora
Uma arvore binária que carrega expressões aritméticas e realiza o cálculo. 

# 📐 Calculadora Aritmética com Árvores de Expressão em Java

Este projeto implementa uma **calculadora de expressões matemáticas** que utiliza **Árvores Binárias de Expressão** para representar, percorrer e resolver expressões aritméticas. O sistema é construído na linguagem de programação Java e funciona por meio de um menu interativo no terminal.

---

## 🔍 Visão Geral

A aplicação permite que o usuário insira uma expressão aritmética na notação infixa (ex: 3 + 4 * (2 - 1)), que é então convertida para a notação pós-fixa (3 4 2 1 - * +) por meio da classe InfixToSufix. Essa forma pós-fixa facilita a construção automática da Árvore Binária de Expressão, onde cada operando (número) é inserido como uma folha, e cada operador torna-se um nó interno, conectando seus respectivos operandos.

Durante o processo, a expressão é validada para garantir que contenha apenas caracteres válidos, como: 0 1 2 3 4 5 6 7 8 9 + - * / ( ) . . Erros como parênteses desbalanceados, símbolos inválidos ou expressões incompletas também são tratados para evitar falhas na construção da árvore.

O sistema também trata corretamente **números decimais representados com ponto (`.`)**, permitindo que a árvore aritmética manipule e calcule valores de ponto flutuante (como `3.5 + 1.2`) com precisão, graças ao uso do tipo `float`.

A partir da árvore construída, é possível:

- Exibir a estrutura hierárquica da árvore no console
- Percorrer a árvore em diferentes ordens (pré, em, pós e por níveis)
- Realizar o **cálculo da expressão aritmética** representada pela árvore

---

## 📌 Objetivos

- ✅ Trabalhar com estrutura de dados em árvore (binária)
- ✅ Aplicar conceitos de pilha e filas para manipulação de expressões
- ✅ Interpretar e calcular expressões numéricas com operadores básicos
- ✅ Proporcionar um sistema modular e extensível em Java

---

## 🧠 Estrutura da Solução

### 1. Conversão de Infixa para Pós-fixa

A conversão é feita pela classe `InfixToSufix`, que:
- Valida os caracteres da expressão
- Lida com operadores com diferentes precedências
- Garante o balanceamento dos parênteses
- Retorna uma string formatada em pós-fixa para a criação da árvore

### 2. Construção da Árvore a partir da Pós-fixa

A classe `Arvore` implementa o método `construirArvore(String expressaoPósFixa)`, que:
- Percorre cada token da expressão
- Cria um nó do tipo `NoOperando` se o token for um número
- Cria um nó do tipo `NoOperador` se o token for um operador
- Monta a árvore conectando os nós usando uma pilha
- Garante que o último item da pilha seja a **raiz da árvore**

Exemplo visual da árvore:

Expressão pós-fixa: `3 4 5 * +`
```
    +
   / \
  3   *
     / \
    4   5
```

### 3. Percursos da Árvore

A árvore pode ser percorrida em:
- **Pré-ordem (prefixa)**: raiz → esquerda → direita
- **Em ordem (infixa)**: esquerda → raiz → direita
- **Pós-ordem (sufixa)**: esquerda → direita → raiz
- **Em largura (nível a nível)**: usando uma `Queue`

### 4. Cálculo da Expressão

O cálculo é feito recursivamente pelos métodos `visitar()` das classes `NoOperador` e `NoOperando`, de acordo com a estrutura da árvore.

---

## 🧩 Estrutura de Classes

```bash
📦 Projeto CalculadoraAritmetica
 ┣ 📄 Main.java               → Interface principal via terminal
 ┣ 📄 InfixToSufix.java       → Conversão de notação infixa para pós-fixa
 ┣ 📄 Arvore.java             → Representa a árvore binária de expressão
 ┣ 📄 No.java                 → Classe abstrata base dos nós
 ┣ 📄 NoOperador.java         → Nó que contém um operador e faz cálculo
 ┗ 📄 NoOperando.java         → Nó que contém um número float
```