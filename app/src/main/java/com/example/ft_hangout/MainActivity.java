package com.example.ft_hangout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //**************************//
    //     LAYOUT AND VARIABLES //
    //**************************//

    Button              btn_create;
    ListView            lv_contact;
    DbHelper            databaseHelper;
    PreferenceHelper    preferenceHelper;
    ArrayAdapter        contactArrayAdapter;

    //**************************//
    //     LIFE CYCLE           //
    //**************************//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to all the button and Layout
        btn_create = findViewById(R.id.btn_create);
        lv_contact = findViewById(R.id.lv_contact);
        databaseHelper = new DbHelper(MainActivity.this);
        preferenceHelper = new PreferenceHelper();
//        databaseHelper.close();
//        MainActivity.this.deleteDatabase("CONTACT_TABLE");
//        showContactList();


        //On click listener for every element
        lv_contact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent contactCardActivity = new Intent(MainActivity.this, ContactCard.class);
                Contact contactClick = (Contact) adapterView.getItemAtPosition(i);

                contactCardActivity.putExtra("contactInfo", contactClick); // On passe une string dans l'Itent mais on peut aussi passer une collection
                startActivity(contactCardActivity);
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
            case R.id.language:
                Intent j = new Intent(android.provider.Settings.ACTION_APPLICATION_SETTINGS);
                startActivity(j);
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

    //launch the toString method for every Contact Object
    public void showContactList(){
        contactArrayAdapter = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
        lv_contact.setAdapter(contactArrayAdapter);
    }
}

/* A FAIRE
*  Tester sur un smartphone et changer la langue en par default puis en fr
*  Gérer le layout horizontale
*  Gérer le fait de pouvoir envoyer un texto
*  Front end un peu plus stylé
*  Faire passer en String le phone avec un check sur du full num car le 0 part quand c'est du int?
* */

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
