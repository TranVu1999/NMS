package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DemoDialogActivity extends AppCompatActivity {

    Button btnAddCate, btnAddNote;
    Dialog dialog;

    Spinner spinnerCate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_dialog);

        btnAddCate = (Button)findViewById(R.id.btnAddCate);
        btnAddNote = (Button)findViewById(R.id.btnAddNote);

        dialog = new Dialog(this);
        spinnerCate = (Spinner)findViewById(R.id.spinnerCate);

        btnAddCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategoryDialog();
            }
        });
//        btnAddNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openAddNoteDialog();
//            }
//        });
//
//
//
//        // Create list option
//        ArrayList<String> arr = new ArrayList<String>();
//        arr.add("Tran Le Anh Vu");
//        arr.add("Pham Duc Huy");
//        arr.add("Nguyen Hoang Toan");
//        arr.add("Tran Minh Thinh");
//        arr.add("Huynh Ngoc Quoc");
//
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arr);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item  );
//        spinnerCate.setAdapter(arrayAdapter);



    }

    private void openLoseDialog() {

    }

    private void openAddCategoryDialog() {
//        dialog.setContentView(R.layout.win_dialog);
        dialog.setContentView(R.layout.layout_add_cate_form);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        Button btnSaveData = dialog.findViewById(R.id.btnSaveDataAdd);

        dialog.show();
    }

    private void openAddNoteDialog() {
        dialog.setContentView(R.layout.layout_add_note);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        Button btnSaveData = dialog.findViewById(R.id.btnSaveDataAdd);
        Button btnSelectPlanDate = dialog.findViewById(R.id.btnSelectPlanDate);
        btnSelectPlanDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectDateDialog();
            }
        });


        OptionAdapter optionsAdapter;
        spinnerCate = dialog.findViewById(R.id.spinnerCate);
        spinnerPriority = dialog.findViewById(R.id.spinnerPrioroty);
        spinnerStatus = dialog.findViewById(R.id.spinnerStatus);
        ArrayList<Option> arrOptionCate;
        // action menu
        arrOptionCate = new ArrayList<Option>();
        arrOptionCate.add(new Option("Cate 1", "1"));
        arrOptionCate.add(new Option("Cate 2", "2"));
        arrOptionCate.add(new Option("Cate 3", "3"));
        arrOptionCate.add(new Option("Cate 4", "4"));

        ArrayList<Option> arrOptionPriority;
        // action menu
        arrOptionPriority = new ArrayList<Option>();
        arrOptionPriority.add(new Option("Prio 1", "1"));
        arrOptionPriority.add(new Option("Prio 2", "2"));
        arrOptionPriority.add(new Option("Prio 3", "3"));

        ArrayList<Option> arrOptionStatus;
        // action menu
        arrOptionStatus = new ArrayList<Option>();
        arrOptionStatus.add(new Option("Status 1", "1"));
        arrOptionStatus.add(new Option("Status 2", "2"));
        arrOptionStatus.add(new Option("Status 3", "3"));

        optionsAdapter = new OptionAdapter(this, R.layout.layout_option_item, arrOptionCate);
        spinnerCate.setAdapter(optionsAdapter);

        optionsAdapter = new OptionAdapter(this, R.layout.layout_option_item, arrOptionPriority);
        spinnerPriority.setAdapter(optionsAdapter);

        optionsAdapter = new OptionAdapter(this, R.layout.layout_option_item, arrOptionStatus);
        spinnerStatus.setAdapter(optionsAdapter);

        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Toast.makeText(getApplicationContext(), "You pick category " + position, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        spinnerPriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Toast.makeText(getApplicationContext(), "You pick priority " + position, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                Toast.makeText(getApplicationContext(), "You pick status " + position, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        dialog.show();
    }

}