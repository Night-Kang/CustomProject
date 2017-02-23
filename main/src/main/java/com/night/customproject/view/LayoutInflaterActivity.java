package com.night.customproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.night.customproject.R;

public class LayoutInflaterActivity extends AppCompatActivity {

    private Button mInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_layout_inflater);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.activity_layout_inflater, null);

        mInflater = (Button)view.findViewById(R.id.inflater);
        mInflater.setText("Hello");

        setContentView(view);
    }
}
