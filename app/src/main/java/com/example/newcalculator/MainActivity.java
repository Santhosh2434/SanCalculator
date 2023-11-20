package com.example.newcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result_tv,solution_tv;
    MaterialButton ac,c;
    MaterialButton badd,bsub,bmul,bdiv,bequ;
    MaterialButton bopen,bclose,bdot;
    MaterialButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        assign(ac,R.id.btn_ac);
        assign(c,R.id.btn_c);
        assign(badd,R.id.btn_add);
        assign(bsub,R.id.btn_sub);
        assign(bmul,R.id.btn_mul);
        assign(bdiv,R.id.btn_div);
        assign(bequ,R.id.btn_equ);
        assign(b0,R.id.btn_0);
        assign(b1,R.id.btn_1);
        assign(b2,R.id.btn_2);
        assign(b3,R.id.btn_3);
        assign(b4,R.id.btn_4);
        assign(b5,R.id.btn_5);
        assign(b6,R.id.btn_6);
        assign(b7,R.id.btn_7);
        assign(b8,R.id.btn_8);
        assign(b9,R.id.btn_9);
        assign(bclose,R.id.btn_openbrakets);
        assign(bopen,R.id.btn_closebraket);
        assign(bdot,R.id.btn_dot);

    }

    void assign(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String buttext = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttext.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttext.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }
        if(buttext.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate + buttext;
        }
        solution_tv.setText(dataToCalculate);

        String finalRes = getResult(dataToCalculate);

        if(!finalRes.equals("Err")) {
            result_tv.setText(finalRes);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalRes = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalRes.endsWith(".0")){
                finalRes = finalRes.replace(".0","");
            }
            return finalRes;
        }
        catch (Exception e){
            return "Err";
        }
    }
}