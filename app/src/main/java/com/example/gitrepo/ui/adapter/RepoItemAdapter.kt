package com.example.gitrepo.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitrepo.R
import com.example.gitrepo.databinding.GitRepoItemBinding
import com.example.gitrepo.model.Item
import dagger.hilt.android.qualifiers.ApplicationContext

class RepoItemAdapter:
    PagingDataAdapter<Item, RepoItemAdapter.RepoViewHolder>(ITEM_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            GitRepoItemBinding.inflate(LayoutInflater.from(parent.context), )
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(currentItem)
        }
    }

    inner class RepoViewHolder(private val binding:GitRepoItemBinding ) :
        RecyclerView.ViewHolder(binding.root) {


        @SuppressLint("SetTextI18n")
        fun bind(item: Item) {
            binding.apply {

                repoName.text ="Repo Name: " +item.name
                size.text = "Repo size: " +item.size.toString()
                loginName.text = "Login name: " + item.owner?.let {
                    it.login.toString()
                }
                if(item.has_wiki!!)
                    itemLayout.setBackgroundResource(R.color.purple_200)
                else
                    itemLayout.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }


    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item) =
                oldItem == newItem
        }
    }
}
