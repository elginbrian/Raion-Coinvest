package com.raion.coinvest.presentation.navigation

enum class CoinvestUserFlow{
    // Login Section
    IntroScreen,                // 1
    IntroScreen2,               // 2
    IntroScreen3,               // 3
    IntroScreen4,               // 4
    LoginScreen,                // 5
    RoleSectionScreen,          // 6

    AuthorRegisterScreen,       // 7
    AuthorRegisterScreen2,      // 8
    AuthorRegisterScreen3,      // 9

    MemberRegisterScreen,       // 10

    MentorRegisterScreen,       // 11
    MentorRegisterScreen2,      // 12
    MentorRegisterScreen3,      // 13

    // HomeSection
    HomeScreen,                 // 14

    // CommunitySection
    CommunityScreen,            // 15
    CommunityCreatePost,        // 16
    CommunityPostReply,         // 17
    CommunityPostReplying,      // 18

    // CommunitySearchSection
    CommunitySearchScreen,      // 19
    CommunitySearchGrid,        // 20

    // CommunityProfileSection
    CommunityProfileScreen,     // 21
    CommunityFollowerScreen,    // 22

    // MentorSection
    MentorScreen,               // 23
    MentorSearchScreen,         // 24
    MentorVideoPlayer,          // 25
    MentorCreate,               // 26
    MentorNew,                  // 27

    // NewsSection
    NewsScreen,                 // 28
    NewsPage,                   // 29
    NewsCreate,                 // 30
    NewsReply,                  // 31
    NewsReplying,               // 32

    // UserProfileSection
    UserProfileScreen,          // 29
    UserFollowerScreen,         // 30

    // StocksSection
    StocksScreen,               // 31
    StocksPage;                 // 32
}