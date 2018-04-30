package com.example.david.crudlite;

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

import com.example.david.crudlite.adapter.UserAdapter;
import com.example.david.crudlite.clickhandler.ListClickHandler;
import com.example.david.crudlite.dao.Dao;
import com.example.david.crudlite.dao.DaoFactory;
import com.example.david.crudlite.dao.UserItemDao;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/*
 *  Displays all users in a fragment using list view
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlaceholderFragment placeholderFragment = new PlaceholderFragment();

        List<UserItem> allUserItems = DaoFactory.getUserItemDao(this).select();

        // Use this bundle implementation to pass objects to the fragment
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.USER_ITEMS_ARRAY, (ArrayList<UserItem>)allUserItems);
        placeholderFragment.setArguments(args);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.userListFrame, placeholderFragment)
                    .commit();
        }
    }

    /*
     *  Go to activity to create a new user
     */
    public void createUser(View view){
        Intent userActivityIntent = new Intent(this, UserActivity.class);
        startActivity(userActivityIntent);
    }

    /*
     *  Generates fragment to display users
     */
    public static class PlaceholderFragment extends Fragment{

        public PlaceholderFragment(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            List<UserItem> users = getArguments().getParcelableArrayList(Constants.USER_ITEMS_ARRAY);

            // User custom adapter to pass a list of user item objects
            ArrayAdapter<UserItem> adapter = new UserAdapter(getActivity(), R.layout.text_view, R.id.listTextView, users);

            View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);

            ListView listView = rootView.findViewById(R.id.listView);

            listView.setAdapter(adapter);

            // Click on a text view item to enter the activity to update user
            listView.setOnItemClickListener(new ListClickHandler(getActivity()));

            return rootView;
        }
    }
}
