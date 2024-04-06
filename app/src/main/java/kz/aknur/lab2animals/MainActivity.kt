package kz.aknur.lab2animals

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kz.aknur.lab2animals.adapter.AnimalsAdapter
import kz.aknur.lab2animals.api.RetrofitObj
import kz.aknur.lab2animals.databinding.ActivityMainBinding
import kz.aknur.lab2animals.model.Animal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = AnimalsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.setOnClickListener {
            val query = binding.searchInput.text.toString()
            search(query)
        }

        binding.animalRecycler.adapter = adapter
        binding.animalRecycler.layoutManager = LinearLayoutManager(this)


    }

    private fun search(query: String) {
        RetrofitObj.service.getAnimalsByName(query).enqueue(object :
            Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                if (response.isSuccessful) {
                    adapter.submitList(response.body())
                }
            }
            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                Log.e("Network error", t.message.toString())
            }

        })
    }
}