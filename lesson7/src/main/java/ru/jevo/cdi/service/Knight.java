package ru.jevo.cdi.service;

import lombok.Data;

import javax.inject.Inject;

@Data
public class Knight {

    /**
     * без Inject будет тупо null ,
     * а с инжект создаст нам класс (Можно сделать большую вложенность)
     */
    @Inject
    private Unit unit;

}
