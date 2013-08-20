package com.song.myAnim.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.song.myAnim.R;
import com.song.myAnim.customer.view.DragListView;


public class Anim9Activity extends Activity {
    private DragListView mShowAll;

    private EditListAdapter mSelectAdapter;

    private ArrayList<String> mCodeData = new ArrayList<String>();

    private ArrayList<String> mNameData = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        dataInit();
        setContentView(R.layout.activity_anim9);
        mShowAll = (DragListView) findViewById(R.id.dzh_del_win_show_all);
        System.out.println(mShowAll==null);
        mSelectAdapter = new EditListAdapter(this);
        mSelectAdapter.updateData(mCodeData, mNameData);
        mShowAll.setAdapter(mSelectAdapter);
    }


    public void dataInit() {
        mCodeData.clear();
        mNameData.clear();
        for(int i=0;i<20;i++){
            mCodeData.add("股票代码"+"6000"+i);
            mNameData.add("股票名称"+i);
        }
    }

  public  class EditListAdapter extends BaseAdapter {
        private ArrayList<String> mShowCodeData = new ArrayList<String>();

        private ArrayList<String> mShowNameData = new ArrayList<String>();

        private Context mContext;

        private LayoutInflater mInflater;

        public EditListAdapter(Context context) {
            // TODO Auto-generated constructor stub
            this.mContext = context;
            mInflater = LayoutInflater.from(mContext);
        }

        public class ViewHolder {
            TextView code;

            TextView name;

            ImageView move;

            ImageView delete;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mShowCodeData.size();
        }

        @Override
        public String[] getItem(int position) {
            // TODO Auto-generated method stub
            return new String[] {
                    mShowCodeData.get(position), mShowNameData.get(position)
            };
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final int pos = position;
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.activity_anim9_item, null);
                holder = new ViewHolder();
                holder.code = (TextView) convertView.findViewById(R.id.dzh_delete_item_code);
                holder.name = (TextView) convertView.findViewById(R.id.dzh_delete_item_name);
                holder.move = (ImageView) convertView.findViewById(R.id.move_item);
                holder.delete = (ImageView) convertView.findViewById(R.id.dzh_delete_item_delete);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String itemCodeName = mShowCodeData.get(position);
            String itemSymbolName = mShowNameData.get(position);
            if (itemCodeName.equals("")) {
                holder.move.setBackgroundColor(Anim9Activity.this.getResources().getColor(R.color.grey));
                holder.delete.setBackgroundColor(Anim9Activity.this.getResources().getColor(R.color.grey));
            }else{
                holder.move.setBackgroundResource(R.drawable.edit_move);
                holder.delete.setBackgroundResource(R.drawable.win_item_delete);
            }
            if (itemCodeName != null) {
                OnClickListener itemListener = new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.dzh_delete_item_delete:
                                deleteItem(pos);
                                break;
                        }
                    }
                };
                holder.code.setText(itemCodeName);
                holder.name.setText(itemSymbolName);
                holder.delete.setOnClickListener(itemListener);
            }
            return convertView;
        }

        public void up(int position) {
            if (position == 0)
                return;
            String codeUpData = mShowCodeData.get(position);
            String codeDownData = mShowCodeData.get(position - 1);
            mShowCodeData.set(position, codeDownData);
            mShowCodeData.set(position - 1, codeUpData);
            String nameUpData = mShowNameData.get(position);
            String nameDownData = mShowNameData.get(position - 1);
            mShowNameData.set(position, nameDownData);
            mShowNameData.set(position - 1, nameUpData);
            this.notifyDataSetChanged();
        }

        public void down(int position) {
            if (position == mShowCodeData.size() - 1)
                return;
            String codeDownData = mShowCodeData.get(position);
            String codeUpData = mShowCodeData.get(position + 1);
            mShowCodeData.set(position, codeUpData);
            mShowCodeData.set(position + 1, codeDownData);
            String nameDownData = mShowNameData.get(position);
            String nameUpData = mShowNameData.get(position + 1);
            mShowNameData.set(position, nameUpData);
            mShowNameData.set(position + 1, nameDownData);
            this.notifyDataSetChanged();
        }

        public void deleteItem(int position) {
            if (position > mShowCodeData.size() - 1)
                return;
            mShowCodeData.remove(position);
            mShowNameData.remove(position);
            this.notifyDataSetChanged();
        }

        public void deleteAll() {
            mShowCodeData.clear();
            mShowNameData.clear();
            this.notifyDataSetChanged();
        }

        public void updateData(ArrayList<String> codeData, ArrayList<String> nameData) {
            mShowCodeData = codeData;
            mShowNameData = nameData;
            this.notifyDataSetChanged();
        }

        public void remove(String[] item) {
            mShowCodeData.remove(item[0]);
            mShowNameData.remove(item[1]);
            this.notifyDataSetChanged();
        }

        public void insert(String[] item, int index) {
            mShowCodeData.add(index, item[0]);
            mShowNameData.add(index, item[1]);
            this.notifyDataSetChanged();
        }
    }
}
                    