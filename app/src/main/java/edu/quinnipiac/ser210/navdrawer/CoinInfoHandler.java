package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

public class CoinInfoHandler {

    private JSONObject CoinInformation;
    private static int LIST_LENGTH;
    private CoinHolder[] coinArray;
    private String url1 = "https://bravenewcoin-v1.p.rapidapi.com/convert?qty=";
    private String url2 = "&from=";
    private String url3 = "&to=";
    private String conversionResult;
    private Boolean convertComplete;
    private Boolean running;


    public CoinInfoHandler(int length){
        new FetchCoinValue().execute("");
        LIST_LENGTH = length;
        convertComplete = false;

    }

    public CoinInfoHandler(CoinHolder[] coinArray){
        this.coinArray = coinArray;
        LIST_LENGTH = coinArray.length;
        convertComplete = false;
    }

    public String executeConversion(String coinAmount, String coin, String currency){
        new ConvertCoin().execute(coinAmount,currency, coin);
       return conversionResult;
    }

    private String convert(JSONObject jsonObject) throws JSONException {
        Log.d("Coin", jsonObject.toString());
        String value = jsonObject.getString("to_quantity");
        conversionResult = value;
        convertComplete = true;
        return value;
    }

    private void createCoinList(){
        try {
            coinArray = new CoinHolder[LIST_LENGTH];
            JSONArray list =  CoinInformation.getJSONObject("contextWrites").getJSONArray("to");
            for(int i = 0; i < LIST_LENGTH; i++){
                JSONObject tempObject = list.getJSONObject(i);
                coinArray[i] = new CoinHolder(tempObject.getString("name"),tempObject.getString("symbol"),tempObject.getInt("rank"),tempObject.getInt("price_usd"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public CoinHolder[] getCoinArray(){
        return coinArray;
    }

    public void printCoinArray(){
        for(int i = 0; i < coinArray.length; i++){
            Log.d("Coin " + i, "\nName: " + coinArray[i].getName() + "\nSymbol: " + coinArray[i].getSymbol() + "\nRank: " + coinArray[i].getRank() + "\nValue: " + coinArray[i].getValue());
        }
    }

    private class FetchCoinValue extends AsyncTask<String,Void,JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {

            HttpURLConnection urlConnection =null;
            BufferedReader reader =null;

            try {
                URL url = new URL("https://CoinMarketCapzakutynskyV1.p.rapidapi.com/getCryptocurrenciesList");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("X-RapidAPI-Host", "CoinMarketCapzakutynskyV1.p.rapidapi.com");
                urlConnection.addRequestProperty("X-RapidAPI-Key","1a0163a90fmshd163a24e170daccp1b2e7ejsn49b0f038e29a");
                urlConnection.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }
                reader  = new BufferedReader(new InputStreamReader(in));
                String coinValueJsonString = getBufferStringFromBuffer(reader).toString();
                return new JSONObject(coinValueJsonString);


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
                        Log.e("Handler","Error" + e.getMessage());
                        return null;
                    }
                }
            }

        }

        protected void onPostExecute(JSONObject result){
            CoinInformation = result;
            try {
                Log.d("Working JSON", CoinInformation.getJSONObject("contextWrites").getJSONArray("to").getJSONObject(0).getString("id"));
                createCoinList();
                printCoinArray();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class ConvertCoin extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection urlConnection =null;
            BufferedReader reader =null;

            try {
                URL url = new URL(url1 + params[0] + url2 + params[2] + url3 + params[1]);

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

        protected void onPostExecute(String result){ }

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
}
