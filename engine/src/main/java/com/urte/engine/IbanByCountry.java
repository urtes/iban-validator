package com.urte.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that holds info on valid country codes and IBAN length in those countries.
 * Info is kept in a map whose keys represent country code and values represent length of IBAN in that
 * country.
 * Includes info on partial IBAN countries as well.
 * Datasource: https://www.iban.com/structure.
 */
public class IbanByCountry {

    private IbanByCountry() {
    }

    public final static Map<String, Integer> LENGTH_BY_COUNTRY = new HashMap<String, Integer>() {{
        put("AL", 28);
        put("AD", 24);
        put("AT", 20);
        put("AZ", 28);
        put("BH", 22);
        put("BY", 28);
        put("BE", 16);
        put("BA", 20);
        put("BR", 29);
        put("BG", 22);
        put("CR", 22);
        put("HR", 21);
        put("CY", 28);
        put("CZ", 24);
        put("DK", 18);
        put("DO", 28);
        put("SV", 28);
        put("EE", 20);
        put("FO", 18);
        put("FI", 18);
        put("FR", 27);
        put("GE", 22);
        put("DE", 22);
        put("GI", 23);
        put("GR", 27);
        put("GL", 18);
        put("GT", 28);
        put("VA", 22);
        put("HU", 28);
        put("IS", 26);
        put("IQ", 23);
        put("IE", 22);
        put("IL", 23);
        put("IT", 27);
        put("JO", 30);
        put("KZ", 20);
        put("XK", 20);
        put("KW", 30);
        put("LV", 21);
        put("LB", 28);
        put("LI", 21);
        put("LT", 20);
        put("LU", 20);
        put("MT", 31);
        put("MR", 27);
        put("MU", 30);
        put("MD", 24);
        put("MC", 27);
        put("ME", 22);
        put("NL", 18);
        put("MK", 19);
        put("NO", 15);
        put("PK", 24);
        put("PS", 29);
        put("PL", 28);
        put("PT", 25);
        put("QA", 29);
        put("RO", 24);
        put("LC", 32);
        put("SM", 27);
        put("ST", 25);
        put("SA", 24);
        put("RS", 22);
        put("SC", 31);
        put("SK", 24);
        put("SI", 19);
        put("ES", 24);
        put("SE", 24);
        put("CH", 21);
        put("TL", 23);
        put("TN", 24);
        put("TR", 26);
        put("UA", 29);
        put("AE", 23);
        put("GB", 22);
        put("VG", 24);
        put("DZ", 26);
        put("AO", 25);
        put("BJ", 28);
        put("BF", 28);
        put("BI", 16);
        put("CM", 27);
        put("CV", 25);
        put("CF", 27);
        put("TD", 27);
        put("KM", 27);
        put("CG", 27);
        put("DJ", 27);
        put("EG", 27);
        put("GQ", 27);
        put("GA", 27);
        put("GW", 25);
        put("HN", 28);
        put("IR", 26);
        put("CI", 28);
        put("MG", 27);
        put("ML", 28);
        put("MA", 28);
        put("MZ", 25);
        put("NI", 32);
        put("NE", 28);
        put("SN", 28);
        put("TG", 28);
    }};
}
