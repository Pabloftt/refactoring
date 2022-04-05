package com.kreitek.refactor.mal.Documents;

import com.kreitek.refactor.mal.Interface.ValidaDocumentos;

import java.io.IOException;

public class DNI implements ValidaDocumentos {

    @Override
    public void valida(String number) {

        try {
            // posibles letras del DNI
            String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";
            // los 8 primeros caracteres deben de ser números
            String intPartDNI = number.trim().replaceAll(" ", "").substring(0, 8);
            // el último es un dígito de control
            char ltrDNI = number.charAt(8);
            // cálculo de la letra mediante el módulo de 23
            int valNumDni = Integer.parseInt(intPartDNI) % 23;
            // comprobación de que todo está correcto
            if (number.length() != 9 || isNumeric(intPartDNI) == false || dniChars.charAt(valNumDni) != ltrDNI) {
                imprime("DNI " + number + " es: False");// algo no se cumple
            } else {
                imprime("DNI " + number + " es: True");// correcto
            }
        }
        catch(Exception e){
            imprime("Datos introducidos incorrectos "+e);
        }
    }

    @Override
    public void imprime(String mensaje) {
        System.out.println(mensaje);
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
