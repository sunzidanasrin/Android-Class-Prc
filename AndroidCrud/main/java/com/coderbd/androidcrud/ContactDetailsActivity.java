package com.coderbd.androidcrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactDetailsActivity extends AppCompatActivity {

    private EditText editText_name;
    private EditText editText_email;
    private EditText editText_phone;
    private Button button_save;
    private Button button_update;
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        editText_name = (EditText)findViewById(R.id.editTextName);
        editText_email = (EditText)findViewById(R.id.editTextEmail);
        editText_phone = (EditText)findViewById(R.id.editTextPhone);
        button_save = (Button)findViewById(R.id.buttonSave);
        button_update = (Button)findViewById(R.id.buttonUpdate);

        //Show details info by clicking on name
        Bundle b2 = getIntent().getExtras();
        if(b2 != null){
            int position = b2.getInt("position");
            DBHelper db = new DBHelper(this);
            contact = db.getAllContacts().get(position);
            editText_name.setText(contact.getName());
            editText_email.setText(contact.getEmail());
            editText_phone.setText(contact.getPhone());
            button_save.setVisibility(View.INVISIBLE);
        }

        //Save Button
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });

        //Update Button
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });
    }

    //for delete icon in toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_delete_contact:
                deleteContact();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //save
    private void saveContact() {
        DBHelper db = new DBHelper(this);
        Contact newContact = new Contact();
        newContact.setName(editText_name.getText().toString());
        newContact.setEmail(editText_email.getText().toString());
        newContact.setPhone(editText_phone.getText().toString());

        db.insertContact(newContact);
        finish();
    }

    //update
    private void updateContact() {
        DBHelper db = new DBHelper(this);
        Contact newContact = new Contact();
        newContact.setId(contact.getId());
        newContact.setName(editText_name.getText().toString());
        newContact.setEmail(editText_email.getText().toString());
        newContact.setPhone(editText_phone.getText().toString());

        db.updateContact(newContact);
        finish();
    }

    //delete
    private void deleteContact() {
        DBHelper db = new DBHelper(this);
        db.deleteContact(contact);
        finish();
    }
}
