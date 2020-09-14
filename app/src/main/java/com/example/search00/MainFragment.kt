package com.example.search00


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.search00.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.concurrent.thread


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler()

        // メインスレッドだと動かない
        thread {
            try {
                val response = APIClient.fetchReposList()
                val firstRepos = response.body()!![0]

                // handlerでUIを動かす
                handler.post(Runnable {
                    name_text.text = firstRepos.name
                    description_text.text = firstRepos.description
                    language_text.text = firstRepos.language
                    url_text.text = firstRepos.url
                })

                Log.d("retrofit", "Repository ID" + response.body())
            } catch (e: Exception) {
                Log.w("retrofit", "fetchReposList :" + e)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton: Button = view.findViewById(R.id.search_button)
        searchButton.setOnClickListener{ v -> clickButton() }
    }

    // ボタン押下時に文章の表示・非表示を切り替える
    private fun clickButton() {
        when(binding.isShow) {
            // 初回はnull
            null -> binding.isShow = true
            true -> binding.isShow = false
            false -> binding.isShow = true
        }
    }
}