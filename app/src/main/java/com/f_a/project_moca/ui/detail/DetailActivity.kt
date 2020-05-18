package com.f_a.project_moca.ui.detail

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.f_a.project_moca.R
import com.f_a.project_moca.UserMainActivity
import com.f_a.project_moca.model.MFavourite
import com.f_a.project_moca.static.Static
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : AppCompatActivity() {
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    lateinit var DB: DatabaseReference
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("User")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        val btn_fav: Button = findViewById(R.id.btnFav)

        mDatabase = FirebaseDatabase.getInstance()
        val type = intent.getStringExtra("type")
        mDatabaseReference = if (type == "Movie") {
            mDatabase!!.reference.child(Static.FB_DataMovie)
        } else {
            mDatabase!!.reference.child(Static.FB_DataTVShow)
        }

        val id = intent.getStringExtra("idData")
        val mUserReference = mDatabaseReference!!.child(id)

        btn_fav.setOnClickListener {
            mUserReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    DB = FirebaseDatabase.getInstance().reference
                    val MF = MFavourite.create()

                    MF.title = snapshot.child("title").value as String
                    MF.genre = snapshot.child("genre").value as String
                    MF.release = snapshot.child("release").value as String
                    MF.rating = snapshot.child("rating").value as String
                    MF.deskripsi = snapshot.child("deskripsi").value as String
                    MF.poster = snapshot.child("poster").value as String

                    MF.id = intent.getStringExtra("idData")
                    val newData =
                        DB.child("User").child(mUser!!.uid).child(Static.FB_DataFavourite).child(intent.getStringExtra("idData"))

                    newData.setValue(MF)
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            val go = Intent(this, UserMainActivity::class.java)
            startActivity(go)
            Toast.makeText(this,"Add to Favorite!!", Toast.LENGTH_SHORT).show()
        }

        inisialisasi()

    }

    private fun inisialisasi() {
        mDatabase = FirebaseDatabase.getInstance()
        val type = intent.getStringExtra("type")
        mDatabaseReference = if (type == "Movie") {
            mDatabase!!.reference.child(Static.FB_DataMovie)
        } else {
            mDatabase!!.reference.child(Static.FB_DataTVShow)
        }
    }

    override fun onStart() {
        super.onStart()

        val id = intent.getStringExtra("idData")
        val mUserReference = mDatabaseReference!!.child(id)

        val title: TextView = findViewById(R.id.tv_title)
        val genre: TextView = findViewById(R.id.tv_genre)
        val release: TextView = findViewById(R.id.tv_release)
        val deskripsi: TextView = findViewById(R.id.tv_description)
        val poster: ImageView = findViewById(R.id.iv_poster)
        val background: ImageView = findViewById(R.id.iv_background)
        val rate_bar: RatingBar = findViewById(R.id.rb_star)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                title.text = snapshot.child("title").value as String
                genre.text = snapshot.child("genre").value as String
                release.text = snapshot.child("release").value as String
                deskripsi.text = snapshot.child("deskripsi").value as String

                val dImg = snapshot.child("poster").value as String
                val EncodeByte: ByteArray =
                    android.util.Base64.decode(dImg, android.util.Base64.DEFAULT)
                val BM = BitmapFactory.decodeByteArray(
                    EncodeByte, 0,
                    EncodeByte.size
                )
                poster.setImageBitmap(BM)
                background.setImageBitmap(BM)
                rate_bar.rating = (snapshot.child("rating").value as String).toFloat()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }
}

