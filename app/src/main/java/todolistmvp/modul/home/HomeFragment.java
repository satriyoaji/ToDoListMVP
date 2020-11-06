package todolistmvp.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import todolistmvp.base.BaseFragment;
import todolistmvp.data.model.Task;
import todolistmvp.modul.R;
import todolistmvp.modul.edittask.EditTaskActivity;
import todolistmvp.modul.login.LoginActivity;
import todolistmvp.modul.newtask.NewTaskActivity;
import todolistmvp.utils.RecyclerViewAdapterTodolist;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private TextView welcometitle;
    private TextView nowdate;
    private Button newListBtn;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView searchTask;
    private String username;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();
        setTitle(getResources().getString(R.string.app_name));

        welcometitle = fragmentView.findViewById(R.id.welcometitle);
        nowdate = fragmentView.findViewById(R.id.nowdate);
        newListBtn = fragmentView.findViewById(R.id.newListBtn);
        searchTask = fragmentView.findViewById(R.id.searchTask);

        welcometitle.setText("Hello"+ (username == null ? "!" : (", " + username) ));

        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        nowdate.setText(date);

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodoList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);

        newListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTask();
            }
        });

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                Log.d("BELAJAR ACTIVITY",">>>>>"+ position);
                editTask(id);
            }
        });

        return fragmentView;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void logout() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void createNewTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        startActivity(intent);
    }

    public void editTask(String id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("TaskId", id);
        startActivity(intent);
    }

    public void setUsername(String username) {
        //set username view from email (depends on user's email in DB)
        this.username = username;
    }

}
