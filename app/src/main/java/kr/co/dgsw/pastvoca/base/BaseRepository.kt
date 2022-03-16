package kr.co.dgsw.pastvoca.base

abstract class BaseRepository<D, E> {
    protected abstract val dao: D

    abstract suspend fun insert(obj: E)

    abstract suspend fun update(obj: E)

    abstract suspend fun delete(obj: E)
}