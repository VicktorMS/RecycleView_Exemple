package br.pro.moraes.myapplication.models

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.pro.moraes.myapplication.databinding.AlunosPresencaItemListBinding

class AlunoPresencaAdapter: ListAdapter<AlunoPresenca, AlunoPresencaAdapter.ViewHolder>(AlunoPresencaDiffCallBack()){

    class ViewHolder private constructor(val binding: AlunosPresencaItemListBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(item: AlunoPresenca){
                binding.tvNomeAluno.text = item.nome
                if (item.presente == true){
                    binding.tvPresenca.text = "P"
                    binding.tvPresenca.setBackgroundColor(Color.parseColor("#008800"))
                }else{
                    binding.tvPresenca.text = "F"
                    binding.tvPresenca.setBackgroundColor(Color.parseColor("#880000"))
                }
            }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlunosPresencaItemListBinding.inflate(
                    layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


}

class AlunoPresencaDiffCallBack: DiffUtil.ItemCallback<AlunoPresenca>(){

    override fun areItemsTheSame(oldItem: AlunoPresenca, newItem: AlunoPresenca): Boolean {
        return oldItem.nome == newItem.nome
    }
    override fun areContentsTheSame(oldItem: AlunoPresenca, newItem: AlunoPresenca): Boolean {
        return oldItem == newItem
    }


}