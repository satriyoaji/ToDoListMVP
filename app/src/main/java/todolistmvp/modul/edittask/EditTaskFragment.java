package todolistmvp.modul.edittask;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import todolistmvp.base.BaseFragment;
import todolistmvp.data.model.Task;
import todolistmvp.modul.R;
import todolistmvp.modul.home.HomeActivity;
import todolistmvp.modul.login.LoginActivity;


public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText etTitleTask, etDescTask, etDateTask;
    private String id;
    private ImageButton datePickerBtn;
    private Button updateTaskBtn, deleteTaskBtn;

    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_edit_task, container, false);
        mPresenter = new EditTaskPresenter(this);
        mPresenter.start();
        setTitle(getResources().getString(R.string.edit_task_details));

        etTitleTask = fragmentView.findViewById(R.id.etTitleTask);
        etDescTask = fragmentView.findViewById(R.id.etDescTask);
        etDateTask = fragmentView.findViewById(R.id.etDateTask);

        datePickerBtn = fragmentView.findViewById(R.id.datePickerBtn);
        updateTaskBtn = fragmentView.findViewById(R.id.updateTaskBtn);
        deleteTaskBtn = fragmentView.findViewById(R.id.deleteTaskBtn);

        //get Value from selected task
        mPresenter.loadData(this.id);

        deleteTaskBtn.setOnClickListener(new View.OnClickListener() {
            private String id = getTaskId();

            @Override
            public void onClick(View v) {
                mPresenter.deleteData(id);
            }
        });

        updateTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChangeTask();
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        return fragmentView;
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public void setTaskId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return this.id;
    }

    @Override
    public void redirectToTaskList() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void logout() {
        Intent intent = new Intent(activity, LoginActivity.class);
        //process logout logic
        startActivity(intent);
        activity.finish();
    }

    public void showDateDialog() {
        //Calendar untuk mendapatkan tanggal sekarang
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this.activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                etDateTask.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    public void saveChangeTask() {
        String title = etTitleTask.getText().toString();
        String date = etDateTask.getText().toString();
        String description = etDescTask.getText().toString();
        mPresenter.saveData(this.id,title,date,description);

        Toast.makeText(getContext(), "The task has successfully updated!", Toast.LENGTH_LONG).show();
        redirectToTaskList();
    }

    @Override
    public void showData(Task task) {
        this.etTitleTask.setText(task.getTitle());
        this.etDescTask.setText(task.getDesc());
        this.etDateTask.setText(task.getDate());
    }

    @Override
    public void deleteProcess(final Task task) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.delete_dialog, null);

        Button accDeleteBtn = view.findViewById(R.id.accDeleteBtn);
        Button cancelBtnTask = view.findViewById(R.id.cancelBtnTask);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();
        alertDialog.show();

        accDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), ("Task "+ task.getId() +" has successfully deleted!"), Toast.LENGTH_LONG).show();
                redirectToTaskList();
            }
        });
        cancelBtnTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}
