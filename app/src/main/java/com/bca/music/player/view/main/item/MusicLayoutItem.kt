package com.bca.music.player.view.main.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.ext.loadImage
import com.bca.music.player.databinding.ItemMusicLayoutBinding
import com.mikepenz.fastadapter.FastAdapter

import com.mikepenz.fastadapter.binding.AbstractBindingItem
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.select.SelectExtension

class MusicLayoutItem(val item: SearchResultItem) : AbstractBindingItem<ItemMusicLayoutBinding>() {

    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemMusicLayoutBinding {
      return  ItemMusicLayoutBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: ItemMusicLayoutBinding, payloads: List<Any>) {
        binding.run {
            albumCover.loadImage(item.artworkUrl100.orEmpty())
            songTitle.text = item.trackName
            artistName.text = item.artistName
            albumName.text = item.collectionName
            lottieAudio.isVisible = isSelected
        }
    }

}