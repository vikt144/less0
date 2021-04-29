package com.virf.less;
// https://pastebin.com/aUML6mpN

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.os.Environment;
import android.util.*;

import android.content.pm.ActivityInfo; //  тут нужен для ориентации экрана


import android.app.Activity;


import android.view.KeyEvent.Callback ;

import android.view.*;  

import android.view.View;
import android.view.View.*;  //OnClickListener

import android.widget.*;   

import java.util.*;
import java.util.List;

 

public class MainActivity extends Activity implements OnClickListener {


  TextView text0;
  TextView text1;
    
  Button btn0, btn1, btn2, btn3,
                btn4, btn5;
  
  EditText edText;
     
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    text0 = (TextView) findViewById(R.id.text0);    
    text1 = (TextView) findViewById(R.id.text1);  

    btn0=(Button) findViewById(R.id.btn0);
        btn0.setOnClickListener(this); 
    btn1=(Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);  
    btn2=(Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
   btn3=(Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);   
   btn4=(Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);  
  btn5=(Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);      
    
      
   edText=(EditText)findViewById(R.id.edText);

//  setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // задает ориентацию портрет
// подробнее об ориентации см
//
    }

   
	@Override
	public void onClick(View v) {
		// по id определеяем кнопку, вызвавшую этот обработчик
		switch (v.getId()) {
		
			case R.id.btn0 :
			    text0.setText("ON " );  
 
			break;		
			case R.id.btn1 :
			    text0.setText("OFF " );  
 
			break;			
	
			case R.id.btn4 :
			    String gtext = edText.getText().toString();    
			    if ( ! gtext.equals("") )  // если текст не равен ""
			        btn2.setText(gtext);                      // то этот текст присваивается 2 кнопке
 
			break;		
		}//switch

	}//click	
 
}//all
