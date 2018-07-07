/*--------------------------------------------------------------------------------------------------
*  Do it Note.js 프로그래밍 [개정판] CH13-02 동영상 강의 참조
*-------------------------------------------------------------------------------------------------*/
package com.aiclever.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/*--------------------------------------------------------------------------------------------------
*  Android Volley 사용 Network 통신하는 법
*  2013년 구글 I/O 전에는 안드로이드에서 네트워크 통신을 하기 위해서는 Async Task 클래스를 이용해서
*  직접 운영할 수 밖에 없었음.
*  하지만 이 방법에는 예외처리하기가 까다롭기 때문에, 네트워크가 들어가는 순간 에너지가 많이 들어감.
*  이에 구글은 Andorid Volley 라이브러리를 13년도에 발표하면서 편의성을 높혀 주었음.
*-------------------------------------------------------------------------------------------------*/
/*--------------------------------------------------------------------------------------------------
*  Android Volley 주 기능과 장점
*  1. 하나의 requestQueue 모든 요청을 처리한다 -> 알아서 동시다발적으로 요청을 할 수 있음
*  2. 우선 순위를 설정을 할 수 있음
*     - 이미지 파일들은 기본적으로 낮은 우선 순위를 갖는다.
*     - Priority Class 를 상속하여 우선 순위를 customize 할 수도 있음.
*  3. request를 취소할 수도 있음 (ListView 에서 유저가 빠르게 스크롤링 할 경우, 스크롤링한 내용을
   *  다 로딩할 필요가 없고, 보여지는 부분 이외의 로딩이 안 된 request를 취소할 수 있음
   *
*-------------------------------------------------------------------------------------------------*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });
    }

    public void request() {

        EditText editText = findViewById(R.id.editText);
        String url = editText.getText().toString();

        // Volly Library에 StringRequest() 함수 활용하여 request 객체를 만드는 부분
        // request 객체는 queue 에 넣어주면 바로 요청이 전송됨.
        // 요청이 응답이 오면 Response.Listener() 가 호출되고,
        // 에러가 발생하면 ErrorListener() 가 호출됨.
        StringRequest request = new StringRequest(
                Request.Method.POST,  //POST 방식으로 list user 조회하도록 되어 있음
                url,
                new Response.Listener<String>() {  // 문자열로 받겠다.
                    @Override
                    public void onResponse(String response) {
                        println(response);  // 결과물을 화면에 표시해주는 부분
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 발생함.");
                    }
                }
        );

        // 요청을 전송하는 부분
        request.setShouldCache(false);
        Volley.newRequestQueue(this).add(request); // 새로운 Queue를 만들고 Queue에 Add 해주면 전송됨.
        println("요청하였음.");
    }


    public void println(String data) {
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
    }
}
