package com.primenumberfarms.mysqltestasync;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mack on 4/25/15.
 */
public class ApiConnector {

    public JSONArray GetAllCows() {

        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/get_all_cows.php");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
             jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray GetCowDetails(String Tag)
    {
        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/UpdateCow.php?Tag="+Tag);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    public JSONArray UpdateCow(String Tag, String Name, String Brand, String RegNumber) {

        String result = "";
        InputStream isr = null;

        String temp = "http://www.primenumberfarms.com/update_cow.php?Tag="+Tag+"&Name="+Name+"&Brand="+Brand+"&RegNumber="+RegNumber;

        Log.v(Tag, "PN ******* The URL I am trying is: "+temp);

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/update_cow.php?Tag="+Tag+"&Name="+Name+"&Brand="+Brand+"&RegNumber="+RegNumber);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();



            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }


}
