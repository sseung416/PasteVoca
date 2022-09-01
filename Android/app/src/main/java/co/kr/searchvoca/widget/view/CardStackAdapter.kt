package co.kr.searchvoca.widget.view

import android.view.View
import co.kr.cardstackview.CardStackListener
import co.kr.cardstackview.Direction

interface CardStackAdapter : CardStackListener {
    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {}

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}
}