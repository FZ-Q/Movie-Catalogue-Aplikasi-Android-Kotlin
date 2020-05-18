package com.f_a.project_moca.ui.fragment

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.f_a.project_moca.R
import com.f_a.project_moca.session.SharedPreferences
import com.f_a.project_moca.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import kotlinx.android.synthetic.main.fragment_user.view.*

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {
    lateinit var mView: View
    var fireDatabase : FirebaseDatabase? = null

    private var sharedPreference: SharedPreferences? = null

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_user, container, false)

        val activity = activity as Context

        fireDatabase = FirebaseDatabase.getInstance()
        sharedPreference = SharedPreferences(activity)

//        mDatabase = FirebaseDatabase.getInstance()
//        mDatabaseReference = mDatabase!!.reference!!.child("User")
//        mAuth = FirebaseAuth.getInstance()
//        val mUser = mAuth!!.currentUser

        mView.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            sharedPreference!!.clearSharedPreference()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        account()
        return mView
    }

    private fun account(){
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("User")
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)

        val nama : TextView = mView!!.findViewById(R.id.tv_nama)
        val avatar : ImageView = mView!!.findViewById(R.id.civ_avatar)

        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                nama!!.text = snapshot.child("name").value as String

                val dImg = snapshot.child("avatar").value as String
                val EncodeByte: ByteArray = android.util.Base64.decode(dImg, android.util.Base64.DEFAULT)
                val BM = BitmapFactory.decodeByteArray(
                    EncodeByte, 0,
                    EncodeByte.size
                )
                avatar.setImageBitmap(BM)
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

}
