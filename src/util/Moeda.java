/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Work
 */
public class Moeda {

    public String valorStringTODoubleAtt(String pString) {
        if (pString != null && !pString.equals("null")) {

            int pontoConter = 0;
            String formatDoubleString;
            String valorString = pString;
            for (int i = 0; i < pString.length(); i++) {
                if (pString.charAt(i) == '.') {
                    pontoConter++;
                }
            }

            if (pontoConter > 1) {

                formatDoubleString = valorString.replace(".", "").replace("R$", "").replace(" ", "").replace(",", ".");
                formatDoubleString = formatDoubleString.replace(".", "").replace(",", ".");
            } else {
                formatDoubleString = valorString.replace("R$", "").replace(" ", "");

            }
            Double valorformatado;
            try {
                valorformatado = new Double(formatDoubleString);
            } catch (NumberFormatException e) {
                valorformatado = new Double(formatDoubleString.replace(".", "").replace(",", "."));
            }
            NumberFormat formatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return String.format((formatado.format(valorformatado)));
        } else {
            return "R$ 0,00";
        }
    }

    public String FommatoStringoSomarMil(String pString) {
        String valorMil = pString.replace(".", "").replace("R$", "").replace(" ", "").replace(",", ".");
        valorMil = valorMil.replace(".", "x").replace(".", ",").replace("x", ".").replace("R$", "").replace(" ", "");
        valorMil = valorMil.replace(",", ".").replace("R$", "").replace(" ", "");
        valorMil = valorMil.replace(",", "").replace("R$", "").replace(" ", "");
        Float valorMilFormat;
        try {
            valorMilFormat = Float.parseFloat(valorMil);
        } catch (NumberFormatException e) {
            valorMilFormat = Float.parseFloat("0.00");
        }

        NumberFormat formatado = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        String Retorno = String.format(formatado.format(valorMilFormat)).replace("$", "").replace(",", "");
        return Retorno;
    }

    public String valorStringTODoubleTeste(String pString) {
        String valorString = pString;
        String formatDoubleString = valorString.replace(".", "").replace("R$", "").replace(" ", "").replace(",", ".");
        Double valorformatado = new Double(formatDoubleString);
        NumberFormat formatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return String.format((formatado.format(valorformatado)));
    }

    public String FommatoStringoSomarMilTeste(String pString) {
        String valorMil = pString.replace("R$", "").replace(" ", "");
        Float valorMilFormat = Float.parseFloat(valorMil);
        NumberFormat formatado = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        return String.format(formatado.format(valorMilFormat));
    }

}
