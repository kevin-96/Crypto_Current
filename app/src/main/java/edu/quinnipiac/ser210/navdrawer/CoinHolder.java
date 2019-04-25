package edu.quinnipiac.ser210.navdrawer;

import java.io.Serializable;

public class CoinHolder implements Serializable {

    private String name, symbol;
    private int value, rank;

    public CoinHolder(String name, String symbol, int rank, int value){
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.value = value;
    }

    public String getName(){return name;}
    public String getSymbol(){return symbol;}
    public int getValue(){return value;}
    public int getRank(){return rank;}
}
