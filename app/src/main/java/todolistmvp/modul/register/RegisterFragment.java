package todolistmvp.modul.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import todolistmvp.base.BaseFragment;
import todolistmvp.data.source.session.UserSessionRepository;
import todolistmvp.modul.R;
import todolistmvp.modul.home.HomeActivity;
import todolistmvp.modul.login.LoginActivity;


public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View, View.OnClickListener {

    EditText etEmail, etPassword, etConfirmPassword;
    TextView toLogin;
    Button btnRegister;
    public static final String KEY_EMAIL = "EMAIL_KEY";

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, new UserSessionRepository(getActivity()));

        initView();

        return fragmentView;
    }

    @Override
    public void initView() {
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        etConfirmPassword = fragmentView.findViewById(R.id.et_confirm_password);
        toLogin = fragmentView.findViewById(R.id.tvToLogin);
        btnRegister = fragmentView.findViewById(R.id.bt_register);

        btnRegister.setOnClickListener(this);
        toLogin.setOnClickListener(this);

        setTitle(getResources().getString(R.string.register) + " Page");
    }

    public void setBtRegisterClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String confirmPass = etConfirmPassword.getText().toString();
        mPresenter.performRegister(email,password,confirmPass);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void logout() {
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, HomeActivity.class);
        startActivity(intent);
        activity.finish();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnRegister.getId())
            setBtRegisterClick();
        if(v.getId() == toLogin.getId())
            redirectToLogin();
    }
}
