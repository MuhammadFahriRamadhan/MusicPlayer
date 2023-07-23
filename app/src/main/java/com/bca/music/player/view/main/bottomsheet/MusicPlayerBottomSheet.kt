package com.bca.music.player.view.main.bottomsheet

import android.os.Bundle
import android.view.View
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bca.music.player.core.base.BaseBottomSheet
import com.bca.music.player.databinding.BottomsheetMusicPlayerBinding


class MusicPlayerBottomSheet(
    private val audioUrl: String,
    private val onDismissCallback: () -> Unit
) : BaseBottomSheet<BottomsheetMusicPlayerBinding>(){

    private lateinit var player : ExoPlayer

    override fun getUiBinding(): BottomsheetMusicPlayerBinding {
        return BottomsheetMusicPlayerBinding.inflate(cloneLayoutInflater)
    }

    override fun onFirstLaunch(view: View, savedInstanceState: Bundle?) {
        initializePlayer();
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        viewBinding?.playerView?.player = player
        // Build the media item.
        val mediaItem = MediaItem.fromUri(audioUrl)
        // Set the media item to be played.
        player.setMediaItem(mediaItem)
        // Prepare the player.
        player.prepare()
        // Start the playback.
        player.play()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDismissCallback.invoke()
        player.stop()
    }

    companion object {
        fun newInstance(
            audioUrl: String,
            onDismissCallback: () -> Unit
        ): MusicPlayerBottomSheet {
            return MusicPlayerBottomSheet(audioUrl,onDismissCallback)
        }
    }

}