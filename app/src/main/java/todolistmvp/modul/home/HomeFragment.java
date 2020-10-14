package todolistmvp.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import todolistmvp.base.BaseFragment;
import todolistmvp.modul.R;
import todolistmvp.modul.edittask.EditTaskActivity;
import todolistmvp.modul.login.LoginActivity;
import todolistmvp.modul.newtask.NewTaskActivity;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private TextView titledoes, descdoes, datedoes;
    private Button newListBtn;
    private RecyclerView ourlist;
//    private ArrayList<ToDoList> lists;
//    private ToDoListAdapter adapter;
    private SearchView searchTask;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_home, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();
        setTitle(getResources().getString(R.string.app_name));

        titledoes = fragmentView.findViewById(R.id.titledoes);
        descdoes = fragmentView.findViewById(R.id.descdoes);
        datedoes = fragmentView.findViewById(R.id.datedoes);
        newListBtn = fragmentView.findViewById(R.id.newListBtn);
        searchTask = fragmentView.findViewById(R.id.searchTask);

        newListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewTask();
            }
        });
        titledoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTaskDetail();
            }
        });

        return fragmentView;
    }

    @Override
    public void goToTaskDetail() {
        Intent i = new Intent(activity, EditTaskActivity.class);
        startActivity(i);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void goBackToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void createNewTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setProfileAttribute(String email) {
//        this.emailData = email;
    }
}
