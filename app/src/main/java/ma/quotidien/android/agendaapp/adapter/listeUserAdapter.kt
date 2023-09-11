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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeuaz.data.Partage
import com.example.storeuaz.data.Programme
import com.example.storeuaz.data.User
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.R
import ma.quotidien.android.agendaapp.idLogin

class listeUserAdapter (var activity: Activity, var owner: androidx.lifecycle.ViewModelStoreOwner,var prog:Programme) : RecyclerView.Adapter<listeUserAdapter.MyviewHolder>() {

    private var progList= emptyList<User>()
    lateinit var mUserViewModel: UserViewModel



    class MyviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var id =itemView.findViewById<TextView>(R.id.TextIdUser)
        var desc=itemView.findViewById<TextView>(R.id.TextNomUser)
        var layout=itemView.findViewById<CardView>(R.id.CustomRowListUser)
        val sup=itemView.findViewById<ImageButton>(R.id.btnSend)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val ViewHold=MyviewHolder(LayoutInflater.from(parent.context).inflate(R.layout.liste_user_adapter,parent,false))
        return  ViewHold
    }

    override fun getItemCount(): Int {
        return progList.size
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        val current=progList[position]
        holder.id.text = current.id.toString()
        holder.desc.text=current.pseudo

        holder.sup.setOnClickListener {
            val builder= AlertDialog.Builder(activity)

            builder.setPositiveButton("OUI"){_,_->
                val part=Partage(0,current.id, prog.idUser,prog.content,prog.date)

                mUserViewModel= ViewModelProvider(owner).get(UserViewModel::class.java)
                mUserViewModel.addPart(part)
                Toast.makeText(activity,"ENVOYER AVEC SUCCES", Toast.LENGTH_SHORT).show()
                notifyDataSetChanged()
            }

            builder.setNegativeButton("NON"){_,_ ->
                //dismiss()
            }
            val alert=builder.create()
            alert.setTitle("Voulez vous vraiment envoyer?")

            alert.show()
        }
        holder.layout.setOnClickListener{

        }

    }
    fun setData(user: List<User>){
        this.progList=user
        notifyDataSetChanged()
    }
}