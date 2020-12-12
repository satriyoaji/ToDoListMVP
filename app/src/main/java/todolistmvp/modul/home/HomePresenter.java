package todolistmvp.modul.home;


import java.util.ArrayList;

import todolistmvp.data.model.Task;
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
    public void performLogout() {

    }
}
