package todolistmvp.modul.newtask;


import todolistmvp.data.model.Task;
import todolistmvp.data.source.local.TableHandler;
import todolistmvp.data.source.session.SessionRepository;

public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;
    private final SessionRepository sessionRepository;
    private final TableHandler tableHandler;

    public NewTaskPresenter(NewTaskContract.View view, SessionRepository sessionRepository, TableHandler tableHandler) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {

    }

    public void saveData(final String title, final String date, final String description){
        Task newTask = new Task(title, date, description, 0);
        //save new task
        tableHandler.create(newTask);

        //then go back to task list
        view.redirectToTaskList();
    }
}
