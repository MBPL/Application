package mbpl.graphical.passwords.customList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.customProgressBar.CustomProgress;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.Passpoint;

/**
 * Created by pierre on 26/04/16.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] listeNoms;
    private final float secValue;
    private final float usaValue;

    public CustomList(Activity context, String[] listeNoms, float secValue, float usaValue) {
        super(context, R.layout.list_single, listeNoms);
        this.context = context;
        this.listeNoms = listeNoms;
        this.secValue = secValue;
        this.usaValue = usaValue;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);



        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setTextSize(20);

        String name = this.listeNoms[position];
        float u;
        float s;
        switch (name) {
            case "Passpoints":
                Passpoint pp = new Passpoint();
                u = (float) pp.getIndiceUtilisabilite();
                s = (float) pp.getIndiceSecurite();
                break;
            case "Déjà Vu":
                DejaVu dv = new DejaVu();
                u = (float) dv.getIndiceUtilisabilite();
                s = (float) dv.getIndiceSecurite();
                break;
            case "Passfaces":
                Passfaces pf = new Passfaces();
                u = (float) pf.getIndiceUtilisabilite();
                s = (float) pf.getIndiceSecurite();
                break;
            default:
                u = (float) 5.0f;
                s = (float) 5.0f;
                break;
        }

        CustomProgress cpU = (CustomProgress) rowView.findViewById(R.id.customProgressUsability);
        cpU.setMaximumPercentage(s/5);
        cpU.setProgressColor(ContextCompat.getColor(getContext(), R.color.blue_500));
        cpU.setProgressBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_200));
        cpU.setText(R.string.security);
        cpU.setTextSize(14);
        cpU.setTextColor(Color.WHITE);
        cpU.setGravity(Gravity.LEFT | Gravity.START);
        cpU.setPadding(20, 0, 0, 0);
        cpU.updateView(true);

        CustomProgress cpS = (CustomProgress) rowView.findViewById(R.id.customProgressSecurity);
        cpS.setMaximumPercentage(u/5);
        cpS.setProgressColor(ContextCompat.getColor(getContext(), R.color.green_500));
        cpS.setProgressBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_200));
        cpS.setText(R.string.usability);
        cpS.setTextSize(14);
        cpS.setTextColor(Color.WHITE);
        cpS.setGravity(Gravity.LEFT | Gravity.START);
        cpS.setPadding(20, 0, 0, 0);
        cpS.updateView(true);


        txtTitle.setText(listeNoms[position]);

        return rowView;
    }
}