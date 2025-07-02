public class NoOperador extends No {
    char operador;

    public NoOperador(char operador) {
        this.operador = operador;
    }

    @Override
    public float visitar() {
        float esquerdo = (esquerda != null) ? esquerda.visitar() : 0;
        float direito = (direita != null) ? direita.visitar() : 0;

        switch (operador) {
            case '+': return esquerdo + direito;
            case '-': return esquerdo - direito;
            case '*': return esquerdo * direito;
            case '/': return (direito != 0) ? esquerdo / direito : Float.NaN;
            default: return Float.NaN;
        }
    }
}
