/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonmethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class JSONMethod {
    private static  HttpURLConnection connection;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        
        try {
           URL url =new URL ("https://candidate.hubteam.com/candidateTest/v3/problem/dataset?userKey=f6a20952b41951def3fa693c28b3");
           // URL url =new URL ("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();
            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int status = connection.getResponseCode();
        //    System.out.println(status);
            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
          //  System.out.println(responseContent.toString());
            System.out.println("*****************");
            parse(responseContent.toString());
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(JSONMethod.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JSONMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            connection.disconnect();
        }
    }
    
    public static String parse (String responseBody){
        
        JSONObject req = new JSONObject(responseBody);
        JSONArray par = req.getJSONArray("partners");
      //  for (int i = 0; i < par.length(); i++){
        for (int i = 0; i < 10; i++){
            JSONObject parJSONObj = par.getJSONObject(i);
            String firstName = parJSONObj.getString("firstName");
            String lastName = parJSONObj.getString("lastName");
            String email = parJSONObj.getString("email");
            String country = parJSONObj.getString("country");
            
            JSONArray avai = parJSONObj.getJSONArray("availableDates");
            String[] availableDates = new String[avai.length()];
            for (int j = 0; j < availableDates.length; j++){
                availableDates[j] =avai.getString(j);
            }
            
            //System.out.println("*****************");
            System.out.print(firstName +" "+ lastName + " "+ email + " " + country + " " );  
            for (int k = 0; k < availableDates.length; k++){
                System.out.print(availableDates[k] +" ");
            }
            System.out.println("");
            
                    
            
        }
        
//        for (int i = 0; i < arr.length(); i++){
//            JSONObject temp = arr.getJSONObject(i);
            
            //String partners = temp.getString("partners");
//            JSONObject partners = 
//            String firstName = temp.getString("firstName");
//            String lastName = temp.getString("lastName");
//            String email = temp.getString("email");
//            String country = temp.getString("country");
           // JSONObject tempDates = temp.getJSONObject("availableDates");
          //  JSONArray tempDates = temp.getJSONArray("availableDates");
           // String[] availableDates = new String[tempDates.length()];
//            for (int j = 0; j < tempDates.length(); j++){
//                availableDates[i] = tempDates.getString("availableDates");
//            }
//            int id = temp.getInt("id");
//            int userID = temp.getInt("userId");
//            String title = temp.getString("title");
            


            
//        }
        return null;
    }
    
    
}
