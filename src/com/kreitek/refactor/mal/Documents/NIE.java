package com.kreitek.refactor.mal.Documents;

import com.kreitek.refactor.mal.Interface.ValidaDocumentos;

public class NIE implements ValidaDocumentos {

    @Override
    public void valida(String number) {
        boolean esValido = false;
        int i = 1;
        int caracterASCII = 0;
        char letra = ' ';
        int miNIE = 0;
        int resto = 0;
        char[] asignacionLetra = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X','B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        String nie = number;

        // comprobacion de que la longitud y la letra del NIE es correcta
        try {
            if (nie.length() == 9 && Character.isLetter(nie.charAt(8))
                    && nie.substring(0, 1).toUpperCase().equals("X")
                    || nie.substring(0, 1).toUpperCase().equals("Y")
                    || nie.substring(0, 1).toUpperCase().equals("Z")) {
                do {
                    caracterASCII = nie.codePointAt(i);
                    esValido = (caracterASCII > 47 && caracterASCII < 58);
                    i++;
                }
                while (i < nie.length() - 1 && esValido);
            }
            if (esValido && nie.substring(0, 1).toUpperCase().equals("X")) {
                nie = "0" + nie.substring(1, 9);
            }
            else if (esValido && nie.substring(0, 1).toUpperCase().equals("Y")) {
                nie = "1" + nie.substring(1, 9);
            }
            else if (esValido && nie.substring(0, 1).toUpperCase().equals("Z")) {
                nie = "2" + nie.substring(1, 9);
            }
            // asignacion de la letra correspondiente sacada mediante el modulo 23
            if (esValido) {
                letra = Character.toUpperCase(nie.charAt(8));
                miNIE = Integer.parseInt(nie.substring(1, 8));
                resto = miNIE % 23;
                esValido = (letra == asignacionLetra[resto]);
            }
            if (esValido) {
                imprime("NIE " + number + " es: True");// correcto
            }
            else {
                imprime("NIE " + number + " es: False");// algo no se cumple
            }
        }
        catch(Exception e) {
            imprime("Datos introducidos incorrectos "+e);
        }
    }

    @Override
    public void imprime(String mensaje) {
        System.out.println(mensaje);
    }
}
