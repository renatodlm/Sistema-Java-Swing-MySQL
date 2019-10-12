/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Smweb
 */
public class Mascaras {

    public String converterVirgulaParaPonto(String pString) {
        String retorno = new String();
        int tamanhoString = pString.length();
        for (int i = 0; i < tamanhoString; i++) {
            if (pString.charAt(i) == ',') {
                retorno += '.';
            } else {
                retorno += pString.charAt(i);
            }
        }
        return retorno;
    }

    public float arredondamentoComPontoDuasCasas(float pValor) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(this.converterVirgulaParaPonto(df.format(pValor)));
    }

    public String arredondamentoDoubleComPontoDuasCasasString(Double pValor) {
        DecimalFormat df = new DecimalFormat("#.00");
        return this.converterVirgulaParaPonto(df.format(pValor));
    }

    /**
     *
     * @param pString Seta string para a função
     * @return retorna string para a função
     */
    public String DataSalvanosql(String pString) {
        String tString = pString;
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");

        try {
            data = formatar.parse(tString);
        } catch (ParseException e) {
            return null;
        }

        SimpleDateFormat formatarParaSQL = new SimpleDateFormat("yyyy-MM-dd");

        return String.format((formatarParaSQL.format(data)));
    }

    /**
     *
     * @param pString Seta string para a função
     * @return retorna string para a função
     */
    public String DataRecuperasql(String pString) {
        String tString = pString;
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");

        try {
            data = formatar.parse(tString);
        } catch (ParseException e) {
            return null;
        }

        SimpleDateFormat formatarDoSQL = new SimpleDateFormat("dd/MM/yyyy");

        return String.format((formatarDoSQL.format(data)));
    }

    public String valorStringTOBigDecimal(String pString) {
        String valorString = pString.replace(".", ",");
        String formatDoubleString = valorString.replace("R$", "").replace(" ", "").replace(".", "").replace(",", ".");
        Double valorformatado = new Double(formatDoubleString);
        NumberFormat formatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return String.format((formatado.format(valorformatado)));
    }

    /**
     * adiciona um ponto a string
     *
     * @param pString seta ponto a string
     * @return String retorna ponto a string
     */
    public String addPonto(String pString) {
        int pontoConter = 0;
        for (int i = 0; i < pString.length(); i++) {
            if (pString.charAt(i) == '.') {
                pontoConter++;
            }
        }
        if (pontoConter == 0) {
            pString += ".0";
        }
        return pString;
    }

    /**
     * arredonda um valor com ponto string formatada
     *
     * @param pValor seta arredonda um valor com ponto string formatada
     * @return retorna arredonda um valor com ponto string formatada
     */
    public String arredondamentoComPontoDuasCasasString(float pValor) {
        DecimalFormat df = new DecimalFormat("#.00");
        return this.converterVirgulaParaPonto(df.format(pValor));
    }

}
