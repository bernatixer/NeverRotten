package com.copenhacks.hp.copenhacks;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListAdapter listAdapter;
    public EditText inputSearch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
        setContentView(R.layout.activity_main);

        if(!isNetworkAvailable()){
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("No Connection Found ");
            alertDialog.setMessage("Your device needs to be connected to run this app.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            DatabaseReference mFoodRef = FirebaseDatabase.getInstance().getReference("Food");
            listView = (ListView) findViewById(R.id.listView);
            listAdapter = new ListAdapter(mFoodRef, Alimento.class, R.layout.list_item, this);
            listView.setAdapter(listAdapter);



            // pintarvista();

            FloatingActionButton mfloatingbutton;
            mfloatingbutton = (FloatingActionButton) findViewById(R.id.floatingbutton);
            mfloatingbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(MainActivity.this, CreateActivity.class);
                    MainActivity.this.startActivity(intent2);
                }
            });

        }

    }



    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

   /* public void pintarvista() {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        int n = listView.getChildCount();
        for(int i = 0; i< n; ++i){

            if (i < firstListItemPosition || i > lastListItemPosition ) {
                view = listView.getAdapter().getView(i, null, listView);
            } else {
                final int childIndex = i - firstListItemPosition;
                view = listView.getChildAt(childIndex);
            }

            view.setBackgroundColor(Color.RED);

        }

    }*/


}

