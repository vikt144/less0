 
package com.virf.less;  // тут тоже, что и в MaiActivity

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.os.Environment;
import android.util.*;

import android.content.pm.ActivityInfo; //  тут нужен для ориентации экрана
import android.content.Intent;
import android.content.Context;
import android.content.*;//  .Intent;Context;

import android.app.Activity;
 
import android.view.KeyEvent.Callback ;

import android.view.*;  //KeyEvent SurfaceHolder; SurfaceView;

import android.view.View;
import android.view.View.*;  //OnClickListener

import android.widget.*;  

import java.util.*;
import java.util.List;

public class abslayout  extends Activity implements OnClickListener  {

  TextView text0;
  TextView text1;
   
  Button btn0;
  Button btn1;
  Button btn2;
  Button btn3;
  EditText edText;
// названия похожи на те, что  и в MainActivity, но это чтоб удобней
// было копипастить, на самом деле они должны соответствовать
// принадлежащему этому активити xml файлу

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.absl);  // ссылка absl.xml

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

   edText=(EditText)findViewById(R.id.edText);
    }

    @Override
    public void onClick(View v) {
        // по id определеяем кнопку, вызвавшую этот обработчик
        switch (v.getId()) {
       
            case R.id.btn0 :
                btn3.setText("AAA" ); 
                AbsoluteLayout.LayoutParams OBJ = new
                             AbsoluteLayout.LayoutParams(70,70,200,400);
              btn3.setLayoutParams(OBJ);
            // AbsoluteLayout.LayoutParams(width,height,X-position,Y-position)
            //3 кнопке присваиваются размеры 70 на 70
          // устанавливается в позицию 200 400 и называют ААА
             break;       
            case R.id.btn1 :
                btn3.setText("OOO" ); 
                AbsoluteLayout.LayoutParams OBJi = new
                       AbsoluteLayout.LayoutParams(70,70,100,300);
                  btn3.setLayoutParams(OBJi); 
            break;           
            case R.id.btn2 :
                 Intent filenameIntent = new Intent(Intent.ACTION_MAIN);
                 filenameIntent.putExtra("path", "/"); 
                 filenameIntent.putExtra("action","getfile"); 
                 filenameIntent.setComponent(new ComponentName("com.outside.act","com.outside.act.fileDialog"));
                 startActivityForResult(filenameIntent,100);
		 		      
	    
            break;  
        }//switch

    }//click   
 
   @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (data == null) {return;}
    switch (requestCode) {
       case 100 :
           String fn =  data.getStringExtra("filename");
           edText.setText(fn);
        break;
       }
  } 	
 
}//all