package com.nrc7.book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, BookListFragment.newInstance("",""), "listFragment")
                .commit();
    }

    @Override
    public void onBackPressed() {
        // Fragment anterior, el cual se quiere dejar de mostrar
        Fragment oldFragment = getSupportFragmentManager().findFragmentByTag("detailsFr");
        // Si no hay un fragment de detalle, cerrar la app
        if (oldFragment == null) {
            super.onBackPressed();
        }
        // Si hay fragment de detalle, quitarlo y poner el de lista
        else {
            getSupportFragmentManager().beginTransaction()
                    // Fragment actual que se desea mostrar
                    .add(R.id.container, BookListFragment.newInstance("",""), "listFragment")
                    .remove(oldFragment)
                    .commit();
        }

    }
}
