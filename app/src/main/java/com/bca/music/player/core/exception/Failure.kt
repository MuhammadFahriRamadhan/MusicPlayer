package com.bca.music.player.core.exception



sealed class Failure {
    object NetworkConnection : Failure()
    data class ServerError(val message: String) : Failure()
    object ExpiredSession: Failure()
    data class NotFound(val message : String) : Failure()


    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()
}
