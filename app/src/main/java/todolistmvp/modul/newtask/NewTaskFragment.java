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
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import todolistmvp.base.BaseFragment;
import todolistmvp.modul.R;
import todolistmvp.modul.home.HomeActivity;
import todolistmvp.modul.home.HomePresenter;
import todolistmvp.modul.login.LoginActivity;


public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText titleTask, descTask, dateTask;
    private ImageButton datePickerBtn;
    private Button createTaskBtn, cancelBtn;

    public NewTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_new_task, container, false);
        mPresenter = new NewTaskPresenter(this);
        mPresenter.start();
        setTitle(getResources().getString(R.string.add_new_task_title));

        titleTask = fragmentView.findViewById(R.id.titleTask);
        descTask = fragmentView.findViewById(R.id.descTask);
        dateTask = fragmentView.findViewById(R.id.dateTask);
        datePickerBtn = fragmentView.findViewById(R.id.datePickerBtn);
        createTaskBtn = fragmentView.findViewById(R.id.createTaskBtn);
        cancelBtn = fragmentView.findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelCreateTask();
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
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void logout() {
        Intent intent = new Intent(activity, LoginActivity.class);
        //process logout logic
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showDateDialog() {
        //Calendar untuk mendapatkan tanggal sekarang
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this.activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                dateTask.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @Override
    public void cancelCreateTask() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
