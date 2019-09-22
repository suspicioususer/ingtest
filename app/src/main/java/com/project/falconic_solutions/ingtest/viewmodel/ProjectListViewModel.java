package com.project.falconic_solutions.ingtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.project.falconic_solutions.ingtest.backend.model.Project;
import com.project.falconic_solutions.ingtest.backend.repository.ProjectRepository;

import java.util.List;

public class ProjectListViewModel extends AndroidViewModel {

    private final String username;
    private LiveData<List<Project>> projectListObservable;

    public ProjectListViewModel(Application application, String username) {
        super(application);
        this.username = username;
        projectListObservable = ProjectRepository.getInstance().getProjectList(username);

    }

    public LiveData<List<Project>> getProjectListObservable() {
        return projectListObservable;
    }

    public void setProjectListObservable(String username){
        projectListObservable = ProjectRepository.getInstance().getProjectList(username);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final String username;

        public Factory(@NonNull Application application, String username) {
            this.application = application;
            this.username = username;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProjectListViewModel(application, username);
        }
    }
}
