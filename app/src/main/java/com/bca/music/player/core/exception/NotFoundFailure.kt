package com.bca.music.player.core.exception

import com.bca.music.player.core.exception.Failure.FeatureFailure

class NotFoundFailure {

    class DataNotExist() : FeatureFailure()

    class DataPromotionNotExist() : FeatureFailure()

    class EmptyListLoadMore() : FeatureFailure()

    class UserProfileNotComplete(val type: String) : FeatureFailure()
}
