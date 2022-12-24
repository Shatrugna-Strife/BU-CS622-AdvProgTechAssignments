package com.met622;

import com.met622.Entity.ControlClass;
import com.met622.Entity.MultipleLinesChart;
import com.met622.constant.Constant;
import com.met622.model.Pair;

import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty("entityExpansionLimit", "2000000");
        ControlClass controlClass = new ControlClass();
        for(String tmp : Constant.WORD_LIST){
            Pair<List<Integer>, Pair<List<Double>, List<Double>>> temp = controlClass.execute(tmp);
            new MultipleLinesChart(tmp,temp.getFirst(), temp.getSecond().getFirst(), temp.getSecond().getSecond()).setVisible(true);
        }


//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MultipleLinesChart().setVisible(true);
//            }
//        });

    }

}