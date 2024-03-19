package com.raion.coinvest.presentation.navigation

enum class CoinvestUserFlow{
    // Debugging
    DebugScreen3,
    DebugScreen5,

    // Login Section
    LoginScreen,                // 1
    RoleSectionScreen,          // 2

    AuthorRegisterScreen,       // 3
    AuthorRegisterScreen2,      // 4
    AuthorRegisterScreen3,      // 5

    MemberRegisterScreen,       // 6

    MentorRegisterScreen,       // 7
    MentorRegisterScreen2,      // 8
    MentorRegisterScreen3,      // 9

    // HomeSection
    HomeScreen,                 // 10

    // CommunitySection
    CommunityScreen,            // 11
    CommunityCreatePost,        // 12
    CommunityPostReply,         // 13
    CommunityPostReplying,      // 14

    // CommunitySearchSection
    CommunitySearchScreen,      // 15
    CommunitySearchGrid,        // 16

    // CommunityProfileSection
    CommunityProfileScreen,     // 17
    CommunityFollowerScreen,    // 18

    // MentorSection
    MentorScreen,               // 19
    MentorSearchScreen,         // 20
    MentorVideoPlayer,          // 21
    MentorCreate,               // 22
    MentorNew,                  // 23

    // NewsSection
    NewsScreen,                 // 24
    NewsPage,                   // 25
    NewsCreate,                 // 26
    NewsReply,                  // 27
    NewsReplying,               // 28

    // UserProfileSection
    UserProfileScreen,          // 29
    UserFollowerScreen,         // 30

    // StocksSection
    StocksScreen,               // 31
    StocksPage;                 // 32
}