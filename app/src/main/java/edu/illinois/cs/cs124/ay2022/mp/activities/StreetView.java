package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.LatLng;

public class StreetView extends AppCompatActivity {
  private StreetViewPanoramaView streetViewPanoramaView;
  private static final String STREETVIEW_BUNDLE_KEY = "AIzaSyB8sOA9n9Xif8MXn2tjvGATRzsVT_Bs5Og";

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Get this activity intent and value passed through extra
    Intent intent = getIntent();
    String latitude = intent.getStringExtra("latitude");
    String longitude = intent.getStringExtra("longitude");
    double lat = Double.parseDouble(latitude);
    double lon = Double.parseDouble(longitude);
    // Convert to LatLng object
    LatLng loc = new LatLng(lat, lon);

    // TODO: Add back button

    StreetViewPanoramaOptions options = new StreetViewPanoramaOptions();
    if (savedInstanceState == null) {
      options.position(loc);
    }

    streetViewPanoramaView = new StreetViewPanoramaView(this, options);
    addContentView(
        streetViewPanoramaView,
        new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    // *** IMPORTANT ***
    // StreetViewPanoramaView requires that the Bundle you pass contain _ONLY_
    // StreetViewPanoramaView SDK objects or sub-Bundles.
    Bundle streetViewBundle = null;
    if (savedInstanceState != null) {
      streetViewBundle = savedInstanceState.getBundle(STREETVIEW_BUNDLE_KEY);
    }
    streetViewPanoramaView.onCreate(streetViewBundle);
  }

  @Override
  protected void onResume() {
    streetViewPanoramaView.onResume();
    super.onResume();
  }

  @Override
  protected void onPause() {
    streetViewPanoramaView.onPause();
    super.onPause();
  }

  @Override
  protected void onDestroy() {
    streetViewPanoramaView.onDestroy();
    super.onDestroy();
  }

  @Override
  public void onSaveInstanceState(@NonNull final Bundle outState) {
    super.onSaveInstanceState(outState);

    Bundle streetViewBundle = outState.getBundle(STREETVIEW_BUNDLE_KEY);
    if (streetViewBundle == null) {
      streetViewBundle = new Bundle();
      outState.putBundle(STREETVIEW_BUNDLE_KEY, streetViewBundle);
    }

    streetViewPanoramaView.onSaveInstanceState(streetViewBundle);
  }
}
