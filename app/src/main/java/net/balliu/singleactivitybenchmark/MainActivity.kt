package net.balliu.singleactivitybenchmark

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.balliu.singleactivitybenchmark.dummy.DummyContent

class MainActivity : AppCompatActivity(), PlusOneFragment.OnFragmentInteractionListener, ItemFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onFragmentInteraction(uri: Uri?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
