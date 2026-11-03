package padroescomportamentais.integracao;

public class DescontoPromocional implements Desconto {

    private static DescontoPromocional descontoPromocional = new DescontoPromocional();

    private DescontoPromocional() {};

    public static DescontoPromocional getDescontoPromocional() {
        return descontoPromocional;
    }

    public float aplicar(float preco) {
        return preco * 0.75f;
    }
}
