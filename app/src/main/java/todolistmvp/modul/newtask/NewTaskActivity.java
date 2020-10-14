package todolistmvp.modul.newtask;

import android.content.Intent;
import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;
import todolistmvp.modul.login.LoginFragment;


public class NewTaskActivity extends BaseFragmentHolderActivity {
    NewTaskFragment newTaskFragment;
    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        newTaskFragment = new NewTaskFragment();
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTaskFragment.logout();
            }
        });
        setCurrentFragment(newTaskFragment, false);

        Intent intent = getIntent();
    }

}
