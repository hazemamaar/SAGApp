package com.example.features.calls.data




import androidx.room.*

@Dao
interface ContactDao {
    @Insert
    fun insert(contact: Contact)

    @Insert
    fun insertAll(contacts: List<Contact>)

    @Update
    fun update(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contact>

    @Query("SELECT * FROM contacts WHERE name LIKE :searchQuery OR phoneNumber LIKE :searchQuery")
    fun searchContacts(searchQuery: String): List<Contact>

    @Query("SELECT EXISTS(SELECT 1 FROM contacts WHERE phoneNumber = :phoneNumber LIMIT 1)")
    suspend fun checkIfNumberExists(phoneNumber: String): Boolean


}
