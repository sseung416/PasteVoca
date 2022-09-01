package co.kr.cardstackview.internal;

import android.view.animation.Interpolator;

import co.kr.cardstackview.Direction;

public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}
