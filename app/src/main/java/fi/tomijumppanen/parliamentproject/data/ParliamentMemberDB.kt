package fi.tomijumppanen.parliamentproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.tomijumppanen.parliamentproject.MainActivity

@Database(entities = [ParliamentMember::class], version = 1, exportSchema = false)
abstract class ParliamentMemberDB: RoomDatabase() {
    abstract val parliamentMemberDAO: ParliamentMemberDao
    companion object {
        @Volatile
        private var INSTANCE: ParliamentMemberDB? = null
        fun getInstance(context: Context = MainActivity.appContext): ParliamentMemberDB {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(context, ParliamentMemberDB::class.java, "Parliament_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}