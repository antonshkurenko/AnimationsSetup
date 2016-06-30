package io.github.tonyshkurenko.animationssetup.examples;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.tonyshkurenko.animationssetup.R;

public class AnimationDrawableActivity extends BaseExampleActivity {

  @BindView(R.id.image_view_example) ImageView mImageViewExample;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_animation_drawable);
    ButterKnife.bind(this);

    final AnimationDrawable animationDrawable = (AnimationDrawable) mImageViewExample.getDrawable();
    animationDrawable.start();
  }
}
