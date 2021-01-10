package todolistmvp.modul.newtask;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;


public class NewTaskActivity extends BaseFragmentHolderActivity {
    NewTaskFragment newTaskFragment;
//    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btExit.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        newTaskFragment = new NewTaskFragment();
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTaskFragment.exit();
            }
        });
        setCurrentFragment(newTaskFragment, false);

    }

}
