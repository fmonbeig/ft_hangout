package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

// Il faut creer la database ainsi que la classe qui aura un constructeur et des variables semblable a la db

public class Database extends AppCompatActivity {

    // reference to all the button and Layout

    Button btn_add;
    Button btn_view;
    EditText et_age, et_name;
    Switch sw_active;
    ListView lv_customerView;
    ArrayAdapter customerArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        // R is the resource folder
        // We catch all the element of the layout here when we create an activity
        btn_add = findViewById(R.id.btn_create);
        btn_view = findViewById(R.id.btn_view);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_first_name);
        sw_active = findViewById(R.id.sw_active);
        lv_customerView = findViewById(R.id.lv_customerView);
        databaseHelper = new DatabaseHelper(Database.this);
        showCustomerList();

        //OnClickListener for Button
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerModel customerModel;
                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()),sw_active.isChecked());
//                    Toast.makeText(Database.this, customerModel.toString()  , Toast.LENGTH_SHORT).show();
                } catch(Exception e)
                {
                    Toast.makeText(Database.this, "error creating customer"  , Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1,"error", 0, false  );
                }

                boolean success = databaseHelper.addOne(customerModel);
                Toast.makeText(Database.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                showCustomerList();
            }
        });

        //when we click on add we have to summon the DB to change/create it
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomerList();
//                Toast.makeText(Database.this, everyone.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        lv_customerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CustomerModel customerClick = (CustomerModel) adapterView.getItemAtPosition(i);
                databaseHelper.deleteOne(customerClick);
                showCustomerList();
                Toast.makeText(Database.this,"Delete " + customerClick.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void showCustomerList(){
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(Database.this, android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
        lv_customerView.setAdapter(customerArrayAdapter);
    }
}