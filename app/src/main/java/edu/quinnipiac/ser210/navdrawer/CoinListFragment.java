/*
 *Fragment fo the coin list screen activity
 */
package edu.quinnipiac.ser210.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Coin List Fragment
 * Created by Kevin Sangurima and Brian Carballo
 * Crypto-Current
 *
 * Fragment contains sorted list of coins and displays them in a list
 */

//Final fragment will use recycle view
public class CoinListFragment extends ListFragment {


    private CoinInfoHandler handler;
    ArrayAdapter<String> coinList;
    CoinHolder[] coinInfo;
    ArrayList<String> coinNames;

    //Required empty constructor
    public CoinListFragment(){

    }

    //Method that allows for fragment to instantiate with specific list values
    public static CoinListFragment newInstance(Bundle bundle) {
        Bundle fragBundle = bundle;
        //Creates new fragment
        CoinListFragment fragment = new CoinListFragment();
        //Gives fragment a bundle to start with
        fragment.setArguments(fragBundle);
        //Debug method call
        new CoinInfoHandler(10);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coin_list, container, false);
        //Gives bundle to method for unbundling
        readBundle(getArguments());

       // handler = new CoinInfoHandler(10);

        //Instantiates variables
        coinInfo = handler.getCoinArray();
        coinNames = new ArrayList<String>();
        for (int i = 0; i <coinInfo.length; i++) {
            //Grabs coin names from array and creates sorted ArrayList
            coinNames.add(handler.getCoinArray()[i].getName());
        }
        //Adapts ArrayList to list in fragment
        coinList=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                coinNames);
        setListAdapter(coinList);

        return layout;


    }

    //Creates a new CoinInfoHandler with bundle contents
    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            handler = ( new CoinInfoHandler((CoinHolder[]) bundle.getSerializable("data")));
        }
    }
}