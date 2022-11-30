package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;
import edu.illinois.cs.cs124.ay2022.mp.network.Client;
import java.util.concurrent.CompletableFuture;

public class AddPlaceActivity extends AppCompatActivity {
  private static final String TAG = AddPlaceActivity.class.getSimpleName();

  // Helper function for posting place to server
  private void clientPostPlace(final Place place) {
    final Client client = Client.start();
    // A CompletableFuture allows us to wait for the result of an asynchronous call
    CompletableFuture<ResultMightThrow<Boolean>> completableFuture = new CompletableFuture<>();
    // When getPlaces returns, it causes the CompletableFuture to complete
    client.postFavoritePlace(place, completableFuture::complete);
  }

  // Activity Start
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Load the layout for this activity
    setContentView(R.layout.activity_addplace);

    // Get this activity intent and value passed through extra
    Intent intent = getIntent();
    String latitude = intent.getStringExtra("latitude");
    String longitude = intent.getStringExtra("longitude");
    double lat = Double.parseDouble(latitude);
    double lon = Double.parseDouble(longitude);

    // Define Intent for returning to MainActivity.java
    // Set flag:
    // This will cause any existing task that would be associated with
    // the activity to be to IS cleared before the activity is started.
    // That is, the activity becomes the new root of an otherwise empty task,
    // and any old activities are finished.
    Intent launchMainActivity = new Intent(this, MainActivity.class);
    launchMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    // Find the button component in the layout and configure listener
    // Also save the reference for later use
    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(
        v -> {
          Log.d(TAG, "Cancel button clicked");
          startActivity(launchMainActivity);
        });
    Button saveButton = findViewById(R.id.save_button);
    saveButton.setOnClickListener(
        v -> {
          Log.d(TAG, "Save button clicked: " + lat + " | " + lon);
          EditText descriptionWidget = findViewById(R.id.description);
          String description = descriptionWidget.getText().toString();
          EditText nameWidget = findViewById(R.id.name);
          String name = nameWidget.getText().toString();
          String id = FavoritePlacesApplication.CLIENT_ID;

          // Init new place
          Place place = new Place(id, name, lat, lon, description);
          // Try posting place to server using helper function
          try {
            clientPostPlace(place);
          } catch (Exception e) {
            Log.e(TAG, "Error posting place to client: " + e);
          }
          startActivity(launchMainActivity);
        });
  }
}
