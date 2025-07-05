package br.com.prover.teste.service;

import br.com.prover.teste.model.ResultadoAnalise;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.TreeMap;

@ApplicationScoped // Define que esta classe será um bean CDI gerenciado
public class AnaliseService  implements IAnaliseService{

    /**
     * Este método é sincronizado para garantir que apenas uma thread
     * o execute por vez, conforme o requisito.
     *
     * @param frase A frase a ser analisada.
     * @return Um objeto contendo os resultados da análise.
     */
    @Override
    public synchronized ResultadoAnalise analisarFrase(String frase) {
        if (frase == null || frase.trim().isEmpty()) {
            return new ResultadoAnalise(0, new TreeMap<>());
        }

        // Remove pontuações e converte para minúsculas para uma contagem mais precisa.
        // O regex \\W+ divide a string por qualquer caractere que não seja uma letra ou número.
        String[] palavras = frase.toLowerCase().split("(?U)\\W+");

        // TreeMap é usado para que o resultado final seja ordenado alfabeticamente.
        Map<String, Integer> ocorrencias = new TreeMap<>();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                // Se a palavra já existe no mapa, incrementa o contador. Senão, adiciona com valor 1.
                ocorrencias.put(palavra, ocorrencias.getOrDefault(palavra, 0) + 1);
            }
        }

        int palavrasDistintas = ocorrencias.size();

        return new ResultadoAnalise(palavrasDistintas, ocorrencias);
    }
}
