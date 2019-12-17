package com.urte.engine;

/**
 * Class that represent data that can be either valid or invalid IBAN
 */
public class IbanSource {

    private String iban;
    private boolean valid;

    public IbanSource(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return String.format("%s;%b", iban, valid);
    }
}
