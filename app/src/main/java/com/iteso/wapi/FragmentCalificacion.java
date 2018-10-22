package com.iteso.wapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.iteso.wapi.beans.DiaHoras;
import com.iteso.wapi.beans.Materia;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCalificacion.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCalificacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCalificacion extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Materia> materias = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterMateria adapterMateria;
    Spinner spinner;


    public FragmentCalificacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCalificacion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCalificacion newInstance(String param1, String param2) {
        FragmentCalificacion fragment = new FragmentCalificacion();
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
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calificacion, container, false);
        recyclerView = rootView.findViewById(R.id.fragment_calificacion_recyclerView);
        spinner = (Spinner) rootView.findViewById(R.id.fragment_calificacion_spinner);

        ArrayAdapter<CharSequence> adapterPeriodo = ArrayAdapter.createFromResource(rootView.getContext(),
                R.array.periodo, android.R.layout.simple_spinner_item);
        adapterPeriodo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapterPeriodo);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        DiaHoras dias = new DiaHoras();

        materias = new ArrayList<>();
        materias.add(new Materia(1,1, "Micros", dias, (float) 5.3));
        materias.add(new Materia(2, 1, "Moviles", dias, (float)3.3));
        materias.add(new Materia(3, 1, "GBD", dias, (float)9.0));

        adapterMateria = new AdapterMateria(2, getActivity(), materias);
        recyclerView.setAdapter(adapterMateria);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Materia materia = data.getParcelableExtra("Materia");
        Iterator<Materia> iterator = materias.iterator();
        int position = 0;
        while(iterator.hasNext()){
            Materia item = iterator.next();
            if(item.getIdMateria() == materia.getIdMateria()){
                materias.set(position, materia);
                break;
            }
            position++;
        }
        adapterMateria.notifyDataSetChanged();
    }
*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
