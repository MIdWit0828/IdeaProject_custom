package com.MidWit.jframe.view;
import com.MidWit.jframe.model.dto.IdeaDTO;

import javax.swing.*;
import java.awt.*;

public class PopupEvent extends JOptionPane {
    Font myFont = new Font("고딕", Font.TRUETYPE_FONT, 13);
    public PopupEvent() {
        UIManager.put("OptionPane.messageFont", myFont);
        UIManager.put("OptionPane.buttonFont", myFont);
    }
    public IdeaDTO createNewIdea(){
        String name = JOptionPane.showInputDialog("새로운 아이디어 이름 입력");
        if (name == null) {
            return null;
        }
        int rank = Integer.parseInt(JOptionPane.showInputDialog("난이도를 정수로 입력 (1~5)"));
        if(rank >5 || rank < 1){
            warning("난이도를 잘못 입력하셨습니다.");
            return null;
        }
        String[] answer = {"색", "동세", "캐릭터", "복장", "배경", "컨셉"};
        int tagNum = JOptionPane.showOptionDialog(this, "태그입력", "",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, answer, null);
        IdeaDTO idea = new IdeaDTO(name, rank, tagNum);

        return idea;
    }
    public Boolean askAgain(String str) {
        int temp = JOptionPane.showConfirmDialog(this, str, "",
                JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
        if (temp == 0) {
            return true;
        }else return false;
    }

    public void warning(String str) {
        JOptionPane.showMessageDialog(this,str,"",ERROR_MESSAGE);
    }
}
