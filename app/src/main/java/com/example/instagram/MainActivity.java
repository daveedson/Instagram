package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private EditText edtName,edtpunchspeed,edtPunchPower,edtKickSpeed,edtKickPower;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsave = findViewById(R.id.btnSave);
        edtName = findViewById(R.id.edtName);
        edtpunchspeed = findViewById(R.id.edtPunchSpeed);
        edtPunchPower = findViewById(R.id.edtPunchPower);
        edtKickSpeed = findViewById(R.id.edtKickSpeed);
        edtKickPower = findViewById(R.id.edtkickPower);


        btnsave.setOnClickListener(MainActivity.this);
        edtName.setOnClickListener(this);
        edtpunchspeed.setOnClickListener(MainActivity.this);
        edtPunchPower.setOnClickListener(MainActivity.this);
        edtKickSpeed.setOnClickListener(MainActivity.this);
        edtKickPower.setOnClickListener(MainActivity.this);

    }


    @Override
    public void onClick(View v) {

            try {
                final ParseObject fighter = new ParseObject("fighter");
                fighter.put("Name", edtName.getText().toString());
                fighter.put("punch_speed", Integer.parseInt(edtpunchspeed.getText().toString()));
                fighter.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
                fighter.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
                fighter.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));
                fighter.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {

                            FancyToast.makeText(MainActivity.this, fighter.get("Name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }

                    }
                });

            }catch (Exception e){
                FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

            }
    }
}
