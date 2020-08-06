# AndroFacebookAuth

#### https://github.com/AndroKwon/UnityPlatformPlugin 을 먼저 추가합니다.


#### MainTemplate.gradle 설정

사용할 라이브러리를 추가합니다. https://bintray.com/beta/#/numicn/AndroAndroidPlugin?tab=packages ( 추가하기전 라이브러리 버전 확인 )
```
dependencies {
    implementation 'com.andro.unityplatformplugin.common:app:0.0.16'
    implementation 'com.andro.unityplatformplugin.facebookauth:app:0.0.11'
    ....
```

페이스북 계정 ID 를 초기화합니다
```
    public class PlatformAuth
    {
        ....
        public static string FacebookAppID = "xxxxxxxxxxxxxxxxxx";       
```

인게임 시작부분에서 초기화 호출을 진행합니다.

```
    void Start()
    {
        Jin.PlatformSDK.Common.Auth.PlatformAuth.AuthInitialize("facebook");
        ....
    }
```

이후에 로그인을 시도합니다
```
        Jin.PlatformSDK.Common.Auth.PlatformAuth.Login("facebook", (error, token) =>
        {
          // TODO : login result
        });
```
