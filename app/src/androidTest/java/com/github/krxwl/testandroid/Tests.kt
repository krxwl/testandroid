package com.github.krxwl.testandroid

import android.util.Log
import com.kaspersky.kaspresso.kaspresso.Kaspresso

import com.kaspersky.kaspresso.params.ClickParams
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.github.krxwl.testandroid.activities.MainActivity
import com.github.krxwl.testandroid.database.Repository
import com.github.krxwl.testandroid.viewmodels.MainViewModel
import com.github.krxwl.testandroid.viewmodels.OnBoardingViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class Tests : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            clickParams = ClickParams.kakaoVisual()
        }
    )
) {

    val mainActivityViewModel = MainViewModel()
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test1() = run {
        /*
        Проверка условия:
        Изображение и текста из очереди извлекается правильно (в порядке добавления в очередь).
        */

        step("Open OnboardingActivity and check queue1") {
            OnBoarding {
                imageView {
                    hasDrawable(R.drawable.queue1)
                }
                firstDescription {
                    hasText(R.string.queue_1_1_description)
                }
                secondDescription {
                    hasText(R.string.queue_1_2_description)
                }
                btnNext {
                    isVisible()
                    click()
                }
            }
        }
        step("check queue2") {
            OnBoarding.imageView {
                hasDrawable(R.drawable.queue2)
            }
            OnBoarding.firstDescription {
                hasText(R.string.queue_2_1_description)
            }
            OnBoarding.secondDescription {
                hasText(R.string.queue_2_2_description)
            }
            OnBoarding.btnNext {
                isVisible()
                click()
            }
        }
        step("check queue3") {
            OnBoarding.imageView {
                hasDrawable(R.drawable.queue3)
            }
            OnBoarding.firstDescription {
                hasText(R.string.queue_3_1_description)
            }
            OnBoarding.secondDescription {
                hasText(R.string.queue_3_2_description)
            }
        }
    }

    @Test
    fun test2() = run {
        val viewmodel = OnBoardingViewModel()
        mainActivityViewModel.insertFrames()
        step("Open OnboardingActivity and check queue1") {
            OnBoarding {
                val valueBefore = viewmodel.framesCount()
                Log.i("1234", "${viewmodel.framesList}")
                btnNext {
                    isVisible()
                    click()
                }
                Log.i("1234", "${viewmodel.framesList}")
                assertEquals(viewmodel.framesCount(), valueBefore - 1)
            }
        }
        step("queue2") {
            OnBoarding {
                val valueBefore = viewmodel.framesCount()
                btnNext {
                    isVisible()
                    click()
                }
                assertEquals(viewmodel.framesCount(), valueBefore - 1)
            }
        }
        step("queue3") {
            OnBoarding {
                val valueBefore = viewmodel.framesCount()
                btnNext {
                    isVisible()
                    click()
                }
                assertEquals(viewmodel.framesCount(), valueBefore - 1)
            }
        }
    }
}