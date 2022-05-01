package uom.team2.weball_statistics.UI_Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uom.team2.weball_statistics.databinding.FragmentPlaceHolderBinding;
/*
 * @author Leonard Pepa ics20033
 */
public class PlaceHolder extends Fragment {

    private FragmentPlaceHolderBinding binding;
    private int index;

    public PlaceHolder() {
        // Required empty public constructor
    }

    public PlaceHolder(int position) {
        index = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPlaceHolderBinding.inflate(inflater, container, false);
        String formattedText = String.format(binding.placeHolderViewText.getText().toString(), index);
        binding.placeHolderViewText.setText(formattedText);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}