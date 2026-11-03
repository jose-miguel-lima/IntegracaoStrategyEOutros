package padroescomportamentais.integracao;

public class DescontoNenhum implements Desconto {

    private static DescontoNenhum descontoNenhum = new DescontoNenhum();

    private DescontoNenhum() {};

    public static DescontoNenhum getDescontoNenhum() {
        return descontoNenhum;
    }

    public float aplicar(float preco) {
        return preco;
    }
}
