package com.choubey.fragmenttesting;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        frame.setId(R.id.contactsListFrame);
        setContentView(frame, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        if (savedInstanceState == null) {
            String query = getSearchKey(getIntent());
            Fragment newFragment = new ContactsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("key", query);
            newFragment.setArguments(bundle);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.contactsListFrame, newFragment, "contactsList").commit();
        }

        Log.i(this.getClass().getSimpleName(), "Inside oncreate of Search results activity");
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search_results);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        Log.i(this.getClass().getSimpleName(), "Inside onCreateOptionsMenu of search results activity");
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(this.getClass().getSimpleName(), "Received new intent");
        handleIntent(intent);
    }

    public String getSearchKey(Intent intent)
    {
        if (intent != null && Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            return query;
        }
        return null;
    }

    private void handleIntent(Intent intent) {
        Log.i(this.getClass().getSimpleName(), "The intent action is " + intent.getAction());
        String query = getSearchKey(intent);
        if(query != null)
        {
            Toast.makeText(this, query, Toast.LENGTH_SHORT);
        }
    }
}
