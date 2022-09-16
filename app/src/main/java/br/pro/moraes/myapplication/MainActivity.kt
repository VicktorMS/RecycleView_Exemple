package br.pro.moraes.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.moraes.myapplication.databinding.ActivityMainBinding
import br.pro.moraes.myapplication.models.AlunoPresenca
import br.pro.moraes.myapplication.models.AlunoPresencaAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val adapter = AlunoPresencaAdapter()
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
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.fabAdd.setOnClickListener{
            addtoList()
        }
        binding.fabDelete.setOnClickListener{
            removeFromList()
        }
        binding.fabChange.setOnClickListener{
            changePresence()
        }
        binding.fabFilter.setOnClickListener{
            filtar()
        }
    }
    var filtroAtivo = false
    private fun filtar() {
        filtroAtivo = !filtroAtivo
        if (filtroAtivo){
            val listaNova = listaAlunosPresenca.filter {
                item -> item.presente == true
            }
            adapter.submitList(listaNova)
            binding.rvAlunosPresenca.adapter = adapter
        }else{
            atualizaRecycleView()
        }

    }

    private fun changePresence() {
        val posicao = binding.inputPosicao.text.toString().toInt()
        val presencaAtual = listaAlunosPresenca.get(posicao).presente
        listaAlunosPresenca.get(posicao).presente = !presencaAtual
        atualizaRecycleView()
    }

    private fun removeFromList() {
        val posicao = binding.inputPosicao.text.toString().toInt()
        listaAlunosPresenca.removeAt(posicao)
        atualizaRecycleView()
    }


    private fun setupRecyclerView() {
        setupInitialList()
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

    private fun atualizaRecycleView(){
        adapter.submitList(listaAlunosPresenca)
        binding.rvAlunosPresenca.adapter = adapter
    }

    fun addtoList(){
        val nome = binding.inputNome.text.toString()
        val presenca = binding.inputPresenca.toString() == "P"
        listaAlunosPresenca.add( AlunoPresenca(nome, presenca))
    }
}