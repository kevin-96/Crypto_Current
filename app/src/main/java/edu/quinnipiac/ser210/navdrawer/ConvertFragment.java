/*
 *Fragment fo the convert screen activity
 */
package edu.quinnipiac.ser210.navdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class ConvertFragment extends Fragment implements View.OnClickListener {

    private CoinInfoHandler handler;
    private String coinName;
    private String currencyName;
    private String coinAmount;
    private String result;
    EditText text;


    public ConvertFragment(){

    }

    public static ConvertFragment newInstance(Bundle bundle) {
        Bundle fragBundle = bundle;

        ConvertFragment fragment = new ConvertFragment();
        fragment.setArguments(fragBundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_convert, container, false);
        readBundle(getArguments());
        ArrayList<String> coins = new ArrayList<String>();
        for (int i = 0; i < handler.getCoinArray().length; i++) {
            coins.add(handler.getCoinArray()[i].getSymbol());
        }

        String[] currency = new String[] {"USD", "EUR", "CAD", "AUD", "JPY", "MXN", "CNY"};

        ArrayAdapter<String> coinAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,coins);
        ArrayAdapter<String> currAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,currency);

        Spinner coinChoose = (Spinner) layout.findViewById(R.id.coinChooseSpinner);
        Spinner currencyChoose = (Spinner) layout.findViewById(R.id.currencyChooseSpinner);
        coinChoose.setAdapter(coinAdapter);
        currencyChoose.setAdapter(currAdapter);
        coinChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                coinName = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        // Stores the value selected on the second spinner related to the currency
        currencyChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currencyName = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Button button = layout.findViewById(R.id.convertButton);
        button.setOnClickListener(this);

        text = layout.findViewById(R.id.coinAmountText);

        return layout;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            handler = ( new CoinInfoHandler((CoinHolder[]) bundle.getSerializable("data")));
        }
    }
    public void onClick(View v) {
        coinAmount = text.getText().toString();
        result = handler.executeConversion(coinAmount,coinName, currencyName);
        Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content),
               "Your " + coinAmount + " " + coinName + " is worth: " + result + " " + currencyName, Snackbar.LENGTH_LONG);
        snackBar.show();;
    }

}