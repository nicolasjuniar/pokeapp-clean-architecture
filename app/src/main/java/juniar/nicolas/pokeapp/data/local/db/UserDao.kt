package juniar.nicolas.pokeapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity): Long

    @Query("select username from users where username = :username")
    suspend fun getUsername(username: String): String?

    @Query("select username from users where username = :username and password = :password")
    suspend fun login(username: String, password: String): String?
}