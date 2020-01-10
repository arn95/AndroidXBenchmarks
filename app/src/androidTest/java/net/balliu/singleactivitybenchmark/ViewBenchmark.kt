package net.balliu.singleactivitybenchmark

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ViewBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val mainActivityRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, true)

    @get:Rule
    val basicActivityRule = ActivityTestRule<BasicActivity>(BasicActivity::class.java, false, true)

    @get:Rule
    val scrollingActivity = ActivityTestRule<ScrollingActivity>(ScrollingActivity::class.java, false, true)

    @Test
    fun benchmarkActivityLifecycle() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        benchmarkRule.measureRepeated {
            val intent = Intent(context, MainActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            mainActivityRule.launchActivity(null)

            mainActivityRule.activity.startActivity(Intent(mainActivityRule.activity, BasicActivity::class.java))
            mainActivityRule.activity.startActivity(Intent(mainActivityRule.activity, ScrollingActivity::class.java))

            mainActivityRule.finishActivity()
        }
    }

    @Test
    fun benchmarkFragmentLifecycle() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        benchmarkRule.measureRepeated {
            val intent = Intent(context, MainActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            mainActivityRule.launchActivity(null)

            val fm = mainActivityRule.activity.supportFragmentManager

            val transaction = fm.beginTransaction()

            transaction.replace(R.id.fragmentContainer, ItemFragment.newInstance(5))

            transaction.commit()

            val newTransaction = fm.beginTransaction()

            newTransaction.replace(R.id.fragmentContainer, PlusOneFragment.newInstance("Meow", "mrow"))

            newTransaction.commit()

            mainActivityRule.finishActivity()
        }
    }
}