package com.livroandroid.carros.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.livroandroid.carros.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CarrosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CarrosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarrosFragment extends BaseFragment {
    private int tipo;

    public static CarrosFragment newInstance(int tipo) {
        Bundle args = new Bundle();
        args.putInt("tipo", tipo);

        CarrosFragment f = new CarrosFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.tipo = getArguments().getInt("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        TextView text = (TextView)view.findViewById(R.id.text);
        text.setText("Carros " + getString(tipo));
        return view;
    }
}
