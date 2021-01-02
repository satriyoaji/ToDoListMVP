package todolistmvp.modul.register;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;


public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
        void showToast(String message);
    }

    interface Presenter extends BasePresenter {
        void performRegister(String email, String password, String confirmPassword);
    }
}
