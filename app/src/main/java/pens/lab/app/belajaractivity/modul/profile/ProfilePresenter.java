package pens.lab.app.belajaractivity.modul.profile;

/**
 * Created by fahrul on 13/03/19.
 */

public class ProfilePresenter implements ProfileContract.Presenter{
    private final ProfileContract.View view;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

}
