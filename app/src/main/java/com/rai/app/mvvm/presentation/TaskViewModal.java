package com.rai.app.mvvm.presentation;

import com.rai.app.mvvm.modals.Task;
import com.rai.app.mvvm.presentation.base.BaseViewModel;

/**
 * Created by amitrai on 12/5/17.
 * this class is used for :- view modal for tasks
 */

public class TaskViewModal extends BaseViewModel {

    Task task;
    String title_text;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }
}
