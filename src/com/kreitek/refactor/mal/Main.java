package com.kreitek.refactor.mal;

import com.kreitek.refactor.mal.Facade.FacadeClass;

class  Main
{
    public static void main(String args[]) {

        FacadeClass facadeClass = new FacadeClass();

        PrintHead.cabezera();

        // Creamos DNI correcto
        facadeClass.validateDNI("11111111H");

        // creamos un DNI incorrecto
        facadeClass.validateDNI("24324356A");

        // creamos un NIE correcto
        facadeClass.validateNIE("X0932707B");

        // creamos un NIE incorrecto
        facadeClass.validateNIE("Z2691139Z");

        // creamos un CIF correcto
        facadeClass.validateCIF("W9696294I");

        // creamos un CIF incorrecto
        facadeClass.validateCIF("W9696294A");

    }
}