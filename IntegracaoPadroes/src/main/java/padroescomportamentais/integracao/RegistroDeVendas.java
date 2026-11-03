package padroescomportamentais.integracao;

public class RegistroDeVendas {

    private static RegistroDeVendas registroDeVendas = new RegistroDeVendas();

    private float totalArrecadado;
    private int quantidadeVendas;

    private RegistroDeVendas() {};

    public static RegistroDeVendas getRegistroDeVendas() {
        return registroDeVendas;
    }

    public void registrar(float valor) {
        this.totalArrecadado += valor;
        this.quantidadeVendas++;
    }

    public float getTotalArrecadado() {
        return totalArrecadado;
    }

    public int getQuantidadeVendas() {
        return quantidadeVendas;
    }

    public void limpar() {
        this.totalArrecadado = 0;
        this.quantidadeVendas = 0;
    }
}
