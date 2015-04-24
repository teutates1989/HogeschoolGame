package com.lumini.hogeschoolgame.hogeschoolgame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.UUID;


public class MainActivity extends ActionBarActivity {
    Manager instance = Manager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ScanNow(View v){

    //public void onClick(View v) {


       // IntentIntegrator integrator = new IntentIntegrator(this);
       // integrator.initiateScan(this);
        IntentIntegrator.initiateScan(this);


    }
    public void Skills(View v){

        Intent myIntent = new Intent(v.getContext(),ListSkillActivity.class);
        v.getContext().startActivity(myIntent);


    }

    public void DebugClear(View v){

        instance.ClearCollection();
        new AlertDialog.Builder(this)
                .setTitle("Cleared")
                .setMessage("List is cleared")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                /*.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent ) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result

            System.out.println(scanResult.toString());
        }
        String Result = scanResult.getContents();

        final int size = instance.getTotalSkills().length;
        for (int i = -1; i < size; i++)
       {
           String Test = String.valueOf(i);
           if (Result.equals(Test)) {

                instance.setCollection(instance.getASkill(i));
                //do something with i
                System.out.println("Er is een skill unlocked");
               System.out.println(i);
                System.out.println(instance.getASkill(Integer.parseInt(Test)));


               new AlertDialog.Builder(this)
                       .setTitle("Nieuwe Skill Toegevoegd")
                       .setMessage(instance.getASkill(Integer.parseInt(Test)) )
               .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {
                       // continue with delete
                   }
               })
                /*.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .show();





            }
       }

    }

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        System.out.println(uniqueID);
        return uniqueID;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
