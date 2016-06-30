package io.github.tonyshkurenko.animationssetup.examples;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.tonyshkurenko.animationssetup.R;

public class ViewPropertyAnimatorActivity extends BaseExampleActivity {

  @BindView(R.id.button_example) Button mButtonExample;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_property_animator);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.button_example) public void onClick() {

    final float angle = 270f;
    final float alpha = 0.5f;
    final float fullAlpha = 1f;
    final float translation = 200f;

    mButtonExample.animate()
        .setDuration(ANIMATION_DURATION)
        .rotationYBy(angle)
        .alpha(alpha)
        .translationXBy(translation)
        .translationYBy(translation)
        .setListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mButtonExample.animate()
                .setDuration(ANIMATION_DURATION)
                .rotationYBy(-angle)
                .alpha(fullAlpha)
                .translationXBy(-translation)
                .translationYBy(-translation)
                .setListener(null); // prevent repeating
          }
        });
  }
}
