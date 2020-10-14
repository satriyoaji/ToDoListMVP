package todolistmvp.modul.newtask;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void logout();
        void showDateDialog();
        void cancelCreateTask();
    }

    interface Presenter extends BasePresenter {
        
    }
}
