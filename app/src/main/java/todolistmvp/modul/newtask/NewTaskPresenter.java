package todolistmvp.modul.newtask;


import todolistmvp.data.model.Task;

public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;

    public NewTaskPresenter(NewTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    public void saveData(final String id, final String title, final String date, final String description){
        //update task
        Task newTask = new Task(id, title, date, description, false);
        //then go back to task list
        view.redirectToTaskList();
    }
}
