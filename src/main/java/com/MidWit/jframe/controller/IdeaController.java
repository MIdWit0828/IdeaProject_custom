package com.MidWit.jframe.controller;

import com.MidWit.jframe.common.IdeaCondition;
import com.MidWit.jframe.model.dto.IdeaDTO;
import com.MidWit.jframe.model.service.IdeaService;
import com.MidWit.jframe.view.MainFrame;
import com.MidWit.jframe.view.MenuPrint;
import com.MidWit.jframe.view.PopupEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IdeaController {
    private IdeaService ideaService = new IdeaService();
    private MenuPrint menuPrint = new MenuPrint();
    private PopupEvent popupEvent = new PopupEvent();
    private Map<String, String> sortMap = new HashMap<>();

    public IdeaController() {
        sortMap.put("sortOption", "코드");
    }

    public void selectAllIdea() {
        List<IdeaDTO> ideaList = ideaService.selectAllIdea();

        if (ideaList != null && !ideaList.isEmpty()) {
            menuPrint.printSuccessMsg("selectAllIdea", 0);
            MainFrame.getInstance().setMainJList(sortList(ideaList));
        } else {
            menuPrint.printErrorMsg("selectAllIdea");
        }
    }

    public void selectIdeaByCondition(IdeaCondition ideaCondition) {

        List<IdeaDTO> ideaList = ideaService.selectIdeaByCondition(ideaCondition);
        ideaList = sortList(ideaList);

        if (ideaList != null && !ideaList.isEmpty()) {
            menuPrint.printSuccessMsg("byCondition", 0);
            MainFrame.getInstance().setMainJList(sortList(ideaList));
        } else {
            menuPrint.printErrorMsg("byCondition");
        }
    }

    public void insertIdea() {
        IdeaDTO idea = popupEvent.createNewIdea();
        List<IdeaDTO> ideaList = ideaService.insertNewIdea(idea);

        if (ideaList != null && !ideaList.isEmpty()) {
            menuPrint.printSuccessMsg("insertIdea", 0);
            MainFrame.getInstance().setMainJList(sortList(ideaList));
        } else {
            menuPrint.printErrorMsg("insertIdea");
        }
    }
    public void selectAllChoice() {
        List<IdeaDTO> choiList = ideaService.selectAllChoice();
        if (choiList != null && !choiList.isEmpty()) {
            menuPrint.printSuccessMsg("selectAllChoice",0);
            MainFrame.getInstance().setChoiJList(choiList);
        } else {
            menuPrint.printErrorMsg("selectAllChoice");
            MainFrame.getInstance().setChoiJList(choiList);
        }
    }
    public void selectMainIdeaToChoiceList(IdeaDTO idea) {
        List<IdeaDTO> choiList = ideaService.selectMainIdeaToChoiceList(idea);
        if (choiList != null && !choiList.isEmpty()) {
            MainFrame.getInstance().setChoiJList(choiList);
//            MainFrame.getInstance().sortMainJList();
            selectAllIdea();
            menuPrint.printSuccessMsg("selectMainIdeaToChoiceList",0);
        } else {
            menuPrint.printErrorMsg("selectMainIdeaToChoiceList");
        }
    }

    public void deleteChoiceIdeaByIdeaDTO(IdeaDTO idea) {
        List<IdeaDTO> choiList = ideaService.deleteChoiceIdeaByIdeaDTO(idea);
        if (choiList != null ) {
            MainFrame.getInstance().setChoiJList(choiList);
//            MainFrame.getInstance().sortMainJList();
            selectAllIdea();
            menuPrint.printSuccessMsg("deleteChoiceIdeaByIdeaDTO",0);
        } else {
            menuPrint.printErrorMsg("deleteChoiceIdeaByIdeaDTO");
        }
    }


    public void updateIdea(IdeaDTO idea) {
        String str = idea.toString() + "\n이 아이디어를 수정 하시겠습니까?";
        if (!popupEvent.askAgain(str)) {
            return;
        }
        IdeaDTO newIdea = popupEvent.createNewIdea();
        newIdea.setIdeaCode(idea.getIdeaCode());
        newIdea.setWorkStatus(idea.getWorkStatus());

        boolean isSuccess = ideaService.updateIdea(newIdea);

        if (isSuccess) {
            menuPrint.printSuccessMsg("updateIdea", 0);
            selectAllChoice();
        } else {
            menuPrint.printErrorMsg("updateIdea");
        }
    }

    public void deleteIdea(IdeaDTO idea) {
        String str = idea.toString() + "\n이 아이디어를 삭제 하시겠습니까?";
        if (!popupEvent.askAgain(str)) {
            return;
        }

        IdeaCondition ideaCondition = new IdeaCondition("deleteByCode",Integer.toString(idea.getIdeaCode()));
        int result = ideaService.deleteIdea(ideaCondition);

        if (result > 0) {
            menuPrint.printSuccessMsg("deleteIdea", result);
            selectAllChoice();
        } else {
            menuPrint.printErrorMsg("deleteIdea");
        }
    }

    public void setSortMap(String sortOption) {
        sortMap.put("sortOption", sortOption);
    }

    public List<IdeaDTO> sortList(List<IdeaDTO> list) {
        List<IdeaDTO> sortList = list;
        if (sortMap.get("sortOption").equals("코드")) {
            sortList.sort((o1, o2) -> o1.getIdeaCode() - o2.getIdeaCode());
        } else if (sortMap.get("sortOption").equals("이름")) {
            sortList.sort(((o1, o2) -> o1.getIdeaName().compareTo(o2.getIdeaName())));
        } else if (sortMap.get("sortOption").equals("난이도")) {
            sortList.sort(((o1, o2) -> o1.getIdeaRank() - o2.getIdeaRank()));
        }
        return sortList;
    }

    public void selectAllWork() {
    }
}
