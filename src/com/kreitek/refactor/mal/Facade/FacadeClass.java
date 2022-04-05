package com.kreitek.refactor.mal.Facade;

import com.kreitek.refactor.mal.Documents.CIF;
import com.kreitek.refactor.mal.Documents.DNI;
import com.kreitek.refactor.mal.Documents.NIE;
import com.kreitek.refactor.mal.Interface.ValidaDocumentos;

public class FacadeClass {

    private ValidaDocumentos  dni;
    private ValidaDocumentos  cif;
    private ValidaDocumentos  nie;

    public FacadeClass(){
        cif = new CIF();
        dni = new DNI();
        nie = new NIE();
    }

    public void validateDNI(String number){
        dni.valida(number);
    }

    public void validateCIF(String number){ cif.valida(number); }

    public void validateNIE(String number){
        nie.valida(number);
    }

}
