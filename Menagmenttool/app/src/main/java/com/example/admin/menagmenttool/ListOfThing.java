package com.example.admin.menagmenttool;

import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.admin.menagmenttool.R.id.frgthree;

public class ListOfThing extends AppCompatActivity{

    public ListView listView;
    public List<String> array;
    public List<String>quote;
    AlertDialog alertDialog1;
    String name;
    private static final int uniqeID = 123456;
    String id;
    CharSequence[] values = new CharSequence[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_thing);
        listView = (ListView) findViewById(R.id.list);
        array = new ArrayList<>();
        quote = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        name = extras.getString("Message");
        id = extras.getString("ID");

        create();

        ThreeFragment frag2 = new ThreeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
       // ft.add(R.id.list,frag2);
        ft.commit();

    }
    public void create(){
        array.add("How much money you spend today?"); //DONE
        array.add("How you feel yourself today?");  // DONE
        array.add("Draw a plan");// Done
        array.add("Day photo");// DLYA SOFTA TOZHE
        array.add("Day recipe");
        array.add("Quotation of the day");// DONE
        array.add("Day Dream");


        quote.add("We become what we think about. –Earl Nightingale");
        quote.add("Life is 10% what happens to me and 90% of how I react to it. –Charles Swindoll");
        quote.add("The mind is everything. What you think you become.  –Buddha");
        quote.add(" Your time is limited, so don’t waste it living someone else’s life. –Steve Jobs");
        quote.add(" I am not a product of my circumstances. I am a product of my decisions. –Stephen Covey");
        quote.add("The two most important days in your life are the day you are born and the day you find out why. –Mark Twain");
        quote.add(" I attribute my success to this: I never gave or took any excuse. –Florence Nightingale");

        for(int i = 0; i<3; i++){
           int  indx = new Random().nextInt(quote.size());
            values[i] = quote.get(indx);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    Intent intent = new Intent(ListOfThing.this, moneypage.class);
                    intent.putExtra("Message2",name);
                    intent.putExtra("idd",id);
                    startActivity(intent);

                }
                if(i==2){
                    Intent intent = new Intent(ListOfThing.this, Main2Activity.class);
                    intent.putExtra("Message2",name);
                    intent.putExtra("idd",id);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent = new Intent(ListOfThing.this, LoadImage.class);
                    intent.putExtra("Message2",name);
                    intent.putExtra("idd",id);
                    startActivity(intent);
                    startActivity(intent);
                } if(i==4){
                    Intent intent = new Intent(ListOfThing.this, recipesPage.class);
                    intent.putExtra("Message2",name);
                    intent.putExtra("idd",id);
                    startActivity(intent);
                }if(i==6){
                    Intent intent = new Intent(ListOfThing.this, Sleepingactivity.class);
                    intent.putExtra("Message2",name);
                    intent.putExtra("idd",id);
                    startActivity(intent);
                }
                if(i==5) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListOfThing.this);

                    builder.setTitle("Quote of day,Please choose one");

                    builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int item) {

                            switch(item)
                            {
                                case 0:

                                    Toast.makeText(ListOfThing.this, values[0], Toast.LENGTH_LONG).show();
                                    function(values[0].toString());
                                    break;
                                case 1:

                                    Toast.makeText(ListOfThing.this, values[1], Toast.LENGTH_LONG).show();
                                    function(values[1].toString());
                                    break;
                                case 2:

                                    Toast.makeText(ListOfThing.this, values[2], Toast.LENGTH_LONG).show();
                                    function(values[2].toString());
                                    break;
                            }
                            alertDialog1.dismiss();
                        }
                    });
                    alertDialog1 = builder.create();
                    alertDialog1.show();
                }
                if(i==1){

                    AlertDialog.Builder builder = new AlertDialog.Builder(ListOfThing.this);

                    View v = LayoutInflater.from(ListOfThing.this).inflate(R.layout.custom_layout, null);

                    TextView title = (TextView) v.findViewById(R.id.title);
                    ImageButton imageButton = (ImageButton) v.findViewById(R.id.image);

                    title.setText("Hello There!");

                    imageButton.setImageResource(R.drawable.smile);

                    builder.setPositiveButton("Excellent", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ListOfThing.this, "Keep it up!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setNegativeButton("Bad", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ListOfThing.this, "Never Mind!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    builder.setView(v);
                    builder.show();

                }
            }
        });
    }
    private void function(String a){
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle("Quote")
                .setContentText(a)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon)
                .setSound(soundUri);

        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(uniqeID, notification);
    }


}
