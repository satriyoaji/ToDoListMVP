package todolistmvp.modul.home;

import java.util.ArrayList;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;
import todolistmvp.data.model.Task;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void createNewTask();
        void editTask(String id);
        void logout();
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
        void updateChecked(String id, Boolean isChecked);
        void performLogout();
    }
}
