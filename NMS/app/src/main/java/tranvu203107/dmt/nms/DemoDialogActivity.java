package tranvu203107.dmt.nms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DemoDialogActivity extends AppCompatActivity {

    Button btnAddCate, btnAddNote;
    Dialog dialog;
    Spinner spinnerCate;

    ArrayList<Option> arrOption;
    OptionAdapter optionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_dialog);

        btnAddCate = (Button)findViewById(R.id.btnAddCate);
        btnAddNote = (Button)findViewById(R.id.btnAddNote);

        dialog = new Dialog(this);
        spinnerCate = findViewById(R.id.spinnerCate);

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
                Toast toast = Toast.makeText(DemoDialogActivity.this, "dialog", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        // set list option
        arrOption = new ArrayList<Option>();
        arrOption.add(new Option("Tran Le Anh Vu", "123"));
        arrOption.add(new Option("Pham Duc Huy", "123"));
        arrOption.add(new Option("Pham Hoang Toan", "123"));
        arrOption.add(new Option("Tran Minh Thinh", "123"));
        arrOption.add(new Option("Pham Hoang Toan", "123"));

        optionAdapter = new OptionAdapter(this, R.layout.item_row_option, arrOption);
        spinnerCate.setAdapter(optionAdapter);

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
        dialog.show();
    }

}