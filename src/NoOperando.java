public class NoOperando extends No {
    private float valor;

    public NoOperando(float valor) {
        this.valor = valor;
    }

    @Override
    public float visitar() {
        return valor;
    }
}
