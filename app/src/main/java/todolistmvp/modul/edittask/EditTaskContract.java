package todolistmvp.modul.edittask;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void logout();
        void showDateDialog();
        void showDeleteDialog();
        void saveChangeTask();
    }

    interface Presenter extends BasePresenter {
        
    }
}
