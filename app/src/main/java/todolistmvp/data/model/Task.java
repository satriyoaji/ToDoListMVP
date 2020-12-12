package todolistmvp.data.model;

import todolistmvp.base.BaseModel;

public class Task extends BaseModel {
    private String id;
    private String title;
    private String date;
    private String desc;
    private int check;

    public Task(String id, String title, String date, String desc, int check) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.check = check;
    }

    public Task(String title, String date, String desc, int check) {
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.check = check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
