package com.example.ft_hangout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.ListContactAdapter;
import HelperClass.DbHelper;
import HelperClass.PreferenceHelper;
import Pojo.Contact;
import Pojo.RowContactList;

public class MainActivity extends AppCompatActivity {

    //**************************//
    //     LAYOUT AND VARIABLES //
    //**************************//

    ImageView           btn_add;
    ListView            lv_contact;
    DbHelper            databaseHelper;
    PreferenceHelper    preferenceHelper;
    ListContactAdapter  listContactAdapter;

    //**************************//
    //     LIFE CYCLE           //
    //**************************//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to all the button and Layout
        btn_add = findViewById(R.id.btn_add);
        lv_contact = findViewById(R.id.lv_contact);
        databaseHelper = new DbHelper(MainActivity.this);
        preferenceHelper = new PreferenceHelper();
        showContactList();

        //On click listener for every element
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent contactCardActivity = new Intent(MainActivity.this, ContactCard.class);
                RowContactList contactClick = (RowContactList) adapterView.getItemAtPosition(i);
                //We can pass Object in putExtra but the Object have to  implement Serializable
                contactCardActivity.putExtra("contactInfo", contactClick);
                startActivity(contactCardActivity);
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateContact(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferenceHelper.setColor(this, getSupportActionBar());
        showContactList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.color:
                Intent i = new Intent(this, Color.class );
                startActivity(i);
//                Intent j = new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS);
//                startActivity(j);
        }
        return super.onOptionsItemSelected(item);
    }

    //**************************//
    //     ACTIVITY LAUNCHER    //
    //**************************//

    public void launchCreateContact(View v){
        Intent i = new Intent(this, CreateContact.class );
        startActivity(i);
    }

    //**************************//
    //     OTHER METHODS        //
    //**************************//

    public void showContactList(){
        listContactAdapter = new ListContactAdapter(MainActivity.this, createListContact());
        lv_contact.setAdapter(listContactAdapter);
    }

    public ArrayList<RowContactList> createListContact(){
        RowContactList rowContactList;
        ArrayList<RowContactList> returnlist = new ArrayList<>();
        List<Contact> list = databaseHelper.getEveryOne();
        for (int i = 0; i < list.size(); i++)
        {
            String fullName = list.get(i).getFirstName() + " " + list.get(i).getName();
            rowContactList = new RowContactList(fullName, list.get(i).getPhone(), list.get(i).getPicture(),
                    list.get(i).getId());
            returnlist.add(rowContactList);
        }
        return returnlist;
    }
}

/*                    LIFE CYCLE
    When you open the app it will go through below states: onCreate() –> onStart() –> onResume()

        When you press the back button and exit the app

        onPaused() — > onStop() –> onDestroy()

        When you press the home button

        onPaused() –> onStop()

        After pressing the home button, again when you open the app from a recent task list

        onRestart() –> onStart() –> onResume()

        After dismissing the dialog or back button from the dialog

        onResume()

        If a phone is ringing and user is using the app

        onPause() –> onResume()

        After the call ends

        onResume()

        When your phone screen is off

        onPaused() –> onStop()

        When your phone screen is turned back on

        onRestart() –> onStart() –> onResume()

        Happy Coding...@Ambilpura


        Select next occurrence: Alt + J (Ctrl + G for Mac OS X)

        Unselect next occurrence: Shift + Alt + J (Shift + Ctrl + G for Mac OS X)

        Select all occurrences: Shift + Ctrl + Alt + J (Ctrl + Cmd + G for Mac OS X)

        Remove all selections: Esc

        Add/remove a selection: Alt + Shift + Mouse Click
*/
