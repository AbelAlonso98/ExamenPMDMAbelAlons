package com.example.examenpmdm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    List<String> modelList;

    public UserAdapter(List<String> modelList){
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int tiempo = Integer.parseInt(modelList.get(position));
        holder.texto.setText(format(tiempo));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView texto;

        public ViewHolder(View v){
            super(v);
            texto = v.findViewById(R.id.textView);
        }
    }
    private String format(int segundos){
        int min = segundos / 60;
        segundos %= 60;
        int hor = min / 60;
        min %= 60;
        return String.format("%02d:%02d:%02d", hor, min, segundos);
    }
}
