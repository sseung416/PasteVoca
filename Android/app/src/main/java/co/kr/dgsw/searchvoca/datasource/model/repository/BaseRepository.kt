package co.kr.dgsw.searchvoca.datasource.model.repository

abstract class BaseRepository<D, E> {
    protected abstract val dao: D

    abstract suspend fun insert(obj: E)

    abstract suspend fun update(obj: E)

    abstract suspend fun delete(obj: E)
}