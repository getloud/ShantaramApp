package com.lezv;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.*;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LunchList extends Activity {
    List<Restaurant> model  = new ArrayList<Restaurant>();
    ArrayAdapter<Restaurant> adapter = null;
    private static final String[] ADDRESS = new String[] {
            "Grinchenka", "Smelyanskaya"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        Spinner list=(Spinner)findViewById(R.id.restaurants);
        adapter=new ArrayAdapter<Restaurant>(this,
                android.R.layout.simple_spinner_item,
                model);
        list.setAdapter(adapter);


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, ADDRESS);
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.addr);
        textView.setAdapter(adapter1);

    }

    private View.OnClickListener onSave=new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();
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
            adapter.add(r);
        }
    };






}
