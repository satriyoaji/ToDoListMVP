package todolistmvp.modul.home;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;
import todolistmvp.modul.login.LoginFragment;


public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
//    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        homeFragment = new HomeFragment();
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.logout();
            }
        });

        setCurrentFragment(homeFragment, false);
    }

}
