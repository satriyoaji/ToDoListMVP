package todolistmvp.modul.home;

import java.util.ArrayList;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;
import todolistmvp.data.model.Task;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void createNewTask();
        void editTask(String id);
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
    }
}
