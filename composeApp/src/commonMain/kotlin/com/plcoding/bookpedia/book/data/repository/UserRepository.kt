package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.database.UserDao
import com.plcoding.bookpedia.book.data.mappers.toUser
import com.plcoding.bookpedia.book.domaine.DataError
import com.plcoding.bookpedia.book.domaine.User
import com.plcoding.bookpedia.book.domaine.UserRepo
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result

class UserRepository(
    private val localDatabase: UserDao
) : UserRepo {
    override suspend fun register(newUser: User): EmptyResult<DataError.Local> {

        val users = localDatabase.getAllUser()
        val isExist = users.map {
                it.email == newUser.email&&it.password==newUser.password
        }
        if (isExist.equals(true)) {
            return Result.Error(DataError.Local.ALREADY_REGISTER)

        } else {
            newUser.isLogin=true
            localDatabase.upsert(newUser.toUser())
            return Result.Success(Unit)
        }
    }

    override suspend fun login(email: String, password: String):Result<User?,DataError.Local> {

        val user=localDatabase.login(email)?.toUser()
        return if (user==null){
            Result.Error(DataError.Local.NOT_FOUND_USER)
        }else{
            localDatabase.updateUserLogin(email)
            Result.Success(user)
        }

    }

    override suspend fun getLoginUser(): Result<User?, DataError.Local> {
      val loggedInUser=localDatabase.selectUserLogin()?.toUser()
       return Result.Success(loggedInUser)
    }
}