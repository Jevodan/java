package ru.jevo.cdi;

import ru.jevo.cdi.service.Knight;
import ru.jevo.cdi.service.MegaService;
import ru.jevo.cdi.service.TestService;

import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.CDI;


public class AppCDI {

    public static void main(String[] args) {

        /**
         *  Инициализация enterprise компоненты weldsi
         */
        SeContainerInitializer.newInstance().addPackages(AppCDI.class).initialize();

        /**
         * Получить класс megaService - синглтон
         */
        final MegaService megaService = CDI.current().select(MegaService.class).get();

        /**
         *  Получить класс testService - обычный
         */
        final TestService testService = CDI.current().select(TestService.class).get();

        final Knight knight = CDI.current().select(Knight.class).get();

        System.out.println(knight.getUnit().getName());

        System.out.println("***");
        System.out.println("megaService: " + megaService.hashCode());
        System.out.println("testService: " + testService.hashCode());
        System.out.println();

        System.out.println("***");
        System.out.println("megaService:" + CDI.current().select(MegaService.class).get().hashCode());
        System.out.println("megaService:" + CDI.current().select(MegaService.class).get().hashCode());
        System.out.println("megaService:" + CDI.current().select(MegaService.class).get().hashCode());
        System.out.println("***");

        System.out.println("***");
        System.out.println("testService:" + CDI.current().select(TestService.class).get().hashCode());
        System.out.println("testService:" + CDI.current().select(TestService.class).get().hashCode());
        System.out.println("testService:" + CDI.current().select(TestService.class).get().hashCode());
        System.out.println("***");




    }
}
