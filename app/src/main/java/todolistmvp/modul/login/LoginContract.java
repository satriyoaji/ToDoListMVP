package todolistmvp.modul.login;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome(String email);
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }
}
