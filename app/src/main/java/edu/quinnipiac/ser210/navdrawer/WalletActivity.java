package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class WalletActivity extends CardWalletFragment implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // links the tool bar to the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // links the navigation drawer
        drawer = findViewById(R.id.drawer_layout_wallet);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new RecyclerFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_wallet);
        }
    }

    // method for changing activities from the navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_wallet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RecyclerFragment()).commit();
                break;
            case R.id.nav_convert:
                Intent intentConvert = new Intent(WalletActivity.this, ConvertActivity.class);
                WalletActivity.this.startActivity(intentConvert);
                break;
            case R.id.nav_coinList:
                Intent intentCoinList = new Intent(WalletActivity.this, CoinListActivity.class);
                WalletActivity.this.startActivity(intentCoinList);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Intent intentSetting = new Intent(WalletActivity.this, SettingActivity.class);
                WalletActivity.this.startActivity(intentSetting);
                break;
            case R.id.nav_create:
                Intent intentCreate = new Intent(WalletActivity.this, CreateWalletActivity.class);
                WalletActivity.this.startActivity(intentCreate);
                break;
            case R.id.nav_delete:
                Intent intentDelete = new Intent(WalletActivity.this, DeleteWalletActivity.class);
                WalletActivity.this.startActivity(intentDelete);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected Fragment createFragment() {
        return new RecyclerFragment().newInstance();
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