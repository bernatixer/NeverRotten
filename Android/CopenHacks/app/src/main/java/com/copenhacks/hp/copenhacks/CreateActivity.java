package com.copenhacks.hp.copenhacks;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateActivity  extends AppCompatActivity {
    DatabaseReference mFoodRef;
    DatabaseReference fotoRef;
    DatabaseReference util;
    private String m_Text = "";
    Calendar date;
    boolean primero = true;
    Context context;
    String strDt;
    boolean verificar = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        setContentView(R.layout.create_activity);
        control = FirebaseDatabase.getInstance().getReference();


        date = Calendar.getInstance();
       // key = "0";
        //hauré de saber quina és la que s'ha creat
        mFoodRef = FirebaseDatabase.getInstance().getReference("Food");
        final ImageView image = findViewById(R.id.image_view);
        final TextView text = findViewById(R.id.name2);
        final TextView text2 = findViewById(R.id.name3);
        final Button eliminar = findViewById(R.id.button2);
        final Button afegir = findViewById(R.id.button);
        //Ya lo tengo toh
       // Bundle extras = getIntent().getExtras();
      //  if(extras != null) key = extras.getString("KEY");

        FirebaseDatabase.getInstance().getReference("Info").child("scan").setValue(1);
        mFoodRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Query lastQuery = mFoodRef.orderByKey().limitToLast(1);
                lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (final DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            if(primero) {
                                showDialog();
                                primero = false;
                            }
                            if(!postSnapshot.child("data").getValue().toString().equals("0")) {
                                Long data = (Long) postSnapshot.child("data").getValue();
                                Date date = new Date((Long) data * -1000);
                                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                                String strDt = simpleDate.format(date);
                                text2.setText("YOUR EXPIRY DATE:  "+ strDt);
                                afegir.setVisibility(View.GONE);
                            }




                            Picasso.get().load(postSnapshot.child("foto").getValue().toString()).into(image);
                            text.setText(postSnapshot.child("name").getValue().toString());



                            eliminar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    postSnapshot.getRef().removeValue();

                                }
                            });

                            afegir.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(verificar){
                                        showDatePickerDialog();
                                        afegir.setText("CONFIRM");
                                        afegir.setBackgroundColor(R.color.colorPrimaryDark);
                                        verificar = false;
                                    }
                                    else{
                                        postSnapshot.child("data").getRef().setValue(date.getTimeInMillis()/-1000);
                                        verificar = true;
                                        Long data = (Long) postSnapshot.child("data").getValue();
                                        Date date = new Date((Long) data * -1000);
                                        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                                        String strDt = simpleDate.format(date);
                                        text2.setText("YOUR EXPIRY DATE:  "+ strDt);
                                        text.setText("WAITING...");

                                        Toast.makeText(CreateActivity.this, "New"+postSnapshot.child("name").getValue().toString()+" Added", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            }); }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    };
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {






            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                Query lastQuery = mFoodRef.orderByKey().limitToLast(1);
                lastQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        for (final DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            if(!postSnapshot.child("data").getValue().toString().equals("0")) {
                                Long data = (Long) postSnapshot.child("data").getValue();
                                Date date = new Date((Long) data * -1000);
                                SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
                                String strDt = simpleDate.format(date);
                                text2.setText("YOUR EXPIRICY DATE:  "+ strDt);
                                afegir.setVisibility(View.GONE);
                            }




                            Picasso.get().load(postSnapshot.child("foto").getValue().toString()).into(image);
                            text.setText(postSnapshot.child("name").getValue().toString());



                            eliminar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    postSnapshot.getRef().removeValue();
                                }
                            });

                            afegir.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(verificar){
                                        showDatePickerDialog();
                                        afegir.setText("CONFIRM");
                                        afegir.setBackgroundColor(R.color.colorPrimaryDark);
                                        verificar = false;
                                    }
                                    else{
                                        postSnapshot.child("data").getRef().setValue(date.getTimeInMillis()/-1000);
                                        verificar = true;
                                        text2.setText("YOUR EXPIRY DATE:");
                                        Toast.makeText(CreateActivity.this, "New "+postSnapshot.child("name").getValue()+" Added", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }); }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    };
                });






            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(fotoRef != null)

            Glide.with(context).load(fotoRef.toString()).into(image);

    }

//TODO: enviar les dates inicials positives

    private void showDatePickerDialog(){

        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero


                date.set(year, month, day);
                Date date2 = new Date((Long) date.getTimeInMillis() );
                SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                String strDt = simpleDate.format(date2);
                final TextView text2 = findViewById(R.id.name3);
                text2.setText("YOUR EXPIRY DATE: "+ strDt);
            }
        });
        newFragment.show(CreateActivity.this.getSupportFragmentManager(), "datePicker");

    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Put your name here:");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

}
