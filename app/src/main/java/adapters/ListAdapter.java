package adapters;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListAdapter extends BaseAdapter {

    private ArrayList<?> inputs;
    private int R_layout_IdView;
    private Context context;

    public ListAdapter(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
        super();
        this.context = contexto;
        this.inputs = entradas;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup pariente) {
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView, null);
        }
        input(inputs.get(posicion), view);
        return view;
    }

    @Override
    public int getCount() {
        return inputs.size();
    }

    @Override
    public Object getItem(int position) {
        return inputs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /** Devuelve cada una de las inputs con cada una de las vistas a la que debe de ser asociada
     * @param input La enrada que será la asociada a la view. La entrada es del tipo del paquete/handler
     * @param view View particular que contendrá los datos del paquete/handler
     */
    public abstract void input(Object input, View view);

}