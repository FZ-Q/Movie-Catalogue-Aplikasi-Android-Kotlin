package com.f_a.project_moca.ui.detail

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.f_a.project_moca.R
import com.f_a.project_moca.UserMainActivity
import com.f_a.project_moca.static.Static
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailFavActivity : AppCompatActivity() {
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_fav)

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("User")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser
        val btn_fav: Button = findViewById(R.id.btnFav)

        btn_fav.setOnClickListener {
            val id = intent.getStringExtra("idData")
            mDatabaseReference!!.child(mUser!!.uid).child(Static.FB_DataFavourite).child(id).removeValue()
            Toast.makeText(this,"Un Favorite!!", Toast.LENGTH_SHORT).show()
            val back = Intent(this, UserMainActivity::class.java)
            back.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(back)
        }

        inisialisasi()
    }

    private fun inisialisasi() {
        mDatabase = FirebaseDatabase.getInstance()
        mDatabase!!.reference.child("User")
    }

    override fun onStart() {
        super.onStart()

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("User")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser

        val id = intent.getStringExtra("idData")
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid).child(Static.FB_DataFavourite).child(id)

        val title: TextView = findViewById(R.id.tv_title)
        val genre: TextView = findViewById(R.id.tv_genre)
        val release: TextView = findViewById(R.id.tv_release)
        val deskripsi: TextView = findViewById(R.id.tv_description)
        val poster: ImageView = findViewById(R.id.iv_poster)
        val background: ImageView = findViewById(R.id.iv_background)
        val rate_bar: RatingBar = findViewById(R.id.rb_star);

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                title.text = snapshot.child("title").value as String?
                genre.text = snapshot.child("genre").value as String?
                release.text = snapshot.child("release").value as String?
                deskripsi.text = snapshot.child("deskripsi").value as String?

                val dImg = snapshot.child("poster").value as String?
                if(dImg != null){
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

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}
