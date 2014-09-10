package my.i906.todolist.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import my.i906.todolist.R;
import my.i906.todolist.adapter.TodoAdapter;
import my.i906.todolist.contentprovider.TodoContentProvider;
import my.i906.todolist.model.Todo;

public class TodoListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private TodoAdapter mAdapter;
    private Callbacks mCallbacks = sDummyCallbacks;

    public TodoListFragment() { }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillData();
    }

    private void fillData() {
        String[] from = {Todo.COLUMN_TITLE, Todo.COLUMN_DESCRIPTION};
        int[] to = {R.id.row_title, R.id.row_description};

        mAdapter = new TodoAdapter(getActivity(), null, from, to, 0);
        setListAdapter(mAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallbacks.onItemSelected(id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                Todo.COLUMN_ID,
                Todo.COLUMN_TITLE,
                Todo.COLUMN_DESCRIPTION
        };

        CursorLoader cursorLoader = new CursorLoader(getActivity(), TodoContentProvider.CONTENT_URI, projection, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(long id) {
        }
    };

    public interface Callbacks {
        public void onItemSelected(long id);
    }

}
