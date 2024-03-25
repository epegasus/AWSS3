package dev.epegasus.awss3_bucket

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.AmplifyConfiguration
import com.amplifyframework.storage.s3.AWSS3StoragePlugin

/**
 * @Author: SOHAIB AHMED
 * @Date: 20/03/2024
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://stackoverflow.com/users/20440272/sohaib-ahmed
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initAWS()
    }

    private fun initAWS() {
        try {
            val config = AmplifyConfiguration.fromConfigFile(applicationContext, R.raw.amplifyconfiguration)
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(config, applicationContext)
            Log.i(TAG, "App: Amplify -> Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(TAG, "App: Amplify -> Could not initialize Amplify", error)
        }
    }

    companion object {
        const val TAG = "TAG_AWS"
    }
}