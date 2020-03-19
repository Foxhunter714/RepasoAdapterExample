package com.nrc7.book;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nrc7.book.adapter.BookAdapter;
import com.nrc7.book.model.Book;
import com.nrc7.book.model.DataSource;

import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private BookAdapter adapter;

    public BookListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BookListFragment newInstance(String param1, String param2) {
        BookListFragment fragment = new BookListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        // Lista de libros
        final List<Book> bookList = new DataSource().getAllBooks();
        adapter = new BookAdapter(bookList, getContext());
        recyclerView.setAdapter(adapter);

        // Evento onClick
        adapter.setOnItemClickListener(new BookAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Book book = bookList.get(position);
                // Toast.makeText(getContext(), book.getName(), Toast.LENGTH_SHORT).show();

                // Llamar a FragmentManager
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                // Intercambio de Fragmentos en pantalla
                fragmentManager.beginTransaction()
                        // Entregar parametros al nuevo Fragment
                        .add(R.id.container, DetailsFragment.newInstance(book.getName(),book.getAuthor()), "detailsFr")
                        // Ocultar fragment antiguo por su tag
                        .remove(fragmentManager.findFragmentByTag("listFragment"))
                        .commit();
            }
        });
    }
}
