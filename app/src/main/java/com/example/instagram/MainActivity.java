package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnsave;
    private EditText edtName,edtpunchspeed,edtPunchPower,edtKickSpeed,edtKickPower;

    private TextView txtGetdata;
    private Button  btnGetData;
    private  String AllFighters;
    private  Button btnTransition;



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
        txtGetdata = findViewById(R.id.txtgetdata);
        btnGetData = findViewById(R.id.btnGetData);
        btnTransition = findViewById(R.id.btnNextActivity);




        btnsave.setOnClickListener(MainActivity.this);
        edtName.setOnClickListener(this);
        edtpunchspeed.setOnClickListener(MainActivity.this);
        edtPunchPower.setOnClickListener(MainActivity.this);
        edtKickSpeed.setOnClickListener(MainActivity.this);
        edtKickPower.setOnClickListener(MainActivity.this);

        txtGetdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("fighter");
                parseQuery.getInBackground("hLBNNONtsm", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e == null){

                            txtGetdata.setText(object.get("Name") + "");
                        }

                    }
                });


            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllFighters = "";
                ParseQuery<ParseObject> queryall = ParseQuery.getQuery("fighter");
                queryall.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if (e == null){

                            if (objects.size() > 0){
                                FancyToast.makeText(MainActivity.this, AllFighters, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                for (ParseObject fighter: objects){
                                    AllFighters = AllFighters + fighter.get("name") + "\n";
                                }
                            }
                        }else {

                            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });
            }
        });

        //onClickListener for next Activity
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);


            }
        });

    }


    @Override
    public void onClick(View v) {

            try {
                final ParseObject fighter = new ParseObject("fight er");
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
