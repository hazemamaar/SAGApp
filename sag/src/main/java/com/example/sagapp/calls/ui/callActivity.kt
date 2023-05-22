package com.example.sagapp.calls.ui

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.content.Intent
import android.net.Uri
import com.example.features.calls.data.AppDatabase
import com.example.features.calls.data.Contact
import com.example.features.calls.data.ContactDao
import com.example.sagapp.R

class callActivity : AppCompatActivity() {
    private lateinit var contactDao: ContactDao
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        val searchView = findViewById<SearchView>(R.id.searchView)
        listView = findViewById(R.id.listView)

        val appDatabase = AppDatabase.getInstance(this)
        contactDao = appDatabase.contactDao()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        listView.adapter = adapter

        cacheContacts(this, "+201283017356")

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchContacts(it)
                }
                return true
            }
        })
    }

    @SuppressLint("Range")
    private fun cacheContacts(context: Context, phoneNumber: String) {
        val contentResolver = context.contentResolver
        val contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        // Start a coroutine on the IO dispatcher
        lifecycleScope.launch(Dispatchers.IO) {
            val cursor = contentResolver.query(contactsUri, null, null, null, null)

            cursor?.use {
                val contactsList = mutableListOf<Contact>()

                while (it.moveToNext()) {
                    val nameColumnIndex =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    val phoneNumberColumnIndex =
                        it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                    val name = it.getString(nameColumnIndex)
                    val phoneNumber = it.getString(phoneNumberColumnIndex)

                    if (name != null) {
                        val contact = Contact(name = name, phoneNumber = phoneNumber)
                        contactsList.add(contact)
                    }
                }

                // Insert contacts into Room database
                contactDao.insertAll(contactsList)

                // Update the list view with the contact names
                val contactNames = contactsList.map { it.name }
                withContext(Dispatchers.Main) {
                    adapter.addAll(contactNames)
                }
            }
        }
    }

    private fun searchContacts(query: String) {
        // Start a coroutine on the IO dispatcher
        lifecycleScope.launch(Dispatchers.IO) {
            val matchedContacts = contactDao.searchContacts("%$query%")

            // Check if the phone number exists in Room database
            val isNumberExist = withContext(Dispatchers.IO) {
                contactDao.checkIfNumberExists(query)
            }

            // Handle the result
            withContext(Dispatchers.Main) {
                if (isNumberExist) {
                    // The phone number exists in Room database
                    // Display matchedContacts
                    println("The phone number exists in Room database")

                    callPhoneNumber(query)
                    // ...
                } else {
                    // The phone number does not exist in Room database
                    // Clear the adapter or display a message indicating no results
                    adapter.clear()
                    println("The phone number doesn't exist in Room database")
                    // ...
                }
            }
        }
    }

    private fun callPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
}
