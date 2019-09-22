package com.project.falconic_solutions.ingtest.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.project.falconic_solutions.ingtest.backend.model.Project;
import com.project.falconic_solutions.ingtest.backend.repository.ProjectRepository;

public class ProjectViewModel extends AndroidViewModel {

    private final LiveData<Project> projectObservable;
    private final String username;
    private final String projectID;

    public ObservableField<Project> project = new ObservableField<>();

    public ProjectViewModel(@NonNull Application application,
                            final String username,
                            final String projectID) {
        super(application);
        this.username = username;
        this.projectID = projectID;
        projectObservable = ProjectRepository.getInstance().getProjectDetails(username, projectID);
    }

    public LiveData<Project> getObservableProject() {
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        private final String username;
        private final String projectID;

        public Factory(@NonNull Application application, String username, String projectID) {
            this.application = application;
            this.username = username;
            this.projectID = projectID;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new ProjectViewModel(application, username, projectID);
        }
    }
}