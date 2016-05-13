package com.itis.androidlab.retrofit.network;

import com.itis.androidlab.retrofit.BuildConfig;
import com.itis.androidlab.retrofit.Config;
import com.itis.androidlab.retrofit.utils.AndroidUtils;
import com.itis.androidlab.retrofit.utils.JacksonConverter;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SessionRestManager {

    private static volatile SessionRestManager sInstance;

    private SessionRestManager() {
    }

    public static SessionRestManager getInstance() {
        if (sInstance == null)
            synchronized (SessionRestManager.class) {
                if (sInstance == null)
                    sInstance = new SessionRestManager();
            }
        return sInstance;
    }

    /**
     * Настраиваем поля, которые будут добавляться к каждому запросу
     */
    private final RequestInterceptor REQUEST_INTERCEPTOR = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            // К каждому запросу в параметры будет добавляться ApplicationId,
            // чтобы сервер мог определять какое приложение выполняет запрос
            request.addQueryParam("appid", Config.APPLICATION_ID);
            request.addQueryParam("cnt", Config.DAYS_COUNT);
            request.addQueryParam("units","metric");
            request.addHeader("Accept", "application/json");
        }
    };

    /**
     * Определяем RestAdapter,
     *
     *  - endpoint - базовый URL для запросов
     *  - converter - конвертер, при помощи которого будут парсится JSON
     *  - RequestInterceptor - класс который перехватывает каждый запрос
     *  и добавляет к нему поля(определили выше)
     */
    private final RestAdapter.Builder REST_ADAPTER_BUILDER = new RestAdapter.Builder()
            .setEndpoint(AndroidUtils.getRestEndpoint())
            .setConverter(new JacksonConverter())
            .setRequestInterceptor(REQUEST_INTERCEPTOR)
            .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL
                    : RestAdapter.LogLevel.NONE);

    private final RestAdapter REST_ADAPTER = REST_ADAPTER_BUILDER.build();

    public WeatherRest getRest() {
        return REST_ADAPTER.create(WeatherRest.class);
    }

}
