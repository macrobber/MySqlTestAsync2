package com.primenumberfarms.mysqltestasync;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private ListView GetAllCowListView;
    private JSONArray jArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.GetAllCowListView = (ListView) this.findViewById(R.id.GetAllCowListView);


        // from the main thread, we need to execute the class to run
        new GetAllCowsTask().execute(new ApiConnector());

        this.GetAllCowListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try  // so the user clicked a list item
                {
                    // Get the cow that was clicked
                    JSONObject cowClicked = jArray.getJSONObject(position);

                    //send the cow Tag (Primary Key) and spawn a new Intent?
                    Intent showDetails = new Intent(getApplicationContext(), CowDetailActivity.class);
                    //showDetails.putExtra("Tag", cowClicked.get("Tag"));
                    showDetails.putExtra("Tag", cowClicked.getString("Tag"));

                    startActivity(showDetails);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    public void setListAdapter(JSONArray jArray) // after we have the data, set the listview here...
    {
        this.jArray = jArray;
        this.GetAllCowListView.setAdapter(new GetAllCowListViewAdapter(jArray, this));
    }

  // this is called just above in the onCreate main thread...this is an async task done behind the scenes in a separate thread
    private class GetAllCowsTask extends AsyncTask<ApiConnector, Long, JSONArray>
    {
        @Override
            protected JSONArray doInBackground(ApiConnector...params) {
            // This is executed in the background thread
            return params[0].GetAllCows();
        }

        @Override
           protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
            setListAdapter(jArray);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
