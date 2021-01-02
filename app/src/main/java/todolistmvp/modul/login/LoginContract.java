package todolistmvp.modul.login;

import com.google.firebase.auth.AuthCredential;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
        void showToast(String message);
        void googleSignIn();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
        void processGoogleLogin();
        void firebaseAuthWithGoogle(AuthCredential credential);
    }
}
