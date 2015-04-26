package com.example.mauriciorojas.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generarNuevoArreglo();
        TextView src = (TextView) findViewById(R.id.src);
        String srcText = convertirArregloString("Src =", data);
        src.setText(srcText);
    }

    static final int MAX_VALUE = 100;
    int data[] = new int[10];

    public String convertirArregloString(String prefijo, int[] arreglo) {
        String text = prefijo;
        for (int i = 0; i < arreglo.length; i++){
            text += arreglo[i] + " ";
        }
        return text;
    }

    public void generarNuevoArreglo() {
        Random random = new Random();

        for (int i = 0; i < data.length; i++){
            data[i] = random.nextInt(MAX_VALUE);
        }
    }

    public void generarArreglo(View view) {
        TextView src = (TextView) findViewById(R.id.src);
        generarNuevoArreglo();
        String srcText = convertirArregloString("Src = " , data);
        src.setText(srcText);
    }

    public void ejecutarBubbleSort(View view) {
        bubbleSort();
        String targetText = convertirArregloString("Target = ",data);
        TextView target = (TextView) findViewById(R.id.target);
        target.setText(targetText);
    }

    private void bubbleSort() {
        int min;
        for (int i = 0; i < data.length - 1; i++){
            min = i;
            for (int  j = i + 1; j < data.length; j++){
                if (data[j] < data[min]){
                    min = j;
                }
            }
            if (i != min){
                int tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;
            }
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
