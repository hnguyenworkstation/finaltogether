package layout.MainFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.reinvo.studytogether.R;

/**
 * Created by Jason on 4/7/2016.
 */
public class QuickAddDialogFragment extends DialogFragment {

    private EditText quick_add_input_;
    /*
    * Public Constructor
    * */
    public QuickAddDialogFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quick_add_activity, container);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        return view;
    }
}
