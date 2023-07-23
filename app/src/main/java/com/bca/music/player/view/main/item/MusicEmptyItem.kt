package com.bca.music.player.view.main.item

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bca.music.player.databinding.ItemEmptyMusicBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class MusicEmptyItem : AbstractBindingItem<ItemEmptyMusicBinding>() {
    override val type: Int
        get() = hashCode()

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemEmptyMusicBinding {
        return ItemEmptyMusicBinding.inflate(inflater,parent,false)
    }
}