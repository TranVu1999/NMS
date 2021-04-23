package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class DemoDialogActivity extends AppCompatActivity {

    Button btnAddCate, btnAddNote;
    Dialog dialog;
    Dialog planDateDialog;
    String date;

    Spinner spinnerCate, spinnerPriority, spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_dialog);

        btnAddCate = (Button)findViewById(R.id.btnAddCate);
        btnAddNote = (Button)findViewById(R.id.btnAddNote);

        dialog = new Dialog(this);
        planDateDialog = new Dialog(this);
        spinnerCate = (Spinner)findViewById(R.id.spinnerCate);

        btnAddCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCategoryDialog();
            }
        });
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNoteDialog();
            }
        });

    }

    private void openLoseDialog() {

    }

    private void openAddCategoryDialog() {
//        dialog.setContentView(R.layout.win_dialog);
        dialog.setContentView(R.layout.layout_add_cate_form);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        Button btnSaveData = dialog.findViewById(R.id.btnSaveData);

        dialog.show();
    }

    private void openAddNoteDialog() {
        dialog.setContentView(R.layout.layout_add_note);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);
        Button btnSaveData = dialog.findViewById(R.id.btnSaveData);
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

    private void openSelectDateDialog(){
        //mở layout chọn ngày lên
        planDateDialog.setContentView(R.layout.layout_select_plandate);
        planDateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        Button btnOk, btnCancel;
        btnOk = planDateDialog.findViewById(R.id.btnOk);
        btnCancel = planDateDialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planDateDialog.dismiss();
            }
        });

        CalendarView calendarView;
        calendarView = planDateDialog.findViewById(R.id.cldPlanDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                date = curDate+'/'+Month+'/'+Year;
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView lblPlanDate = dialog.findViewById(R.id.lblPlanDate);
                lblPlanDate.setText(date);
                planDateDialog.dismiss();
            }
        });

        planDateDialog.show();
    }
}