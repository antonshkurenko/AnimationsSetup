package io.github.tonyshkurenko.animationssetup.examples;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.tonyshkurenko.animationssetup.R;

/**
 * Type evaluators
 * Time interpolators
 */
public class ObjectAnimatorActivity extends BaseExampleActivity {

  @BindView(R.id.button_example_top) Button mButtonExampleTop;
  @BindView(R.id.button_example_center) Button mButtonExampleCenter;
  @BindView(R.id.button_example_bottom) Button mButtonExampleBottom;
  @BindView(R.id.button_example_right) Button mButtonExampleRight;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_object_animator);
    ButterKnife.bind(this);
  }

  @OnClick({
      R.id.button_example_top, R.id.button_example_center, R.id.button_example_bottom,
      R.id.button_example_right
  }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.button_example_top:

        //noinspection ResourceType
        final AnimatorSet sequentialAnimator =
            (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.anim_sequential_example);
        sequentialAnimator.setTarget(mButtonExampleTop);
        sequentialAnimator.start();

        Toast.makeText(ObjectAnimatorActivity.this, "From xml", Toast.LENGTH_SHORT).show();
        break;
      case R.id.button_example_center:

        final ObjectAnimator objectAnimator =
            ObjectAnimator.ofFloat(mButtonExampleCenter, "alpha", 1f, 0f);
        objectAnimator.setDuration(1000);
        objectAnimator.setRepeatCount(1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mButtonExampleCenter.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        objectAnimator.addListener(new AnimatorListenerAdapter() {
          @Override public void onAnimationEnd(Animator animation) {
            mButtonExampleCenter.setLayerType(View.LAYER_TYPE_NONE, null);
          }
        });

        objectAnimator.start();

        Toast.makeText(ObjectAnimatorActivity.this, "From code with layer type hardware",
            Toast.LENGTH_SHORT).show();
        break;
      case R.id.button_example_right:

        final ObjectAnimator propertyHolderAnimator =
            ObjectAnimator.ofPropertyValuesHolder(mButtonExampleRight,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f));

        propertyHolderAnimator.setDuration(1000);

        propertyHolderAnimator.setInterpolator(new DecelerateInterpolator());

        propertyHolderAnimator.setRepeatCount(1);
        propertyHolderAnimator.setRepeatMode(ValueAnimator.REVERSE);
        propertyHolderAnimator.start();

        Toast.makeText(ObjectAnimatorActivity.this, "From code", Toast.LENGTH_SHORT).show();
        break;
      case R.id.button_example_bottom:

        final ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,
            R.animator.animator_simple_translation);

        animator.setTarget(mButtonExampleBottom);
        animator.start();

        Toast.makeText(ObjectAnimatorActivity.this, "From xml", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
