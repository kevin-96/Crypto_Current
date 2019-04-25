package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class ConvertActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_convert);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_convert,
                    new ConvertFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_convert);
        }

    }

    // method for changing activities from the navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_wallet:
                Intent intentWallet = new Intent(ConvertActivity.this, WalletActivity.class);
                ConvertActivity.this.startActivity(intentWallet);
                break;
            case R.id.nav_convert:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_convert,
                        new ConvertFragment()).commit();
                break;
            case R.id.nav_coinList:
                Intent intentCoinList = new Intent(ConvertActivity.this, CoinListActivity.class);
                ConvertActivity.this.startActivity(intentCoinList);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Intent intentSetting = new Intent(ConvertActivity.this, SettingActivity.class);
                ConvertActivity.this.startActivity(intentSetting);
                break;
            case R.id.nav_create:
                Intent intentCreate = new Intent(ConvertActivity.this, CreateWalletActivity.class);
                ConvertActivity.this.startActivity(intentCreate);
                break;
            case R.id.nav_delete:
                Intent intentDelete = new Intent(ConvertActivity.this, DeleteWalletActivity.class);
                ConvertActivity.this.startActivity(intentDelete);
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
