package com.lumini.hogeschoolgame.hogeschoolgame;

import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends ActionBarActivity {

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



    public void onActivityResult(int requestCode, int resultCode, Intent intent ) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            // handle scan result
            System.out.println("Er is geen resultaat gevonden");
            System.out.println(scanResult.toString());
        }
        String Result = scanResult.getContents();
        
        if (Result.equals("1")) {

            System.out.println("Er is een skill unlocked");
        }

        // else continue with any other code you need in the method

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
