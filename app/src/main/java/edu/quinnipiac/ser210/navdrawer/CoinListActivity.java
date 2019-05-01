package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class CoinListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private CoinHolder[] coinHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Top 20 Cryptocurrencies");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_convert);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent intent = getIntent();
        coinHolder = (CoinHolder[]) intent.getSerializableExtra("data");

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container_coin_list, CoinListFragment.newInstance(intent.getExtras()));
            ft.commit();
            navigationView.setCheckedItem(R.id.nav_coinList);
        }

    }

    // method for changing activities from the navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_wallet:
                Intent intentWallet = new Intent(CoinListActivity.this, WalletActivity.class);
                CoinListActivity.this.startActivity(intentWallet);
                break;
            case R.id.nav_convert:
                Intent intentConvert = new Intent(CoinListActivity.this, ConvertActivity.class);
                intentConvert.putExtra("data", (coinHolder));
                CoinListActivity.this.startActivity(intentConvert);
                break;
            case R.id.nav_coinList:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_coin_list,
                        new CoinListFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Intent intentSetting = new Intent(CoinListActivity.this, SettingActivity.class);
                CoinListActivity.this.startActivity(intentSetting);
                break;
            case R.id.nav_create:
                Intent intentCreate = new Intent(CoinListActivity.this, CreateWalletActivity.class);
                CoinListActivity.this.startActivity(intentCreate);
                break;
            case R.id.nav_delete:
                Intent intentDelete = new Intent(CoinListActivity.this, DeleteWalletActivity.class);
                CoinListActivity.this.startActivity(intentDelete);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
