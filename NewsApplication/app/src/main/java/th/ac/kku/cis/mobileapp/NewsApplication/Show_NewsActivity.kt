package th.ac.kku.cis.mobileapp.NewsApplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add__news.*
import kotlinx.android.synthetic.main.activity_show__news.*

class Show_NewsActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show__news)

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

        var name = getIntent().getStringExtra("name")
        var data1 = getIntent().getStringExtra("data")

        text_show_news.text = name
        text_show_d.text = data1

    }
}
