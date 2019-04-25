/*
 *Fragment fo the splash screen activity
 */
package edu.quinnipiac.ser210.navdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SplashScreenFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_splash_screen, container, false);
        Button b = (Button) v.findViewById(R.id.start_bn);
        b.setOnClickListener(this);
return v;
    }

    @Override
    public void onClick(View v) {
    switch (v.getId()) {
        case R.id.start_bn:
            Intent intent = new Intent(getActivity(), WalletActivity.class);
            startActivity(intent);
            break;
    }
    }
}
