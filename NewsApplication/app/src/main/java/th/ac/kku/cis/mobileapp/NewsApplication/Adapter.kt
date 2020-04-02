package th.ac.kku.cis.mobileapp.NewsApplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ToDoItemAdapter(context: android.content.Context, toDoItemList: MutableList<AddNews>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoItemList


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // create object from view
        val name: String = itemList.get(position).NameNews as String
        val view: View
        val vh: ListRowHolder

        // get list view
        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_text1, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        // add text to view
        vh.label1.text = name

        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label1: TextView = row!!.findViewById<TextView>(R.id.tv_news) as TextView
    }
}