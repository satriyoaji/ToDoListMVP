package todolistmvp.modul.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import pens.lab.app.belajaractivity.R;
import todolistmvp.base.BaseFragment;
import todolistmvp.modul.login.LoginActivity;


public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private EditText dataEmail, dataPassword;
    private TextView tvEmail, tvPassword;
    private String emailData, passwordData;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new HomePresenter(this);
        mPresenter.start();
        setTitle(getResources().getString(R.string.profile_title));

        dataEmail = fragmentView.findViewById(R.id.data_email);
        dataPassword = fragmentView.findViewById(R.id.data_password);
        tvEmail = fragmentView.findViewById(R.id.tv_email);
        tvPassword = fragmentView.findViewById(R.id.tv_password);

        dataEmail.setText(emailData);
        dataPassword.setText(passwordData);

        return fragmentView;
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
    public void setProfileAttribute(String email, String password) {
        this.emailData = email;
        this.passwordData = password;
    }
}
