package com.bca.music.player.core.helper

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

object RxBus {

    private val subject = PublishSubject.create<Any>()
    //create event with intial value
    private val subjectBehavior = BehaviorSubject.create<Any>()

    fun post(event: Any) {
        subject.onNext(event)
    }

    fun postBehavior(event: Any){
        subjectBehavior.onNext(event)
    }

    fun getBehaviorEvent() : Observable<Any>{
        return subjectBehavior
    }

    fun getEvents(): Observable<Any> {
        return subject
    }
}
