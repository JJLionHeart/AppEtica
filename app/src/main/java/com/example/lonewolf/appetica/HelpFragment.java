package com.example.lonewolf.appetica;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HelpFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HelpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HelpFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private OnFragmentInteractionListener mListener;

    private MapView map_view;
    private GoogleMap mGoogleMap;

    private Marker AEQUS_Marker;
    private Marker INGENIUM_Marker;

    public HelpFragment() {
        // Required empty public constructor
    }


    public static HelpFragment newInstance(String param1, String param2) {
        HelpFragment fragment = new HelpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        getActivity().setTitle("Buscar Ayuda");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_help, container, false);

        map_view = (MapView) view.findViewById(R.id.help_centers_positions);

        map_view.onCreate(savedInstanceState);
        if(map_view != null) {
            map_view.getMapAsync(this);
        }

        ListView help_centers = (ListView) view.findViewById(R.id.help_centers);
        ArrayAdapter<String> centers_list = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);

        centers_list.add("AEQUS");
        centers_list.add("Ingenium A.B.P");

        help_centers.setAdapter(centers_list);

        help_centers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        onMarkerClick(AEQUS_Marker);
                        break;
                    case 1:
                        onMarkerClick(INGENIUM_Marker);
                        break;
                    default:
                        break;
                }
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;


        mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info_window, null);
                TextView center_name = (TextView) view.findViewById(R.id.center_name);
                TextView center_direction = (TextView) view.findViewById(R.id.center_direction);
                TextView center_phone = (TextView) view.findViewById(R.id.center_phone);

                switch(marker.getTitle()){
                    case "AEQUS":
                        center_name.setText("AEQUS");
                        center_direction.setText("5ta Zona #409 Col. Caracol 64810 Monterrey");
                        center_phone.setText("01 81 8358 2000 Ext 3413");
                        break;
                    case "Ingenium":
                        center_name.setText("Ingenium A.B.P");
                        center_direction.setText("Mariano Matamoros s/n, entre 5 de Mayo y 16 de Septiembre, Col. Palo Blanco, San Pedro Garza García");
                        center_phone.setText(" 01 81 8040 9418");
                        break;
                }
                return view;
            }
        });

        // Monterrey latlng
        LatLng monterrey = new LatLng(25.6648358, -100.3487929);

        // LatLng corresponding to the help centers
        LatLng AEQUS = new LatLng(25.6648453, -100.293757);
        LatLng Ingenium = new LatLng(25.6571442,-100.3977316);

        AEQUS_Marker = mGoogleMap.addMarker(new MarkerOptions().position(AEQUS).title("AEQUS"));
        INGENIUM_Marker = mGoogleMap.addMarker(new MarkerOptions().position(Ingenium).title("Ingenium"));
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(monterrey, (float)11.5);
        mGoogleMap.moveCamera(camera);

        mGoogleMap.setOnMarkerClickListener(this);


        map_view.onResume();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(marker.getPosition().latitude +0.0003,
                marker.getPosition().longitude), 18));
        marker.showInfoWindow();
        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
