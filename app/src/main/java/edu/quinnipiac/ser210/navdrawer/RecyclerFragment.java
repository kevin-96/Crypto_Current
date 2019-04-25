package edu.quinnipiac.ser210.navdrawer;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private MySQLiteHelper db = new MySQLiteHelper(getContext());

    public static Fragment newInstance(){
        return new RecyclerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        //ArrayList<String> wallets = db.getAllWallets();
        //Log.e("arraylist", String.valueOf(wallets));

        List<String> listOfWalletNames = new ArrayList<>();
        listOfWalletNames.add("Home");
        listOfWalletNames.add("Work");
        listOfWalletNames.add("Home2");
        listOfWalletNames.add("Work2");
        listOfWalletNames.add("Savings");
        listOfWalletNames.add("Travel");
        listOfWalletNames.add("SER210 :D");

        List<String> listOfAmountNames = new ArrayList<>();
        listOfAmountNames.add("Bitcoin");
        listOfAmountNames.add("XRP");
        listOfAmountNames.add("NANO");
        listOfAmountNames.add("LiteCoin");
        listOfAmountNames.add("XRP");
        listOfAmountNames.add("Jah Coin");
        listOfAmountNames.add("AndroidCoin");

        List<String> listOfCoinNames = new ArrayList<>();
        listOfCoinNames.add("12.34");
        listOfCoinNames.add("6329.23");
        listOfCoinNames.add("1679.69");
        listOfCoinNames.add("3902.74");
        listOfCoinNames.add("408.98");
        listOfCoinNames.add("420.20");
        listOfCoinNames.add("123.45");


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

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            recyclerViewHolder.mNameView.setText(walletName.get(i));
            recyclerViewHolder.mAmountView.setText(amountValue.get(i));
            recyclerViewHolder.mCoinView.setText(coinName.get(i));
        }

        @Override
        public int getItemCount() {
            return walletName.size();
        }
    }
}
