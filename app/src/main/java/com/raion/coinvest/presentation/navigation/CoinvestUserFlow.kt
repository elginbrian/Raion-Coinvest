package com.raion.coinvest.presentation.navigation

enum class CoinvestUserFlow{
    // Debugging
    DebugScreen3,
    DebugScreen5,

    // Login Section
    LoginScreen,                // 1
    RoleSectionScreen,          // 2

    // HomeSection
    HomeScreen,                 // 3

    // CommunitySection
    CommunityScreen,            // 4
    CommunityCreatePost,        // 5
    CommunityPostReply,         // 6
    CommunityPostReplying,      // 7

    // CommunitySearchSection
    CommunitySearchScreen,      // 8
    CommunitySearchGrid,        // 9

    // CommunityProfileSection
    CommunityProfileScreen,     // 10
    CommunityFollowerScreen,    // 11

    // MentorSection
    MentorScreen,               // 12
    MentorSearchScreen,         // 13
    MentorVideoPlayer,          // 14
    MentorCreate,               // 15
    MentorNew,                  // 16

    // NewsSection
    NewsScreen,                 // 17
    NewsPage,                   // 18
    NewsCreate,                 // 19
    NewsReply,                  // 20
    NewsReplying,               // 21

    // UserProfileSection
    UserProfileScreen,          // 22
    UserFollowerScreen,         // 23

    // StocksSection
    StocksScreen,               // 24
    StocksPage;                 // 25
}