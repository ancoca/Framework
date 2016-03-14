package framework.functions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import framework.module.config.model.classes.ClassConfig;

public class Format {

    public static String format1decimal(float number) {
        DecimalFormat nformat = new DecimalFormat(".0");
        
        return nformat.format(number);
    }

    public static String format2decimal(float number) {
        DecimalFormat nformat = new DecimalFormat(".00");
        
        return nformat.format(number);
    }

    public static String format3decimal(float number) {
        DecimalFormat nformat = new DecimalFormat(".000");
        
        return nformat.format(number);
    }

    public static String formatCurrency(float moneda) {
        NumberFormat coin = null;
        switch (ClassConfig.getInstance().getCurrency()) {
            case '$':
                coin = NumberFormat.getCurrencyInstance(Locale.US);// Dolar
                moneda = moneda*1.0844f;
                break;

            case '£':
                coin = NumberFormat.getCurrencyInstance(Locale.UK);	// Libra
                moneda = moneda*0.72686f;
                break;

            case '€':
                coin = NumberFormat.getCurrencyInstance(Locale.FRANCE);	// Euro
                break;
        }
        
        return coin.format(moneda);
    }
}
