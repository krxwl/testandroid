package com.github.krxwl.testandroid

import com.github.krxwl.testandroid.activities.OnBoardingActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object OnBoarding : KScreen<OnBoarding>() {
    override val layoutId: Int
        get() = R.id.onboarding_activity
    override val viewClass: Class<*>
        get() = OnBoardingActivity::class.java

    val btnNext = KButton {withId(R.id.next_btn)}
    val btnSkip = KButton {withId(R.id.skip_btn)}
    val imageView = KImageView {withId(R.id.onboarding_image)}
    val firstDescription = KTextView {withId(R.id.property_textview)}
    val secondDescription = KTextView {withId(R.id.description_textview)}
}