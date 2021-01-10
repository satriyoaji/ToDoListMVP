package todolistmvp.modul.newtask;

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
import todolistmvp.data.source.local.TaskTableHandler;
import todolistmvp.data.source.session.TaskSessionRepository;
import todolistmvp.modul.R;
import todolistmvp.modul.home.HomeActivity;
import todolistmvp.modul.login.LoginActivity;


public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View, View.OnClickListener {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText etTitleTask, etDescTask, etDateTask;
    private ImageButton datePickerBtn;
    private Button createTaskBtn, cancelBtn;

    public NewTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_new_task, container, false);
        mPresenter = new NewTaskPresenter(this, new TaskSessionRepository(getActivity()), new TaskTableHandler(getActivity()));

        initView();

        return fragmentView;
    }

    @Override
    public void initView() {
        setTitle(getResources().getString(R.string.add_new_task_title));

        etTitleTask = fragmentView.findViewById(R.id.etTitleTask);
        etDescTask = fragmentView.findViewById(R.id.etDescTask);
        etDateTask = fragmentView.findViewById(R.id.etDateTask);
        datePickerBtn = fragmentView.findViewById(R.id.datePickerBtn);
        createTaskBtn = fragmentView.findViewById(R.id.createTaskBtn);
        cancelBtn = fragmentView.findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(this);
        createTaskBtn.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        datePickerBtn.setOnClickListener(this);

        mPresenter.start();
    }

    @Override
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void exit() {
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

    public void redirectToTaskList() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }

    public void saveTask(){
        String title = etTitleTask.getText().toString();
        String desc = etDescTask.getText().toString();
        String date = etDateTask.getText().toString();
        mPresenter.saveData(title,date,desc);

        Toast.makeText(getContext(), "The task has successfully added!", Toast.LENGTH_LONG).show();
        redirectToTaskList();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == cancelBtn.getId())
            redirectToTaskList();
        else if(v.getId() == createTaskBtn.getId())
            saveTask();
        else if(v.getId() == datePickerBtn.getId())
            showDateDialog();
    }
}
