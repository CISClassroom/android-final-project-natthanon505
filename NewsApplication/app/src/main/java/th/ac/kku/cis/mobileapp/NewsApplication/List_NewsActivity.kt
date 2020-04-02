package th.ac.kku.cis.mobileapp.NewsApplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list__news.*

class List_NewsActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: ToDoItemAdapter
    private var listViewItems: ListView? = null
    var toDoItemList: MutableList<AddNews>? = null

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list__news)
        val NameSetting: TextView = findViewById(R.id.Username)
        val Profile: ImageView = findViewById(R.id.imageView2)
        val Email: TextView = findViewById(R.id.Email)
        auth = FirebaseAuth.getInstance()
        auth.currentUser!!.email
        val xx: Uri? = auth.currentUser!!.photoUrl
        NameSetting.text = auth.currentUser!!.displayName.toString()
        Picasso.get().load(xx).into(Profile)
        Email.text = auth.currentUser!!.email
        auth.currentUser!!.email


        val goadd: Button = findViewById(R.id.btn_news_add)
        goadd.setOnClickListener {
            var i = Intent(this, Add_NewsActivity::class.java)
            //i.putExtra("name",name)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

        listViewItems = findViewById<View>(R.id.list_news) as ListView
        toDoItemList = mutableListOf<AddNews>()
        adapter = ToDoItemAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("News").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.iterator()
//                var name = getIntent().getStringExtra("name")
                // Check if current database contains any collection
                if (items.hasNext()) {
                    while (items.hasNext()) {
                        val toDoListindex = items.next()
                        val map = toDoListindex.getValue() as HashMap<String, Any>
//                        if (map.get("studentss") == name) {
                            // add data to object
                            val todoItem = AddNews.create()
                            todoItem.NameNews = map.get("nameNews") as String?
                            todoItem.Data = map.get("data") as String?
                            toDoItemList!!.add(todoItem);
                            adapter.notifyDataSetChanged()
//                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
        list_news.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as AddNews
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Show_NewsActivity::class.java)
            intent.putExtra("name", selectedItem.NameNews)
            intent.putExtra("data", selectedItem.Data)
            startActivity(intent)
        }
    }
}
