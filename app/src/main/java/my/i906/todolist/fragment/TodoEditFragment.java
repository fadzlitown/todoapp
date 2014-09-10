package my.i906.todolist.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import my.i906.todolist.R;
import my.i906.todolist.contentprovider.TodoContentProvider;
import my.i906.todolist.model.Todo;

public class TodoEditFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String ARG_TODO_ID = "todoId";

    private long mTodoId;

    @InjectView(R.id.todoedit_title)
    protected EditText mTitleView;

    @InjectView(R.id.todoedit_description)
    protected EditText mDescriptionView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(1, null, this);
    }

    public static TodoEditFragment newInstance(long id) {
        TodoEditFragment fragment = new TodoEditFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_TODO_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public TodoEditFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTodoId = getArguments().getLong(ARG_TODO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_todo_edit, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri item = ContentUris.withAppendedId(TodoContentProvider.CONTENT_URI, mTodoId);
        CursorLoader loader = new CursorLoader(this.getActivity(), item, null, null, null, null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.getCount() > 0) {
            data.moveToFirst();

            int iTitle = data.getColumnIndex(Todo.COLUMN_TITLE);
            int iDescription = data.getColumnIndex(Todo.COLUMN_DESCRIPTION);

            mTitleView.setText(data.getString(iTitle));
            mDescriptionView.setText(data.getString(iDescription));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
