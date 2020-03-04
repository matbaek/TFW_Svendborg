package dk.offlines.tfwsvendborg.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): User

    @Insert
    suspend fun insertUser(user: User)
}