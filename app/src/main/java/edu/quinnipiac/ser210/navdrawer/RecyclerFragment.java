/*
 * This fragment is in charge of the wallets for the wallet activity. It will have 
 * a recycler view with a card view inside of it.
 *
 * Dev's: Kevin Sangurima, Brian Carballo
 */

package edu.quinnipiac.ser210.navdrawer;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private MySQLiteHelper walletDB;
    private SQLiteDatabase db;


    private Cursor cursor;


    public static Fragment newInstance(){
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        walletDB = new MySQLiteHelper(getContext());
        db = walletDB.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM wallets",null,null);
        cursor.moveToLast();
        List<String> listOfWalletNames = new ArrayList<>();
        List<String> listOfAmountNames = new ArrayList<>();
        List<String> listOfCoinNames = new ArrayList<>();
        while(!cursor.isBeforeFirst()){
            listOfWalletNames.add(cursor.getString(1));
            listOfCoinNames.add(cursor.getString(3));
            listOfAmountNames.add(cursor.getString(2));
            cursor.moveToPrevious();
        }
        cursor.close();
        db.close();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter(listOfWalletNames, listOfCoinNames, listOfAmountNames));



        return view;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mNameView;
        private TextView mAmountView;
        private TextView mCoinView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }

        public RecyclerViewHolder(LayoutInflater inflater, ViewGroup container){
            super(inflater.inflate(R.layout.card_view, container, false));

            mCardView = itemView.findViewById(R.id.card_container);
            mNameView = itemView.findViewById(R.id.name_holder);
            mAmountView = itemView.findViewById(R.id.amount_holder);
            mCoinView = itemView.findViewById(R.id.coin_holder);

        }
    }


    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        private List<String> walletName;
        private List<String> amountValue;
        private List<String> coinName;

        public RecyclerViewAdapter(List<String> list1, List<String> list2, List<String> list3){
            this.walletName = list1;
            this.amountValue = list2;
            this.coinName = list3;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new RecyclerViewHolder(inflater, viewGroup);
        }

        // This sets the text of each card/wallet in the card view 
        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            recyclerViewHolder.mNameView.setText(walletName.get(i));
            recyclerViewHolder.mAmountView.setText(amountValue.get(i));
            recyclerViewHolder.mCoinView.setText(coinName.get(i));
        }

        // This creates cards depending on the items of the list.
        @Override
        public int getItemCount() {
            return walletName.size();
        }
    }
}
