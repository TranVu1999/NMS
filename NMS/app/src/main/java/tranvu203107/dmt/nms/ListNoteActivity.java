package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static tranvu203107.dmt.nms.CategoryActivity.nameCategory;

public class ListNoteActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ListView listView;
    ListView listViewAccount;
    EditText textAddNote;

    ArrayList<ItemMenu> arrList;
    ArrayList<ItemMenu> arrListAccount;
    MenuAdapter menuAdapter;

    ArrayList<Note> arrNote;
    RecyclerView noteListRecycler;
    NoteAdapter noteAdapter;

    public static String DATABASE_NAME="myDB.sqlite";
    public static String DB_PATH_SUFFIX="/databases/";
    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);
        addControls();
        processCopy();

        // map
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.listView);
        listViewAccount = (ListView) findViewById(R.id.listViewAccount);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        noteListRecycler = findViewById(R.id.noteList);

        // Config toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(GravityCompat.START );
            }
        });

        // action menu
        arrList = new ArrayList<ItemMenu>();
        arrList.add(new ItemMenu("Home", R.drawable.ic_action_home));
        arrList.add(new ItemMenu("Category", R.drawable.ic_action_category));
        arrList.add(new ItemMenu("Priority", R.drawable.ic_action_priority));
        arrList.add(new ItemMenu("Status", R.drawable.ic_action_status));
        arrList.add(new ItemMenu("Note", R.drawable.ic_action_note));

        menuAdapter = new MenuAdapter(this, R.layout.item_row_menu, arrList);
        listView.setAdapter(menuAdapter);

        // action menu account
        arrListAccount = new ArrayList<ItemMenu>();
        arrListAccount.add(new ItemMenu("Edit Profile", R.drawable.ic_action_edit));
        arrListAccount.add(new ItemMenu("Change Password", R.drawable.ic_action_change));

        menuAdapter = new MenuAdapter(this, R.layout.item_row_menu, arrListAccount);
        listViewAccount.setAdapter(menuAdapter);


        // list note
        arrNote = new ArrayList<Note>();
        showListNote();
    }

    private void addControls() {

    }

    private void showListNote() {
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
        switch (nameCategory)
        {
            case "Exercise":
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id\n" +
                        "WHERE CATEGORY.Name =\"Exercise\"",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
            case "HomeWork":
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id\n" +
                        "WHERE CATEGORY.Name =\"Homework\"",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
            case "Meeting":
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id\n" +
                        "WHERE CATEGORY.Name =\"Meeting\"",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
            case "Entertainment":
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id\n" +
                        "WHERE CATEGORY.Name =\"Entertainment\"",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
            case "MyJob":
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id\n" +
                        "WHERE CATEGORY.Name =\"MyJob\"",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
            default:
            {
                Cursor cursor = database.rawQuery("SELECT Status.status,CATEGORY.name,NOTE.Name,PRIORITY.Priority,NOTE.PlanDate,NOTE.CreatedDate\n" +
                        "FROM NOTE\n" +
                        "INNER JOIN CATEGORY ON NOTE.CateId =CATEGORY.Id\n" +
                        "INNER JOIN Priority ON NOTE.PriorityId = Priority.Id\n" +
                        "INNER JOIN Status ON NOTE.StatusId=Status.id",null);
                //adapter.clear();
                //Toast.makeText(MainActivity.this,"Da vao",Toast.LENGTH_LONG).show();
                while(cursor.moveToNext())
                {
                    String status = cursor.getString(0);
                    String cateName = cursor.getString(1);
                    String noteName = cursor.getString(2);
                    String priorityName = cursor.getString(3);
                    String planDate = cursor.getString(4);
                    String createdDate = cursor.getString(5);
                    arrNote.add(new Note(status,cateName,noteName,priorityName,planDate,createdDate));
                }
                cursor.close();
                noteAdapter =new NoteAdapter(getApplicationContext(), arrNote);
                noteListRecycler.setAdapter(noteAdapter);
                break;
            }
        }


    }

    private void processCopy()
    {
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            if (!dbFile.exists()) {
                copyDatabaseFromAsset();
                Toast.makeText(ListNoteActivity.this, "Sao chép thành công", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(ListNoteActivity.this,"Khong sao chep",Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(ListNoteActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            Log.e("LOI",ex.toString());
        }
    }
    private String getDatabasePath()
    {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
    private void copyDatabaseFromAsset() {
        try{
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
            {
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte []buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer))>0)
            {
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();


        }catch (Exception ex)
        {
            Log.e("LOI",ex.toString());
        }

    }
    public void addNoteClick(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_add_note);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
    }
    public void eventSave(View view) {

        String noteName =textAddNote.getText().toString();

        ContentValues newValues = new ContentValues();
        newValues.put("Name",noteName);


        long kq =ListNoteActivity.database.insert("NOTE",null,newValues);
        if(kq>0)
            Toast.makeText(ListNoteActivity.this,"Them thanh cong",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(ListNoteActivity.this,"Them that bai",Toast.LENGTH_LONG).show();
    }
}