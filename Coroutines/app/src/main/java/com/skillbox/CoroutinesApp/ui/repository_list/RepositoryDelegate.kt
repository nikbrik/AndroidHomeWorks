package com.skillbox.CoroutinesApp.ui.repository_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.CoroutinesApp.databinding.FragmentRepositoryBinding
import com.skillbox.CoroutinesApp.network.Repository

class RepositoryDelegate : AbsListItemAdapterDelegate<Repository, Repository, RepositoryHolder>() {
    override fun isForViewType(
        item: Repository,
        items: MutableList<Repository>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RepositoryHolder {
        return RepositoryHolder(
            FragmentRepositoryBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(
        item: Repository,
        holder: RepositoryHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}
