package io.github.tonyshkurenko.animationssetup.examples;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.tonyshkurenko.animationssetup.R;

public class ActivityTransitionsActivity extends BaseExampleActivity {

  @BindView(R.id.image_view_icon) ImageView mImageViewIcon;
  @BindString(R.string.transition_name_android) String mTransitionNameIcon;

  @Override protected void onCreate(Bundle savedInstanceState) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
      getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
    }

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_transitions);
    ButterKnife.bind(this);

    ViewCompat.setTransitionName(mImageViewIcon, mTransitionNameIcon);
  }

  @OnClick(R.id.image_view_icon) public void onClick() {

    final Intent toSimpleTo = new Intent(this, SimpleToActivity.class);

    //noinspection unchecked
    final ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
        Pair.<View, String>create(mImageViewIcon, mTransitionNameIcon));

    ActivityCompat.startActivity(this, toSimpleTo, options.toBundle());
  }

  public static class SimpleToActivity extends BaseExampleActivity {

    @BindView(R.id.image_view_icon) ImageView mImageViewIcon;
    @BindString(R.string.transition_name_android) String mTransitionName;

    @Override protected void onCreate(Bundle savedInstanceState) {

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
      }

      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_activity_transitions_to);
      ButterKnife.bind(this);

      ViewCompat.setTransitionName(mImageViewIcon, mTransitionName);
    }
  }
}
