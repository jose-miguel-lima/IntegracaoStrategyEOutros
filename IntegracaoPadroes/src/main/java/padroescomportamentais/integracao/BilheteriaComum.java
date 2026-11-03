package padroescomportamentais.integracao;

public class BilheteriaComum extends Bilheteria {

    @Override
    protected Desconto criarDesconto() {
        return DescontoNenhum.getDescontoNenhum();
    }

}
