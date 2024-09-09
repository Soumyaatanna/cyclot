package hcl.esg.ebike.application.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcl.esg.ebike.application.Fragments.AvailableFragment;
import hcl.esg.ebike.application.Fragments.ProfileFragment;
import hcl.esg.ebike.application.Fragments.SecurityHomeFragment;
import hcl.esg.ebike.application.Fragments.UserHomeFragment;
import hcl.esg.ebike.application.R;


public class SecurityNavigation extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Toolbar toolbar;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_navigation);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.security_navigation);
        bottomNavigationView.setBackground(null);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.bottom_home){
                    openFragment(new SecurityHomeFragment());
                    return true;

                }
                else if(itemId == R.id.bottom_scanner) {
                    Intent intent = new Intent(SecurityNavigation.this, QrCodeScanner.class);
                    startActivity(intent);
                }
                else if(itemId == R.id.bottom_profile) {
                    openFragment(new ProfileFragment());
                    return true;
                }
                return false;
            }
        });
        fragmentManager = getSupportFragmentManager();
        openFragment(new SecurityHomeFragment());


    }




    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);


    }
}