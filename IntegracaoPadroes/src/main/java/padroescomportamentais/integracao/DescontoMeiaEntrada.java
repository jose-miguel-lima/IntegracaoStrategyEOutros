package padroescomportamentais.integracao;

public class DescontoMeiaEntrada implements Desconto {

    private static DescontoMeiaEntrada descontoMeiaEntrada = new DescontoMeiaEntrada();

    private DescontoMeiaEntrada() {};

    public static DescontoMeiaEntrada getDescontoMeiaEntrada() {
        return descontoMeiaEntrada;
    }

    public float aplicar(float preco) {
        return preco / 2;
    }
}
