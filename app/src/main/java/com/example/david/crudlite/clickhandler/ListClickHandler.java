package com.example.david.crudlite.clickhandler;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.david.crudlite.UserUpdateActivity;
import com.example.david.crudlite.model.UserItem;
import com.example.david.crudlite.utils.Constants;

public class ListClickHandler implements OnItemClickListener {
    private Context context;

    public ListClickHandler(Context context){
        this.context = context;
    }

    /*
     *  When you click on a list view item it will return the user item object
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserItem userItem = (UserItem) parent.getItemAtPosition(position);

        Intent userUpdateIntent = new Intent(this.context , UserUpdateActivity.class);
        userUpdateIntent.putExtra(Constants.EXTRA_SELECTED_USER_ITEM, userItem);
        this.context.startActivity(userUpdateIntent);

    }
}
