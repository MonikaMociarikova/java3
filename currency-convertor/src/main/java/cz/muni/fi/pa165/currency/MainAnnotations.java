/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author xmociar
 */
public class MainAnnotations {

    public static void main(String[] args) {
        
        ApplicationContext applicationContext 
                = new AnnotationConfigApplicationContext("cz.muni.fi.pa165.currency");
        
        CurrencyConvertor currencyConvertor = 
                applicationContext.getBean(CurrencyConvertor.class);
        
        
        currencyConvertor.convert(Currency.getInstance("EUR"),Currency.getInstance("CZK"),BigDecimal.valueOf(1));
        
    }

}
