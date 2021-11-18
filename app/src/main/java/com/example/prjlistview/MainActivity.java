package com.example.prjlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prjlistview.model.Country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
,  AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener,
        DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }


    EditText edCName,edCCapital;
    ListView lvCountries;
    Button btnAdd,btnUpdate,btnSort;

    //THe needed objects for liat
    ArrayList<Country> listOfCountries;
    ArrayAdapter<Country> lvAdabter;

    AlertDialog.Builder alertRemove;
    AlertDialog userDialog;

    int position = -1;

    private void initialize(){

        edCName = findViewById(R.id.edCName);
        edCCapital = findViewById(R.id.edCCapital);
        lvCountries = findViewById(R.id.lvCountries);
        btnAdd=findViewById(R.id.btnAdd);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnSort=findViewById(R.id.btnSort);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnSort.setOnClickListener(this);
        lvCountries.setOnClickListener(this);
        lvCountries.setOnItemLongClickListener(this);

        listOfCountries = new ArrayList<Country>();
        Country c0 = new Country("Canada","Ottawa");
        Country c1 = new Country("India", "Dehli");
        Country c2 = new Country("France", "Paris");

        listOfCountries.add(c0);
        listOfCountries.add(c1);
        listOfCountries.add(c2);


        //Initiolize the adabter
        lvAdabter = new ArrayAdapter<Country>(this, android.R.layout.simple_list_item_1, listOfCountries);

        lvCountries.setAdapter(lvAdabter);


        alertRemove = new AlertDialog.Builder(this);
        alertRemove.setTitle("Remove Country");
        alertRemove.setMessage("Do you want to remove?");
        alertRemove.setPositiveButton("Yes", this);
        alertRemove.setNegativeButton("No", this);
        userDialog = alertRemove.create();



    }

    @Override
    public  void onClick(View view){

        int id = view.getId();
        switch (id){
            case R.id.btnAdd:
                addCountry();
                break;
            case R.id.btnUpdate:
                updateCountry();
                break;
            case R.id.btnSort:
                sortList();
                break;
        }
    }

    private void sortList() {

        //collections.sort() call the class Country
        //and use the method compareTo in order to sort the array of objects
        Collections.sort(listOfCountries);
        lvAdabter.notifyDataSetChanged();
    }

    private void updateCountry() {

        String cName = edCName.getText().toString();
        String cCapital = edCCapital.getText().toString();

        Country country = new Country(cName, cCapital);

        if(listOfCountries.contains(country)){
            Toast.makeText(this, "The country : "+ cName + " exists", Toast.LENGTH_SHORT).show();


            //find the position inside the arraylist
            int pos = listOfCountries.indexOf(country);
            listOfCountries.set(pos, country);
            lvAdabter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(this, "The country : "+ cName + " doesnt exist!", Toast.LENGTH_LONG).show();
        }


    }

    private void addCountry() {

        String cName = edCName.getText().toString();
        String cCapital = edCCapital.getText().toString();

        listOfCountries.add(new Country(cName,cCapital));

        lvAdabter.notifyDataSetChanged();

        Toast.makeText(this, "The country with the name: " +cName+" Was successfully added", Toast.LENGTH_SHORT).show();

    }

    private void clearWidgets() {
        edCName.setText(null);
        edCCapital.setText(null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView,View view,int i, long l){


        TextView tvCountry = (TextView) view;
        Toast.makeText(this, "The country is : " + tvCountry.getText().toString(), Toast.LENGTH_LONG).show();


        Country country = listOfCountries.get(i);
        edCName.setText(country.getcNAme());

        edCCapital.setText(country.getcCapital());
        Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

        userDialog.show();

        position = i;

        Toast.makeText(this, "Item Long clicked", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int whichButton) {

        switch (whichButton){
            case Dialog.BUTTON_POSITIVE:
                listOfCountries.remove(position);
                lvAdabter.notifyDataSetChanged();
                break;
            case Dialog.BUTTON_NEGATIVE:
                break;

        }

    }
}