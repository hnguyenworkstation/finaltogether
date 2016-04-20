package AppConnector.MainFragments;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.reinvo.together.R;

/**
 * Created by Jason on 4/3/2016.
 */
public class MapFragment extends Fragment implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private static View view;
    /**
     * Note that this may be null if the Google Play services APK is not
     * available.
     */
    private MapView mMapView;
    private GoogleMap googleMap;
    private static SupportMapFragment mMap;

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LatLng mMyCoordinate;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buildGoogleClient(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        // initialize the Google Map
        MapsInitializer.initialize(getActivity().getApplicationContext());
        googleMap = mMapView.getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.setBuildingsEnabled(true);

        return v;
    }

    private void buildGoogleClient(Context context) {
        if(mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
        }
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            mMyCoordinate = new LatLng(mLastLocation.getLatitude(),
                    mLastLocation.getLongitude());

            if (isNetworkAvailable()) {
                updateCameraPosition();
                // create marker
                MarkerOptions marker = new MarkerOptions().position(mMyCoordinate).title("Hello Maps");

                // Changing marker icon
                marker.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                // adding marker
                googleMap.addMarker(marker);
            } else {
            }

        } else {
            // Todo: Add activities if lost connection
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    /*
    *  CHECKING IF THE DEVICE IS STILL CONNECTED TO THE INTERNETS
    * */
    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)this.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    /*
    *   MOVING TO THE CURRENT POSITION WITH ANIMATIONS
    * */
    private void updateCameraPosition() {
        final CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(mMyCoordinate)
                .zoom(13.5f)
                .bearing(0)
                .tilt(25)
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    /* *****************************************************************************
    *   OVERRIDE CLASSES FOR CHECKING THE IMPLEMENTATIONS
    * ******************************************************************************/
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
