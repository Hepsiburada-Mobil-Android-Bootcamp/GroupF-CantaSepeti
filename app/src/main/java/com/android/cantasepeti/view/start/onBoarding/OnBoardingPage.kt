package com.android.cantasepeti.view.start.onBoarding
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.cantasepeti.R

enum class OnBoardingPage(
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val logoResource: Int
) {

    ONE( R.string.onboarding_slide1_subtitle,R.string.onboarding_slide1_desc, R.drawable.ic_drawkit_vector_illustration_ecommerce_02),
    TWO( R.string.onboarding_slide2_subtitle,R.string.onboarding_slide2_desc, R.drawable.ic_drawkit_vector_illustration_ecommerce_01),
    THREE( R.string.onboarding_slide3_subtitle,R.string.onboarding_slide1_desc, R.drawable.ic_drawkit_vector_illustration_ecommerce_16)

}