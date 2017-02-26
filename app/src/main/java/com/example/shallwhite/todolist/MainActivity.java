package com.example.shallwhite.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private MissionDatabaseHelper mMissionDatabaseHelper;
    public static final int GO_USER_HEADER = 0;
    public static final int GO_ADD_MISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        mMissionDatabaseHelper = new MissionDatabaseHelper(this,account+".db",null,1);//为每个账户建立一个任务表


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);//菜单界面
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_list_light);
        }

        ImageView imageView = (ImageView)findViewById(R.id.avatar);//利用intent设置用户头像和用户名
        TextView textView = (TextView)findViewById(R.id.username);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.navigation_header);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,UserHeaderActivity.class);
                startActivityForResult(intent1,GO_USER_HEADER);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);//悬浮按钮控制
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,AddMissionActivity.class);
                startActivityForResult(intent,GO_ADD_MISSION);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//根据每个菜单选项来进行操作
        switch (item.getItemId()){
            case R.id.search:

                break;
            case R.id.log:
                Toast.makeText(MainActivity.this,"此功能未实现，不清楚具体逻辑。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.message:

                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//利用返回的结果进行设置
        switch (requestCode){
            case GO_USER_HEADER:
                if (resultCode == RESULT_OK){
                    String username = data.getStringExtra("username");//具体逻辑未处理

                }
                break;
            case GO_ADD_MISSION:
                if (resultCode == RESULT_OK){
                    String missionText = data.getStringExtra("mission_text");
                    String dateMonth = data.getStringExtra("date_month");
                    String dateDay = data.getStringExtra("date_day");
                }
            default:
        }
    }
}
