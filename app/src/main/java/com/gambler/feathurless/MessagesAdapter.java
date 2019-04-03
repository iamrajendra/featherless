package com.gambler.feathurless;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gambler.feathurless.models.Message;

import org.feathersjs.client.service.FeathersService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MessagesAdapter extends FeathersServiceAdapter<Message, MessagesAdapter.MessageViewHolder> {

    public MessagesAdapter(Activity activity, FeathersService<Message> service, int resource) {
        super(activity, service, resource);
    }

    public static class MessageViewHolder extends FeathersViewHolder {
        protected TextView vUsername;
        protected TextView vText;

        public MessageViewHolder(View v) {
            super(v);
            vUsername = (TextView) v.findViewById(R.id.username);
            vText = (TextView) v.findViewById(R.id.message_text);

        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_message, parent, false);

        return new MessageViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {

        final Message message = (Message) getDataSet().get(position);

        holder.vText.setText(message.text);
        if(message.sentBy != null) {
            holder.vUsername.setText(message.sentBy.email);
        }

        // Testing updating a message when clicking it
        // This will throw an API error if you didn't create the message since we're not checking
        // client side
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Message updatedMessage = message;
                JSONObject obj = new JSONObject();
                try {
                    obj.put("text", "newTExt" + new Date().getTime());
                    updatedMessage.text = obj.optString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mService.update(updatedMessage._id, updatedMessage, new FeathersService.FeathersCallback<Message>() {
                    @Override
                    public void onError(String errorMessage) {

                    }

                    @Override
                    public void onSuccess(Message t) {

                    }
                });
            }
        });
    }
}
