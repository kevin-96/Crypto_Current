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

//Final fragment will use recycle view
public class CoinListFragment extends ListFragment {


    private CoinInfoHandler handler;
    ArrayAdapter<String> coinList;
    CoinHolder[] coinInfo;
    ArrayList<String> coinNames;

    public CoinListFragment(){

    }

    public static CoinListFragment newInstance(Bundle bundle) {
        Bundle fragBundle = bundle;

        CoinListFragment fragment = new CoinListFragment();
        fragment.setArguments(fragBundle);
        new CoinInfoHandler(10);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_coin_list, container, false);
        readBundle(getArguments());
       // handler = new CoinInfoHandler(10);
        coinInfo = handler.getCoinArray();
        coinNames = new ArrayList<String>();
        for (int i = 0; i <coinInfo.length; i++) {
            coinNames.add(handler.getCoinArray()[i].getName());
        }
        coinList=new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                coinNames);
        setListAdapter(coinList);

        return layout;


    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            handler = ( new CoinInfoHandler((CoinHolder[]) bundle.getSerializable("data")));
        }
    }
}