package ness.edu.locationaware;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class SignInActivity extends AppCompatActivity {


    @BindView(R.id.etName)
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnTextChanged(R.id.etName)
    public void etChanged(CharSequence text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


//    @OnEditorAction(R.id.etName)
//    public void etAction(){
//        Toast.makeText(this, "action", Toast.LENGTH_SHORT).show();
//    }

}
