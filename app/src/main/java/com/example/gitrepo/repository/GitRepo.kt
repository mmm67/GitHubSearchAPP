package com.example.gitrepo.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gitrepo.gitapi.MyApiService
import com.example.gitrepo.util.Constants
import javax.inject.Inject

class GitRepo @Inject constructor(private val gitApi: MyApiService) {

    fun getRepos(query: String = Constants.DEFAULT_QUERY) =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { RepoPagingSource(gitApi, query) }
        ).liveData
}