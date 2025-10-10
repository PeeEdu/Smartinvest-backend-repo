package br.com.smartinvest.smart_invest_api.Util;

import br.com.smartinvest.smart_invest_api.model.RendaVariavel;
import br.com.smartinvest.smart_invest_api.model.RendaVariavelHistorico;
import br.com.smartinvest.smart_invest_api.repository.RendaVariavelHistoricoRepository;
import br.com.smartinvest.smart_invest_api.repository.RendaVariavelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RendaVariavelUtil {

    private final RendaVariavelRepository rendaVariavelRepository;
    private final RendaVariavelHistoricoRepository historicoRepository;
    private final Random random = new Random();

    public void atualizarValores() {
        List<RendaVariavel> acoes = rendaVariavelRepository.findAll();

        for (RendaVariavel acao : acoes) {
            BigDecimal precoAtual = acao.getPrecoAtual();

            // Simula variação de preço entre -5% e +5%
            double percentual = (random.nextDouble() * 10) - 5;
            BigDecimal variacao = precoAtual.multiply(BigDecimal.valueOf(percentual / 100));
            BigDecimal novoPreco = precoAtual.add(variacao).setScale(2, RoundingMode.HALF_UP);

            // Atualiza preço atual
            acao.setPrecoAtual(novoPreco);
            acao.setDataAtualizacao(new Date());

            // Salva no histórico
            RendaVariavelHistorico historico = RendaVariavelHistorico.builder()
                    .acao(acao)
                    .preco(novoPreco)
                    .dataAtualizacao(new Date())
                    .build();

            historicoRepository.save(historico);
        }

        rendaVariavelRepository.saveAll(acoes);

        System.out.println("Valores atualizados e histórico salvo em: " + new Date());
    }
}
