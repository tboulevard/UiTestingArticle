package com.boulevard.uitestingarticle.ui.bindingadapters

import android.support.annotation.IntDef
import android.view.View

@IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
@Retention(AnnotationRetention.RUNTIME)
annotation class Visibility
