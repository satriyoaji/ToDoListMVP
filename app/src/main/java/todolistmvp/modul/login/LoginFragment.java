package todolistmvp.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;

import todolistmvp.data.source.session.UserSessionRepository;
import todolistmvp.modul.R;
import todolistmvp.base.BaseFragment;
import todolistmvp.modul.home.HomeActivity;
import todolistmvp.modul.register.RegisterActivity;

import static android.content.ContentValues.TAG;


public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {

    private static final int RC_SIGN_IN = 101;
    EditText etEmail,etPassword;
    TextView tvToRegister;
    Button btnLogin, btnGoogleLogin;
    public static final String KEY_EMAIL = "EMAIL_KEY";
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()));
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        initView();

        return fragmentView;
    }

    @Override
    public void initView() {
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.bt_login);
        tvToRegister = fragmentView.findViewById(R.id.tvToRegister);
        btnGoogleLogin = fragmentView.findViewById(R.id.btnGoogleLogin);

        btnLogin.setOnClickListener(this);
        btnGoogleLogin.setOnClickListener(this);
        tvToRegister.setOnClickListener(this);

        setTitle(getResources().getString(R.string.login_title));
    }

    public void setBtLoginClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email,password);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
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

    private void redirectToRegister() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void googleSignIn() {
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            String idToken = account.getIdToken();
            String name = account.getDisplayName();
            String email = account.getEmail();
            // you can store user data to SharedPreference
            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            mPresenter.firebaseAuthWithGoogle(credential);
        }else{
            // Google Sign In failed, update UI appropriately
            Log.e(TAG, "Login gagal "+result.toString());
            Toast.makeText(getContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnLogin.getId())
            setBtLoginClick();
        else if(v.getId() == tvToRegister.getId())
            redirectToRegister();
        else if(v.getId() == btnGoogleLogin.getId())
            mPresenter.processGoogleLogin();
    }
}
