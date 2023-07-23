package com.bca.music.player.view.main.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bca.music.player.core.domain.model.SearchResultItem
import com.bca.music.player.core.ext.loadImage
import com.bca.music.player.databinding.MusicItemLayoutBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MusicLayoutItem(private val item: SearchResultItem) : AbstractBindingItem<MusicItemLayoutBinding>() {

    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): MusicItemLayoutBinding {
      return  MusicItemLayoutBinding.inflate(inflater,parent,false)
    }

    override fun bindView(binding: MusicItemLayoutBinding, payloads: List<Any>) {
        binding.run {
            albumCover.loadImage(item.artworkUrl100.orEmpty())
            songTitle.text = item.trackName
            artistName.text = item.artistName
            albumName.text = item.collectionName
        }
    }
}