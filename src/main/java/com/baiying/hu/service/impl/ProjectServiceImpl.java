package com.baiying.hu.service.impl;

import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.Project;
import com.baiying.hu.entity.ProjectExample;
import com.baiying.hu.entity.dto.ProjectEditDto;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.mapper.ConsultantMapper;
import com.baiying.hu.mapper.ProjectMapper;
import com.baiying.hu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/28
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final ConsultantMapper consultantMapper;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper, ConsultantMapper consultantMapper) {
        this.projectMapper = projectMapper;
        this.consultantMapper = consultantMapper;
    }

    @Override
    public int insertProject(Project project) {
        if (Objects.isNull(project.getId())) {
            project.setStatus(Constant.TO_DO);
            return projectMapper.insert(project);
        } else {
            return projectMapper.updateByPrimaryKeySelective(project);
        }
    }

    @Override
    public List<Project> getProjectListBySelect(ProjectExample example) {
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project project : projects) {
            if (!Objects.isNull(project.getConsultantId()) && project.getConsultantId() != 0) {
                ConsultantResult consultantResult = consultantMapper.selectByPrimaryKey(project.getConsultantId());
                project.setConsultantName(consultantResult.getActualName());
            }
        }
        return projects;
    }

    @Override
    public int editProject(ProjectEditDto dto) {
        return projectMapper.editProjectInfo(dto);
    }
}
