package todolistmvp.modul.home;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void goBackToLogin();

        void setProfileAttribute(String email);
    }

    interface Presenter extends BasePresenter {
        
    }
}
