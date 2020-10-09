package pens.lab.app.belajaractivity.modul.profile;

import pens.lab.app.belajaractivity.base.BasePresenter;
import pens.lab.app.belajaractivity.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface ProfileContract {
    interface View extends BaseView<Presenter> {
        void goBackToLogin();

        void setProfileAttribute(String email, String password);
    }

    interface Presenter extends BasePresenter {
        
    }
}
