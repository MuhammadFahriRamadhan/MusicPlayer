package com.bca.music.player.view.main

import androidx.fragment.app.FragmentManager
import com.bca.music.player.view.main.bottomsheet.MusicPlayerBottomSheet

class MainRouter {

    fun openBottomSheetMusicPlayer(
        source: FragmentManager,
        audioUrl : String,
        onDismissCallback: () -> Unit
    ) {
        MusicPlayerBottomSheet.newInstance(audioUrl = audioUrl,onDismissCallback).show(source, "music_player_bottom_sheet")
    }

}