package todolistmvp.data.source.local;

import java.util.ArrayList;

public interface TableHandler<T> {
    public void create(T t);
    public T readById(String id);
    public ArrayList<T> readAll();
    public ArrayList<T> readByUser(String user);
    public void update(T t);
    public void delete(T t);
}
