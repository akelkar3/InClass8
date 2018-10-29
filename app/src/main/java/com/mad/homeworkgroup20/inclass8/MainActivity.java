package com.mad.homeworkgroup20.inclass8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
/*Assignment no. 8
group: ankit kelkar, shubhra mishra*/
public class MainActivity extends AppCompatActivity
        implements MainFragment.OnFragmentInteractionListener,DisplayFragment.OnFragmentInteractionListener ,EditFragment.OnFragmentInteractionListener {
    private static final String TAG = "test";
    Student student ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.containerelement, new MainFragment(), "mainFragment").commit();
    }

    @Override
    public void onFragmentInteraction(Student student) {
        Log.d(TAG, "onFragmentInteraction: "+ student.toString());
        this.student=student;
        Log.d(TAG, "onFragmentInteraction this : "+ this.student.toString());

        getSupportFragmentManager().beginTransaction()

                .replace(R.id.containerelement, DisplayFragment.newInstance(student), "displayFragment").commit();
    }

    @Override
    public void onDisplayFragmentInteraction(String variable) {
        Log.d(TAG, "onDisplayFragmentInteraction: this will edit"+variable);
        // TODO: 3/19/2018 initialize the edit fragment here and pass the student object  like below
        Log.d(TAG, "onFragmentInteraction this : "+ this.student.toString());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerelement, EditFragment.newInstance(variable,student), "editFragment").commit();
    }
    @Override
    public void onEditFragmentInteraction(Student variable) {
        this.student=variable;
        Log.d(TAG, "onFragmentInteraction this : "+ this.student.toString());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerelement, DisplayFragment.newInstance(student), "displayFragment").commit();
    }

}
