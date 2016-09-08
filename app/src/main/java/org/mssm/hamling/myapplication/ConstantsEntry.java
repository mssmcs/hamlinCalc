package org.mssm.hamling.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ConstantsEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constants_entry);
        Log.d("Lifecycle","ConstantsEntry onCreate");
    }

    public void onClickConstant(View v)
    {
        Intent returnIntent = new Intent();

        switch (v.getId())
        {
            case R.id.eButton:
                returnIntent.putExtra("result",Math.E);
                break;
            case R.id.piButton:
                returnIntent.putExtra("result",Math.PI);
                break;
        }

        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }



    public void onClickCancel(View v)
    {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Log.d("Lifecycle","ConstantsEntry onPause");
    }
    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        Log.d("Lifecycle","ConstantsEntry onStart");
    }

    @Override
    public void onStop() {
        super.onStop();  // Always call the superclass method first
        Log.d("Lifecycle","ConstantsEntry onStop");
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Log.d("Lifecycle","ConstantsEntry onResume");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass method first
        Log.d("Lifecycle","ConstantsEntry onDestroy");
    }
}
