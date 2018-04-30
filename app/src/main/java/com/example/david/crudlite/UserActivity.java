package com.example.david.crudlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.crudlite.dao.DaoFactory;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.utils.Constants;

/*
 * Activity to create user
 */
public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    /*
     *  Creat user
     */
    public void submit(View view){
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString().toLowerCase();

        // Check if values have been entered in the Edit Text to prevent null pointer exceptions
        if(!firstName.equals("") && !lastName.equals("") && !email.equals("")){
            UserItem userItem = new UserItem();
            userItem.setId(null);
            userItem.setFirstName(firstName);
            userItem.setLastName(lastName);
            userItem.setEmail(email);

            DaoFactory.getUserItemDao(this).insert(userItem);

            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);

        }else{
            Toast.makeText(this, Constants.TOAST_FILL_FIELDS, Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View view){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
