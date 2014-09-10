package my.i906.todolist.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import my.i906.todolist.R;

public class TodoEditFragment extends Fragment {

    private static final String ARG_TODO_ID = "todoId";

    private int mTodoId;

    public static TodoEditFragment newInstance(String param1) {
        TodoEditFragment fragment = new TodoEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TODO_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public TodoEditFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTodoId = getArguments().getInt(ARG_TODO_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_todo_edit, container, false);
    }

}
