package com.MidWit.jframe.model.service;

import com.MidWit.jframe.common.IdeaCondition;
import com.MidWit.jframe.model.dao.IdeaDAO;
import com.MidWit.jframe.model.dto.IdeaDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.MidWit.jframe.common.Template.getSession;

public class IdeaService {
    IdeaDAO dao;
    public List<IdeaDTO> selectAllIdea() {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        List<IdeaDTO> ideaList = dao.selectAllIdea();
        if (ideaList != null && !ideaList.isEmpty()) {
            session.commit();
        } else {
            session.rollback();
        }

        session.close();
        return ideaList;
    }

    public List<IdeaDTO> selectIdeaByCondition(IdeaCondition ideaCondition) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        List<IdeaDTO> ideaList = dao.selectIdeaByCondition(ideaCondition);
        if (ideaList != null && !ideaList.isEmpty()) {
            session.commit();
        } else {
            session.rollback();
        }
        session.close();
        return ideaList;
    }

    public List<IdeaDTO> insertNewIdea(IdeaDTO idea) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        Boolean isSuccess = dao.insertNewIdea(idea);
        if (!isSuccess) {
            session.rollback();
            return null;
        }
        List<IdeaDTO> ideaList = dao.selectAllIdea();
        if (ideaList == null && ideaList.isEmpty()) {
            session.rollback();
            return null;
        }
        session.commit();
        session.close();

        return ideaList;
    }

    public boolean updateIdea(IdeaDTO idea) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        Boolean isSuccess = dao.updateNewIdea(idea);
        if (isSuccess) {
            session.commit();
        } else {
            session.rollback();
        }
        session.close();
        return isSuccess;
    }

    public int deleteIdea(IdeaCondition ideaCondition) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        Integer result = dao.deleteIdea(ideaCondition);
        if (result > 0) {
           session.commit();
        } else {
            session.rollback();
        }
        session.close();
        return result;
    }
    public List<IdeaDTO> selectAllChoice() {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        List<IdeaDTO> ideaList = dao.selectAllChoice();
        if (ideaList != null && !ideaList.isEmpty()) {
            session.commit();
        } else {
            session.rollback();
        }

        session.close();
        return ideaList;
    }

    public List<IdeaDTO> selectMainIdeaToChoiceList(IdeaDTO idea) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        //코드를 tbl_idea_choice 에 입력
        boolean isInsertChoiceSuccess = dao.insertChoice(idea.getIdeaCode());
        if (!isInsertChoiceSuccess) {
            session.rollback();
            return null;
        }

        //해당하는 코드의 workStatus를 "S"로 변경
        idea.setWorkStatus("S");
        boolean isUpdateIdeaSuccess = dao.updateNewIdea(idea);
        if (!isUpdateIdeaSuccess) {
            session.rollback();
            return null;
        }

        //idea_choice의 모든 DTO를 불러온다
        List<IdeaDTO> ideaList = dao.selectAllChoice();
        if (ideaList == null ) {
            session.rollback();
            return null;
        }
        session.commit();
        return ideaList;

    }

    public List<IdeaDTO> deleteChoiceIdeaByIdeaDTO(IdeaDTO idea) {
        SqlSession session = getSession();
        dao = session.getMapper(IdeaDAO.class);

        //해당하는 코드를 idea_choice에서 삭제
        int isDeleteChoiceResult = dao.deleteChoice(idea.getIdeaCode());
        if (isDeleteChoiceResult < 0) {
            session.rollback();
            return null;
        }

        //해당하는 코드의 workStatus를 "N"로 변경
        idea.setWorkStatus("N");
        boolean isUpdateIdeaSuccess = dao.updateNewIdea(idea);
        if (!isUpdateIdeaSuccess) {
            session.rollback();
            return null;
        }

        //idea_choice의 모든 DTO를 불러온다
        List<IdeaDTO> ideaList = dao.selectAllChoice();
        if (ideaList == null ) {
            session.rollback();
            System.out.println("롤백!");
            return null;
        }
        session.commit();
        return ideaList;

    }
}
