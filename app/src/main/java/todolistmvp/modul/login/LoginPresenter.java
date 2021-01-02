package todolistmvp.modul.login;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import todolistmvp.data.model.User;
import todolistmvp.data.source.session.SessionRepository;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;
    private FirebaseAuth mAuth;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){
            view.redirectToHome();
            view.showToast("Successfully logged in!");
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    User user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
                    sessionRepository.setSessionData(user);
                    // call redirect to home
                    start();
                    view.showToast("Login successful");
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d(TAG, "gagal", task.getException());
                    view.showToast(task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void processGoogleLogin() {
        view.googleSignIn();
    }

    @Override
    public void firebaseAuthWithGoogle(AuthCredential credential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        User user = new User(task.getResult().getUser().getEmail(), task.getResult().getUser().getUid());
                        sessionRepository.setSessionData(user);
                        start();
                        view.showToast("Login successful");
                    }else{
                        Log.w(TAG, "signInWithCredential" + task.getException().getMessage());
                        task.getException().printStackTrace();
                        view.showToast("Authentication failed");
                    }
                }
            });
    }

}
