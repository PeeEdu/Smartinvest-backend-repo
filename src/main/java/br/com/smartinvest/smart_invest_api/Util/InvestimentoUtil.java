package br.com.smartinvest.smart_invest_api.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoUtil {

    public static class ResultadoRendaFixa {
        public BigDecimal valorFinal;
        public BigDecimal jurosTotais;

        public ResultadoRendaFixa(BigDecimal valorFinal, BigDecimal jurosTotais) {
            this.valorFinal = valorFinal;
            this.jurosTotais = jurosTotais;
        }
    }

    public static class ResultadoRendaVariavel {
        public BigDecimal valorFinal;
        public BigDecimal ganhoOuPerda;
        public List<BigDecimal> historico;

        public ResultadoRendaVariavel(BigDecimal valorFinal, BigDecimal ganhoOuPerda, List<BigDecimal> historico) {
            this.valorFinal = valorFinal;
            this.ganhoOuPerda = ganhoOuPerda;
            this.historico = historico;
        }
    }

    public static ResultadoRendaFixa calcularRendaFixa(BigDecimal valorInicial, int prazoMeses, BigDecimal taxaMensal) {
        validarParametros(valorInicial, prazoMeses, taxaMensal);

        BigDecimal fator = BigDecimal.ONE.add(taxaMensal);
        BigDecimal valorFinal = valorInicial.multiply(fator.pow(prazoMeses)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal jurosTotais = valorFinal.subtract(valorInicial).setScale(2, RoundingMode.HALF_UP);

        return new ResultadoRendaFixa(valorFinal, jurosTotais);
    }

    public static ResultadoRendaVariavel calcularRendaVariavel(BigDecimal valorInicial, int prazoMeses, List<BigDecimal> variacoesMensais) {
        validarParametros(valorInicial, prazoMeses, null);

        if (variacoesMensais == null || variacoesMensais.size() != prazoMeses) {
            throw new IllegalArgumentException("A lista de variações deve conter exatamente " + prazoMeses + " elementos.");
        }

        List<BigDecimal> historico = new ArrayList<>();
        BigDecimal valorAtual = valorInicial;

        for (BigDecimal variacao : variacoesMensais) {
            if (variacao == null) throw new IllegalArgumentException("Variação mensal inválida.");
            valorAtual = valorAtual.multiply(BigDecimal.ONE.add(variacao));
            historico.add(valorAtual.setScale(2, RoundingMode.HALF_UP));
        }

        BigDecimal ganhoOuPerda = valorAtual.subtract(valorInicial).setScale(2, RoundingMode.HALF_UP);
        return new ResultadoRendaVariavel(valorAtual.setScale(2, RoundingMode.HALF_UP), ganhoOuPerda, historico);
    }

    private static void validarParametros(BigDecimal valorInicial, int prazoMeses, BigDecimal taxa) {
        if (valorInicial == null || valorInicial.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inicial deve ser maior que zero.");
        }
        if (prazoMeses <= 0) {
            throw new IllegalArgumentException("Prazo deve ser maior que zero.");
        }
        if (taxa != null && taxa.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxa de juros não pode ser negativa.");
        }
    }
}