package padroescomportamentais.integracao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescontoTest {

    @Test
    void deveManterPrecoSemDesconto() {
        assertEquals(40.0f, DescontoNenhum.getDescontoNenhum().aplicar(40.0f));
    }

    @Test
    void deveAplicarMeiaEntrada() {
        assertEquals(20.0f, DescontoMeiaEntrada.getDescontoMeiaEntrada().aplicar(40.0f));
    }

    @Test
    void deveAplicarDescontoPromocional() {
        assertEquals(30.0f, DescontoPromocional.getDescontoPromocional().aplicar(40.0f));
    }

    @Test
    void deveRetornarSempreAMesmaInstanciaDeCadaEstrategia() {
        assertSame(DescontoNenhum.getDescontoNenhum(), DescontoNenhum.getDescontoNenhum());
        assertSame(DescontoMeiaEntrada.getDescontoMeiaEntrada(), DescontoMeiaEntrada.getDescontoMeiaEntrada());
        assertSame(DescontoPromocional.getDescontoPromocional(), DescontoPromocional.getDescontoPromocional());
    }

    @Test
    void deveAceitarEstrategiaNovaComoLambda() {
        Desconto cupom = preco -> preco - 5.0f;
        assertEquals(35.0f, cupom.aplicar(40.0f));
    }

}
