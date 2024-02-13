package com.MidWit.jframe;

import com.MidWit.jframe.controller.IdeaController;
import com.MidWit.jframe.view.MainFrame;
import com.MidWit.jframe.view.PopupEvent;

public class Application {

    public static void main(String[] args) {
        /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        ERD의 작업테이블 부분 구현 안됨...ㅠㅠ
        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        * */
        IdeaController ideaController = new IdeaController();
        PopupEvent popupEvent = new PopupEvent();
        MainFrame.getInstance();
        ideaController.selectAllIdea();
        ideaController.selectAllChoice();
        ideaController.selectAllWork();
        popupEvent.warning("수정 및 삭제는 선택중인 리스트에서만 작동합니다.");
        popupEvent.warning("작업테이블 구현 안됬습니다...ㅠㅠㅠ");

    }
}
