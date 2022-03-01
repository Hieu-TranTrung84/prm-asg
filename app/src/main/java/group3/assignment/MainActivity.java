package group3.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import group3.assignment.fragment.ChangePassFragment;
import group3.assignment.fragment.MemberFragment;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    private View mHeaderView;
    private TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);


        //set toolbar
        ActionBar ab = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#1761A0"));

        // Set BackgroundDrawable
        ab.setBackgroundDrawable(colorDrawable);
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //show user in header
        NavigationView nv = findViewById(R.id.nvView);

        mHeaderView = nv.getHeaderView(0);
        txtUser = mHeaderView.findViewById(R.id.txtUser);
        Intent intent = getIntent();
        txtUser.setText("Welcome !");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {
                    case R.id.nav_Card:
                        setTitle("Card");
                        break;
                    case R.id.nav_Book_Category:
                        setTitle("Book Category");
                        break;
                    case R.id.nav_Book:
                        setTitle("Book");
                        break;
                    case R.id.nav_Member:
                        setTitle("Member");
                        MemberFragment memberFragment = new MemberFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, memberFragment)
                                .commit();
                        break;
                    case R.id.sub_Revenue:
                        setTitle("Revenue");
                        break;
                    case R.id.sub_AddUser:
                        setTitle("Add User");
                        break;
                    case R.id.sub_Pass:
                        setTitle("Change Password");
                        ChangePassFragment changePassFragment = new ChangePassFragment();
                        manager.beginTransaction()
                                .replace(R.id.fl_content, changePassFragment)
                                .commit();
                        break;
                    case R.id.sub_Logout:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}