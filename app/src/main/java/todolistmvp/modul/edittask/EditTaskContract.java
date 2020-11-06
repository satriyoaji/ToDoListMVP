package todolistmvp.modul.edittask;

import todolistmvp.base.BasePresenter;
import todolistmvp.base.BaseView;
import todolistmvp.data.model.Task;

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void showData(Task task);
        void setTaskId(String id);
        String getTaskId();
        void redirectToTaskList();
        void deleteProcess(Task task);
    }

    interface Presenter extends BasePresenter {
        void saveData(String id, String title, String date, String description);
        void loadData(String id);
        void deleteData(String id);
    }
}
