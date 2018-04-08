package com.example.david.crudlite;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.david.crudlite.dao.Dao;
import com.example.david.crudlite.dao.UserItemDao;
import com.example.david.crudlite.model.UserItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Dao<UserItem> userItemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceholderFragment placeholderFragment = new PlaceholderFragment();

        userItemDao = new UserItemDao(this);
        List<UserItem> allUserItems = userItemDao.select();

        ArrayList<String> userItemsArray = generateUserList(allUserItems);

        Bundle args = new Bundle();
        args.putStringArrayList("userItemsArray", userItemsArray);
        placeholderFragment.setArguments(args);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.userListFrame, placeholderFragment)
                    .commit();
        }
    }

    public void creatUser(View view){
        Intent userActivityIntent = new Intent(this, UserActivity.class);
        startActivity(userActivityIntent);
    }

    private ArrayList<String> generateUserList(List<UserItem> allUserItems){
        ArrayList<String> userItemsArray = new ArrayList();

        for(UserItem userItem : allUserItems){
            StringBuffer sb = new StringBuffer();
            String firstName = userItem.getFirstName();
            String lastName = userItem.getLastName();
            String email = userItem.getEmail();

            sb.append("Name: " + firstName + " " + lastName + "\n" + "Email: " + email);

            userItemsArray.add(sb.toString());
        }

        return userItemsArray;
    }

    public static class PlaceholderFragment extends Fragment{

        public PlaceholderFragment(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

/*            String[] forecastArray = {
                    "Today-Sunny 50/60",
                    "Tomorrow-Cloudy 20/30",
                    "Wednesday-Snowy 40/50",
                    "Thursday-Rainy 20/40",
                    "Friday-Funny 20/50",
                    "Sat-Sunny 70/80",
                    "Sun-Sunny 90/100",
                    "Rick",
                    "Morty",
                    "Summer"
            };

            List<String> weekForecast = new LinkedList(Arrays.asList(forecastArray));*/
            List<String> users = getArguments().getStringArrayList("userItemsArray");

            ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), R.layout.text_view, R.id.listTextView, users);

            View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);

            ListView listView = rootView.findViewById(R.id.listView);

            listView.setAdapter(adapter);

            return rootView;
        }
    }
}
