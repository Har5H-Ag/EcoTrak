package com.example.ecotrak;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ecotrak.ui.login.AboutActivity;

public class HomeActivity extends AppCompatActivity {

    private ArrayAdapter<String> taskAdapter;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button addTaskButton = findViewById(R.id.addTaskButton);

        addTaskButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        }) ;

        // Initialize the task list and adapter
        ListView taskListView = findViewById(R.id.taskListView);
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        taskListView.setAdapter(taskAdapter);

        // Sample tasks
        taskAdapter.add("Task 1");
        taskAdapter.add("Task 2");
        taskAdapter.add("Task 3");
        taskAdapter.add("Task 4");
        // Set up the app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        registerForContextMenu(taskListView);
        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position; // Store the selected position
        });    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }
    int i=5;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            // Handle "Add Task" action
            Intent i = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(i);
            Toast.makeText(this, "About us clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_help) {
            Toast.makeText(this, "Help clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        assert info != null;
        selectedPosition = info.position; // Store the selected position

        String selectedTask = taskAdapter.getItem(selectedPosition); // Use selectedPosition

        if (item.getItemId() == R.id.context_edit) {
            // Handle "Edit Task" action

            // Create a dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_task, null);
            builder.setView(dialogView);

            EditText editTaskName = dialogView.findViewById(R.id.editTaskName);
            Button saveButton = dialogView.findViewById(R.id.saveButton);

            // Pre-fill the EditText with the current task name
            editTaskName.setText(selectedTask);

            // Show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Set a click listener for the "Save" button in the dialog
            saveButton.setOnClickListener(v -> {
                String editedName = editTaskName.getText().toString();
                // Update the item in the adapter using selectedPosition
                taskAdapter.remove(selectedTask);
                taskAdapter.insert(editedName, selectedPosition);

                // Notify the adapter that the data has changed
                taskAdapter.notifyDataSetChanged();
                // Dismiss the dialog
                dialog.dismiss();
            });
            Toast.makeText(this, "Edit Task: " + selectedTask, Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.context_delete) {
            // Handle "Delete Task" action
            taskAdapter.remove(selectedTask);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    public void showPopupMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.popup_create) {
                // Handle "Share Task" action
                taskAdapter.add("Task " + i++);
                Toast.makeText(this, "Create button clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.popup_import) {
                // Handle "Task Details" action
                Toast.makeText(this, "Import button clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false; // Default case
            }
        });
        popupMenu.show();
    }
}
