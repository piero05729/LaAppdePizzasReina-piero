package com.example.pizzadelivery;

import android.os.Bundle;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

import android.widget.ImageView;

import com.example.pizzadelivery.ui.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar con menu (carrito y usuario) y logo clickeable
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        ImageView ivToolbarLogo = findViewById(R.id.ivToolbarLogo);

        // ViewPager y TabLayout
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Adapter para los 3 fragments: Ofertas, Menú, Pizza Personalizada
        MainPagerAdapter adapter = new MainPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Vincular tabs con el ViewPager y asignar títulos
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Ofertas");
                    break;
                case 1:
                    tab.setText("Menú");
                    break;
                case 2:
                    tab.setText("Pizza Personalizada");
                    break;
            }
        }).attach();

        // Logo siempre lleva a Ofertas (posición 0)
        ivToolbarLogo.setOnClickListener(v -> viewPager.setCurrentItem(0, true));

        // Manejar clicks de los iconos del menú del toolbar
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_user) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            } else if (id == R.id.action_cart) {
                startActivity(new Intent(MainActivity.this, CarritoActivity.class));
                return true;
            }
            return false;
        });
    }
}