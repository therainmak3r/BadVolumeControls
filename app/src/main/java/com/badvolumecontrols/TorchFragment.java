package com.badvolumecontrols;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class TorchFragment extends Fragment {
    public TorchFragment() {
        super(R.layout.torch_fragment);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.torch_fragment, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        final Switch torch_button = view.findViewById(R.id.torch);
        torch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CameraManager camManager = (CameraManager) view.getContext().getSystemService(view.getContext().CAMERA_SERVICE);
                String cameraId = null;
                try {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, true);   //Turn ON

                    View parent = (View) view.getParent();
                    if (isChecked) {
                        camManager.setTorchMode(cameraId, true);   //Turn ON

                        torch_button.setBackgroundColor(0xFFFF0000);
                        view.setBackgroundColor(0xFF00FF00);
                        parent.setBackgroundColor(0xFFFF0000);
                    } else {
                        camManager.setTorchMode(cameraId, false);   //Turn ON

                        torch_button.setBackgroundColor(0xFF00FF00);
                        view.setBackgroundColor(0xFFFF0000);
                        parent.setBackgroundColor(0xFF00FF00);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
