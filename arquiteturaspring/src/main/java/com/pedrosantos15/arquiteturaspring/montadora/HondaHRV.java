package com.pedrosantos15.arquiteturaspring.montadora;

import com.pedrosantos15.arquiteturaspring.montadora.enums.Montadora;

import java.awt.*;

public class HondaHRV extends Carro{

    public HondaHRV(Motor motor) {
        super(motor);
        setModelo("HRV");
        setCor(Color.BLACK);
        setMontadora(Montadora.HONDA);
    }
}
