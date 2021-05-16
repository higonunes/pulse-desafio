package com.pulse.desafiotecnico.service;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
public class BoletoService {

    public Date calculaDataVencimento() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    public String geraNumeroBoleto() {
        return String.valueOf(Math.abs(new Random().nextLong()));
    }
}
