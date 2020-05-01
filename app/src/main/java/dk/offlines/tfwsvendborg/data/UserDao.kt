package dk.offlines.tfwsvendborg.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    // After API
    @Insert
    fun saveUser(user: User)

    @Query("SELECT * FROM users")
    fun getUser(): User

    @Query("DELETE FROM users")
    fun removeUser()
}