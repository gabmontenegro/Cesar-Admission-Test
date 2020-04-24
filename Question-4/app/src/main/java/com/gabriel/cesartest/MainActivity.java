package com.gabriel.cesartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{

    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String[] moviewList;
    public static ArrayList<WordNames> wordNamesArrayList = new ArrayList<WordNames>();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Locate view components
        editsearch = (SearchView) findViewById(R.id.search);
        list = (ListView) findViewById(R.id.listview);

        // Generate sample data

        moviewList = new String[]{"pale","pales", "you", "probably", "despite", "moon", "misspellings"};

        wordNamesArrayList = new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++)
        {
            WordNames wordNames = new WordNames(moviewList[i]);
            wordNamesArrayList.add(wordNames);
        }

        // Passing the results to ListViewAdapter Class
        adapter = new ListViewAdapter(this);

        // Binding the Adapter to the ListView
        list.setAdapter(adapter);
        editsearch.setOnQueryTextListener(this);

        //Listening to the word typed in search box
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(MainActivity.this, wordNamesArrayList.get(position).getWordName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        String text = newText;
        adapter.filter(text);

        return false;
    }
}