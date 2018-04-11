package com.example.david.crudlite.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.crudlite.R;
import com.example.david.crudlite.model.UserItem;

import java.util.List;

/*
 *  Customer ArrayAdapter to display custom values in the text view
 */
public class UserAdapter extends ArrayAdapter<UserItem>{


    public UserAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<UserItem> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /*
     * Generate List View based on details in user item object
     */
    @Override
    public View getView(int position, View convertView, ViewGroup Parent){

        if(convertView == null){
            convertView = View.inflate(getContext(), R.layout.text_view, null);
        }

        StringBuffer sb = new StringBuffer();
        UserItem userItem = getItem(position);

        String firstName = userItem.getFirstName();
        String lastName = userItem.getLastName();
        String email = userItem.getEmail();

        sb.append("Name: " + firstName + " " + lastName + "\n" + "Email: " + email);

        TextView text = convertView.findViewById(R.id.listTextView);
        text.setText(sb.toString());


        return convertView;
    }
}
