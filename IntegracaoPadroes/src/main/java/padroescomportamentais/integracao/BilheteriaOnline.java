package padroescomportamentais.integracao;

public class BilheteriaOnline extends Bilheteria {

    @Override
    protected Desconto criarDesconto() {
        return DescontoPromocional.getDescontoPromocional();
    }

    @Override
    protected float calcularTaxa(float valor) {
        return 5.0f;
    }

    @Override
    public String getTipo() {
        return "BilheteriaOnline";
    }
}
