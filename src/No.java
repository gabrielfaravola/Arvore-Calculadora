public abstract class No {
    No esquerda;
    No direita;

    public No() {
        this.esquerda = null;
        this.direita = null;
    }

    public float visitar() {
        return Float.NaN; // Valor indefinido
    }
}
