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

//Final fragment will use recycle view
public class CoinListFragment extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coin_list, container, false);
    }
}