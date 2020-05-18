package com.f_a.project_moca.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.f_a.project_moca.R
import com.f_a.project_moca.adapter.AFavourite
import com.f_a.project_moca.model.MFavourite
import com.f_a.project_moca.session.SharedPreferences
import com.f_a.project_moca.static.Static
import com.f_a.project_moca.ui.detail.DetailFavActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class FavouriteFragment : Fragment() {

    lateinit var mView: View
    var fireDatabase : FirebaseDatabase? = null

    private var sharedPreference: SharedPreferences? = null

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    lateinit var aF: AFavourite
    var alFavourite: ArrayList<MFavourite>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_favourite, container, false)

        val activity = activity as Context

        fireDatabase = FirebaseDatabase.getInstance()
        sharedPreference = SharedPreferences(activity)

        var recyclerView: RecyclerView = mView.findViewById(R.id.rv_favourite)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layoutManager

        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("User")
        mAuth = FirebaseAuth.getInstance()
        val mUser = mAuth!!.currentUser

        alFavourite = arrayListOf<MFavourite>()
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(mUser!!.uid).child(Static.FB_DataFavourite)

        mDatabaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    alFavourite!!.clear()
                    for (h in p0.children){
                        val bal = h.getValue(MFavourite::class.java)
                        alFavourite?.add(bal!!)
                    }
                }
                aF = AFavourite(activity,alFavourite as ArrayList<MFavourite>)
                recyclerView?.setAdapter(aF)
                aF.onItemClick = { MFavourite ->
                    val intent = Intent(activity, DetailFavActivity::class.java)
                    intent.putExtra("idData", MFavourite.id)
                    startActivity(intent)}
            }
        })

        return mView
    }
}
