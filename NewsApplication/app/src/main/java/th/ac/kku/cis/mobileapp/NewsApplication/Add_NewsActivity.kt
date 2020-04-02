package th.ac.kku.cis.mobileapp.NewsApplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add__news.*

class Add_NewsActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__news)

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

        mDatabase = FirebaseDatabase.getInstance().reference
        btn_add.setOnClickListener {
            AddData("String")
        }
    }
    fun AddData(data: String) {
//        var name = getIntent().getStringExtra("name")
        var newData: AddNews = AddNews.create()
        val obj = mDatabase.child("News").push()

        newData.NameNews = editNameNews.text.toString()
        newData.Data = editData.text.toString()

        newData.Id = obj.key
        obj.setValue(newData)

        Toast.makeText(applicationContext, "Note save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, List_NewsActivity::class.java)
        startActivity(i)
        finish()
    }
}
