package com.lezv;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.*;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LunchList extends Activity {
    List<Restaurant> model  = new ArrayList<Restaurant>();
    ArrayAdapter<Restaurant> adapter = null;
    private static final int ROW_TYPE_SIT_DOWN = 1;
    private static final int ROW_TYPE_TAKE_OUT = 2;
    private static final int ROW_TYPE_DELIVERY = 3;
    private static final String[] ADDRESS = new String[] {
            "Grinchenka", "Smelyanskaya"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        ListView list=(ListView)findViewById(R.id.restaurants);
        adapter=new RestaurantAdapter();
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


    public class RestaurantAdapter extends ArrayAdapter<Restaurant> {
        RestaurantAdapter() {
            super(LunchList.this, android.R.layout.simple_list_item_1, model);


        }

        public int getViewTypeCount() {
            return 3;
        }


        public int getItemViewType(int position){
            String type = model.get(position).getType();
            if (type == "delivery" ){
                return ROW_TYPE_DELIVERY ;
            }
            else if (type == "take_out") {
                 return ROW_TYPE_TAKE_OUT;
            }
            else
                return ROW_TYPE_SIT_DOWN;
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
        RestaurantHolder(View row) {
            name=(TextView)row.findViewById(R.id.title);
            address=(TextView)row.findViewById(R.id.address);
            icon=(ImageView)row.findViewById(R.id.icon);
        }
        void populateFrom(Restaurant r) {
            name.setText(r.getName());
            address.setText(r.getAddress());
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
