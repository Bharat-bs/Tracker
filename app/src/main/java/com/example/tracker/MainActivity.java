package com.example.tracker;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    MaterialButton displayDate2;
    MaterialButton displayDate3;
    MaterialButton displayLocation;
    MaterialButton displayShift;
    TextView display_from;
    TextView display_to;
    TextView display_location;
    TextView display_shift;
    static boolean loc_flag = false;
    static boolean shif_fag = false;
    String date_from;
    String date_to;
    String location_filter = "ALL";
    String shift_filter = "ALL";
    ArrayList<User> data_set = new ArrayList<User>();
    ArrayList<User> display_data = new ArrayList<User>();
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Filters

        //
        LoadData();
        Data();
        //
        display_from = (TextView) findViewById(R.id.textView3);
        display_to = (TextView) findViewById(R.id.textView4);
        display_location = (TextView) findViewById(R.id.textView8);
        display_shift = (TextView) findViewById(R.id.textView7);
        displayDate2 = (MaterialButton) findViewById(R.id.fromdatepicker);
        displayDate3 = (MaterialButton) findViewById(R.id.todatepicker);
        displayLocation = (MaterialButton) findViewById(R.id.locationpicker);
        displayShift = (MaterialButton) findViewById(R.id.shiftpicker);
        //
        display_from.setText("today");
        display_to.setText("tomorrow");
        display_location.setText("ALL");
        display_shift.setText("ALL");
        date_from = "";
        date_to = "";
        location_filter = "ALL";
        shift_filter = "ALL";
        //

        displayDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickInit();
            }
        });
        displayDate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickTo();
            }
        });
        displayLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocation();
            }
        });
        displayShift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShift();
            }
        });
    }

    void datePickInit() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date_from = day + "/" + (month + 1) + "/" + year;
                display_from.setText(date_from);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.show();
    }


    void datePickTo() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date_to = day + "/" + (month + 1) + "/" + year;
                display_to.setText(date_to);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.show();
    }

    void setLocation() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, displayLocation);
        popupMenu.getMenuInflater().inflate(R.menu.location_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                location_filter = menuItem.getTitle().toString();
                display_location.setText(location_filter);
                Data();
                return true;
            }
        });
        popupMenu.show();
    }

    void setShift() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, displayLocation);
        popupMenu.getMenuInflater().inflate(R.menu.shift_popup, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                shift_filter = menuItem.getTitle().toString();
                display_shift.setText(shift_filter);
                Data();
                return true;
            }
        });
        popupMenu.show();
    }

    private void initSearchWidgets() {
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                display_data.clear();
                for (User data : data_set) {
                    if (s.equals("")) {
                        Data();
                    }
                    if (data.getVehicleNo().toLowerCase().contains(s.toLowerCase())) {
                        display_data.add(data);
                    } else {

                    }
                }
                return false;
            }


        });
    }


    void LoadData() {

        User u1 = new User("TN8993", "bharat", "900", "1800", "MADURAI", "DAY");
        User u2 = new User("TN8994", "ashwin", "900", "1800", "CHENNAI", "NIGHT");
        User u3 = new User("TN8995", "navin", "900", "1800", "TRICHY", "DAY");
        User u4 = new User("TN8996", "naveen", "900", "1800", "MADURAI", "NIGHT");
        data_set.add(u1);
        data_set.add(u2);
        data_set.add(u3);
        data_set.add(u4);
    }


    void Data() {
        recyclerView = findViewById(R.id.userlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        display_data.clear();
//
        initSearchWidgets();
        if (location_filter.equals("ALL")) {
            loc_flag = true;
        }
        if (shift_filter.equals("ALL")) {
            shif_fag = true;
        }
        for (User data : data_set) {
            Log.d(String.valueOf(loc_flag), "");
            boolean b = (data.getLocation().equals(location_filter));
            if ((data.getLocation().equals(location_filter) ^ loc_flag) && (data.getShift().equals(shift_filter) ^ shif_fag)) {
                display_data.add(data);
            }
        }


        loc_flag = false;
        shif_fag = false;
        myAdapter = new MyAdapter(this, display_data);
        recyclerView.setAdapter(myAdapter);
    }

}