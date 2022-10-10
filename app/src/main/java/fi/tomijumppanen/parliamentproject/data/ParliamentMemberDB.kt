/**
Name:  Tomi Jumppanen
ID:    2113590
Last update:  9.10.2022
Description:
This class idea is to execute database creation
 */

package fi.tomijumppanen.parliamentproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fi.tomijumppanen.parliamentproject.MainActivity

@Database(entities = [ParliamentMember::class, ParliamentMemberExtras::class, ParliamentMemberComments::class], version = 5, exportSchema = false)
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