package com.raion.coinvest.presentation.navigation

enum class CoinvestUserFlow{
    /*
        Nanti tiap screen di deklarasi dulu disini
        baru setelahnya dipanggil di NavHost MainActivity.kt
    */

    // Debugging
    DebugScreen3,
    DebugScreen5,

    // Login Section
    LoginScreen,
    RoleSectionScreen,

    // CommunitySection
    CommunityScreen,
    CommunityCreatePost,
    CommunityPostReply,
    CommunityPostReplying;
}