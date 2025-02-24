package ru.develgame.codelab.javaeeasync;

import ru.develgame.codelab.javaeeasync.async.AsyncTask;
import ru.develgame.codelab.javaeeasync.async.AsyncTaskManager;
import ru.develgame.codelab.javaeeasync.async.AsyncTaskStatus;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Named("demoAsyncTasks")
@ViewScoped
public class DemoAsyncTasksBean implements Serializable {
    @EJB
    private AsyncTaskManager asyncTaskManager;

    private AsyncBean asyncBean;

    private String page;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        page = ((HttpServletRequest) context.getExternalContext().getRequest()).getRequestURI();
    }
    
    public int getRandomValue() {
        asyncBean = CDI.current().select(AsyncBean.class).get();
        return asyncBean.getRandomValue();
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Refreshed");
    }

    public AsyncBean getAsyncBean() {
        return asyncBean;
    }

    public void startAsyncTask() {
        AsyncBean asyncBean = CDI.current().select(AsyncBean.class).get();
        asyncTaskManager.startAsyncTask(page, asyncBean);
    }

    public List<AsyncTask> getAsyncTasks() {
        return asyncTaskManager.getAsyncTasks(page);
    }

    public String getAsyncTaskInfo(AsyncBean asyncTask) {
        if (asyncTask.getAsyncTaskStatus() == AsyncTaskStatus.IN_PROGRESS)
            return "Random value: " + asyncTask.getRandomValue();

        return "";
    }

    public void undoTask(AsyncTask asyncTask) {
         asyncTaskManager.undoTask(page, asyncTask);
    }
}
