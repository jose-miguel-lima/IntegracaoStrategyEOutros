package padroescomportamentais.integracao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistroDeVendasTest {

    @BeforeEach
    void limparRegistro() {
        RegistroDeVendas.getRegistroDeVendas().limpar();
    }

    @Test
    void deveRetornarSempreAMesmaInstancia() {
        assertSame(RegistroDeVendas.getRegistroDeVendas(), RegistroDeVendas.getRegistroDeVendas());
    }

    @Test
    void deveIniciarZerado() {
        assertEquals(0.0f, RegistroDeVendas.getRegistroDeVendas().getTotalArrecadado());
        assertEquals(0, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
    }

    @Test
    void deveAcumularVendasEntreReferenciasDiferentes() {
        RegistroDeVendas.getRegistroDeVendas().registrar(10.0f);
        RegistroDeVendas outraReferencia = RegistroDeVendas.getRegistroDeVendas();
        outraReferencia.registrar(15.0f);
        assertEquals(25.0f, RegistroDeVendas.getRegistroDeVendas().getTotalArrecadado());
        assertEquals(2, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
    }

    @Test
    void deveLimparORegistro() {
        RegistroDeVendas.getRegistroDeVendas().registrar(10.0f);
        RegistroDeVendas.getRegistroDeVendas().limpar();
        assertEquals(0.0f, RegistroDeVendas.getRegistroDeVendas().getTotalArrecadado());
        assertEquals(0, RegistroDeVendas.getRegistroDeVendas().getQuantidadeVendas());
    }

}
