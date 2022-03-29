package co.kr.dgsw.searchvoca.repository.remote

abstract class BaseRemote<SV> {
    protected abstract val service: SV
}