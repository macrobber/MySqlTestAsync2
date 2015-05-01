package com.primenumberfarms.mysqltestasync;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mack on 4/26/15.
 */
public class GetAllCowListViewAdapter extends BaseAdapter {

    private JSONArray dataArray; // to handle the json being used
    private Activity activity; // activity to be inflated
    private static LayoutInflater inflater = null;

    public GetAllCowListViewAdapter(JSONArray jArray, Activity a)
    {
        // passing into the constructor both the json array and activity
        this.dataArray = jArray;
        this.activity = a;

        inflater = (LayoutInflater) this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.dataArray.length();
    } // this passes back the number of items in the data array

    @Override
    public Object getItem(int position) {
        return position;
    } // this returns the position of the item

    @Override
    public long getItemId(int position) {
        return position;
    } // also returns the position of the item

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // set up the convert view if it's null
        ListCell cell;
        if(convertView==null)
        {  // so if it is null, it has not been used before - you must inflate the view first
            convertView = inflater.inflate(R.layout.get_all_cow_list_view_cell, null);
            cell = new ListCell();
            cell.Tag = (TextView) convertView.findViewById(R.id.Tag);
            cell.Name = (TextView) convertView.findViewById(R.id.Name);
            cell.Brand = (TextView) convertView.findViewById(R.id.Brand);
            cell.RegNumber = (TextView) convertView.findViewById(R.id.RegNumber);

            convertView.setTag(cell);  // set the tag so I can reuse it.
        }
        else
        {
            cell = (ListCell) convertView.getTag();
        }
        // change the data of the cell here...
        // get the data from the JSON array and use the position that was passed into the getView function
        try
        {
            JSONObject jsonObject = this.dataArray.getJSONObject(position);

            // Populate the view using the JSON object
            cell.Tag.setText("Tag: "+jsonObject.getString("Tag"));
            cell.Name.setText("Name: "+jsonObject.getString("Name"));
            cell.Brand.setText("Brand: "+jsonObject.getString("Brand"));
            cell.RegNumber.setText("RegNumber: "+jsonObject.getString("RegNumber"));



        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


        return convertView;
    }

    private class ListCell
    {
        private TextView Tag;
        private TextView Name;
        private TextView Brand;
        private TextView RegNumber;
    }

}
