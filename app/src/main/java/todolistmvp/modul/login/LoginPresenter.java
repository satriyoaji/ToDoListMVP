package todolistmvp.modul.login;


import todolistmvp.data.model.User;
import todolistmvp.data.source.SessionRepository;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){
            view.redirectToHome();  //jika sudah login langsung masuk home
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login

        //if login success
        User loggedUser = new User(email, "TOKEN123456");                                    //new
        sessionRepository.setSessionData(loggedUser);

        // call redirect to home
        view.redirectToHome();
    }

}
