package todolistmvp.modul.home;

import android.content.Intent;
import android.view.View;
import android.view.Window;

import todolistmvp.base.BaseFragmentHolderActivity;
import todolistmvp.modul.login.LoginFragment;


public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        homeFragment = new HomeFragment();
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.goBackToLogin();
            }
        });
        setCurrentFragment(homeFragment, false);

        Intent intent = getIntent();
        String emailText = intent.getStringExtra(LoginFragment.KEY_EMAIL);
        String passwordText = intent.getStringExtra(LoginFragment.KEY_PASSWORD);

        homeFragment.setProfileAttribute(emailText);
    }

}
