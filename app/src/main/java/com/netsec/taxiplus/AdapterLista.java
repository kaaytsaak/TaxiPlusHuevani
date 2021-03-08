package com.netsec.taxiplus;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterLista extends RecyclerView.Adapter<AdapterLista.ViewHolderRecycler>
{

    private ArrayList<Taxi_Plus_Recycler> rankingrecycler;
    ViewHolderRecycler viewHolderRecycler;
    private  RecyclerView recyclerView;
    private Context context;
    private String id_viaje,tipo_viaje,fecha_inicio,fecha_termino,estado,costo,id_conductor;

    public AdapterLista(ArrayList<Taxi_Plus_Recycler> rankingrecycler)
    {
        this.rankingrecycler=rankingrecycler;
    }
    @Override
    public ViewHolderRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        context=parent.getContext();
        return new ViewHolderRecycler(vista);
    }

    @Override
    public void onBindViewHolder(final ViewHolderRecycler holder,final int position){
        viewHolderRecycler=holder;
        id_viaje= rankingrecycler.get(position).getId_viaje();
        tipo_viaje= rankingrecycler.get(position).getTipo_viaje();
        fecha_inicio= rankingrecycler.get(position).getFecha_inicio();
        fecha_termino= rankingrecycler.get(position).getFecha_termino();
        estado= rankingrecycler.get(position).getEstado();
        costo= rankingrecycler.get(position).getCosto();
        id_conductor= rankingrecycler.get(position).getId_conductor();


        holder.id.setText(id_viaje);
        holder.type_travel.setText(tipo_viaje);
        holder.date_star.setText(fecha_inicio);
        holder.date_end.setText(fecha_termino);
        holder.state.setText(estado);
        holder.cost.setText(costo);
        holder.id_driver.setText(id_conductor);





        //Uri uri = Uri.parse(photo);
        // holder.photo.setImageURI(uri);
    }
    @Override
    public int getItemCount(){
        return rankingrecycler.size();

    }
    public class ViewHolderRecycler extends RecyclerView.ViewHolder {


        TextView id,type_travel,date_star,date_end,state,cost,id_driver;

        public ViewHolderRecycler(View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.id_viaje);
            type_travel=(TextView)itemView.findViewById(R.id.tipo_viaje);
            date_star=(TextView)itemView.findViewById(R.id.fecha_inicio);
            date_end=(TextView)itemView.findViewById(R.id.fecha_termino);
            state=(TextView)itemView.findViewById(R.id.estado);
            cost=(TextView) itemView.findViewById(R.id.costo);
            id_driver=(TextView)itemView.findViewById(R.id.id_conductor);

            ;



        }
    }
}
