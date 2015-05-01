package com.primenumberfarms.mysqltestasync;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mack on 4/26/15.
 */
public class CowDetailActivity extends Activity{

    //private EditText Tag;
    private EditText Name;
    private EditText Brand;
    private EditText RegNumber;

    private String Tag;
    private String sName;
    private String sBrand;
    private String sRegNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_details);


        this.Name = (EditText) this.findViewById(R.id.Name);
        this.Brand = (EditText) this.findViewById(R.id.Brand);
        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);

        this.Tag = getIntent().getStringExtra("Tag"); // this was pulled form MainActivity putStringExtra - sent over for query


        if(this.Tag != null)
        {
            // we know we passed a tag....like...maybe???
            new GetCowDetails().execute(new ApiConnector());

        }

        Button bUpdateCow = (Button) findViewById(R.id.btnUpdateCow);

        bUpdateCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sName = Name.getText().toString();
                sBrand = Brand.getText().toString();
                sRegNumber = RegNumber.getText().toString();

//                Toast.makeText(getApplicationContext(), sName, Toast.LENGTH_LONG).show();
                new UpdateCow().execute(new ApiConnector());


                UpdateCowClicked(v, Tag, sName, sBrand, sRegNumber);

            }
        });


    }
    private void UpdateCowClicked(View v, String Tag, String Name, String Brand, String RegNumber) {


        Toast.makeText(getApplicationContext(), Name, Toast.LENGTH_LONG).show();


    }

    //  the following 2 blocks are required to have the menu show up
    //both the onCreateOptionsMenu AND onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected((item));

    }

    private class GetCowDetails extends AsyncTask<ApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            // This is executed in the background thread
            return params[0].GetCowDetails(Tag);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                JSONObject cow = jArray.getJSONObject(0);
                Name.setText(cow.getString("Name"));
                Brand.setText(cow.getString("Brand"));
                RegNumber.setText(cow.getString("RegNumber"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

        private class UpdateCow extends AsyncTask<ApiConnector, Long, JSONArray>
        {
            @Override
            protected JSONArray doInBackground(ApiConnector...params) {
                // This is executed in the background thread
//                Log.v(Tag, "PN: **** Tag is now: "+Tag );
//                Log.v(Tag, "PN: **** sName is now: "+sName );
//                Log.v(Tag, "PN: **** sBrand is now: "+sBrand );

                return params[0].UpdateCow(Tag, sName, sBrand, sRegNumber);

            }

            @Override
            protected void onPostExecute(JSONArray jArray) {
                // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
                try
                {
                    JSONObject cow = jArray.getJSONObject(0);
//                    Name.setText(cow.getString("Name"));
//                    Brand.setText(cow.getString("Brand"));
//                    RegNumber.setText(cow.getString("RegNumber"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

    }

}
