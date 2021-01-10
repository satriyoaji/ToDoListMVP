package todolistmvp.modul.edittask;

import android.view.View;

import todolistmvp.base.BaseFragmentHolderActivity;


public class EditTaskActivity extends BaseFragmentHolderActivity {
    EditTaskFragment editTaskFragment;
//    private final int UPDATE_REQUEST = 2019;

    protected void initializeFragment() {
        initializeView();

        btExit.setVisibility(View.VISIBLE);
        btOptionMenu.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);

        editTaskFragment = new EditTaskFragment();
        String id = getIntent().getStringExtra("TaskId");

        editTaskFragment.setTaskId(id);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTaskFragment.exit();
            }
        });
        setCurrentFragment(editTaskFragment, false);

    }

}
