package ma.quotidien.android.agendaapp.adapter

import android.app.Activity
import android.app.AlertDialog
import android.arch.lifecycle.ViewModelStoreOwner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.storeuaz.data.Programme
import com.example.storeuaz.data.User
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.R
import ma.quotidien.android.agendaapp.idLogin

class listeAdapter(var activity:Activity, var owner: androidx.lifecycle.ViewModelStoreOwner,var listUser:List<User>) : RecyclerView.Adapter<listeAdapter.MyviewHolder>() {

    private var progList= emptyList<Programme>()
    lateinit var mUserViewModel:UserViewModel



    class MyviewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        var id =itemView.findViewById<TextView>(R.id.TextId)
        var desc=itemView.findViewById<TextView>(R.id.TextNom)
        var layout=itemView.findViewById<CardView>(R.id.CustomRowList)
        val sup=itemView.findViewById<ImageButton>(R.id.effacerUser)
        val date=itemView.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val ViewHold=MyviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.liste_adapter,parent,false))
        return  ViewHold
    }

    override fun getItemCount(): Int {

        return progList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val current=progList[position]
        holder.id.text = current.id.toString()
        holder.desc.text=current.content
        holder.date.text= current.date?.year.toString()+"/"+current.date?.month+"/"+current.date?.date+" : "+current.date?.hours+"h"+current.date?.minutes
        holder.sup.setOnClickListener {
            val builder= AlertDialog.Builder(activity)

            builder.setPositiveButton("OUI"){_,_->
                mUserViewModel= ViewModelProvider(owner).get(UserViewModel::class.java)
                mUserViewModel.deleteProg(current)
                Toast.makeText(activity,"EFFACER AVEC SUCCES", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }

            builder.setNegativeButton("NON"){_,_ ->
                //dismiss()
            }
            val alert=builder.create()
            alert.setTitle("Voulez vous vraiment supprimer")

            alert.show()
        }
        holder.layout.setOnClickListener{
            val builder = AlertDialog.Builder(activity)

            val alert = builder.create()

            alert.setTitle("Envoie de la tache")

            //inflate
            val viewDialog=LayoutInflater.from(activity).inflate(R.layout.custom_dlg_user,null)

            val recyclerTest=viewDialog.findViewById<RecyclerView>(R.id.RecyclerListUser)

            val adpater= listeUserAdapter(activity,owner,current)
            recyclerTest.adapter=adpater
            recyclerTest.layoutManager= LinearLayoutManager(activity.applicationContext)
            adpater.setData(listUser)


            alert.setView(viewDialog)
            alert.show()
        }

    }
    fun setData(user: List<Programme>){
        this.progList=user
        notifyDataSetChanged()
    }
}