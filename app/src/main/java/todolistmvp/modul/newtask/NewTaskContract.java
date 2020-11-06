package todolistmvp.modul.newtask;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;

public interface NewTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
    }

    interface Presenter extends BasePresenter {
        void saveData(String id, String title, String date, String description);
    }
}
