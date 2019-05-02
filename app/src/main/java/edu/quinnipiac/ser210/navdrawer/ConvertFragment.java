/*
 *Fragment fo the convert screen activity
 */
package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ConvertFragment extends Fragment implements View.OnClickListener {

    private CoinInfoHandler handler;
    private String coinName;
    private String currencyName;
    private String coinAmount;
    public String coinResult;
    EditText text;
    private Boolean display;
    private String url1 = "https://bravenewcoin-v1.p.rapidapi.com/convert?qty=";
    private String url2 = "&from=";
    private String url3 = "&to=";


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
        new ConvertCoin().execute(coinAmount,coinName, currencyName);

    }

    //Async Task for Conversion API
    private class ConvertCoin extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection =null;
            BufferedReader reader =null;

            try {
                URL url = new URL(url1 + params[0] + url2 + params[1] + url3 + params[2]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key","006da6b0d5msh97aaea208d3f61fp1a7561jsn5292b28eff9a");

                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                reader  = new BufferedReader(new InputStreamReader(in));
                // call getBufferString to get the string from the buffer.

                String coinValueJsonString = getBufferStringFromBuffer(reader).toString();


                return convert(new JSONObject(coinValueJsonString));

            }catch(Exception e){
                e.printStackTrace();
                return null;
            }finally{
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if (reader != null){
                    try{
                        reader.close();
                    }catch (IOException e){
                        return null;
                    }
                }
            }

        }

        protected void onPostExecute(String result){
            coinResult = result;
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("message",  coinAmount + " " + coinName + " is worth: " + coinResult + " " + currencyName);
            startActivity(intent);
        }

    }

    private StringBuffer getBufferStringFromBuffer(BufferedReader br) throws Exception{
        StringBuffer buffer = new StringBuffer();

        String line;
        while((line = br.readLine()) != null){
            buffer.append(line + '\n');
        }

        if (buffer.length() == 0)
            return null;

        return buffer;
    }
    //JSON reader method used in conversion
    private String convert(JSONObject jsonObject) throws JSONException {
        //Debug
        Log.d("Coin", jsonObject.toString());

        //Grabs value from JSON and updates handler value
        String value = jsonObject.getString("to_quantity");
        return value;
    }
}