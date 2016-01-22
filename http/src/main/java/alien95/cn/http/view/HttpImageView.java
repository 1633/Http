package alien95.cn.http.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.ImageView;

import alien95.cn.http.image.HttpRequestImage;
import alien95.cn.http.image.ImageCallBack;
import alien95.cn.http.R;

/**
 * Created by linlongxin on 2015/12/31.
 */
public class HttpImageView extends ImageView {

    private int inSimpleSize;
    private int loadImageId;
    private int failedImageId;

    public HttpImageView(Context context) {
        this(context, null, 0);
    }

    public HttpImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HttpImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpImageView);
        inSimpleSize = typedArray.getInteger(R.styleable.HttpImageView_compressSize, 1);
        loadImageId = typedArray.getResourceId(R.styleable.HttpImageView_loadImage, -1);
        failedImageId = typedArray.getResourceId(R.styleable.HttpImageView_failedImage, -1);
    }

    /**
     * 设置图片网络连接地址
     *
     * @param url
     */
    public void setImageUrl(String url) {
        if (loadImageId != -1)
            setImageResource(loadImageId);
        HttpRequestImage.getInstance().requestImageWithCompress(url, inSimpleSize, new ImageCallBack() {
            @Override
            public void success(Bitmap bitmap) {
                setImageBitmap(bitmap);
            }

            @Override
            public void failure() {
                if (failedImageId != -1)
                    setImageResource(failedImageId);
            }
        });
    }

    /**
     * 设置图片网络地址
     *
     * @param url          网络图片地址
     * @param inSimpleSize 压缩参数
     */
    public void setImageUrlWithCompress(String url, int inSimpleSize) {
        if (inSimpleSize < 1) {
            throw new IllegalArgumentException("inSimpleSize must greater than one");
        }
        if (loadImageId != -1)
            setImageResource(loadImageId);
        HttpRequestImage.getInstance().requestImageWithCompress(url, inSimpleSize, new ImageCallBack() {
            @Override
            public void success(Bitmap bitmap) {
                setImageBitmap(bitmap);
            }

            @Override
            public void failure() {
                if (failedImageId != -1)
                    setImageResource(failedImageId);
            }
        });
    }

    /**
     * 设置图片压缩参数
     * @param inSimpleSize
     */
    public void setInSimpleSize(int inSimpleSize) {
        this.inSimpleSize = inSimpleSize;
    }

    /**
     * 设置加载失败图片ID
     * @param failedImageId
     */
    public void setFailedImageId(int failedImageId) {
        this.failedImageId = failedImageId;
    }

    /**
     * 设置加载时图片的ID
     * @param loadImageId
     */
    public void setLoadImageId(int loadImageId) {
        this.loadImageId = loadImageId;
    }
}
