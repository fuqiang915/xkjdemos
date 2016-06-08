package com.xkj.demos.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xkj.demos.models.ContactInfo;
import com.xkj.xkjdemos.R;

import java.util.List;

/**
 * 适配器RecycleView
 * Created by fuqiang on 15/3/2.
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ContactViewHolder> {

    private List<ContactInfo> contactList;

    public MyRecycleAdapter(List<ContactInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        ContactInfo ci = contactList.get(i);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
        contactViewHolder.vEmail.setText(ci.email);
        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView) v.findViewById(R.id.txtSurname);
            vEmail = (TextView) v.findViewById(R.id.txtEmail);
            vTitle = (TextView) v.findViewById(R.id.title);

            // 添加点击事件
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addItem();
                }
            });
        }
    }

    /**
     * 添加一个Item
     */
    private void addItem() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.name = Time.getCurrentTimezone();
        contactList.add(contactInfo);
        notifyItemInserted(contactList.indexOf(contactInfo));
    }
}