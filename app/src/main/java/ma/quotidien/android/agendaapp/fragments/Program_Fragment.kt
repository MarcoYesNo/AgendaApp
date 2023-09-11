package ma.quotidien.android.agendaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeuaz.data.Partage
import com.example.storeuaz.data.UserViewModel
import ma.quotidien.android.agendaapp.R
import ma.quotidien.android.agendaapp.adapter.listeAdapter
import ma.quotidien.android.agendaapp.adapter.listePartageAdapter
import ma.quotidien.android.agendaapp.idLogin

class Program_Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_program_, container, false)

        val recyclerView=view.findViewById<RecyclerView>(R.id.recyclerPartage)

        val adpater= listePartageAdapter(requireActivity(),this)
        recyclerView.adapter=adpater
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        val listePart= emptyList<Partage>().toMutableList()
        var mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllPartage.observe(viewLifecycleOwner, Observer { user->
            for (item in user){
                if(item.idRecepteur== idLogin){
                    listePart+=item
                }
            }
            adpater.setData(listePart)
        })

        return view
    }
}