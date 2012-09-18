package com.lezv;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import android.view.View;

public class LunchList extends Activity {
    Restaurant r = new Restaurant();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ScrollView sV = new ScrollView(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        TableLayout tL = (TableLayout) findViewById(R.id.table_lay);


        RadioButton[] rButton = new RadioButton[8];
        RadioGroup rGroup = new RadioGroup(this);
        rGroup.setOrientation(RadioGroup.VERTICAL);
        for(int i = 0; i < 8; i++){
             rButton[i] = new RadioButton(this);
             rButton[i].setText("RB" + i);
             rGroup.addView(rButton[i]);
        }
        sV.addView(rGroup, new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tL.addView(sV);

    }

    private View.OnClickListener onSave=new View.OnClickListener() {
        public void onClick(View v) {
            EditText name=(EditText)findViewById(R.id.name);
            EditText address=(EditText)findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());

            RadioGroup types=(RadioGroup)findViewById(R.id.types);
            switch (types.getCheckedRadioButtonId()) {
                case R.id.sit_down:
                    r.setType("sit_down");
                    break;
                case R.id.take_out:
                    r.setType("take_out");
                    break;
                case R.id.delivery:
                    r.setType("delivery");
                    break;
            }
        }
    };






}
