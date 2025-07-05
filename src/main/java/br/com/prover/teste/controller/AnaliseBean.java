package br.com.prover.teste.controller;

import br.com.prover.teste.model.ResultadoAnalise;
import br.com.prover.teste.service.IAnaliseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named // Permite que a classe seja acessada via EL (ex: #{analiseBean})
@ViewScoped // O estado do bean é preservado enquanto o usuário estiver na mesma página
public class AnaliseBean implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(AnaliseBean.class);
    @Inject
    private IAnaliseService analiseService;
    private String fraseDigitada;
    private ResultadoAnalise resultado;

    /**
     * Método de ação disparado pelo botão na interface.
     */
    public void processarAnalise() {
        log.info("Iniciando análise para a frase: '{}'", fraseDigitada);
        this.resultado = analiseService.analisarFrase(fraseDigitada);
        log.info("Análise concluída. Palavras distintas encontradas: {}", resultado);
    }

    public void limpar() {
        this.fraseDigitada = null;
        this.resultado = null;
        log.info("Formulário e resultados limpos.");
    }

    // Getters e Setters para o binding com a página JSF
    public String getFraseDigitada() {
        return fraseDigitada;
    }

    public void setFraseDigitada(String fraseDigitada) {
        this.fraseDigitada = fraseDigitada;
    }

    public ResultadoAnalise getResultado() {
        return resultado;
    }
}
