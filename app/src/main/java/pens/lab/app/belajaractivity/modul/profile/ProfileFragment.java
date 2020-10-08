package pens.lab.app.belajaractivity.modul.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.modul.login.LoginFragment;


public class ProfileFragment extends BaseFragment<ProfileActivity, ProfileContract.Presenter> implements ProfileContract.View {

    EditText dataEmail, dataPassword;
    TextView tvEmail, tvPassword;
    private String emailData, passwordData;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_profile, container, false);
        mPresenter = new ProfilePresenter(this);
        mPresenter.start();
        setTitle("Profile Page");

        dataEmail = fragmentView.findViewById(R.id.data_email);
        dataPassword = fragmentView.findViewById(R.id.data_password);
        tvEmail = fragmentView.findViewById(R.id.tv_email);
        tvPassword = fragmentView.findViewById(R.id.tv_password);

        dataEmail.setText(emailData);
        dataPassword.setText(passwordData);

        return fragmentView;
    }

    public void setProfileAttribute(String email, String password) {
        this.emailData = email;
        this.passwordData = password;
    }

    public void setBtLoginClick(){
    }

    @Override
    public void setPresenter(ProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }


}
