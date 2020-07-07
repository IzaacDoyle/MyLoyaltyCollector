package doyle.myloyaltycollector.Adaptors

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import doyle.myloyaltycollector.Storeage.Card
import doyle.myloyaltycollector.Card_handler_Activity
import doyle.myloyaltycollector.R

import kotlinx.android.synthetic.main.cardview_card.view.*


/*
class RecylerAdaptor_cardView(private val context: Context, private val card: List<Card?>) :
    RecyclerView.Adapter<RecylerAdaptor_cardView.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var currentTitle: Card? = null
        var currentPosition: Int = 1


        fun setData(card: Card?, position: Int) {
            itemView.cardview_title.text = card!!.Title

            this.currentTitle = card
            this.currentPosition = position


        }


        init {
            itemView.setOnClickListener {

                val intent = Intent(itemView.context, Card_handler_Activity::class.java)
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
                itemView.context.startActivity(intent)


            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_card, parent, false)
        return ViewHolder(view)

        /* val layoutInflater = LayoutInflater.from(parent?.context)
        val cellforRow = layoutInflater.inflate(R.layout.cardview_card, parent, false)
        return ViewHolder(cellforRow)*/
    }

    override fun getItemCount(): Int {


        return card.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val card = card[position]
        holder.setData(card, position)


        //holder.NAME.setImaheBitMap(


        //  val card : Card = cardList[position]
        // holder.textViewTitle.text = card.Title
        // holder.imageOfCh.setImageResource()
        // create file system so image can be pulled from
    }


}






 */