package com.example.gitrepo.repository

import androidx.paging.PagingSource
import com.example.gitrepo.gitapi.MyApiService
import com.example.gitrepo.model.Item
import com.example.gitrepo.util.Constants.DEFAULT_QUERY
import com.example.gitrepo.util.Constants.START_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepoPagingSource @Inject constructor(private val api: MyApiService, private val query:String ) :
    PagingSource<Int, Item>() {
    companion object{

    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        val position = params.key ?: START_INDEX
        val apiQuery = query
        return try {
            val response = api.getRepos(apiQuery, position, params.loadSize)
            val repos = response.items

            LoadResult.Page(
                data = repos,
                prevKey = if (position == START_INDEX) null else position - 1,
                nextKey = if (repos?.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}