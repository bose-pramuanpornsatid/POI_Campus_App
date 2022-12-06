package edu.illinois.cs.cs124.ay2022.mp.models;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.OverlayWithIW;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

public class CustomInfoWindow extends BasicInfoWindow {

  public static final int UNDEFINED_RES_ID = 0;

  float rating;

  static int mTitleId = UNDEFINED_RES_ID,
      mDescriptionId = UNDEFINED_RES_ID,
      mSubDescriptionId = UNDEFINED_RES_ID,
      mImageId = UNDEFINED_RES_ID,
      mRatingId = UNDEFINED_RES_ID;

  public CustomInfoWindow(int layoutResId, MapView mapView) {
    super(layoutResId, mapView);

    if (mTitleId == UNDEFINED_RES_ID)
      setResIds(mapView.getContext());

    //default behavior: close it when clicking on the bubble:
    mView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_UP)
          close();
        return true;
      }
    });
  }

  public void setRating(final float setRating) {
    rating = setRating;
  }

  private static void setResIds(Context context) {
    String packageName = context.getPackageName(); //get application package name
    mTitleId = context.getResources().getIdentifier("id/bubble_title", null, packageName);
    mDescriptionId = context.getResources().getIdentifier("id/bubble_description", null, packageName);
    mSubDescriptionId = context.getResources().getIdentifier("id/bubble_subdescription", null, packageName);
    mImageId = context.getResources().getIdentifier("id/bubble_image", null, packageName);
    mRatingId = context.getResources().getIdentifier("id/rating_bar", null, packageName);
    if (mTitleId == UNDEFINED_RES_ID || mDescriptionId == UNDEFINED_RES_ID
        || mSubDescriptionId == UNDEFINED_RES_ID || mImageId == UNDEFINED_RES_ID) {
      Log.e(IMapView.LOGTAG, "BasicInfoWindow: unable to get res ids in " + packageName);
    }
  }

  @Override
  public void onOpen(Object item) {
    OverlayWithIW overlay = (OverlayWithIW) item;
    String title = overlay.getTitle();
    if (title == null)
      title = "";
    if (mView == null) {
      Log.w(IMapView.LOGTAG, "Error trapped, BasicInfoWindow.open, mView is null!");
      return;
    }
    TextView temp = ((TextView) mView.findViewById(mTitleId /*R.id.title*/));

    if (temp != null) temp.setText(title);

    String snippet = overlay.getSnippet();
    if (snippet == null)
      snippet = "";
    Spanned snippetHtml = Html.fromHtml(snippet);
    ((TextView) mView.findViewById(mDescriptionId /*R.id.description*/)).setText(snippetHtml);

    //handle sub-description, hidding or showing the text view:
    TextView subDescText = (TextView) mView.findViewById(mSubDescriptionId);
    String subDesc = overlay.getSubDescription();
    if (subDesc != null && !("".equals(subDesc))) {
      subDescText.setText(Html.fromHtml(subDesc));
      subDescText.setVisibility(View.VISIBLE);
    } else {
      subDescText.setVisibility(View.GONE);
    }

    //handle RatingBar
    RatingBar ratingBar = (RatingBar) mView.findViewById(mRatingId);
    ratingBar.setRating(rating);
  }
}
