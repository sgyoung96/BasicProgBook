package com.example.flashlight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val torch = Torch(this)

        flashSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//                torch.flashOn()
                startService(intentFor<TorchService>().setAction("on"))
            } else {
//                torch.flashOff()
                startService(intentFor<TorchService>().setAction("off"))
            }
        }
    }
}

/**
 * 앱 위젯기능을 추가하였다.
 * 위젯 기능이 동작하게 하도록 위해.. 코드를 다 작성한 후, 앱을 실행한 다음 바로 종료하도록 한다.
 * 그리고 테스트폰에서 위젯을 배치한다.
 * drawable > example_appwidget_preview.png 파일이 미리보기로 표시된다.
 * 미리보기 이미지를 포함하여 각종 위젯의 정보는
 * xml > torch_app_widget_info.xml 파일에 저장돼있다.
 * resize 설정이 horizontal 과 vertical 로 설정되어 위젯의 크기를 사방으로 조절할 수 있다.
 */

// 저자가, 예외처리르 하지 않아서 버그가 있을 수 있다고 감안하라고 한다.
// 에뮬레이터와 안드로이드 6.0 미만에서는 동작하지 않는다.