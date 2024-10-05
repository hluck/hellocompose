package com.hluck.migrationcompose

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.hluck.migrationcompose.databinding.FragmentTestBinding


class TestFragment : Fragment() {

    private lateinit var binding: FragmentTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.composeView.apply {
            setViewCompositionStrategy(
                //使用该Fragment的viewLifecycleOwner来匹配我们期望的生命周期，
                //以便Composition Scope与Fragment的生命周期匹配
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
        }
        binding.composeView.setContent {
            MaterialTheme{
                PlantDescription("hello compose")
            }
        }
        return binding.root
    }

}

@Composable
fun Test(clicked:() -> Unit){
    Column {
        Text("Hello Compose!!!")
        Button(onClick = { clicked() }) {
            Text("check")
        }
    }
}



@Composable
fun PlantDescription(msg:String){
    AndroidView(
        factory = { context ->
            TextView(context)
        },
        update = { tv ->
            tv.text = msg
        }
    )
}