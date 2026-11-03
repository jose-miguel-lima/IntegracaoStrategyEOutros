package padroescomportamentais.integracao;

public class BilheteriaEstudante extends Bilheteria {

    @Override
    protected Desconto criarDesconto() {
        return DescontoMeiaEntrada.getDescontoMeiaEntrada();
    }

    @Override
    public String getTipo() {
        return "BilheteriaEstudante";
    }
}
