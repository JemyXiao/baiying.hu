package com.baiying.hu.service;

import com.baiying.hu.entity.Project;
import com.baiying.hu.entity.ProjectExample;
import com.baiying.hu.entity.dto.ProjectEditDto;

import java.util.List;

/**
 * Created by jmx
 * 2017/9/28.
 */
public interface ProjectService {
    int insertProject(Project project);

    List<Project> getProjectListBySelect(ProjectExample example);

    int editProject(ProjectEditDto dto);
}
