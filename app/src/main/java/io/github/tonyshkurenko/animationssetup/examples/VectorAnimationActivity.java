package io.github.tonyshkurenko.animationssetup.examples;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.tonyshkurenko.animationssetup.R;

public class VectorAnimationActivity extends BaseExampleActivity {

  @BindView(R.id.image_view_android) ImageView mAndroid;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_vector_animations);
    ButterKnife.bind(this);

    final Drawable androidDrawable = mAndroid.getDrawable();
    if (androidDrawable instanceof Animatable) {
      ((Animatable) androidDrawable).start();
    }
  }
}
