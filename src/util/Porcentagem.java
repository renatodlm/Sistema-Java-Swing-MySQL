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
public class Porcentagem {

    public String ValorSemPercentual(String pString) {
        String valorString = pString.replace("%", "").replace(",", ".");
        return String.format(valorString);
    }

    public String ValorComPercentual(String pString) {
        String valorDouble = pString.replace(".", ",");
        StringBuilder strBuilder = new StringBuilder(valorDouble);
        return String.valueOf(strBuilder.append("%"));
    }
}
