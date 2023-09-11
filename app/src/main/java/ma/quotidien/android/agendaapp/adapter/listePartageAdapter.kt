package ma.quotidien.android.agendaapp.adapter

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeuaz.data.Partage
import com.example.storeuaz.data.Programme
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.R

class listePartageAdapter (var activity: Activity, var owner: androidx.lifecycle.ViewModelStoreOwner) : RecyclerView.Adapter<listePartageAdapter.MyviewHolder>() {

    private var progList= emptyList<Partage>()
    lateinit var mUserViewModel: UserViewModel



    class MyviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id =itemView.findViewById<TextView>(R.id.TextIdPartage)
        var desc=itemView.findViewById<TextView>(R.id.TextNomPartage)
        var layout=itemView.findViewById<CardView>(R.id.CustomRowListPartage)
        val sup=itemView.findViewById<ImageButton>(R.id.effacerPartage)
        val idPart=itemView.findViewById<TextView>(R.id.txtNomEnvoyeur)
        val date=itemView.findViewById<TextView>(R.id.datePartage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val ViewHold=MyviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_partage_adapt,parent,false))
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
        holder.idPart.text="Num partageur : ${current.idEnvoyeur.toString()}"
        holder.sup.setOnClickListener {
            val builder= AlertDialog.Builder(activity)

            builder.setPositiveButton("OUI"){_,_->
                mUserViewModel= ViewModelProvider(owner).get(UserViewModel::class.java)
                mUserViewModel.deletePart(current)
                Toast.makeText(activity,"EFFACER AVEC SUCCES", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }

            builder.setNegativeButton("NON"){_,_ ->
                //dismiss()
            }
            val alert=builder.create()
            alert.setTitle("Voulez vous vraiment supprimer")

            alert.show()
            notifyDataSetChanged()
        }
        holder.layout.setOnClickListener{

        }

    }
    fun setData(user: List<Partage>){
        this.progList=user
        notifyDataSetChanged()
    }
}