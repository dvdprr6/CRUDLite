package com.example.david.crudlite.clickhandler;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.david.crudlite.model.UserItem;

public class ListClickHandler implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserItem userItem = (UserItem) parent.getItemAtPosition(position);

    }
}
