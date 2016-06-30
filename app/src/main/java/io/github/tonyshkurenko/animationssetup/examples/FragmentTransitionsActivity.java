package io.github.tonyshkurenko.animationssetup.examples;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.tonyshkurenko.animationssetup.R;

public class FragmentTransitionsActivity extends BaseExampleActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment_transitions);
    ButterKnife.bind(this);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.content, new SimpleFromFragment(), SimpleFromFragment.class.getSimpleName())
          .commit();
    }
  }

  public static final class SimpleFromFragment extends Fragment {

    @BindView(R.id.image_view_icon) ImageView mImageViewIcon;
    @BindString(R.string.transition_name_android) String mTransitionName;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
      final View view = inflater.inflate(R.layout.fragment_example_from, container, false);
      ButterKnife.bind(this, view);

      ViewCompat.setTransitionName(mImageViewIcon, mTransitionName);
      return view;
    }

    @OnClick(R.id.image_view_icon) public void onClick() {

      final SimpleToFragment simpleToFragment = new SimpleToFragment();

      // Note that we need the API version check here
      // because the actual transition classes (e.g. Fade)
      // are not in the support library and are only available in API 21+.
      // The methods we are calling on the Fragment
      // ARE available in the support library (though they don't do anything on API < 21)

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        simpleToFragment.setSharedElementEnterTransition(new CustomTransition());
        simpleToFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        simpleToFragment.setSharedElementReturnTransition(new CustomTransition());
      }

      getFragmentManager().beginTransaction()
          .addSharedElement(mImageViewIcon, mTransitionName)
          .replace(R.id.content, simpleToFragment, SimpleToFragment.class.getSimpleName())
          .addToBackStack(null)
          .commit();
    }
  }

  public static final class SimpleToFragment extends Fragment {

    @BindView(R.id.image_view_icon) ImageView mImageViewIcon;
    @BindString(R.string.transition_name_android) String mTransitionName;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
      final View view = inflater.inflate(R.layout.fragment_example_to, container, false);
      ButterKnife.bind(this, view);

      ViewCompat.setTransitionName(mImageViewIcon, mTransitionName);
      return view;
    }
  }

  @TargetApi(Build.VERSION_CODES.KITKAT) protected static class CustomTransition
      extends TransitionSet {

    public CustomTransition() {
      setOrdering(ORDERING_TOGETHER);
      addTransition(new ChangeBounds());

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            addTransition(new ChangeTransform()).
            addTransition(new ChangeImageTransform());
      }
    }
  }
}
