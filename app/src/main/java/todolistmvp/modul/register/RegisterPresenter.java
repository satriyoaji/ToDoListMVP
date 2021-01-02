package todolistmvp.modul.register;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import todolistmvp.data.model.User;
import todolistmvp.data.source.session.SessionRepository;

import static android.content.ContentValues.TAG;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private final SessionRepository sessionRepository;
    private FirebaseAuth mAuth;

    public RegisterPresenter(RegisterContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){
            view.redirectToHome();
            view.showToast("Successfully registered!");
        }
    }

    @Override
    public void performRegister(final String email, final String password, final String confirmPassword){
        if(password.equals(confirmPassword)){
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            User user = new User(firebaseUser.getEmail(), firebaseUser.getUid());
                            sessionRepository.setSessionData(user);
                            start();
                        } else {
                            Log.d(TAG, "gagal", task.getException());
                            view.showToast(task.getException().getMessage());
                        }
                    }
                });
        }else{
            view.showToast("password and confirmation password doesn't match!");
        }
    }

}
