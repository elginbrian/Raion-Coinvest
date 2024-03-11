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

    // NewsSection
    NewsScreen,                 // 15
    NewsPage,                   // 16
    NewsCreate,                 // 17
    NewsReply,                  // 18
    NewsReplying,               // 19

    // UserProfileSection
    UserProfileScreen,          // 20
    UserFollowerScreen;         // 21
}