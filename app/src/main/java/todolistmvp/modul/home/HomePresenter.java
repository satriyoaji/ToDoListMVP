package todolistmvp.modul.home;


import java.util.ArrayList;

import todolistmvp.data.model.Task;
import todolistmvp.data.source.local.DatabaseContract;
import todolistmvp.data.source.local.TableHandler;
import todolistmvp.data.source.session.SessionRepository;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;
    private final SessionRepository sessionRepository;
    private final TableHandler tableHandler;

    public HomePresenter(HomeContract.View view, SessionRepository sessionRepository, TableHandler tableHandler) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.tableHandler = tableHandler;
    }

    @Override
    public void start() {

    }

    public ArrayList<Task> getDataSet() {
        //get Data from DB
        ArrayList<Task> data = tableHandler.readAll();

        return data;
    }

    @Override
    public void updateChecked(String id, Boolean isChecked) {
        Task updatedTask = (Task) tableHandler.readById(id);

        if(isChecked) //to check
            updatedTask.setCheck(1);
        else //uncheck
            updatedTask.setCheck(0);
        System.out.println("cek " + isChecked.toString() + " jadi " + updatedTask.getCheck());

        tableHandler.update(updatedTask);
    }

    @Override
    public void performLogout() {

    }
}
