package com.example.pizzadelivery.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pizzadelivery.ui.tabs.MenuFragment;
import com.example.pizzadelivery.ui.tabs.OfertasFragment;
import com.example.pizzadelivery.ui.tabs.PizzaPersonalizadaFragment;
import com.example.pizzadelivery.ui.tabs.OfertaCrudFragment;

public class MainPagerAdapter extends FragmentStateAdapter {

    public MainPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new OfertasFragment();
            case 1:
                return new MenuFragment();
            case 2:
                return new PizzaPersonalizadaFragment();
            case 3:
            default:
                return new OfertaCrudFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
