rootProject.name = "mydocent"

include(
    ":app:api",

    ":app:core:user",
    ":app:core:art",

    ":app:model",

    ":app:shared:db",
    ":app:shared:jpa",
    ":app:shared:utils",
    ":app:shared:auth"
)