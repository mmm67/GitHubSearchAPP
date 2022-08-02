package com.example.gitrepo.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gitrepo.R
import com.example.gitrepo.databinding.ActivityGitRepoBinding
import com.example.gitrepo.ui.adapter.RepoItemAdapter
import com.example.gitrepo.ui.viewmodel.GitViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GitRepActivity : AppCompatActivity() {

    private val gitViewModel by viewModels<GitViewModel>()
    private lateinit var  binding:ActivityGitRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RepoItemAdapter()
        binding.apply {
            itemsRcv.apply {
                this.adapter = adapter
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@GitRepActivity)
                addItemDecoration(
                    DividerItemDecoration(
                        this.context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
        gitViewModel.items.observe(this@GitRepActivity) { item ->
            adapter.submitData(this.lifecycle, item)

        }
        binding.searchEditText.editText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val query = binding.searchEditText.editText!!.text.toString()
                gitViewModel.setLiveQuery(query)
                hideSoftKeyboard(this@GitRepActivity, binding.searchEditText)

                binding.searchEditText.editText?.let {
                    it.clearFocus()
                    it.text.clear()
                }

            }
            true
        }
        binding.searchEditText.clearFocus()

    }

    companion object {
        fun hideSoftKeyboard(activity: Activity, view: View) {
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.applicationWindowToken, 0)
        }
    }
}