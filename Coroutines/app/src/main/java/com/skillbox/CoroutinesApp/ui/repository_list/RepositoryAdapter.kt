package com.skillbox.CoroutinesApp.ui.repository_list

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.skillbox.CoroutinesApp.network.Repository

class RepositoryAdapter :
    AsyncListDifferDelegationAdapter<Repository>(repositoryDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RepositoryDelegate())
    }

    class repositoryDiffUtilCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}
