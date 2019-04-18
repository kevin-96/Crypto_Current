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

public class SplashScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_splash);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_splash,
                    new SplashScreenFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_splash);
        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_splash:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_splash,
                        new SplashScreenFragment()).commit();
                break;
            case R.id.nav_wallet:
                Intent intentWallet = new Intent(SplashScreenActivity.this, WalletActivity.class);
                SplashScreenActivity.this.startActivity(intentWallet);
                break;
            case R.id.nav_convert:
                Intent intentConvert = new Intent(SplashScreenActivity.this, ConvertActivity.class);
                SplashScreenActivity.this.startActivity(intentConvert);
                break;
            case R.id.nav_coinList:
                Intent intentCoinList = new Intent(SplashScreenActivity.this, CoinListActivity.class);
                SplashScreenActivity.this.startActivity(intentCoinList);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_setting:
                Intent intentSetting = new Intent(SplashScreenActivity.this, SettingActivity.class);
                SplashScreenActivity.this.startActivity(intentSetting);
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
