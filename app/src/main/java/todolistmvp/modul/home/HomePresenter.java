package todolistmvp.modul.home;


import java.util.ArrayList;

import todolistmvp.data.model.Task;

public class HomePresenter implements HomeContract.Presenter{
    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    public ArrayList<Task> getDataSet() {
        //get Data from DB
        ArrayList<Task> data = new ArrayList<Task> ();
        data.add(new Task("1","Task 1", "27 August 2020", "Deskripsi task satu", false));
        data.add(new Task("2", "Task 2", "27 July 2020","Deskripsi task dua", true));
        data.add(new Task("3", "Task 3", "20 May 2020","Deskripsi task tiga", true));
        return data;
    }
}
