package edu.quinnipiac.ser210.navdrawer;

import java.io.Serializable;

/**
 *
 * Coin Holder Class
 * Created by Brian Carballo
 * Crypto-Current
 *
 * Class holds information about a coin. Holds a coin's name(for identification),
 * symbol(For easy conversion), value(in USD), and rank(compared to other coins).
 *
 *
 */
public class CoinHolder implements Serializable {

    private String name, symbol;
    private int value, rank;

    //Constructor saves values
    public CoinHolder(String name, String symbol, int rank, int value){
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.value = value;
    }

    //Getter methods for easy access
    public String getName(){return name;}
    public String getSymbol(){return symbol;}
    public int getValue(){return value;}
    public int getRank(){return rank;}
}
