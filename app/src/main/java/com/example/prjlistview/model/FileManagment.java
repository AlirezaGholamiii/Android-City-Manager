package com.example.prjlistview.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FileManagment {


    public static ArrayList<Country> reafFile(Context context, String filename) {


        ArrayList<Country> list = new ArrayList<Country>();



        //1- access to the assets folder
        AssetManager assManager= context.getResources().getAssets();

        try {
            //2- open the file countries
            InputStreamReader isr = new InputStreamReader(assManager.open(filename));
            //3- prosces this file
            BufferedReader br = new BufferedReader(isr);
            String oneline = br.readLine();

            while (oneline != null){
                StringTokenizer st = new StringTokenizer(oneline, ",");
                String cName = st.nextToken();
                String cCapital = st.nextToken();
                if(!cName.equalsIgnoreCase("England"))
                    list.add(new Country(cName,cCapital));
                oneline = br.readLine();

            }



            //4- close the file
            br.close();
            isr.close();


        }catch (IOException e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }





        return list;
    }
}
