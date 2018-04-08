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

public class UserUpdateActivity extends AppCompatActivity {
    private UserItem userItem;
    private Dao<UserItem> userItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);

        userItemDao = new UserItemDao(this);

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();

        userItem = (UserItem) extra.get(Constants.EXTRA_SELECTED_USER_ITEM);

        EditText firstName = findViewById(R.id.firstNameUpdateEditText);
        EditText lastName = findViewById(R.id.lastNameUpdateEditText);
        EditText email = findViewById(R.id.emailUpdateEditText);

        firstName.setText(userItem.getFirstName());
        lastName.setText(userItem.getLastName());
        email.setText(userItem.getEmail());


    }

    public void update(View view){
        String firstName = ((EditText) findViewById(R.id.firstNameUpdateEditText)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.lastNameUpdateEditText)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailUpdateEditText)).getText().toString().toLowerCase();

        if(!firstName.equals("") && !lastName.equals("") && !email.equals("")){
            userItem.setFirstName(firstName);
            userItem.setLastName(lastName);
            userItem.setEmail(email);


            userItemDao.update(userItem);

            goToMainActivity();


        }else{
            Toast.makeText(this, Constants.TOAST_FILL_FIELDS, Toast.LENGTH_LONG).show();
        }

    }

    public void delete(View view){
        userItemDao.delete(userItem);
        goToMainActivity();
    }

    public void cancel(View view){
        goToMainActivity();
    }

    private void goToMainActivity(){
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
