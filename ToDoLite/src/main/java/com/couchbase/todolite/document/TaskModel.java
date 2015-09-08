package com.couchbase.todolite.document;

public class TaskModel {
    String _id;
    String list_id;
    String created_at;
    boolean checked;
    String title;
    String type;

    public TaskModel() {
    }

    public TaskModel(String _id, String list_id, String created_at, boolean checked, String title, String type) {
        this._id = _id;
        this.list_id = list_id;
        this.created_at = created_at;
        this.checked = checked;
        this.title = title;
        this.type = type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
