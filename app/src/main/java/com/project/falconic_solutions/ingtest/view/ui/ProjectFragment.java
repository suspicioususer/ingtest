package com.project.falconic_solutions.ingtest.view.ui;

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
import com.project.falconic_solutions.ingtest.databinding.FragmentProjectDetailsBinding;
import com.project.falconic_solutions.ingtest.viewmodel.ProjectViewModel;
import com.squareup.picasso.Picasso;

public class ProjectFragment extends Fragment {

    private static final String KEY_USERNAME = "username";
    private static final String KEY_PROJECT_ID = "project_id";
    private FragmentProjectDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectViewModel.Factory factory = new ProjectViewModel.Factory(
                getActivity().getApplication(), getArguments().getString(KEY_USERNAME), getArguments().getString(KEY_PROJECT_ID));

        final ProjectViewModel viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectViewModel.class);

        binding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectFragment.super.getActivity().onBackPressed();
            }
        });

        binding.ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long repoId = viewModel.project.get().id;
                if(Helper.favoriteList.contains(repoId)){
                    Helper.favoriteList.remove(repoId);
                } else {
                    Helper.favoriteList.add(repoId);
                }
            }
        });

        binding.setProjectViewModel(viewModel);

        observeViewModel(viewModel);
    }

    private void observeViewModel(final ProjectViewModel viewModel) {
        viewModel.getObservableProject().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project != null) {
                    viewModel.setProject(project);
                    Picasso.get().load(viewModel.project.get().owner.avatar_url).into(binding.ivAvatar);
                }
            }
        });
    }

    public static ProjectFragment forProject(String username, String projectID) {
        ProjectFragment fragment = new ProjectFragment();
        Bundle args = new Bundle();

        args.putString(KEY_USERNAME, username);
        args.putString(KEY_PROJECT_ID, projectID);
        fragment.setArguments(args);

        return fragment;
    }
}
