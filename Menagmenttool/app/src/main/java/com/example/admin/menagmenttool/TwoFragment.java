package com.example.admin.menagmenttool;

import android.content.Context;
import android.media.MediaPlayer;
import android.app.Notification;
import android.app.NotificationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class TwoFragment extends Fragment {

    public GraphView graph;
    private DatabaseReference mDatabase;
    private VideoView video;
    private DatabaseReference SpendMoney;
    String name;
    MediaController mediaControls;
    int first = 600,second = 1110,third = 700,fourth = 2226,fifth = 2000;
    int c = 0;
    public TwoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_two, null);
        TextView txt = (TextView)  v.findViewById(R.id.text);
        video = (VideoView) v.findViewById(R.id.videoView);


        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls= new MediaController(getActivity());
            mediaControls.setAnchorView(video);
        }

        video.setMediaController(mediaControls);
        video.setVideoURI(Uri.parse("android.resource://" + "com.example.admin.menagmenttool" + "/" + R.raw.video));

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.start();
            }
        });
        // implement on completion listener on video view
          video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getActivity(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getActivity(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
        graph = (GraphView) v.findViewById(R.id.graph);
        name = getArguments().getString("User");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        SpendMoney = mDatabase.child(name).child("28112017").child("Spending") ;
        SpendMoney.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cnt = 0;
                for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                    InfoMoney note = noteSnapshot.getValue(InfoMoney.class);
                    InfoMoney p = new InfoMoney(note.getName(),note.getCost());
                    if(cnt==0) {
                        int a = p.getCost();
                        c=a;
                    }
                    if(cnt==1) {
                        int b = p.getCost();
                        c+=b;
                    }
                    if(cnt==2) {
                        int b = p.getCost();
                        c+=b;
                    }
                    cnt++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Hey", databaseError.getMessage());
            }
        });

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, c),
                new DataPoint(1, c),
                new DataPoint(2, c),
                new DataPoint(3, c),
                new DataPoint(4, c)
        });
        graph.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
                staticLabelsFormatter.setHorizontalLabels(new String[] {"Sep","Oct","Nov"});
                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        return v;
    }

}
