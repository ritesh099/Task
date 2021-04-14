package com.example.tasktinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int j=0;
    int rightCount=0, leftCount=0;
    TextView rightSwipe, leftSwipe;
    Button btnCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightSwipe = findViewById(R.id.rightSwipe);
        leftSwipe = findViewById(R.id.leftSwipe);
        btnCount = findViewById(R.id.btnCount);

        rightSwipe.setVisibility(View.GONE);
        leftSwipe.setVisibility(View.GONE);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String res1 = "Number of Right Swipe = " + rightCount;
                String res2 = "Number of Left Swipe = " + leftCount;

                rightSwipe.setText(res1);
                leftSwipe.setText(res2);

                rightSwipe.setVisibility(View.VISIBLE);
                leftSwipe.setVisibility(View.VISIBLE);

            }
        });


        al = new ArrayList<>();
        al.add("Aanya");
        al.add("Sophia");
        al.add("Bella");
        al.add("Anuj");
        al.add("Rooney");
        al.add("Mahi");
        al.add("Khilesh");
        al.add("Jennifer");

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item , R.id.helloText, al);

        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                rightSwipe.setVisibility(View.GONE);
                leftSwipe.setVisibility(View.GONE);
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                leftCount = leftCount+1;
                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                rightCount = rightCount + 1;
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

                if(j==0){
                    al.add("You visited all profiles! ");
                    j=j+1;
                }

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }



}