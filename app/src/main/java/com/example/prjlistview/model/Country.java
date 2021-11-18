package com.example.prjlistview.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Country implements Comparable{


    private String cNAme;
    private String cCapital;

    public Country(String cNAme, String cCapital) {
        this.cNAme = cNAme;
        this.cCapital = cCapital;
    }

    public String getcNAme() {
        return cNAme;
    }

    public void setcNAme(String cNAme) {
        this.cNAme = cNAme;
    }

    public String getcCapital() {
        return cCapital;
    }

    public void setcCapital(String cCapital) {
        this.cCapital = cCapital;
    }


    @NonNull
    @Override
    public String toString() {
        return cNAme;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        Country otherCountry = (Country) obj;

        if(otherCountry.getcNAme().equalsIgnoreCase(cNAme))
        {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        Country country = (Country) o;


        //+1 current object > parameter
        //0 current object = parameter
        //-1 current object < parameter


        if (cNAme.compareTo(country.getcNAme())>0)
            return 1;
        else
            if (cNAme.compareTo(country.getcNAme())== 0)
                return 0;
            else
                return -1;
    }
}
