package ma.quotidien.android.agendaapp.Room.data

import androidx.room.TypeConverter
import java.util.*

class Typeconverter {
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return date?.time
        }
    }
