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
import com.example.david.crudlite.dao.UserItemDao;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.utils.Constants;

import java.util.ArrayList;
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

        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.USER_ITEMS_ARRAY, (ArrayList<UserItem>)allUserItems);
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

    public static class PlaceholderFragment extends Fragment{

        public PlaceholderFragment(){}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            List<UserItem> users = getArguments().getParcelableArrayList(Constants.USER_ITEMS_ARRAY);

            ArrayAdapter<UserItem> adapter = new UserAdapter(getActivity(), R.layout.text_view, R.id.listTextView, users);

            View rootView = inflater.inflate(R.layout.list_view_fragment, container, false);

            ListView listView = rootView.findViewById(R.id.listView);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new ListClickHandler(getActivity()));

            return rootView;
        }
    }
}
