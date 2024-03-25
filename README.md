## AWS Configuration

We'll be convering following things:
  -  Connection with AWS via terminal using `Amplify`
  -  Add `Storage` plugin/category
  -  Fetch complete list in bucket
  -  Download any object using `key`

---

### Official Statement by AWS <sub>[docs](https://github.com/aws-amplify/aws-sdk-android)</sub>
  > [!NOTE]
  > For new projects, we recommend using the latest v2 version of AWS **Amplify** Library for Android to quickly implement common app use cases like Authentication, Storage, Push Notifications and more.

---

### PreRequisite
  -  An AWS console
  -  Create a user in AWS Console and get following.
      *  AWS Console Login:
         -  AccountId
         -  UserName
         -  Password
      *  S3 Bucket Keys:
         -  Access Key
         -  Secret Key

---
---

## Steps

We'll be using amplify-sdk <sub>[docs](https://docs.amplify.aws/android/start/getting-started/introduction/)</sub> for AWS setup

---
### Android Project Setup
1.  Create an android-studio project named "MyAmplifyApp"
2.  Install Amplify [library](https://search.maven.org/search?q=g:com.amplifyframework%20a:core) - [Guide](https://docs.amplify.aws/android/start/project-setup/create-application/)

```
android {
    compileOptions {
        // Support for Java 8 features
        coreLibraryDesugaringEnabled true
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Amplify core dependency
    implementation 'com.amplifyframework:core:ANDROID_VERSION'

    // Support for Java 8 features
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.1.5'
}
```

3.  Sync project files

---
### Install Amplify CLI <sub>[docs](https://docs.amplify.aws/android/start/project-setup/prerequisites/#install-and-configure-the-amplify-cli)</sub>

1.  Open cmd in window (as administrator)
2.  Type

```
curl -sL https://aws-amplify.github.io/amplify-cli/install-win -o install.cmd && install.cmd
```

3.  Verify, if amplify has successfully installed, by typing `amplify _v`

### Connection to Cloud

1.  In terminal, open you android studio project path as "C:\Users\HP\AndroidStudioProjects\MyAmplifyApp>"
2.  Initialize Amplify

```
amplify init
```

3.  Enter the following when prompted:

```
? Enter a name for the project
    `MyAmplifyApp`
? Initialize the project with the above configuration?
    `No`
? Enter a name for the environment
    `dev`
? Choose your default editor:
    `Android Studio`
? Choose the type of app that you're building
    `android`
? Where is your Res directory:
    `app/src/main/res`
? Select the authentication method you want to use:
    `IAM user`
? Enter credentials Access and Secret Key (to login) and region for the AWS account.
    `accessKey`
    `secretKey`
    `region` 
```
 
4.  Declare feature of AWS that we'll be using (e.g. storage s3_bucket)

```
amplify add storage
```

S3_bucket configuration

```
? Please select from one of the below mentioned services:
> Content (Images, audio, video, etc.)
  NoSQL Database
? Please provide a friendly name for your resource that will be used to label this category in the project:
> mystorage
? Please provide bucket name:
> mybucket
```

Access permissions to bucket

```
? Restrict access by?
> Auth/Guest Users
  Individual Groups
  Both
  Learn more


? Who should have access:
❯ Auth users only
  Auth and guest users

? What kind of access do you want for Authenticated users?
  ◯ create/update
> ◉ read
  ◯ delete
? What kind of access do you want for Guest users?
  ◯ create/update
> ◉ read
  ◯ delete

? Do you want to add a Lambda Trigger for your S3 Bucket? (y/N)
-> N
```

Finally push all changes
```
amplify push
```

This will download `amplifyConfiguration.json` and `awsconfiguration.json` in **res/raw** folder.
Once done, you have successfully establish connection with AWS server.

### Android Project Implementation

Add following dependency

```
dependencies {
    implementation 'com.amplifyframework:aws-storage-s3:ANDROID_VERSION'
    implementation 'com.amplifyframework:aws-auth-cognito:ANDROID_VERSION'
}
```

Add following plugins in appClass
```
        try {
            val config = AmplifyConfiguration.fromConfigFile(applicationContext, R.raw.amplifyconfiguration)
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(config, applicationContext)
            Log.i(TAG, "App: Amplify -> Initialized Amplify")
        } catch (error: AmplifyException) {
            Log.e(TAG, "App: Amplify -> Could not initialize Amplify", error)
        }
```

### AWS S3-Bucket is ready, upload files and try to fetch file using this repository code.
