package todolistmvp.modul.home;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import todolistmvp.base.BaseFragment;
import todolistmvp.data.model.Task;
import todolistmvp.data.source.local.TaskTableHandler;
import todolistmvp.data.source.session.TaskSessionRepository;
import todolistmvp.data.source.session.UserSessionRepository;
import todolistmvp.modul.R;
import todolistmvp.modul.edittask.EditTaskActivity;
import todolistmvp.modul.login.LoginActivity;
import todolistmvp.modul.newtask.NewTaskActivity;
import todolistmvp.utils.RecyclerViewAdapterTodolist;

import static todolistmvp.modul.R.id.recyclerViewTodoList;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener  {

    private TextView greetings, nowdate, username;
    private Button newListBtn;
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SearchView searchTask;
    private FirebaseUser user;
    private GoogleSignInAccount account;
    private GoogleApiClient googleApiClient;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_home, container, false);
        mPresenter = new HomePresenter(this, new TaskSessionRepository(getActivity()), new TaskTableHandler(getActivity()));
        user = FirebaseAuth.getInstance().getCurrentUser();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.connect();
        account = GoogleSignIn.getLastSignedInAccount(getContext());
        initView();

        return fragmentView;
    }

    @Override
    public void initView() {
        setTitle(getResources().getString(R.string.app_name));

        greetings = fragmentView.findViewById(R.id.greetingTitle);
        username = fragmentView.findViewById(R.id.username);
        nowdate = fragmentView.findViewById(R.id.nowdate);
        newListBtn = fragmentView.findViewById(R.id.newListBtn);
        searchTask = fragmentView.findViewById(R.id.searchTask);
        mRecyclerView = fragmentView.findViewById(recyclerViewTodoList);

        mPresenter.start();
        setUsername();
        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());
        nowdate.setText(date);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        final ArrayList<Task> data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);

        newListBtn.setOnClickListener(this);

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                editTask(id);
            }

            @Override
            public void onSelected(int position, Boolean isChecked) {
                String id = data.get(position).getId();
                mPresenter.updateChecked(id, isChecked);
            }
        });
    }

    private void setUsername() {
        if(account == null)
            username.setText(user.getEmail());
        else
            username.setText(account.getDisplayName());
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void exit() {
        mPresenter.performLogout(googleApiClient);
        Toast.makeText(getContext(), "You're logged out!",
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finishAffinity();
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

    @Override
    public void greetings() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        System.out.println("waktu "+ timeOfDay);

        if(timeOfDay >= 0 && timeOfDay < 12){
            greetings.setText("Good Morning");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            greetings.setText("Good Afternoon");
        }else if(timeOfDay >= 16 && timeOfDay < 20){
            greetings.setText("Good Evening");
        }else if(timeOfDay >= 20 && timeOfDay < 24){
            greetings.setText("Good Night");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == newListBtn.getId())
            createNewTask();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
