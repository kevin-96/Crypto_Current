package edu.quinnipiac.ser210.navdrawer;

import junit.framework.TestCase;

public class CoinHolderTest extends TestCase {

    private CoinHolder mCoinHolder;
    public void setUp() throws Exception {
        super.setUp();
        mCoinHolder = new CoinHolder("Bitcoin", "BTC", 0,2010);
    }

    public void tearDown() throws Exception {
    }

    public void testGetName() {
        assertEquals("gh", mCoinHolder.getName());
    }

    public void testGetSymbol() {
        assertEquals("BTC", mCoinHolder.getSymbol());
    }

    public void testGetValue() {
        assertEquals(0, mCoinHolder.getRank());
    }

    public void testGetRank() {
        assertEquals(2010, mCoinHolder.getValue());
    }
}