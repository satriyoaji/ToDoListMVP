package todolistmvp.modul.home;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;


public class HomeActivity extends BaseFragmentHolderActivity {
    HomeFragment homeFragment;
//    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btExit.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.VISIBLE);
        ivIcon.setVisibility(View.VISIBLE);

        homeFragment = new HomeFragment();
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.exit();
            }
        });

        setCurrentFragment(homeFragment, false);
    }

}
