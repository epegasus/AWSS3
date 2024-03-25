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
 
4.  








