package com.example.imagesgallery;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ImageRecyclerViewFragment fragment = new ImageRecyclerViewFragment();
            transaction.replace(R.id.imageFragment, fragment);
            transaction.commit();
        }
    }
}
