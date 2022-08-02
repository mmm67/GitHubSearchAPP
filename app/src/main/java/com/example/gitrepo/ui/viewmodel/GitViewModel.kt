package com.example.gitrepo.ui.viewmodel

import android.content.ClipData
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.gitrepo.repository.GitRepo
import com.example.gitrepo.util.Constants.DEFAULT_QUERY

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GitViewModel @Inject constructor(private val repo: GitRepo):ViewModel() {

    private val liveQuery = MutableLiveData(DEFAULT_QUERY)

    val items = liveQuery.switchMap { queryString ->
        repo.getRepos(queryString).cachedIn(viewModelScope)
    }

    fun setLiveQuery(query: String) {
        this.liveQuery.value = query
    }
}