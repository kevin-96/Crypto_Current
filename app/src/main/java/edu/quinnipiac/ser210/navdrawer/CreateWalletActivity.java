package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateWalletActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_wallet);

        final MySQLiteHelper db = new MySQLiteHelper(this);

        Button button = findViewById(R.id.create_Wallet_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText walletName = findViewById(R.id.wallet_Name_EditText);
                EditText coinName = findViewById(R.id.coin_Name_EditText);
                EditText amount = findViewById(R.id.amount_EditText);

                String wName = walletName.getText().toString();
                String cName = coinName.getText().toString();
                String amountValue = amount.getText().toString();
                db.insertWallet(wName, cName, amountValue);

                Intent intent = new Intent(CreateWalletActivity.this, WalletActivity.class);
                //intent.putExtra("walletName", wName);
                //intent.putExtra("coinName", cName);
                //intent.putExtra("amount", amountValue);
                startActivity(intent);
            }
        });
    }
}
