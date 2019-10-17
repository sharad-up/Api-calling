package com.example.apicalling.CountryModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apicalling.R;

public class DynamicCount extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button button;
    String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_count);
        editText = findViewById(R.id.pages);
        button= findViewById(R.id.go);
        button.setOnClickListener(this);


    }
    public void gettingData(){
       // editText.getInputType();
       count = editText.getText().toString();

    }

    @Override
    public void onClick(View view) {
        gettingData();
        Intent intent= new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("String",count);
        startActivity(intent);

    }
}
