package padroescomportamentais.integracao;

public abstract class Bilheteria {

    protected abstract Desconto criarDesconto();

    protected float calcularTaxa(float valor) {
        return 0.0f;
    }

    public String getTipo() {
        return "Bilheteria";
    }

    public final String emitirIngresso(String filme, float preco) {
        validarPreco(preco);
        Desconto desconto = criarDesconto();                       // Factory Method
        float valorComDesconto = desconto.aplicar(preco);          // Strategy
        float total = valorComDesconto + calcularTaxa(valorComDesconto);
        RegistroDeVendas.getRegistroDeVendas().registrar(total);   // Singleton
        return getTipo() + "{" +
                "filme='" + filme + '\'' +
                ", total=" + total +
                '}';
    }

    private void validarPreco(float preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
    }
}
