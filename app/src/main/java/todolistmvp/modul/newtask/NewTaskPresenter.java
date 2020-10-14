package todolistmvp.modul.newtask;


public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;

    public NewTaskPresenter(NewTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

}
