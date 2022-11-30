package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;
import com.google.android.gms.maps.model.StreetViewSource;
import edu.illinois.cs.cs124.ay2022.mp.R;

//public class StreetViewEx extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
//  private StreetViewPanorama streetViewPanorama;
//
//  @Override
//  protected void onCreate(final Bundle saveInstanceState) {
//    super.onCreate(saveInstanceState);
//    setContentView(R.layout.activity_streetview);
//    SupportStreetViewPanoramaFragment streetViewPanoramaFragment = (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
//        .findFragmentById(R.id.streetviewpanorama);
//    assert streetViewPanoramaFragment != null;
//    streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
//    //FloatingActionButton button = findViewById(R.id.button);
//
//  }
//
//  @Override
//  public void onStreetViewPanoramaReady(@NonNull final StreetViewPanorama streetViewPanorama) {
//    streetViewPanorama.setPosition(new LatLng(51.52887, -0.1726073), StreetViewSource.OUTDOOR);
//    streetViewPanorama.setStreetNamesEnabled(true);
//    streetViewPanorama.setPanningGesturesEnabled(true);
//    streetViewPanorama.setZoomGesturesEnabled(true);
//    streetViewPanorama.setUserNavigationEnabled(true);
//    streetViewPanorama.animateTo(
//        new StreetViewPanoramaCamera.Builder().orientation(new StreetViewPanoramaOrientation(20,20))
//            .zoom(streetViewPanorama.getPanoramaCamera().zoom)
//            .build(), 2000
//    );
//    streetViewPanorama.setOnStreetViewPanoramaCameraChangeListener(panormachangelistner);
//  }
//
//  private StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener panormachangelistner = new StreetViewPanorama.OnStreetViewPanoramaCameraChangeListener() {
//    @Override
//    public void onStreetViewPanoramaCameraChange(@NonNull final StreetViewPanoramaCamera streetViewPanoramaCamera) {
//      Toast.makeText(StreetViewEx.this, "Location Update", Toast.LENGTH_SHORT).show();
//    }
//  };

//}
