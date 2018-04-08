package com.example.david.crudlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.crudlite.dao.Dao;
import com.example.david.crudlite.dao.UserItemDao;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.utils.Constants;

public class UserActivity extends AppCompatActivity {
    private Dao<UserItem> userItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userItemDao = new UserItemDao(this);
    }

    public void submit(View view){
        String firstName = ((EditText)findViewById(R.id.firstNameEditText)).getText().toString();
        String lastName = ((EditText)findViewById(R.id.lastNameEditText)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString().toLowerCase();

        if(!firstName.equals("") && !lastName.equals("") && !email.equals("")){
            UserItem userItem = new UserItem();
            userItem.setUserId(null);
            userItem.setFirstName(firstName);
            userItem.setLastName(lastName);
            userItem.setEmail(email);

            userItemDao.insert(userItem);

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
