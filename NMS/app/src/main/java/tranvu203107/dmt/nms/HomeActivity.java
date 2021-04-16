package tranvu203107.dmt.nms;

import androidx.appcompat.app.ActionBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ListView listView;
    ArrayList<ItemMenu> arrList;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // map
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        listView = (ListView) findViewById(R.id.listView);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

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
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));

        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));
        arrList.add(new ItemMenu("import", R.drawable.ic_action_user));

        menuAdapter = new MenuAdapter(this, R.layout.item_row_menu, arrList);
        listView.setAdapter(menuAdapter);
    }





}