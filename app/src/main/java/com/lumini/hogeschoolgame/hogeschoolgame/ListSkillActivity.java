package com.lumini.hogeschoolgame.hogeschoolgame;

/**
 * Created by Cristiaan on 15-4-2015.
 */
import android.app.ListActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;

public class ListSkillActivity extends ListActivity {

    //GameManager instance = GameManager.getInstance();
    Manager instance = Manager.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new MobileArrayAdapter(this, instance.getcollection()  ));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        //get selected items
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();

    }


}
