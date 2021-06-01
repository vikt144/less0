package com.virf.less;

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.os.Environment;
import android.util.*;
import android.net.*;

import android.content.*;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo; //  тут нужен для ориентации экрана

import android.app.Activity;


import android.view.KeyEvent.Callback ;

import android.view.*;  

import android.view.View;
import android.view.View.*;  //OnClickListener

import android.widget.*;   

import java.util.*;
import java.util.List;
import java.io.*;
 

public class MainActivity extends Activity implements OnClickListener {


  TextView text0;
  TextView text1;
    
  Button btn0, btn1, btn2, btn3,
                btn4, btn5;
  
  EditText edText;
     

   Handler H;

//   String 
    File direct ;  // директория с форт прогами

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
  if (startForthQu == 0) {
    initForth();
    startForthQu++;
    }
   else startForthQu++;



//////////////////////////////////////////////////////////////////////создается handler для доступа к элементам gui из потоков 
// подробнее о handler -- 
// https://startandroid.ru/ru/uroki/vse-uroki-spiskom/144-urok-81-handler-posylaem-prostoe-soobschenie.html 
// https://startandroid.ru/ru/uroki/vse-uroki-spiskom/145-urok-82-handler-primer-s-bolee-soderzhatelnymi-soobschenijami.html

    final int _txt_field_ = 0 , _txt_button_ = 1, _txt_edit_ = 2,
       	            _get_txt_  = 0, _set_txt_ = 1 ;
   
    H = new Handler() {
      public void handleMessage(android.os.Message msg) {
	
        switch (msg.what) {
          case _txt_field_ : 
	  
               switch (msg.arg1) {
	                      case 0 : text0.setText( (String) msg.obj );
	                      break;
	                      case 1 : text1.setText( (String) msg.obj );
	                      break;			      
	                      case 2 : edText.setText( (String) msg.obj );
	                      break;
                             }
           break;
           case _txt_button_ : 
               switch (msg.arg1) {
	                      case 0 : btn0.setText( (String) msg.obj );
	                      break;
	                      case 1 : btn1.setText( (String) msg.obj );
	                      break;			      
	                      case 2 : btn2.setText( (String) msg.obj );
	                      break;
	                      case 3 : btn3.setText( (String) msg.obj );
	                      break;		
	                      case 4 : btn4.setText( (String) msg.obj );
	                      break;
	                      case 5 : btn5.setText( (String) msg.obj );
	                      break;					      
                             }
           break;
           case _txt_edit_ : 
               switch (msg.arg1) {
	                      case 0 : 
                                     switch (msg.arg2) {
	                                   case _get_txt_ : edText.getText().toString(); 
	                                   break;
	                                   case _set_txt_ : edText.setText( (String) msg.obj );
	                                   break;
					 }  
	                      break;
                            //case 1
			    //break;  
                            }     
  
             break;
            }
         };
      };
/////////////////////////////////////////////////end handler////////////////////////////////////////////////////
    H.sendEmptyMessage(0);
  
    as.Hnd=H;


    SharedPreferences prf = getPreferences(MODE_PRIVATE);
    String dir = prf.getString("Direct", "");
String sss=dir; text1.setText(dir);
    if (dir != "") {
                  direct = new File(dir);
                   if (  direct.exists() )  {
        	      btn1.setText("load");
                      text0.setText(dir);		      
		      File startFile = new File ( direct, "0" );
		      if ( startFile.exists() ) {
		            text0.setText(dir+"/0");   
                            String ff = direct.getAbsolutePath() +"/0";
			    String data = as.readStringText( ff );
			    as._IN = 0;
                            as.TIB=data;
			    try {
                                  as.interpret();
				  if (as.error==0) text0.setText( as.ErrorString );
				}
				 catch(Exception e)  {text0.setText("Крах системы " );}			    			
			     }

		      }
		      else   text0.setText(dir + " noexist");
		  }  else   text0.setText("pref -no exist");                   

    
    }// oncreate


  

        long back_pressed=0;   
	@Override
	public void onClick(View v) {
		// по id определеяем кнопку, вызвавшую этот обработчик
		switch (v.getId()) {
		
			case R.id.btn0 :

			     long time = System.currentTimeMillis();
			     if (back_pressed + 2000 > time ) {
			               back_pressed=0; 
				       String filepath = edText.getText().toString();
				        text0.setText( filepath  );
					SharedPreferences prf; 
                                        prf = getPreferences(MODE_PRIVATE);    
					Editor ed = prf.edit();
					ed.putString("Direct",filepath );
                                        ed.commit(); 
                                //        direct = filepath;
					} 
                              else {		     
			          back_pressed=time;
				  text0.setText("double click this button for pref change" );  
 		                 }
			break;		
			case R.id.btn1 :
			    String f = edText.getText().toString().trim();
			    String ff = direct.getAbsolutePath() +"/" + f;
			//    try {
			    String data = as.readStringText( ff  );
			    // edText
			    if (data != null)  edText.setText(data);
			     else text1.setText("not файла " +ff);
			     /*
			    text0.setText("uses " +f);
			    }  catch (IOException IO ) { text0.setText("not файла " +ff); 
                            } */
			break;			
			case R.id.btn2 :
			    text1.setText("initforth= "+startForthQu );  
 
			break;		
			case R.id.btn4 :
			    String gtext = edText.getText().toString();    
			    if ( ! gtext.equals("") )  {// если текст не равен ""
			            as._IN = 0;
                         	    as.TIB=gtext;
				    try {
                                        as.interpret();
					if (as.error==0) text0.setText( as.ErrorString );
					}
				       catch(Exception e)  {text0.setText("Крах системы " );}
				    
				    }
 
			break;		
			case R.id.btn5 :  
			   Intent intent = new Intent (this, abslayout.class);
			   startActivity(intent);

			break;
		}//switch

	}//click	
 

/////////////////////////////////////////////forth part///////////////////////////
        public int startForthQu = 0; // подсчет кол-ва попыток вызовов initForth() 
	
        fas  as;

	public void  initForth() {

            //fas
	      as = new fas();

            fas.STACK ST =  as. new STACK();
            int[] stackarray=new int[100];
            ST.stack=stackarray;//new int[100]; //stackarray;
            as.ST=ST;
      as.here=  2;
      as.  latest=3;
      as.state   =4;      
      as.  memory[as.here]=6; //взято от балды
      as.  memory[as.latest]=0; // записи в словари еще не создавались
      as.  memory[as.state] =0; // 0 на данный момент исполнение     

     Vector V=null;
     V = as.initVirtualMem(0);
     as.StringVector=V;

      fas.STACK RST =  as. new STACK();
      int[] ret_stackarray=new int[30];
      RST.stack=ret_stackarray;//new int[100]; //stackarray;

      fas.FVM VM = as. new FVM();
      VM.stack=as.ST;
      VM.adrStack=RST;
      VM.image=as.memory;
      int[] ports = new int[20]; 
      VM.ports=ports; 
      as.VM=VM;   

      as.initFVMwords();

     as.cre1();  // создается  определение для exit
 
     as.exit_addr=as.memory[as.here];//сохранить точску входа в exit,чтобы положить на стек возвратов  
 
 as.ST.push(1); as.comma16();  // lit  
 as.ST.push(0); as.comma16();  // 0
 as.ST.push(1); as.comma16();  // lit  
 as.ST.push(0); as.comma16();  // 0 
 as.ST.push(29); as.comma16(); // out      -- вывод в порт 0, номер фции 0 "exit"

  as.ST.push(2);  as.setCFA();


// определяется "." 
 as.cre1();
  as.ST.push(1); as.comma16();  // lit  
 as.ST.push(1); as.comma16();  // 1     
 as.ST.push(1); as.comma16();  // lit  
 as.ST.push(0); as.comma16();  // 0  // номер порта
 as.ST.push(29); as.comma16(); // out      -- вывод в порт 0, номер фции 1 "." 
  as.ST.push(9); as.comma16();  //9  ";"

  as.ST.push(2);  as.setCFA();

 as.initEXTwords();
 as.interpret();	
	
	}

/////////////////////////////////////////////end forth part///////////////////////////

//////menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      
      menu.add("http");
      menu.add("map");
      menu.add("prf");      //настройки
      menu.add("gpson");   //настройкии gps
      menu.add("all_prf");//главэкран
      menu.add("file");   //     
      menu.add("fileSam");   //
      menu.add("tel");   //
            
      return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
	String error="функция несовместима с устройством";
	try {
                                      //  Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        String menu = item.getTitle().toString();
	
	if (menu.equals("http") ) {
	   intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://fforum.winglion.ru"));
           startActivity(intent);
	    }
	 else
	 if (menu.equals("map") ) {	    
           intent = new Intent();
           intent.setAction(Intent.ACTION_VIEW);
           intent.setData(Uri.parse("geo:55.754283,37.62002"));
           startActivity(intent);         
	  }
	 else 
	 if (menu.equals("prf") ) {
               startActivity(new Intent(  android.provider.Settings.ACTION_DATA_USAGE_SETTINGS ));
	  }     	
	 else 
	 if (menu.equals("gpson") ) {	  
	   startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));   
	 }
	 //https://developer.android.com/reference/android/provider/Settings#ACTION_DATA_USAGE_SETTINGS  
	 //http://developer.alexanderklimov.ru/android/theory/intent.php
	 else 
	 if (menu.equals("all_prf") ) {
            intent = new Intent(Settings.ACTION_SETTINGS) ;
	     startActivity(intent);
	     }
	  else   

	 if (menu.equals("file") ) {	
	   intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
           intent.addCategory(Intent.CATEGORY_OPENABLE);
           intent.setType("*/*");
           startActivity(intent);   // startActivityForResult(intent, REQUEST_CODE);
           }
	  else 
	 if (menu.equals("fileSam") ) {	
	     intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
             intent.putExtra("CONTENT_TYPE", "*/*");
             intent.addCategory(Intent.CATEGORY_DEFAULT);
             startActivity(intent);   // startActivityForResult(intent, REQUEST_CODE);
             }
	  else 
	 if (menu.equals("tel") ) {
	      
	     intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:12345678"));
             startActivity(intent);
	 }	
	 
	 } catch(Exception e) { text0.setText(error); } 	     
      return super.onOptionsItemSelected(item);
    }
    

///\\\\menu


}//all

