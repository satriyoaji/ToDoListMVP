package todolistmvp.modul.edittask;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
//    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        editTaskFragment = new EditTaskFragment();
        String id = getIntent().getStringExtra("TaskId");

        editTaskFragment.setTaskId(id);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTaskFragment.logout();
            }
        });
        setCurrentFragment(editTaskFragment, false);

    }

}
