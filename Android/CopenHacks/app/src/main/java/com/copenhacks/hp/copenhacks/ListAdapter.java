package com.copenhacks.hp.copenhacks;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ListAdapter extends FirebaseListAdapter<Alimento> {
    private final Context context;
    private List<Alimento> originalData = null;
    Calendar date;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference foodRef = database.getReference("Food");
    DatabaseReference eliminarRef = database.getReference();



    public ListAdapter(Query mRef, Class<Alimento> mAlimentoClass, int mLayout, Activity activity) {
        super(mRef, mAlimentoClass, mLayout, activity);
        this.context = activity;
    }

    @Override
    protected void populateView ( final View v, final Alimento model){
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        final String name = model.name;
        //tinc el nom de l'aliment

        TextView textView = (TextView) v.findViewById(R.id.textView);
        textView.setText(name);
        //nom ficat al listview

        TextView textView1 = v.findViewById(R.id.propietari);
        textView1.setText(model.propietari);

        ImageView basura = v.findViewById(R.id.basura);


        basura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              eliminarRef.child(String.valueOf(model.getId())).removeValue();
            }
        });


        //FOTO


        final ImageView imageView = (ImageView) v.findViewById(R.id.image);
        if (model.getFoto() != null) {
                Glide.with(context).load(model.getFoto()).into(imageView);
        }//Imatge pillada


        TextView textView2 = v.findViewById(R.id.dia);
        if(model.data != 0) {
            if((-1000*model.data - System.currentTimeMillis()) <= 0 ){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.red ));

            }
             else if((-1000*model.data - System.currentTimeMillis()) > 0 &&  ((-1000*model.data - System.currentTimeMillis())/100< 41472000)){
                v.setBackgroundColor(ContextCompat.getColor(context, R.color.orange));
            }

            Date date = new Date((Long) model.data * -1000);
            SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
            String strDt = simpleDate.format(date);
            textView2.setText(strDt);

        }
        else{
            textView2.setText("-");
        }


//TODO: data caducitat
//TODO: ARREGLAR CODI

    }

}
