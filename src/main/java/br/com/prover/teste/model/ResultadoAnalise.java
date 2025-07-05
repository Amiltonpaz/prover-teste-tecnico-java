package br.com.prover.teste.model;

import java.util.Map;

public class ResultadoAnalise {
    private final int totalPalavrasDistintas;
    private final Map<String, Integer> ocorrencias;

    public ResultadoAnalise(int totalPalavrasDistintas, Map<String, Integer> ocorrencias) {
        this.totalPalavrasDistintas = totalPalavrasDistintas;
        this.ocorrencias = ocorrencias;
    }

    // Getters para acessar os dados na página JSF
    public int getTotalPalavrasDistintas() {
        return totalPalavrasDistintas;
    }

    public Map<String, Integer> getOcorrencias() {
        return ocorrencias;
    }
}