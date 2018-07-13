package ru.ftc.android.shifttemple.features.tasks.domain.model;

public final class Task {
    private String task_id;
    private String user_id;
    private String title;
    private String short_description;
    private String description;
    private String date;
    private String status;
    private String id_selected_bid;

    private Boolean task_is_mine;

    public Task(String title, String short_description, String description) {
        this.title = title;
        this.short_description = short_description;
        this.description = description;
    }

    public String getId() {
        return task_id;
    }

    public String getTitle() {
        return title;
    }

    public String getUserId() {
        return user_id;
    }


    public String getShortDescription() {
        return short_description;
    }

    public String getDescription() {
        return description;
    }

   // public String

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public String getIdSelectedBid() {
        return id_selected_bid;
    }

    public Boolean getTaskIsMine() {
        return task_is_mine;
    }

    public void setTaskIsMine(Boolean task_is_mine) {
        this.task_is_mine = task_is_mine;
    }
}
