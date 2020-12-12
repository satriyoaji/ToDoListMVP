package todolistmvp.modul.edittask;


import todolistmvp.data.model.Task;
import todolistmvp.data.source.local.TableHandler;

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
    private final TableHandler tableHandler;
    Task editedTask;

    public EditTaskPresenter(EditTaskContract.View view, TableHandler tableHandler) {
        this.view = view;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {
//        loadData(id);
    }

    @Override
    public void saveData(final String title, final String date, final String description){
        editedTask.setTitle(title);
        editedTask.setDesc(description);
        editedTask.setDate(date);
        editedTask.setCheck(editedTask.getCheck());

        tableHandler.update(editedTask);

        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        //load data task by id
        editedTask = (Task) tableHandler.readById(id);
        //then send data to fragment
        view.showData(editedTask);
    }

    @Override
    public void deleteData(String id) {
        editedTask = (Task) tableHandler.readById(id);
        tableHandler.delete(editedTask);
    }

}
