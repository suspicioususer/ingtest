package com.project.falconic_solutions.ingtest.view.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.falconic_solutions.ingtest.R;
import com.project.falconic_solutions.ingtest.backend.model.Project;
import com.project.falconic_solutions.ingtest.backend.repository.Helper;
import com.project.falconic_solutions.ingtest.databinding.FragmentProjectListBinding;
import com.project.falconic_solutions.ingtest.view.adapter.ProjectAdapter;
import com.project.falconic_solutions.ingtest.view.callback.ProjectClickCallback;
import com.project.falconic_solutions.ingtest.viewmodel.ProjectListViewModel;

import java.util.List;

public class ProjectListFragment extends Fragment {

    public static final String TAG = "ProjectListFragment";
    private ProjectAdapter projectAdapter;
    private FragmentProjectListBinding binding;
    private String username = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);

        projectAdapter = new ProjectAdapter(projectClickCallback);
        binding.rvProjectList.setAdapter(projectAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProjectListViewModel.Factory factory = new ProjectListViewModel.Factory(getActivity().getApplication(), username);

        final ProjectListViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectListViewModel.class);

        if (username != null && !username.equals("")) {
            viewModel.setProjectListObservable(username);
            observeViewModel(viewModel);
        }

        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etUsername.getText() != null) {
                    username = binding.etUsername.getText().toString();
                }
                viewModel.setProjectListObservable(username);
                observeViewModel(viewModel);
            }
        });

        //observeViewModel(viewModel);
    }

    private void observeViewModel(ProjectListViewModel viewModel) {
        viewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if (projects != null) {
                    Helper.setFavoriteItems(projects);
                    projectAdapter.setProjectList(projects);
                }
            }
        });
    }

    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).show(username, project);
            }
        }
    };

}