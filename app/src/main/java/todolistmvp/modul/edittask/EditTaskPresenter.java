package todolistmvp.modul.edittask;


import todolistmvp.data.model.Task;

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
//    private String id;

    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
//        loadData(id);
    }

    @Override
    public void saveData(final String id, final String title, final String date, final String description){
        //update task
        Task newTask = new Task(id, title, date, description, false);
        //then go back to task list
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        //load data task by id
        //then send data to fragment
        Task task = new Task("4", "title of taskIndex:"+id, "date of taskIndex:"+id, "description of taskIndex:"+id, false);
        view.showData(task);
    }

    @Override
    public void deleteData(String id) {
        //get data by id
        //then remove data from DB
        Task task = new Task("4", "title of taskIndex:"+id, "date of taskIndex:"+id, "description of taskIndex:"+id, false);
        view.deleteProcess(task);
    }

}
