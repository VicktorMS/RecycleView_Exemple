package br.pro.moraes.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.moraes.myapplication.databinding.ActivityMainBinding
import br.pro.moraes.myapplication.models.AlunoPresenca
import br.pro.moraes.myapplication.models.AlunoPresencaAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val  listaAlunosPresenca = mutableListOf<AlunoPresenca>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setup()
    }

    private fun setup() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        setupInitialList()

        val adapter = AlunoPresencaAdapter()
        binding.rvAlunosPresenca.adapter = adapter
        binding.rvAlunosPresenca.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)
        adapter.submitList(listaAlunosPresenca)
    }

    private fun setupInitialList() {
        listaAlunosPresenca.add(AlunoPresenca("Victor", true))
        listaAlunosPresenca.add(AlunoPresenca("Ana Luisa", false))
        listaAlunosPresenca.add(AlunoPresenca("Moraes", true))
    }
}