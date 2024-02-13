package com.MidWit.jframe.model.dao;

import com.MidWit.jframe.common.IdeaCondition;
import com.MidWit.jframe.model.dto.IdeaDTO;

import java.util.List;
import java.util.Map;

public interface IdeaDAO {
    List<IdeaDTO> selectAllIdea();

    List<IdeaDTO> selectIdeaByCondition(IdeaCondition ideaCondition);

    Boolean insertNewIdea(IdeaDTO idea);

    Boolean updateNewIdea(IdeaDTO idea);

    Integer deleteIdea(IdeaCondition ideaCondition);

    Boolean insertChoice(int ideaCode);

    List<IdeaDTO> selectAllChoice();

    int deleteChoice(int ideaCode);
}
