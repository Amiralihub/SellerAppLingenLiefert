package com.example.sellapplingen;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import com.example.sellapplingen.R;
import com.example.sellapplingen.ScannerFragment;
import com.example.sellapplingen.SettingFragment;
import com.example.sellapplingen.WelcomeFragment;
import com.example.sellapplingen.databinding.ActivitymainBinding;

public class MainActivity extends AppCompatActivity {
    ActivitymainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitymainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setVisibility(View.GONE);

        if (getIntent().getBooleanExtra("OPEN_SCANNER_FRAGMENT", false)) {
            replaceFragment(new ScannerFragment());
        } else {
            replaceFragment(new WelcomeFragment());
        }
        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.code:
                    replaceFragment(new ScannerFragment());
                    break;
                case R.id.settings:
                    replaceFragment(new SettingFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public static void openScannerFragment(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("OPEN_SCANNER_FRAGMENT", true);
        context.startActivity(intent);
    }
}
