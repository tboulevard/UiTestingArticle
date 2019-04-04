package com.boulevard.uitestingarticle

import android.app.Application

/**
 * We use a separate App for tests to prevent initializing dependency injection.
 */
class TestApplication : Application()
