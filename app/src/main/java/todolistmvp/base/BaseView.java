package todolistmvp.base;


public interface BaseView<T> {
    void setPresenter(T presenter);
    void logout();

//    void setLoadingDialog(boolean isLoading, @Nullable String message);
//    void showStatus(int type, String message);
}
