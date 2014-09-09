package my.i906.todolist.fragment;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

import my.i906.todolist.adapter.TodoAdapter;
import my.i906.todolist.model.Todo;

public class TodoListFragment extends ListFragment {

    public TodoListFragment() { }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TodoAdapter adapter = new TodoAdapter(getActivity(), getSampleTodoItems());
        setListAdapter(adapter);
    }

    private ArrayList<Todo> getSampleTodoItems() {

        ArrayList<Todo> list = new ArrayList<Todo>();

        Todo item1 = new Todo();
        item1.id = 1;
        item1.title = "First note";
        item1.description = "Some very long description here.";

        Todo item2 = new Todo();
        item2.id = 2;
        item2.title = "Second note";
        item2.description = "Some very long description here.";

        Todo item3 = new Todo();
        item3.id = 3;
        item3.title = "Third note";
        item3.description = "Some very long description here.";

        list.add(item1);
        list.add(item2);
        list.add(item3);

        return list;
    }

}
