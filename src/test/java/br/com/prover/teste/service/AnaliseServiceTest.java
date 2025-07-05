package br.com.prover.teste.service;

import br.com.prover.teste.model.ResultadoAnalise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnaliseServiceTest {

    private AnaliseService analiseService;
    @BeforeEach
    void setUp() {
        // Cria uma nova instância do serviço antes de cada teste
        analiseService = new AnaliseService();
    }

    @Test
    @DisplayName("Deve contar palavras simples corretamente")
    void testContagemSimples() {
        String frase = "casa bola casa";
        ResultadoAnalise resultado = analiseService.analisarFrase(frase);

        assertEquals(2, resultado.getTotalPalavrasDistintas());
        assertEquals(2, resultado.getOcorrencias().get("casa"));
        assertEquals(1, resultado.getOcorrencias().get("bola"));
    }

    @Test
    @DisplayName("Deve tratar frase nula retornando resultado vazio")
    void testFraseNula() {
        ResultadoAnalise resultado = analiseService.analisarFrase(null);
        assertEquals(0, resultado.getTotalPalavrasDistintas());
        assertTrue(resultado.getOcorrencias().isEmpty());
    }

    @Test
    @DisplayName("Deve tratar frase vazia retornando resultado vazio")
    void testFraseVazia() {
        ResultadoAnalise resultado = analiseService.analisarFrase("   ");
        assertEquals(0, resultado.getTotalPalavrasDistintas());
        assertTrue(resultado.getOcorrencias().isEmpty());
    }

    @Test
    @DisplayName("Deve ignorar maiúsculas/minúsculas")
    void testIgnorarCase() {
        String frase = "Teste teste TESTE";
        ResultadoAnalise resultado = analiseService.analisarFrase(frase);

        assertEquals(1, resultado.getTotalPalavrasDistintas());
        assertEquals(3, resultado.getOcorrencias().get("teste"));
    }

    @Test
    @DisplayName("Deve tratar pontuações diversas corretamente")
    void testComPontuacao() {
        String frase = "Olá, mundo! Olá de novo.";
        ResultadoAnalise resultado = analiseService.analisarFrase(frase);

        assertEquals(4, resultado.getTotalPalavrasDistintas());
        assertEquals(2, resultado.getOcorrencias().get("olá"));
        assertEquals(1, resultado.getOcorrencias().get("mundo"));
        assertEquals(1, resultado.getOcorrencias().get("de"));
        assertEquals(1, resultado.getOcorrencias().get("novo"));
    }
}
