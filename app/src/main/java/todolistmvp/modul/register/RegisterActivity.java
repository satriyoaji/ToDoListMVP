package todolistmvp.modul.register;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;
//    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btExit.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }

}
