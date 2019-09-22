package com.project.falconic_solutions.ingtest.backend.repository;

import com.project.falconic_solutions.ingtest.backend.model.Project;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static ArrayList<Long> favoriteList = new ArrayList<>();

    public static void setFavoriteItems(List<Project> projectList){
        for(Project p : projectList){
            if(favoriteList.contains(p.id)){
                p.favoriteItem = true;
            } else {
                p.favoriteItem = false;
            }
        }
    }
}
