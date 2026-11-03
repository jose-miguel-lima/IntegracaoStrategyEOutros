package padroescomportamentais.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BilheteriaTest {

    @BeforeEach
    void limparRegistro() {
        RegistroDeVendas.getRegistroDeVendas().limpar();
    }

    // Factory Method:

    @Test
    void bilheteriaComumDeveCriarDescontoNenhum() {
        assertSame(DescontoNenhum.getDescontoNenhum(), new BilheteriaComum().criarDesconto());
    }

    @Test
    void bilheteriaEstudanteDeveCriarMeiaEntrada() {
        assertSame(DescontoMeiaEntrada.getDescontoMeiaEntrada(), new BilheteriaEstudante().criarDesconto());
    }

    @Test
    void bilheteriaOnlineDeveCriarDescontoPromocional() {
        assertSame(DescontoPromocional.getDescontoPromocional(), new BilheteriaOnline().criarDesconto());
    }

    // Template Method + Strategy: o resultado de cada venda

    @Test
    void deveEmitirIngressoComum() {
        Bilheteria bilheteria = new BilheteriaComum();
        assertEquals("Bilheteria{filme='Matrix', total=40.0}", bilheteria.emitirIngresso("Matrix", 40.0f));
    }

    @Test
    void deveEmitirIngressoEstudante() {
        Bilheteria bilheteria = new BilheteriaEstudante();
        assertEquals("BilheteriaEstudante{filme='Matrix', total=20.0}", bilheteria.emitirIngresso("Matrix", 40.0f));
    }

    @Test
    void deveEmitirIngressoOnlineComDescontoETaxa() {
        Bilheteria bilheteria = new BilheteriaOnline();
        assertEquals("BilheteriaOnline{filme='Matrix', total=35.0}", bilheteria.emitirIngresso("Matrix", 40.0f));
    }

    @Test
    void naoDeveEmitirIngressoComPrecoInvalido() {
        try {
            Bilheteria bilheteria = new BilheteriaComum();
            bilheteria.emitirIngresso("Matrix", 0.0f);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Preço inválido", e.getMessage());
        }
    }

    @Test
    void naoDeveRegistrarVendaQuandoPrecoForInvalido() {
        try {
            new BilheteriaComum().emitirIngresso("Matrix", -10.0f);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(0, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
        }
    }

    // Estrutura do Template Method

    @Test
    void emitirIngressoDeveSerFinal() throws NoSuchMethodException {
        int modificadores = Bilheteria.class.getMethod("emitirIngresso", String.class, float.class).getModifiers();
        assertTrue(Modifier.isFinal(modificadores));
    }

    @Test
    void deveExecutarOsPassosNaOrdemDoTemplate() {
        List<String> chamadas = new ArrayList<>();

        Bilheteria espiao = new Bilheteria() {
            @Override
            protected Desconto criarDesconto() {
                chamadas.add("criarDesconto");
                return preco -> {
                    chamadas.add("aplicarDesconto");
                    return preco;
                };
            }

            @Override
            protected float calcularTaxa(float valor) {
                chamadas.add("calcularTaxa");
                return 0.0f;
            }
        };

        espiao.emitirIngresso("Matrix", 40.0f);

        assertEquals(List.of("criarDesconto", "aplicarDesconto", "calcularTaxa"), chamadas);
        assertEquals(1, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
    }

    @Test
    void deveUsarEstrategiaNovaCriadaPorUmaNovaBilheteria() {
        Bilheteria cupom = new Bilheteria() {
            @Override
            protected Desconto criarDesconto() {
                return preco -> preco - 5.0f;
            }
        };
        assertEquals("Bilheteria{filme='Matrix', total=35.0}", cupom.emitirIngresso("Matrix", 40.0f));
    }

    // Singleton:

    @Test
    void deveRegistrarVendasDeTodasAsBilheteriasNoMesmoRegistro() {
        new BilheteriaComum().emitirIngresso("Matrix", 40.0f);       // 40
        new BilheteriaEstudante().emitirIngresso("Matrix", 40.0f);   // 20
        new BilheteriaOnline().emitirIngresso("Matrix", 40.0f);      // 35

        assertEquals(95.0f, RegistroDeVendas.getRegistroDeVendas().getTotalArrecadado());
        assertEquals(3, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
    }

    @Test
    void deveRegistrarOTotalComTaxaEDesconto() {
        new BilheteriaOnline().emitirIngresso("Matrix", 40.0f);
        assertEquals(35.0f, RegistroDeVendas.getRegistroDeVendas().getTotalArrecadado());
    }

}
