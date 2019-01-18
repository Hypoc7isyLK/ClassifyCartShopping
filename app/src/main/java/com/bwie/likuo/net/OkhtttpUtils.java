package com.bwie.likuo.net;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/21
 * author:李阔(淡意衬优柔)
 * function:
 */
public class OkhtttpUtils {
    private static OkhtttpUtils mOkhtttpUtils;
        private OkHttpClient mOkHttpClien;
        private final Handler mHandler;

        private OkhtttpUtils() {

    /*        Map<String, String> map = new HashMap<>();
            map.put("source", "android");
            PublicParamInterceptor publicParamInterceptor = new PublicParamInterceptor(map);*/
    //日志拦截器
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
            //创建一个主线程的handler
            mHandler = new Handler(Looper.getMainLooper());
            mOkHttpClien = new OkHttpClient.Builder()
                    .connectTimeout(5000, TimeUnit.MILLISECONDS)
                    .readTimeout(5000, TimeUnit.MILLISECONDS)
                    .writeTimeout(5000, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }

        //单例模式
        public static OkhtttpUtils getInstance() {
            if (mOkhtttpUtils == null) {
                synchronized (OkhtttpUtils.class) {
                    if (mOkhtttpUtils == null) {
                        return mOkhtttpUtils = new OkhtttpUtils();
                    }
                }
            }
            return mOkhtttpUtils;
        }

        public interface OkCallback {
            void onFailure(Exception e);
            void onResponse(String json);
        }


        public void doPost(String url, Map<String, String> map, final OkCallback okCallback) {
            //创建FormBody的对象,把表单添加到formBody中
            FormBody.Builder builder = new FormBody.Builder();
            if (map != null) {
                for (String key : map.keySet()) {
                    builder.add(key, map.get(key));
                }
            }
            FormBody formBody = builder.build();

            //创建Request对象
            Request request = new Request.Builder()
                    .post(formBody)
                    .url(url)
                    .build();
            //创建Call对象
            final Call call = mOkHttpClien.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    if (okCallback != null) {
                        //切换到主线程
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                okCallback.onFailure(e);
                            }
                        });
                    }
                }
                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    try {
                        if (response != null && response.isSuccessful()) {
                            final String json = response.body().string();
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (okCallback != null) {
                                        okCallback.onResponse(json);
                                        return;
                                    }
                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (okCallback != null) {
                        okCallback.onFailure(new Exception("网络异常"));
                    }
                }
            });
        }




        //封装doGet的网络请求
        public void doGet(String url, final OkCallback okCallback) {
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();

            final Call call = mOkHttpClien.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    if (okCallback != null) {
                        //切换到主线程
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                okCallback.onFailure(e);
                            }
                        });
                    }
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {

                    try {
                        if (response != null && response.isSuccessful()) {
                            final String json = response.body().string();
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (okCallback != null) {
                                        okCallback.onResponse(json);
                                        return;
                                    }

                                }
                            });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        }
}
