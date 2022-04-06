package kr.co.dgsw.cardstackview.internal;

import android.view.animation.Interpolator;

import kr.co.dgsw.cardstackview.Direction;

public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}
