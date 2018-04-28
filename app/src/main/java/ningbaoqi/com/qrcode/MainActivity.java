package ningbaoqi.com.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * QRcode二维码
 *          通过在一个矩形区域内使用黑白像素来进行编码，具有高纠错性，高可用性，高识别性，四个方框是用来做定位的
 *          Zxing是一个开放原码的、是1D/2D条形图像处理库，Zxing可以实现使用手机的内置的摄像头完成条形码的扫描和解码
 *          https://github.com/zxing/zxing
 *
 * 将其他模块当作本模块的lib库步骤，open mode settings -->dependencies --> + module dependence
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private ImageView ivQrcode;
    private EditText etCreateQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        ivQrcode = findViewById(R.id.iv_qrcode);
        etCreateQrcode = findViewById(R.id.et_create_qrcode);
    }

    public void scan(View view) {
        startActivityForResult(new Intent(this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            String result = data.getExtras().getString("result");
            tvResult.setText(result);
        }
    }

    public void createQrcode(View view) {
        String response = etCreateQrcode.getText().toString();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        Bitmap bitmap1 = EncodingUtils.createQRCode(response, 500, 500, /*null*/bitmap);
        ivQrcode.setImageBitmap(bitmap1);
    }
}
