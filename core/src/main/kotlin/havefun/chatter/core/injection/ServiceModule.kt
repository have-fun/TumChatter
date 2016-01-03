package havefun.chatter.core.injection

import com.google.gson.Gson
import com.squareup.okhttp.OkHttpClient
import dagger.Module
import dagger.Provides
import havefun.chatter.core.ChatClient
import havefun.chatter.core.ChatClientImpl
import havefun.chatter.core.network.ChatWebService
import havefun.chatter.core.network.mapper.Mappers
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val NETWORK_TIMEOUT_SECS = 1000L

@Module
class ServiceModule(val endpoint: String) {

    @Provides
    @Singleton
    fun provideChatClient(webService: ChatWebService, mappers: Mappers): ChatClient = ChatClientImpl(webService, mappers)

    @Provides
    @Singleton
    fun provideChatWebServiceClient(): ChatWebService {

        val okHttpClient = OkHttpClient()
        okHttpClient.setReadTimeout(NETWORK_TIMEOUT_SECS, TimeUnit.SECONDS)
//        okHttpClient.setConnectTimeout(NETWORK_TIMEOUT_TIME, NETWORK_TIMEOUT_UNIT)
//        okHttpClient.setWriteTimeout(NETWORK_TIMEOUT_TIME, NETWORK_TIMEOUT_UNIT)

        return RestAdapter.Builder()
                .setClient(OkClient(okHttpClient))
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(GsonConverter(Gson()))
                .build()
                .create(ChatWebService::class.java)
    }

    @Provides
    @Singleton
    fun provideMappers(): Mappers {
        return Mappers();
    }
}