package br.com.teste.demo.util;

import br.com.teste.demo.exception.BusinessRuleException;
import org.springframework.stereotype.Service;

@Service
public class Beans {

    public Long parseLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (Exception e) {
            throw new BusinessRuleException("this value is not a number");
        }
    }
}
