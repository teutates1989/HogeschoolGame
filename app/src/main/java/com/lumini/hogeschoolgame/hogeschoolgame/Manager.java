package com.lumini.hogeschoolgame.hogeschoolgame;

/**
 * Created by Cristiaan on 15-4-2015.
 */


import android.os.Handler;

import java.util.ArrayList;

public class Manager {

    private static Manager INSTANCE;
    static final String[] Skills_sk = new String[]{"Communicatie", "Programmeren", "Accesability", "teamwork"};

    // static final String[] Skills_collection = new String[] { };
    private ArrayList<String> Skills_collection = new ArrayList<String>();

    public static Manager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Manager();
        }
        return INSTANCE;
    }

    public String[] getTotalSkills() {
        return Skills_sk;
    }

    public String getASkill(int i) {

      return  Skills_sk[i].toString();
    }

    public ArrayList<String>  getcollection (){
        return Skills_collection;
    }

    public void  setCollection(String object){


        Skills_collection.add(object);

    }



}
