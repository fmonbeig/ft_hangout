package com.example.ft_hangout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //**************************//
    //     LAYOUT AND VARIABLES //
    //**************************//

    Button              btn_language;
    Button              btn_create;
    Button              btn_color;
    ListView            lv_contact;
    DbHelper            databaseHelper;
    ArrayAdapter        contactArrayAdapter;


    //**************************//
    //     LIFE CYCLE           //
    //**************************//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reference to all the button and Layout
        btn_language = findViewById(R.id.btn_language);
        btn_create = findViewById(R.id.btn_create);
        btn_color = findViewById(R.id.btn_color);
        lv_contact = findViewById(R.id.lv_contact);
        databaseHelper = new DbHelper(MainActivity.this);
        showContactList();

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
        showContactList();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    //**************************//
    //     ACTIVITY LAUNCHER    //
    //**************************//

    public void launchColor(View v) {
        Intent i = new Intent(this, Color.class);
        startActivity(i);
    }

    public void launchDatabase(View v){
        Intent i = new Intent(this, Database.class );
        startActivity(i);
    }

    public void launchCreateContact(View v){
        Intent i = new Intent(this, CreateContact.class );
        startActivity(i);
    }

    //**************************//
    //     OTHER METHODS        //
    //**************************//

    public void showContactList(){
        contactArrayAdapter = new ArrayAdapter<Contact>(MainActivity.this,
                android.R.layout.simple_list_item_1, databaseHelper.getEveryOne());
        lv_contact.setAdapter(contactArrayAdapter);
    }
}


/* A FAIRE
*  Interdiction de mettre des errors dans la DB
*  Gestion de la couleur
*  Gestion de la langue (regarder vers un gestionnaire de preference)
*  Gérer le layout horizontale
*  Gérer le fait de pouvoir envoyer un texto
*  Le toast de l'heure (faire par le shared preference et non par une Classe) // A moitié OK
*  Gérer le modify
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
*/
