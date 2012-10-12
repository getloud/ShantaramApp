package com.lezv;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;
import android.view.View;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LunchList extends TabActivity {
    List<Restaurant> model  = new ArrayList<Restaurant>();
    ArrayAdapter<Restaurant> adapter = null;
    EditText name=null;
    EditText address=null;
    RadioGroup types=null;
    private static final int ROW_TYPE_SIT_DOWN = 0;
    private static final int ROW_TYPE_TAKE_OUT = 1;
    private static final int ROW_TYPE_DELIVERY = 2;
    private static final String[] ADDRESS = new String[] {
            "Grinchenka", "Smelyanskaya" , "Julyanskaya"
    };
    DateFormat fmtDate= DateFormat.getDateInstance();
    TextView dateLabel;
    Calendar dateAndTime=Calendar.getInstance();





    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.addr);
        types=(RadioGroup)findViewById(R.id.types);
        dateLabel=(TextView)findViewById(R.id.date);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        ListView list=(ListView)findViewById(R.id.restaurants);
        adapter=new RestaurantAdapter();
        list.setAdapter(adapter);


//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, ADDRESS);
//        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.addr);
//        textView.setAdapter(adapter1);

        TabHost.TabSpec spec=getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List", getResources()
                .getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec=getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources()
                .getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);

        list.setOnItemClickListener(onListClick);


        updateLabel();

    }

    public void chooseDate(View v) {
        new DatePickerDialog(LunchList.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void updateLabel() {
        dateLabel.setText(fmtDate.format(dateAndTime.getTime()));
    }
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private AdapterView.OnItemClickListener onListClick=new
            AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent,
                                        View view, int position,
                                        long id) {
                    Restaurant r=model.get(position);
                    name.setText(r.getName());
                    address.setText(r.getAddress());
                    dateLabel.setText((CharSequence) r.getDate());
                    if (r.getType().equals("sit_down")) {
                        types.check(R.id.sit_down);
                    }
                    else if (r.getType().equals("take_out")) {
                        types.check(R.id.take_out);
                    }
                    else {
                        types.check(R.id.delivery);
                    }
                    getTabHost().setCurrentTab(1);
                }
            };

    private View.OnClickListener onSave=new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();
            EditText name=(EditText)findViewById(R.id.name);
            EditText address=(EditText)findViewById(R.id.addr);
            EditText dateLabel=(EditText)findViewById(R.id.date);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            r.setDate(address.getText().toString());
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


    public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() {
            super(LunchList.this, android.R.layout.simple_list_item_1, model);


        }

        public int getViewTypeCount() {
            return 3;
        }


        public int getItemViewType(int position){
            String type = model.get(position).getType();
            int result = 0;
            if (type == "delivery" ){
                result = ROW_TYPE_DELIVERY ;
            }
            else if (type == "take_out") {
                result = ROW_TYPE_TAKE_OUT;
            }
            else if(type == "sit_down")  {
                result = ROW_TYPE_SIT_DOWN;
            }
            return result;
        }


        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row=convertView;
            RestaurantHolder holder=null;
            if (row==null) {
                LayoutInflater inflater=getLayoutInflater();
                switch(getItemViewType(position)){
                    case ROW_TYPE_DELIVERY :
                        row=inflater.inflate(R.layout.row_delivery_type, parent, false);
                        break;
                    case ROW_TYPE_TAKE_OUT :
                        row=inflater.inflate(R.layout.row_take_out_type, parent, false);
                        break;
                    default:
                        row=inflater.inflate(R.layout.row_sit_down_type, parent, false);
                        break;
                }
                holder=new RestaurantHolder(row);
                row.setTag(holder);
            }
            else {
                holder=(RestaurantHolder)row.getTag();
            }
            holder.populateFrom(model.get(position));
            return(row);
        }
    }


    static class RestaurantHolder {
        private TextView name=null;
        private TextView address=null;
        private ImageView icon=null;
       private  TextView date=null;
        RestaurantHolder(View row) {
            name=(TextView)row.findViewById(R.id.title);
            address=(TextView)row.findViewById(R.id.address);
            icon=(ImageView)row.findViewById(R.id.icon);
            date=(TextView)row.findViewById(R.id.date);
        }
        void populateFrom(Restaurant r) {
            name.setText(r.getName());
            address.setText(r.getAddress());
            date.setText(r.getDate());
            if (r.getType().equals("sit_down")) {
                name.setTextColor(-65536);
                icon.setImageResource(R.drawable.ball_red);
            }
            else if (r.getType().equals("take_out")) {
                name.setTextColor(-256);
                icon.setImageResource(R.drawable.ball_yellow);
            }
            else {
                name.setTextColor(-16711936);
                icon.setImageResource(R.drawable.ball_green);
            }
        }
       }





}
