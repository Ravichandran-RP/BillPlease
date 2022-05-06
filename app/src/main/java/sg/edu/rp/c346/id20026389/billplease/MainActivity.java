package sg.edu.rp.c346.id20026389.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

        EditText amount;
        EditText pax;
        ToggleButton svs;
        ToggleButton gst;
        EditText discount;
        Button split;
        Button reset;
        TextView total;
        TextView eachpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount=findViewById(R.id.editTextAmount);
        pax=findViewById(R.id.editTextPax);
        svs=findViewById(R.id.toggleButtonSVS);
        gst=findViewById(R.id.toggleButtonGST);
        discount=findViewById(R.id.editTextDiscount);
        split=findViewById(R.id.Split);
        reset=findViewById(R.id.Reset);
        total=findViewById(R.id.Total);
        eachpay=findViewById(R.id.EachPay);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount.getText().toString().trim().length()!=0 && pax.getText().toString().trim().length()!=0){
                    double cost=0;
                    if(svs.isChecked()==false && gst.isChecked()==false){
                        cost=Double.parseDouble(amount.getText().toString());
                    }else if (svs.isChecked()==true && gst.isChecked()==false){
                        cost=Double.parseDouble(amount.getText().toString())*1.1;
                    }else if (svs.isChecked()==false && gst.isChecked()==true){
                        cost=Double.parseDouble(amount.getText().toString())*1.07;
                    }else{
                        cost=Double.parseDouble(amount.getText().toString())*1.17;
                    }
                    if (discount.getText().toString().trim().length()!=0){
                        cost=cost*(1-Double.parseDouble(discount.getText().toString())/100);
                    }

                    total.setText("Total Bill: $" + String.format("%.2f", cost));
                    int people=Integer.parseInt(pax.getText().toString());
                    eachpay.setText("Each Pays: $"+String.format("%.2f",cost/people));
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                pax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                discount.setText("");
            }
        });
    }
}