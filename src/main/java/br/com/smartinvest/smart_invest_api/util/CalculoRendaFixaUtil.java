package br.com.smartinvest.smart_invest_api.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculoRendaFixaUtil {
    public static BigDecimal calcularValorFinal(BigDecimal valorInicial, BigDecimal taxaJuros, Integer prazoMeses) {
        // taxaJuros em percentual (ex: 10 = 10%)
        BigDecimal taxa = taxaJuros.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);

        // (1 + taxa) ^ prazoMeses
        BigDecimal fator = BigDecimal.ONE.add(taxa).pow(prazoMeses);

        // valorInicial * (1 + taxa) ^ prazoMeses
        return valorInicial.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }
}
