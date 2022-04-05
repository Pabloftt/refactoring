package com.kreitek.refactor.mal.Documents;

import com.kreitek.refactor.mal.Interface.ValidaDocumentos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CIF implements ValidaDocumentos {

    @Override
    public void valida(String number) {
        int error = 1;

        if (number != null) {
            final String cifUP = number.toUpperCase();

            try {
                // si el primer caracter no es uno de los válidos entonces ya es incorrecto
                if ("ABCDEFGHJKLMNPQRSUVW".indexOf(cifUP.charAt(0)) == -1) {
                    error = 0; //mal
                }
                // si no cumple el patrón de CIF es incorrecto
                final Pattern mask = Pattern
                        .compile("[ABCDEFGHJKLMNPQRSUVW][0-9]{7}[A-Z[0-9]]{1}");
                final Matcher matcher = mask.matcher(cifUP);
                if (!matcher.matches()) {
                    error = 0;
                }

                final char primerCar = cifUP.charAt(0);
                final char ultimoCar = cifUP.charAt(cifUP.length() - 1);
                TypeLastChar tipUltCar;

                // si empiezo por P,Q, S, K o W la última letra tiene que ser una LETRA
                if (primerCar == 'P' || primerCar == 'Q' || primerCar == 'S' || primerCar == 'K' || primerCar == 'W') {
                    tipUltCar = TypeLastChar.LETRA;
                    if (!(ultimoCar >= 'A' && ultimoCar <= 'Z')) {
                        error = 0;
                    }
                    // si empiezo por A, B, E o H la última letra tiene que ser un número
                }
                else if (primerCar == 'A' || primerCar == 'B' || primerCar == 'E'
                        || primerCar == 'H') {
                    tipUltCar = TypeLastChar.NUMERO;
                    if (!(ultimoCar >= '0' && ultimoCar <= '9')) {
                        error = 0;
                    }
                    // en otro caso la última letra puede ser cualquier cosa
                }
                else {
                    tipUltCar = TypeLastChar.AMBOS;
                }

                final String digitos = cifUP.substring(1, cifUP.length() - 1);

                // sumo los pares
                Integer sumaPares = 0;
                for (int i = 1; i <= digitos.length() - 1; i = i + 2) {
                    sumaPares += Integer.parseInt(digitos.substring(i, i + 1));
                }

                // sumo los impares
                Integer sumaImpares = 0;
                for (int i = 0; i <= digitos.length() - 1; i = i + 2) {
                    Integer cal = Integer.parseInt(digitos.substring(i, i + 1)) * 2;
                    if (cal.toString().length() > 1) {
                        cal = Integer.parseInt(cal.toString().substring(0, 1))
                                + Integer.parseInt(cal.toString().substring(1, 2));
                    }
                    sumaImpares += cal;
                }

                // los sumo todos
                final Integer total = sumaPares + sumaImpares;

                // calculo el número de control
                Integer numControl = 10 - (total % 10);

                int pos = numControl == 10 ? 0 : numControl;
                final char carControl = "JABCDEFGHI".charAt(pos);

                // con el número de control calculado validamos
                if (tipUltCar == TypeLastChar.NUMERO) {

                    final Integer ultCar = Integer.parseInt(Character
                            .toString(ultimoCar));
                    if (pos != ultCar.intValue()) {
                        error = 0;
                    }
                } else if (tipUltCar == TypeLastChar.LETRA) {
                    if (carControl != ultimoCar) {
                        error = 0;
                    }
                } else {
                    Integer ultCar = -1;
                    ultCar = "JABCDEFGHI".indexOf(ultimoCar);

                    if (ultCar < 0) {
                        ultCar = Integer.parseInt(Character.toString(ultimoCar));
                        if (pos != ultCar.intValue()) {
                            error = 0;
                        }
                    }
                    if ((pos != ultCar.intValue()) && (carControl != ultimoCar)) {
                        error = 0;
                    }
                }
            }
            catch(Exception e){
                imprime("Datos introducidos incorrectos "+e);
            }
        }
        if (error == 1){
            imprime("CIF " + number + " es: True");// correcto
        }
        else{
            imprime("CIF " + number + " es: False");// algo no se cumple
        }

    }

    @Override
    public void imprime(String mensaje) {
        System.out.println(mensaje);
    }
}
