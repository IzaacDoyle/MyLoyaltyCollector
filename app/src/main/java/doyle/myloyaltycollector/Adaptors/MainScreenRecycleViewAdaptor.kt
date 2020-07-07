package doyle.myloyaltycollector.Adaptors

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doyle.myloyaltycollector.Card_handler_Activity
import doyle.myloyaltycollector.R
import doyle.myloyaltycollector.Storeage.Card
import kotlinx.android.synthetic.main.cardview_card.view.*

private const val Card_TypeDesc: Int =  0
private const val Card_typeImage: Int = 1
class MainScreenRecycleViewAdaptor(var cardListItems: List<Card>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    class cardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(card : Card){
            itemView.cardview_title.text = card.Title
        }


        init {
            itemView.setOnClickListener {

                val intent = Intent(itemView.context, Card_handler_Activity::class.java)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)


            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_card,parent,false)
        return cardViewHolder(view)
    }





    override fun getItemCount(): Int {
    return cardListItems.size
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as cardViewHolder).bind(cardListItems[position])
    }


}