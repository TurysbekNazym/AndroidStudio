package com.example.admin.menagmenttool;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class OneFragment extends Fragment {

    private static ViewPager mPager;
    String name;
    private static int currentPage = 0;
    private static final Integer[] XMEN= {
            R.drawable.einstein,
            R.drawable.paulo,
            R.drawable.jack,
    R.drawable.thomas};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    public static final String DATABASE_PATH = "image";

    private RecyclerView.Adapter adapter;
    //database reference
    private DatabaseReference mDatabase;
    //progress dialog
    private ProgressDialog progressDialog;
    //list to hold all the uploaded images
    private List<Image> uploads;
    RecyclerView recyclerView;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, null);
        name = getArguments().getString("User");


        for(int i=0;i<XMEN.length;i++)
            XMENArray.add(XMEN[i]);

        mPager = (ViewPager) v.findViewById(R.id.pager);
        mPager.setAdapter(new ImageAdapter(getActivity(),XMENArray));
        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);





        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(getActivity());

        uploads = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        mDatabase = FirebaseDatabase.getInstance().getReference(DATABASE_PATH+name);
     //   Toast.makeText(getActivity(),name, Toast.LENGTH_LONG).show();
        //adding an event listener to fetch values
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog.dismiss();
               // Toast.makeText(getActivity(),"Kaz kirem", Toast.LENGTH_LONG).show();
                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                   // Toast.makeText(getActivity(),"kirdim", Toast.LENGTH_LONG).show();
                    Image upload = postSnapshot.getValue(Image.class);
                    Image img = new Image(upload.getName(),upload.getUrl());
                  //  Toast.makeText(getActivity(),img.getName(), Toast.LENGTH_LONG).show();
                    uploads.add(img);
                    adapter = new MyAdapter(getActivity(), uploads);

                    //adding adapter to recyclerview
                    recyclerView.setAdapter(adapter);
                }
                //creating adapter

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(),"Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();
            }
        });
        return  v;
    }





}
