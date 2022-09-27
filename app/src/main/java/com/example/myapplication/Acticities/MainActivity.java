package com.example.myapplication.Acticities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Fragments.acccountFragment;
import com.example.myapplication.Fragments.cartFragment;
import com.example.myapplication.Fragments.searchFragment;
import com.example.myapplication.Fragments.homeFragment;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

           // loadFrag(new homeFragment(),0);
           binding.navBar.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.homee){
                    loadFrag(new homeFragment(),1);
                }else if (id == R.id.cart){
                    loadFrag(new cartFragment(),1);
                }else if (id == R.id.explore){
                    loadFrag(new searchFragment(),1);
                }else
                {
                    loadFrag(new acccountFragment(),1);
                }
            }
        });
         binding.navBar.setSelectedItemId(R.id.homee);

    }

    private void loadFrag(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag ==0){
          //  ft.add(R.id.container, fragment);
        }
        else{
            ft.replace(R.id.container , fragment);

        }
        ft.commit();
    }
}