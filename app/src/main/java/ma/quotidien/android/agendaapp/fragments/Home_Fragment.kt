package ma.quotidien.android.agendaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeuaz.data.Programme
import com.example.storeuaz.data.User
import com.example.storeuaz.data.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ma.quotidien.android.agendaapp.R
import ma.quotidien.android.agendaapp.adapter.listeAdapter
import ma.quotidien.android.agendaapp.idLogin

class Home_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home_, container, false)
        val btn=view.findViewById<FloatingActionButton>(R.id.BtnAjouterProg)

        val recyclerView=view.findViewById<RecyclerView>(R.id.recycleListHome)

        val listUser= emptyList<User>().toMutableList()
        try {
            var mUserViewModel1 = ViewModelProvider(this).get(UserViewModel::class.java)
            mUserViewModel1.readAllData.observe(viewLifecycleOwner) { user ->
                for (item in user){
                    if(item.id!= idLogin){
                        listUser += user
                    }
                }

            }
        }
        catch (e:java.lang.Exception){
            Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()}



        val adpater= listeAdapter(requireActivity(),this,listUser)
        recyclerView.adapter=adpater
        recyclerView.layoutManager= LinearLayoutManager(requireContext())

        val listeProg= emptyList<Programme>().toMutableList()
        var mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllProgramme.observe(viewLifecycleOwner) { user ->
            for (item in user) {
                if (item.idUser == idLogin) {
                    listeProg += item
                }
            }
            adpater.setData(listeProg)
        }

        btn.setOnClickListener {
            view.findNavController().navigate(R.id.action_home_Fragment_to_ajouter_Fragment)
        }


        return view
    }

}
