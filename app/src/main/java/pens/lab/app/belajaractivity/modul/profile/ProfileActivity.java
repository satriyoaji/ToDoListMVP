package pens.lab.app.belajaractivity.modul.profile;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import pens.lab.app.belajaractivity.base.BaseFragmentHolderActivity;
import pens.lab.app.belajaractivity.modul.login.LoginFragment;


public class ProfileActivity extends BaseFragmentHolderActivity {
    ProfileFragment profileFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        profileFragment = new ProfileFragment();
        setCurrentFragment(profileFragment, false);

        Intent intent = getIntent();
        String emailText = intent.getStringExtra(LoginFragment.KEY_EMAIL);
        String passwordText = intent.getStringExtra(LoginFragment.KEY_PASSWORD);

        profileFragment.setProfileAttribute(emailText, passwordText);
    }

}
