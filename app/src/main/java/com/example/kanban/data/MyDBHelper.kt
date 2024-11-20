package com.example.kanban.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kanban.model.KanbanItem

class MyDBHelper(context: Context?) : SQLiteOpenHelper(context,  "KANBANDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // CREATING DB
        db?.execSQL("CREATE TABLE KANBANDB(ID INTEGER PRIMARY KEY AUTOINCREMENT, CONTENT TEXT, STATE INTEGER)")
        // INSERTING EXAMPLES for DB, 1 to do, 2 doing, 3 done
        db?.execSQL("INSERT INTO KANBANDB(CONTENT, STATE) VALUES('Get plane tickets to Rome', 3)")
        db?.execSQL("INSERT INTO KANBANDB(CONTENT, STATE) VALUES('Book the hotel for three nights',2)")
        db?.execSQL("INSERT INTO KANBANDB(CONTENT, STATE) VALUES('Arrange transfer to airport',1)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS KANBANDB")
        onCreate(db)
    }

    // add new list item into the to do list
    // by default each new list item becomes TO DO item, can be changed later with update
    fun addKanbanItem(content: String){
        val values = ContentValues().apply {
            put("CONTENT", content)
            put("STATE", 1)
        }
        writableDatabase.insert("KANBANDB", null, values)
    }

    // updates state of to do list item, content cannot be updated
    // ONLY STATE 1 and 2 can be changed! STATE 3 can only be deleted
    // this method will be used to move kanban items between lists, only forward
    fun updateKanbanItem(originalState: Int, itemId: Int): Int {
        val values = ContentValues().apply {
            if (originalState > 3) {
                put("STATE", originalState + 1)
            }
        }
        val selection = "id=?"
        val selectionArgs = arrayOf(itemId.toString())
        return writableDatabase.update("KANBANDB", values, selection, selectionArgs)
    }

    // deletes one item from the tododb
    fun deleteKanbanItem(itemId: Int): Int {
        val selection = "id=?"
        val selectionArgs = arrayOf(itemId.toString())

        return writableDatabase.delete("KANBANDB", selection, selectionArgs)
    }

    // read data from the DB
    fun readKanbanItems(): MutableList<KanbanItem> {
        val dataList = mutableListOf<KanbanItem>()
        val cursor : Cursor = readableDatabase.query("KANBANDB", null, null, null, null, null, null)
        with(cursor){
            while(moveToNext()){
                val id = getInt(getColumnIndexOrThrow("ID"))
                val content = getString(getColumnIndexOrThrow("CONTENT"))
                val state = getInt(getColumnIndexOrThrow("STATE"))

                dataList.add(KanbanItem(id, content, state))
            }
        }
        cursor.close()
        return dataList
    }

    // read only the values of ONE list
    fun readKanbanItemsByList(stateId: Int): MutableList<KanbanItem> {
        val dataList = mutableListOf<KanbanItem>()
        val cursor : Cursor = readableDatabase.query("KANBANDB", null, "state=?",
            arrayOf(stateId.toString()), null, null, null)
        with(cursor){
            while(moveToNext()){
                val id = getInt(getColumnIndexOrThrow("ID"))
                val content = getString(getColumnIndexOrThrow("CONTENT"))
                val state = getInt(getColumnIndexOrThrow("STATE"))

                dataList.add(KanbanItem(id, content, state))
            }
        }
        cursor.close()
        return dataList
    }

}

/*
* SRC:
* https://medium.com/@myofficework000/utilizing-sqlite-database-in-android-2c6c81a33c9f
* */